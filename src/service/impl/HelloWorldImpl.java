package service.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import service.HelloWord;

@WebService(endpointInterface="service.HelloWord",serviceName="HelloWord")
public class HelloWorldImpl  implements HelloWord{

	@Override
	public String sayHi(@WebParam(name = "text") String text) {
		return "hello"+text;
	}
	
	

}
