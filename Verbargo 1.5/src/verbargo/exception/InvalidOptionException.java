package verbargo.exception;

public class InvalidOptionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Invalid Option Setting.";
	
	public InvalidOptionException() {
		super(MESSAGE);
	}

	public InvalidOptionException(String message) {
		super(message);
	}
}
