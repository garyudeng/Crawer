package com.bimoku.persistence.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Intro 分页类
 * @author Lee
 * @Date 2013-8-19
 */
public class PageBean<T> implements Serializable {
	private static final long serialVersionUID = 6295579614989910451L;
	private int pageNo;
	private int nextNo;
	private int priorNo;
	private int pageCount;
	private int rowCount;
	private int pageSize;
	private int startRow;
	private String orderBy;
	private String orderType;
	private List<String> pageList;
	private int pageListSize = 10;
	private List<T> list;
	private String groupby;

	public PageBean() {
		pageSize = 10;
		pageNo = 1;
		startRow = 0;
		rowCount = 0;
	}
	
	public int getNextNo() {
		nextNo=this.getPageNo()+1;
		if(nextNo>pageCount)return pageCount;
		return nextNo;
	}

	public int getPriorNo() {
		priorNo=this.getPageNo()-1;
		if(priorNo<1)return 1;
		return priorNo;
	}

	public int getPageCount(int rowCounts) {
		if (rowCounts % pageSize == 0 && rowCounts > pageSize) {
			pageCount = rowCounts / pageSize;
		} else if (rowCounts % pageSize != 0 && rowCounts > pageSize) {
			pageCount = rowCounts / pageSize + 1;

		} else {
			pageCount = 1;
		}
		return pageCount;
	}

	public PageBean(int pageNo, int pageSize) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageNo() {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if(pageNo==null) pageNo = 0;
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		if (rowCount % pageSize == 0 && rowCount > pageSize) {
			pageCount = rowCount / pageSize;
		} else if (rowCount % pageSize != 0 && rowCount > pageSize) {
			pageCount = rowCount / pageSize + 1;
		} else {
			pageCount = 1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		if (pageSize <= 0) {
			pageSize = 10;
		}
		pageCount = (rowCount + pageSize - 1) / pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 100)
			this.pageSize = 100;
		else
			this.pageSize = pageSize;
	}

	public int getStartRow() {
		startRow = (pageNo - 1) * pageSize;
		if (startRow < 0) {
			startRow = 0;
		}
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public List<String> getPageList() {
		if (pageCount > 0) {
			pageList = new ArrayList<String>();

			int startNo = 1;
			int endNo = 0;
			if (pageNo - pageListSize / 2 > 0) {
				startNo = pageNo - pageListSize / 2;
			}
			if (pageNo + pageListSize / 2 < pageCount) {
				endNo = pageNo + pageListSize / 2;
			} else {
				endNo = pageCount;
			}
			for (int i = startNo; i <= endNo; i++) {
				pageList.add(Integer.toString(i));
			}
			return pageList;
		} else {
			return new ArrayList<String>();
		}
	}

	public int getPageListSize() {
		return pageListSize;
	}

	public void setPageListSize(int pageListSize) {
		this.pageListSize = pageListSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> reList) {
		this.list = reList;
	}
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	@Override
	public String toString() {
		return "PageBean [pageNo=" + pageNo + ", nextNo=" + nextNo
				+ ", priorNo=" + priorNo + ", pageCount=" + pageCount
				+ ", rowCount=" + rowCount + ", pageSize=" + pageSize
				+ ", startRow=" + startRow + ", orderBy=" + orderBy
				+ ", orderType=" + orderType + ", pageList=" + pageList
				+ ", pageListSize=" + pageListSize + ", list=" + list
				+ ", groupby=" + groupby + "]";
	}
}
