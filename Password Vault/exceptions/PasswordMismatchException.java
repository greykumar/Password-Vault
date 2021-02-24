package exceptions;

public class PasswordMismatchException extends Exception {

	  public PasswordMismatchException() {
	      super("Incorrect password!");
	  }
}
