package exceptions;

public class InvalidPasswordException extends Exception {

	  public InvalidPasswordException() {
	      super("Invalid password. Must be between 6 and 15 "
					+ "characters, contain a number from 0-9 and a special "
					+ "character !@#$%^&");
	  }
}
