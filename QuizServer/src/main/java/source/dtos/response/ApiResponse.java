package source.dtos.response;

public record ApiResponse(String messgae,Object data,Boolean success,Integer code) {
	public ApiResponse(String message,Object data,Integer code) {
		this(message,data,true,code);
	}
	public ApiResponse(String message,Integer code) {
		this(message,null,false,code);
	}
}
