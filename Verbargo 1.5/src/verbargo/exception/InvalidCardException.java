package verbargo.exception;

public class InvalidCardException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "INVALID CARD.";
	
	public InvalidCardException() {
		super(MESSAGE);
	}

	public InvalidCardException(String message) {
		super(message);
	}


}
