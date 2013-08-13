package service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONArray;

import dao.BookDao;
import dao.impl.BookDaoImpl;

import bean.Book;
import service.BookService;

/***************************************
File Name：图书Service 实现类
Author:孙武斌
Last Modified Time: 2013/07/30
Utility:Service
***************************************/

@Path(value="/book")
public class BookImpl implements BookService {

	@Override
	@GET
	@Produces("text/plain")
	public String doGet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@GET
	@Produces("text/plain")
	@Path("/request/{param}")
	public String doRequest(@PathParam("param") String param,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	@GET
	@Path("/query/{id}")
	@Produces({ "application/xml", "application/json" })
	public Book getBean(@PathParam("id") Integer id)  {
		Book book=new Book();
		book.setId(id);
		BookDao bookDao=new BookDaoImpl();
		try {
			book=(Book)bookDao.query(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}

	@Override
	@POST
	@Path("/add")
	public Book postData(Book book) {
		BookDao bookDao=new BookDaoImpl();
		int re=0;
		try {
			re=bookDao.save(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(re==1){
			return book;
		}
		return null;
	}

	@Override
	@DELETE
	@Path("/delete/{id}")
	public void deleteData(@PathParam("id") Integer id) {
		BookDao bookDao=new BookDaoImpl();
		Book book=new Book();
		book.setId(id);
		try {
			bookDao.delete(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@GET
	@Path("/query/list")
	@Produces("text/plain")
	public String getBeans() {
		BookDao bookDao=new BookDaoImpl();
		List<HashMap<String,Object>> list=bookDao.queryAll();
		JSONArray array = new JSONArray(list);
		return array.toString();
	}

	@Override
	@GET
	@Path("/query/list/{id}/{size}")
	@Produces("application/json")
	public String getBeans(@PathParam("id") Integer id,
			@PathParam("size") Integer size, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
		BookDao bookDao=new BookDaoImpl();
		List<HashMap<String,Object>> list=bookDao.queryAll(id,size);
		JSONArray array = new JSONArray(list);
		System.out.println(array.toString());
		return array.toString();
	}
	




	
	

}
