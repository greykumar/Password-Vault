package exceptions;

public class InvalidUsernameException extends Exception {

	  public InvalidUsernameException() {
	      super("Invalid username, must be lowercase and"
					+ " between 6 and 12 characters.");
	  }
}
