package com.bimoku.persistence.dao.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bimoku.persistence.bean.PageBean;
import com.bimoku.persistence.dao.BaseDao;
import com.bimoku.persistence.exception.DaoException;
import com.bimoku.util.db.BeanUtilsBean;


/**
 * 基础数据操作mysql实现
 * 包含：
 * 批量增删改查
 * 获取单一类型（int,long）类型的值
 * 基本数据查询
 * @date 2013-8-20
 * @version v0.1.2[last version]
 * @author LPM
 * @param <T>
 * @param <ID>
 */
public abstract class BaseDaoMysqlImpl<T, ID extends Serializable> extends
		JdbcDaoSupport implements BaseDao<T, ID> {
	public final Log log = LogFactory.getLog(this.getClass());
	private Class<T> persistentClass;
	private String tableName = "";
	private String pk = "";
	private GenerationType strategy;
	protected List<String> transientPropertys = new ArrayList<String>();

	protected BaseDaoMysqlImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		Table table = AnnotationUtils.findAnnotation(persistentClass,
				Table.class);
		if (table == null) {
			throw new RuntimeException(persistentClass + "没有定义@table");
		}
		this.tableName = table.name();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(persistentClass);
		} catch (IntrospectionException e) {
			log.error(e.getMessage(), e);
		}
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			Id id = AnnotationUtils
					.findAnnotation(pd.getReadMethod(), Id.class);
			if (pk.equals("") && id != null) {
				pk = pd.getName();
				GeneratedValue gv = AnnotationUtils.findAnnotation(
						pd.getReadMethod(), GeneratedValue.class);
				if (gv == null) {
					strategy = GenerationType.IDENTITY;
				} else {
					strategy = gv.strategy();
				}
			}
			Transient transient_ = AnnotationUtils.findAnnotation(
					pd.getReadMethod(), Transient.class);
			if (transient_ != null) {
				transientPropertys.add(pd.getName());
			}
		}
		if ("".equals(this.getPk())) {
			throw new RuntimeException(persistentClass + "类型中没有在get方法上定义@Id");
		}
	}

	protected BaseDaoMysqlImpl() {
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public String getPk() {
		return pk;
	}

	@Resource(name = "jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
		super.setJdbcTemplate(jb);
	}

	@Override
	public List<T> getAll() {
		StringBuilder sb = new StringBuilder("select * from ");
		sb.append(this.getTableName());
		return this.search(sb.toString(), null);
	}

	@Override
	public List<T> search(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return new ArrayList<T>();
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		List<T> list = this.getJdbcTemplate().query(sql, values.toArray(),
				new BeanPropertyRowMapper<T>(this.persistentClass));
		return list == null ? new ArrayList<T>() : list;
	}

	@Override
	public PageBean<T> search(T t, PageBean<T> pageBean) {
		Map<String, Object> map;
		try {
			map = BeanUtilsBean.getInstance().describe(t);
			for (String proterty : transientPropertys) {
				map.remove(proterty);
			}
		} catch (Exception e) {
			throw new DaoException("模型类解析异常！", e);
		}
		return this.search(map, pageBean);
	}

	@SuppressWarnings("unchecked")
	protected PageBean<T> search(Map<String, Object> map, PageBean<T> pageBean) {
		ID id = (ID) map.get(this.getPk());
		if (map != null && id != null) {
			map.remove(id);
		}
		List<String> removekeys = new ArrayList<String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() == null) {
				removekeys.add(entry.getKey());
			}
		}
		for (String key : removekeys) {
			map.remove(key);
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("select * from ");
		sb.append(this.getTableName());
		if (map.size() != 0) {
			sb.append(" where  ");
			for (Entry<String, Object> entry : map.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=? ");
				values.add(entry.getValue());
				sb.append(" and ");
			}
		}
		this.deleteLastStr(sb, "and");
		this.search(sb.toString(), values, pageBean);
		return pageBean;
	}

	protected List<T> search(Map<String, Object> map) {
		if (map == null || map.isEmpty())
			return null;
		@SuppressWarnings("unchecked")
		ID id = (ID) map.get(this.getPk());
		if (id != null) {
			map.remove(id);
		}
		List<String> removekeys = new ArrayList<String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() == null) {
				removekeys.add(entry.getKey());
			}
		}
		for (String key : removekeys) {
			map.remove(key);
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("select * from ");
		sb.append(this.getTableName());
		if (map.size() != 0) {
			sb.append(" where ");
			for (Entry<String, Object> entry : map.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=? ");
				values.add(entry.getValue());
				sb.append(" and ");
			}
			this.deleteLastStr(sb, "and");
		}
		return this.search(sb.toString(), values);
	}

	protected T searchOne(Map<String, Object> map) {
		if (map == null || map.isEmpty())
			return null;
		@SuppressWarnings("unchecked")
		ID id = (ID) map.get(this.getPk());
		if (id != null) {
			map.remove(id);
		}
		List<String> removekeys = new ArrayList<String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() == null) {
				removekeys.add(entry.getKey());
			}
		}
		for (String key : removekeys) {
			map.remove(key);
		}
		List<Object> values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("select * from ");
		sb.append(this.getTableName());
		if (map.size() != 0) {
			sb.append(" where ");
			for (Entry<String, Object> entry : map.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=? ");
				values.add(entry.getValue());
				sb.append(" and ");
			}
			this.deleteLastStr(sb, "and");
		}
		List<T> list = this.search(sb.toString(), values);
		if (list != null && list.size() != 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	protected int add(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return 0;
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().update(sql, values.toArray());
	}

	@Override
	public ID addReturnId(final String sql, final List<Object> values) {
		JdbcTemplate template = this.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				int i = 0;
				PreparedStatement ps = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				for (i = 0; i < values.size(); i++) {
					Object value = values.get(i);
					if (value != null) {
						if (value instanceof java.lang.Integer) {
							ps.setInt(i + 1, (Integer) value);
						} else if (value instanceof java.lang.Long) {
							ps.setLong(i + 1, (Long) value);
						} else if (value instanceof java.util.Date) {
							ps.setDate(i + 1,
									new java.sql.Date(((Date) value).getTime()));
							ps.setTimestamp(i + 1, new java.sql.Timestamp(
									((Date) value).getTime()));
						} else if (value instanceof java.lang.String) {
							ps.setString(i + 1, value.toString());
						} else if (value instanceof java.lang.Double) {
							ps.setDouble(i + 1, (Double) value);
						} else if (value instanceof java.lang.Byte) {
							ps.setByte(i + 1, (Byte) value);
						} else if (value instanceof java.lang.Character) {
							ps.setString(i + 1, value.toString());
						} else if (value instanceof java.lang.Float) {
							ps.setFloat(i + 1, (Float) value);
						} else if (value instanceof java.lang.Boolean) {
							ps.setBoolean(i + 1, (Boolean) value);
						} else if (value instanceof java.lang.Short) {
							ps.setShort(i + 1, (Short) value);
						} else {
							ps.setObject(i + 1, value);
						}
					} else {
						ps.setNull(i + 1, Types.NULL);
					}
				}
				return ps;
			}
		}, keyHolder);
		return (ID) (Integer) keyHolder.getKey().intValue();
	}

	@SuppressWarnings("unchecked")
	protected ID addReturnId(Map<String, Object> map) {
		if (map == null || map.size() == 0)
			return null;
		if (strategy.equals(GenerationType.IDENTITY)) {
			map.remove(this.getPk());
		}
		StringBuilder sb = new StringBuilder("insert into ");
		sb.append(this.getTableName());
		List<String> columns = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		for (Entry<String, Object> e : map.entrySet()) {
			columns.add(e.getKey());
			values.add(e.getValue());
		}
		sb.append("(");
		sb.append(StringUtils.join(columns, ','));
		sb.append(") values(");
		String[] paras = new String[values.size()];
		Arrays.fill(paras, "?");
		sb.append(StringUtils.join(paras, ','));
		sb.append(")");
		if (strategy.equals(GenerationType.IDENTITY)) {
			ID id = this.addReturnId(sb.toString(), values);
			return (ID) id;
		} else if (strategy.equals(GenerationType.AUTO)) {
			int count = this.add(sb.toString(), values);
			if (count != 0) {
				return (ID) map.get(this.getPk());
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public int[] batchAdd(String sql, List<List<Object>> values) {
		if (StringUtils.isEmpty(sql))
			return new int[0];
		if (values == null)
			values = new ArrayList<List<Object>>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (List<Object> ol : values) {
			batchArgs.add(ol.toArray());
		}
		return this.getJdbcTemplate().batchUpdate(sql, batchArgs);
	}

	@Override
	public int update(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return 0;
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().update(sql, values.toArray());
	}

	@Override
	public int del(ID id) {
		if (id == null)
			return 0;
		StringBuilder sb = new StringBuilder("delete from ");
		sb.append(this.getTableName());
		sb.append(" where ");
		sb.append(this.getPk());
		sb.append("=?");
		List<Object> values = new ArrayList<Object>();
		values.add(id);
		return this.del(sb.toString(), values);
	}

	@Override
	public int delByIds(List<ID> ids) {
		if (ids == null || ids.isEmpty())
			return 0;
		StringBuilder sb = new StringBuilder("delete from ");
		sb.append(this.getTableName());
		sb.append(" where ");
		sb.append(this.getPk());
		sb.append(" in (");
		Serializable[] ss = new Serializable[ids.size()];
		Arrays.fill(ss, "?");
		sb.append(StringUtils.join(ss, ','));
		sb.append(")");
		List<Object> values = new ArrayList<Object>();
		values.addAll(ids);
		return this.del(sb.toString(), values);
	}

	@Override
	public int del(String sql, List<Object> values) {
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().update(sql, values.toArray());
	}

	@Override
	public int getInt(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return 0;
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().queryForInt(sql, values.toArray());
	}

	@Override
	public long getLong(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return 0;
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().queryForLong(sql, values.toArray());
	}

	@Override
	public int getCount(String sql, List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return 0;
		if (values == null)
			values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("select count(*) from ");
		sb.append(this.getTableName());
		sb.append(sql);
		return this.getInt(sb.toString(), values);
	}
	
	@Override
	public boolean isExit(List<String> keys, List<Object> values) {
		StringBuffer sb = new StringBuffer();
		String suffix1 = " =? ";
		String suffix2 = " and ";
		for(String s:keys){
			sb.append(s);
			sb.append(suffix1);
			sb.append(suffix2);
		}
		String sql = " where "+ sb.toString().substring(0, sb.toString().lastIndexOf(suffix2));
		
		int count = getCount(sql, values);
		if(count > 0)
			return true;
		return false;
	}

	protected int[] batchAdd(List<Map<String, Object>> mapList) {
		if (mapList == null || mapList.isEmpty())
			return new int[0];
		Map<String, Object> map = mapList.get(0);
		if (map == null || map.size() == 0)
			return new int[0];
		if (strategy.equals(GenerationType.AUTO)) {
			map.remove(this.getPk());
		}
		String[] columnNames = new String[map.size()];
		columnNames = map.keySet().toArray(columnNames);
		StringBuilder sb = new StringBuilder("insert into ");
		sb.append(this.getTableName());
		sb.append("(");
		sb.append(StringUtils.join(columnNames, ','));
		sb.append(") values(");
		String[] paras = new String[columnNames.length];
		Arrays.fill(paras, "?");
		sb.append(StringUtils.join(paras, ','));
		sb.append(")");

		List<List<Object>> values = new ArrayList<List<Object>>();
		for (Map<String, Object> m : mapList) {
			if (m != null && !m.isEmpty()) {
				List<Object> l = new ArrayList<Object>();
				for (String str : columnNames) {
					l.add(m.get(str));
				}
				values.add(l);
			}
		}
		return this.batchAdd(sb.toString(), values);
	}

	protected int updateById(Map<String, Object> map) {
		if (map == null || map.size() == 0)
			return 0;
		Serializable id = (Serializable) map.get(this.getPk());
		if (id == null || "".equals(id))
			return 0;
		List<Object> values = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("update ");
		sb.append(this.getTableName());
		sb.append(" set ");
		map.remove(this.getPk());
		for (Entry<String, Object> e : map.entrySet()) {
			sb.append(e.getKey());
			sb.append("=?, ");
			values.add(e.getValue());
		}
		deleteLastStr(sb, ",");
		sb.append(" where ");
		sb.append(this.getPk());
		sb.append("=?");
		values.add(id);
		map.put(this.getPk(), id);
		return this.update(sb.toString(), values);
	}

	@Override
	public PageBean<T> search(String sql, List<Object> values,
			PageBean<T> pageBean) {
		if (StringUtils.isEmpty(sql)) {
			List<T> reList = new ArrayList<T>();
			pageBean.setRowCount(0);
			pageBean.setList(reList);
			return pageBean;
		}
		if (values == null)
			values = new ArrayList<Object>();

		int startRow = pageBean.getStartRow();
		int offset = pageBean.getPageSize();
		StringBuilder sb = new StringBuilder(sql);

		if (StringUtils.isNotEmpty(pageBean.getGroupby())) {
			sb.append(" group by ");
			sb.append(pageBean.getGroupby());
		}

		int count = this.getCount(sb.toString(), values);
		pageBean.setRowCount(count);

		if (!StringUtils.isEmpty(pageBean.getOrderBy())) {
			sb.append(" order by ");
			sb.append(pageBean.getOrderBy());
			if (!StringUtils.isEmpty(pageBean.getOrderType())) {
				sb.append(" ");
				sb.append(pageBean.getOrderType());
			}
		}
		sb.append(" limit ");
		sb.append(startRow);
		sb.append(",");
		sb.append(offset);
		pageBean.setList(this.search(sb.toString(), values));
		return pageBean;
	}

	@Override
	public T get(ID id) {
		if (id == null)
			return null;
		StringBuilder sb = new StringBuilder("select * from ");
		sb.append(this.getTableName());
		sb.append(" where ");
		sb.append(this.getPk());
		sb.append("=?");
		List<Object> values = new ArrayList<Object>();
		values.add(id);
		List<T> list = this.search(sb.toString(), values);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public T save(T t) {
		if (t == null) {
			throw new DaoException("模型类内容为空！");
		}
		Map<String, Object> map = null;
		try {
			map = BeanUtilsBean.getInstance().describe(t);
		} catch (Exception e) {
			throw new DaoException("模型类解析异常！", e);
		}
		for (String proterty : transientPropertys) {
			map.remove(proterty);
		}
		ID id = this.addReturnId(map);
		if (strategy.equals(GenerationType.IDENTITY)) {
			try {
				BeanUtils.setProperty(t, pk, id);
			} catch (Exception e) {
				throw new DaoException("模型类解析异常！", e);
			}
		}
		return t;
	}

	@Override
	public List<T> getByIds(List<ID> ids) {
		List<T> list = null;
		if (ids != null && !ids.isEmpty()) {
			StringBuilder sb = new StringBuilder("select * from ");
			sb.append(this.getTableName());
			sb.append(" where ");
			sb.append(this.getPk());
			sb.append(" in (");
			Serializable[] ss = new Serializable[ids.size()];
			Arrays.fill(ss, "?");
			sb.append(StringUtils.join(ss, ','));
			sb.append(")");
			List<Object> values = new ArrayList<Object>();
			values.addAll(ids);
			list = this.search(sb.toString(), values);
		} else {
			list = new ArrayList<T>();
		}
		return list;
	}

	@Override
	public T update(T t) {
		if (t == null) {
			throw new DaoException("模型类内容为空！");
		}
		Map<String, Object> map = null;
		try {
			map = BeanUtilsBean.getInstance().describe(t);
			for (String proterty : transientPropertys) {
				map.remove(proterty);
			}
		} catch (Exception e) {
			throw new DaoException("模型类解析异常！", e);
		}
		int count = this.updateById(map);
		if (count == 0) {
			throw new DaoException("更新对象失败" + t);
		}
		return t;
	}

	@Override
	public int[] batchSave(List<T> list) {
		if (list == null) {
			throw new DaoException("模型类内容为空！");
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (T t : list) {
			try {
				Map<String, Object> map = BeanUtilsBean.getInstance().describe(
						t);
				for (String proterty : transientPropertys) {
					map.remove(proterty);
				}
				mapList.add(map);
			} catch (Exception e) {
				throw new DaoException("模型类解析异常！", e);
			}
		}
		return this.batchAdd(mapList);
	}

	@Override
	public <E> List<E> search(String sql, List<Object> values, Class<E> e) {
		if (StringUtils.isEmpty(sql))
			return new ArrayList<E>();
		if (values == null)
			values = new ArrayList<Object>();
		logger.info("sql : " + sql + " values:" + values);
		return this.getJdbcTemplate().query(sql, values.toArray(),
				new BeanPropertyRowMapper<E>(e));
	}

	@Override
	public <E> PageBean<E> search(String sql, List<Object> values,
			PageBean<E> pageBean, Class<E> e) {
		if (StringUtils.isEmpty(sql)) {
			List<E> reList = new ArrayList<E>();
			pageBean.setRowCount(0);
			pageBean.setList(reList);
			return pageBean;
		}
		if (values == null)
			values = new ArrayList<Object>();

		int startRow = pageBean.getStartRow();
		int offset = pageBean.getPageSize();
		StringBuilder sb = new StringBuilder(sql);
		if (StringUtils.isNotEmpty(pageBean.getGroupby())) {
			sb.append(" group by ");
			sb.append(pageBean.getGroupby());
		}
		int count = this.getCount(sb.toString(), values);
		pageBean.setRowCount(count);

		if (!StringUtils.isEmpty(pageBean.getOrderBy())) {
			sb.append(" order by ");
			sb.append(pageBean.getOrderBy());
			if (!StringUtils.isEmpty(pageBean.getOrderType())) {
				sb.append(" ");
				sb.append(pageBean.getOrderType());
			}
		}
		sb.append(" limit ");
		sb.append(startRow);
		sb.append(",");
		sb.append(offset);

		pageBean.setList(this.search(sb.toString(), values, e));
		return pageBean;
	}

	@Override
	public List<T> search(T t) {
		if (t == null)
			return new ArrayList<T>();
		Map<String, Object> map;
		try {
			map = BeanUtilsBean.getInstance().describe(t);
			for (String proterty : transientPropertys) {
				map.remove(proterty);
			}
		} catch (Exception e) {
			throw new DaoException("模型类解析异常！", e);
		}
		return this.search(map);
	}

	@Override
	public T searchOne(T t) {
		if (t == null)
			return null;
		Map<String, Object> map;
		try {
			map = BeanUtilsBean.getInstance().describe(t);
			map.remove("class");
			for (String proterty : transientPropertys) {
				map.remove(proterty);
			}
		} catch (Exception e) {
			throw new DaoException("模型类解析异常！", e);
		}
		return this.searchOne(map);
	}

	@Override
	public List<Map<String, Object>> searchForMap(String sql,
			List<Object> values) {
		if (StringUtils.isEmpty(sql))
			return new ArrayList<Map<String, Object>>();
		if (values == null)
			values = new ArrayList<Object>();
		if (log.isDebugEnabled()) {
			log.debug("sql : " + sql + " values:" + values);
		}
		return this.getJdbcTemplate().queryForList(sql, values.toArray());
	}

	@Override
	public PageBean<Map<String, Object>> searchForMap(String sql,
			List<Object> values, PageBean<Map<String, Object>> pageBean) {
		if (StringUtils.isEmpty(sql)) {
			List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
			pageBean.setRowCount(0);
			pageBean.setList(reList);
			return pageBean;
		}
		if (values == null)
			values = new ArrayList<Object>();

		int startRow = pageBean.getStartRow();
		int offset = pageBean.getPageSize();
		StringBuilder sb = new StringBuilder(sql);

		if (StringUtils.isNotEmpty(pageBean.getGroupby())) {
			sb.append(" group by ");
			sb.append(pageBean.getGroupby());
		}

		int count = this.getCount(sb.toString(), values);
		pageBean.setRowCount(count);

		if (!StringUtils.isEmpty(pageBean.getOrderBy())) {
			sb.append(" order by ");
			sb.append(pageBean.getOrderBy());
			if (!StringUtils.isEmpty(pageBean.getOrderType())) {
				sb.append(" ");
				sb.append(pageBean.getOrderType());
			}
		}
		sb.append(" limit ");
		sb.append(startRow);
		sb.append(",");
		sb.append(offset);

		pageBean.setList(this.searchForMap(sb.toString(), values));
		return pageBean;
	}

	private void deleteLastStr(StringBuilder sb, String str) {
		int index = sb.lastIndexOf(str);
		if (index != -1) {
			sb.delete(index, index + str.length());
		}
	}
}
