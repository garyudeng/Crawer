package service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloWord {
	String sayHi(@WebParam(name="text")String text);
	//String sayHiToUser(User user);

}
