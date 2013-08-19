package bimoku.extract.common.exception;

/**
 * 抽取异常
 * @author LPM
 *
 */
public class ExtractException  extends RuntimeException{
	private static final long serialVersionUID = 346441748363053186L;
	public ExtractException(){
		super();
	}
	public ExtractException(String message,Throwable cause){
		super(message, cause);
	}
	
	public ExtractException(Throwable cause){
		super(cause);
	}
	
	public ExtractException(String message){
		super(message);
	}
}
