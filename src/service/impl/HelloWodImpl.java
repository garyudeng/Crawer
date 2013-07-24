package service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import service.HelloWold;

public class HelloWodImpl implements HelloWold {

	@Override
	@GET
	@Produces("text/plain")
	public String doGet() {
		return "hello World";
	}
	
	@Override
	@GET
	@Produces("text/plain")
	@Path("/request/{param}")
	public String doRequest(@PathParam("param") String param,
			@Context HttpServletRequest servletRequest,
			@Context HttpServletResponse servletResponse) {
		return null;
	}

}
