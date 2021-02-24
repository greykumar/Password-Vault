package exceptions;

public class SiteNotFoundException extends Exception {

	  public SiteNotFoundException() {
	      super("Website does not exist in vault!");
	  }
}
