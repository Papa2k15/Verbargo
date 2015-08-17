package verbargo.exception;

public class InvalidNameException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "CAN'T HAVE EMPTY NAME.";
	
	public InvalidNameException() {
		super(MESSAGE);
	}
	
	public InvalidNameException(String message) {
		super(message);
	}

}
