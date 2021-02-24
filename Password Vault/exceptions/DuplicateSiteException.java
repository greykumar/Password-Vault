package exceptions;

public class DuplicateSiteException extends Exception {

   public DuplicateSiteException() {
      super("Website already exists in vault!");
  }
}
