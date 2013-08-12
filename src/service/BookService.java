package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bean.Book;

/***************************************
File Name：图书
Author:孙武斌
Last Modified Time: 2013/07/30
Utility:Bean
***************************************/
@Path(value="/book")
public interface BookService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String doGet();
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/request/{param}")
	 public String doRequest(@PathParam("param") String param, 
	            @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
	
	
	@GET
	@Path("/query/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Book getBean(@PathParam("id") Integer id);
	
	
	@POST
	@Path("/add")
	public Book postData(Book book);
	
	
	@DELETE
	@Path("/delete/{id}")
	public void deleteData(@PathParam("id") Integer id);
	
	
	
	@GET
	@Path("/query/list")
	@Produces("text/plain")
	public String getBeans();
	
	
	@GET
	@Path("/query/list/{id}/{size}")
	@Produces("text/plain")
	public String getBeans(@PathParam("id") Integer id,
			@PathParam("size") Integer size,
			 @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse);
	
	
	
	
	
	
	

}
