package verbargo.exception;

public class UsedAllCardsException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "ALL CARDS HAVE BEEN USED.";
	
	public UsedAllCardsException() {
		super(MESSAGE);
	}

	public UsedAllCardsException(String message) {
		super(message);
	}

}
