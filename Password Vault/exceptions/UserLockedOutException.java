package exceptions;

public class UserLockedOutException extends Exception {
	  public UserLockedOutException() {
	      super("Too many failed login attempts. User locked out.");
	  }
}
