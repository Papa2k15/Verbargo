package verbargo.exception;

public class FullTeamException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "TEAM IS FULL.";
	
	public FullTeamException() {
		super(MESSAGE);
	}

	public FullTeamException(String message) {
		super(message);
	}

}
