package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bean.MapBean;
import bean.User;
import bean.Users;

/*
 * 注释（Annotation）：在javax.ws.rs.*中定义，是JAX-RS（JSR 311）规范的一部分
 * @Path:定义资源基URI。由上下文根和主机名组成，资源标示符类似于http://localhost:8080/RESTful/rest/hello.
 * @GET:这意味着以下 方法可以响应HTTP GET方法
 * @Produces:以纯文本方式定义响应内容MIME类型
 * @Context:使用该注释注入上下文对象，比如Request、Response、UriInfo、ServletContext等。
 * @Path("{contact}"):这是@Path注释，与根路径“/contacts”结合形成子资源的URI
 * @PathParam("contact")：该注释将参数注入方法参数的路径，在本例中就是联系人 id。其他可用的注释有 @FormParam、@QueryParam 等。
 * @Produces：响应支持多个 MIME 类型。在本例和上一个示例中，APPLICATION/XML 将是默认的 MIME 类型。
 * 
 * 
 */



@Path(value="/sample")
public interface RESTSample {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String doGet();
  
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/request/{param}")
  public String doRequest(@PathParam("param") String param,
		   @Context HttpServletRequest servletRequest,
		   @Context HttpServletResponse servletResponse
		  );
  
  @GET
  @Path("/bean/{id}")
  @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
  public User getBean(@PathParam("id") int id);
	
  
  @GET
  @Path("/list")
  @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
  public Users getList();
  
  
  @GET
  @Path("/map")
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public MapBean getMap();
  
  
  /**
   * @Consumes:声明该方法使用HTML FORM
   * @FORMParam:注入该方法的HTML属性确定的表单输入
   * @Response.Created(uri).build():构建新的URI用于新创建的联系人（/contacts/{id}）并设置响应代码（201/created）
   * 可以使用 http://localhost:8080/Jersey/rest/contacts/<id> 访问新联系人
   * 
   * 
   * 
 
   */
  
  @POST
  @Path("/postData")
  public User postData(User user)throws IOException;
  
  @PUT
  @Path("/putData/{id}")
  @Consumes(MediaType.APPLICATION_XML)
  public User putData(@PathParam("id") int id,User user);
  
  
  @DELETE
  @Path("/removeData/{id}")
  public void deleteData(@PathParam("id") int id);
  
	
}
