package verbargo.exception;

public class NoTeamException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "TEAM DOESN'T EXIST.";
	
	public NoTeamException() {
		super(MESSAGE);
	}

	public NoTeamException(String message) {
		super(message);
	}

}
