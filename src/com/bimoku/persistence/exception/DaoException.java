package com.bimoku.persistence.exception;

public class DaoException extends RuntimeException{
	private static final long serialVersionUID = 346441748363053186L;
	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
