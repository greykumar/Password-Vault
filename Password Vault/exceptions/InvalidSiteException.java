package exceptions;

public class InvalidSiteException extends Exception {

	  public InvalidSiteException() {
	      super("Invalid sitename, must be lowercase and"
					+ " between 6 and 12 characters.");
	  }
}
