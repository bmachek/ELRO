package org.machek.elro.shared;

import java.io.Serializable;

public class ConfigErrorException extends Exception implements Serializable {

	public ConfigErrorException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfigErrorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ConfigErrorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ConfigErrorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ConfigErrorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
