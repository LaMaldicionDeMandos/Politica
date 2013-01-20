package org.pasut.games.politica.game.authentication;

public class CouldNotLoginException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 500911082717787640L;

	public CouldNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouldNotLoginException(String message) {
		super(message);
	}

	public CouldNotLoginException(Throwable cause) {
		super(cause);
	}
}
