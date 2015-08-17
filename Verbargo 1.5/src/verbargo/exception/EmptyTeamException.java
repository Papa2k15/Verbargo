package verbargo.exception;

public class EmptyTeamException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "EMPTY TEAM LIST.";
	
	public EmptyTeamException() {
		super(MESSAGE);
	}

	public EmptyTeamException(String message) {
		super(message);
	}

}
