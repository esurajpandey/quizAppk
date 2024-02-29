package exceptions.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import exceptions.ApiException;
@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage","suppressed"})
public class UnAuthorized extends ApiException {
	private static final long serialVersionUID = 344847080577592935L;
	private Integer code = 401;
	private Boolean status = false;
	private String data = null;
	
	public UnAuthorized(String message) {
		super(message);
	}

	public Integer getCode() {
		return code;
	}

	public Boolean getStatus() {
		return status;
	}

	public String getData() {
		return data;
	}
	
}
