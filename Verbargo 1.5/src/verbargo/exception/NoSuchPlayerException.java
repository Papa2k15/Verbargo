package verbargo.exception;

public class NoSuchPlayerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "PLAYER DOESN'T EXIST.";
	
	public NoSuchPlayerException() {
		super(MESSAGE);
	}

	public NoSuchPlayerException(String message) {
		super(message);
	}

}
