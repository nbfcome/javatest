package com.sina.ea.modules.exception;

import org.apache.commons.lang.exception.NestableException;

public class ServiceException extends NestableException {

	private static final long serialVersionUID = -6333943693571492781L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
