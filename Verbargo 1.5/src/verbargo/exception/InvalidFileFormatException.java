package verbargo.exception;

public class InvalidFileFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	private static final String MESSAGE = "INVALID VERB FORMAT FILE.";
	
	public InvalidFileFormatException() {
		super(MESSAGE);
	}
	
	public InvalidFileFormatException(String message) {
		super(message);
	}
	
	

}
