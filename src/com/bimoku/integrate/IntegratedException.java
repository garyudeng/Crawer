package com.bimoku.integrate;

public class IntegratedException extends RuntimeException{
	private static final long serialVersionUID = 346441748363053186L;
	public IntegratedException() {
		super();
	}

	public IntegratedException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntegratedException(String message) {
		super(message);
	}

	public IntegratedException(Throwable cause) {
		super(cause);
	}
}
