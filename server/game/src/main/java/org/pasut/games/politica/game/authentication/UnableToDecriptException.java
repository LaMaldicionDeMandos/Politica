package org.pasut.games.politica.game.authentication;

public class UnableToDecriptException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 370128279611060259L;

	public UnableToDecriptException() {
	}

	public UnableToDecriptException(String message) {
		super(message);
	}

	public UnableToDecriptException(Throwable cause) {
		super(cause);
	}

	public UnableToDecriptException(String message, Throwable cause) {
		super(message, cause);
	}
}
