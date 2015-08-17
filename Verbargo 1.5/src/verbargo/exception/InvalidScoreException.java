package verbargo.exception;

public class InvalidScoreException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "INVALID SCORE OPERATION.";
	
	public InvalidScoreException() {
		super(MESSAGE);
	}
	
	public InvalidScoreException(String message) {
		super(message);
	}

}
