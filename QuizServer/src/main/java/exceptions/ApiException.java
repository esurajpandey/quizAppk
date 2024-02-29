package exceptions;

public class ApiException extends RuntimeException{
	
	private static final long serialVersionUID = 452584463493097343L;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ApiException(String message) {
		super(message);
		this.message = message;
	}
}
