package exceptions.custom;

import exceptions.ApiException;

public class NotFoundException extends ApiException{
	public NotFoundException(String message) {
		super(message);
	}
	private static final long serialVersionUID = -8745836191200882852L;

}
