package main;
/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
import java.util.Scanner;
import exceptions.DuplicateSiteException;
import exceptions.DuplicateUserException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidSiteException;
import exceptions.InvalidUsernameException;
import exceptions.PasswordMismatchException;
import exceptions.SiteNotFoundException;
import exceptions.UserLockedOutException;
import exceptions.UserNotFoundException;
import vault.PasswordVault;

/**
 * This class tests the passwordVault class and handles all the exceptions that
 * are thrown within the PasswordVault class. 
 * 
 * @author Grey Kumar
 * @version 1.0
 *
 */
public class Driver {

	public static void main(String[] args) throws InvalidUsernameException, 
	InvalidPasswordException, DuplicateUserException, DuplicateSiteException, 
	UserNotFoundException, UserLockedOutException, PasswordMismatchException, 
	InvalidSiteException, SiteNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		String user = "greykumar";
		String password = "Catguy123!";

		System.out.println("Adding user 'Greykumar' ");
		String BadUser = "Greykumar";
	
		
		PasswordVault vault = new PasswordVault();
		try {
		vault.addNewUser(BadUser, password);
		}catch (InvalidUsernameException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		} catch (InvalidPasswordException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding user 'greykumar' with password 'cat'");
		try {
		vault.addNewUser(user, "cat");
		System.out.println(user + " logged on!");
		}catch (InvalidUsernameException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		} catch (InvalidPasswordException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
	
		System.out.println();
		System.out.println("Adding user 'greykumar' ");
		try {
		vault.addNewUser(user, password);
		System.out.println(user + " logged on!");
		}catch (InvalidUsernameException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		} catch (InvalidPasswordException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}

		
		System.out.println();
		System.out.println("Adding duplicate user " + user + "...");
		try {
			vault.addNewUser(user, password);
		}catch (DuplicateUserException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding site 'Amazon'...");
		try {
			String site1 = "Amazon";
			String webPassword = vault.addNewSite(user, password, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
		} catch(InvalidSiteException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding site 'amazon'...");
		try {
			String site1 = "amazon";
			String webPassword = vault.addNewSite(user, password, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(InvalidSiteException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println("Adding duplicate site 'amazon'...");
		try {
			String site1 = "amazon";
			String webPassword = vault.addNewSite(user, password, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(DuplicateSiteException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding site 'amazon' for user garybb...");
		try {
			String site1 = "amazon";
			String webPassword = vault.addNewSite("garybb", password, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(UserNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding new site 'google' for 'greykumar' with "
				+ "password 'dog'...");
		try {
			String site1 = "google";
			String webPassword = vault.addNewSite(user, "dog", site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		
		System.out.println();
		System.out.println("Adding new site 'google'...");
		try {
			String site1 = "google";
			String webPassword = vault.addNewSite(user, password, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(DuplicateSiteException | InvalidSiteException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println("Adding new user 'catluver'...");
		String user2 = "catluver";
		String password2 = "Ilovecats1!";
		try {
		vault.addNewUser(user2, password2);
		System.out.println(user2 + " logged on!");
		}catch (InvalidUsernameException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		} catch (InvalidPasswordException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
	
		
		System.out.println();
		System.out.println("Adding site 'amazon' for user " + user2);
		try {
			String site1 = "amazon";
			String webPassword = vault.addNewSite(user2, password2, site1);
			System.out.println("Added site '" + site1 +"' for user '" + user2 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(InvalidSiteException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Adding site 'facebook' for user " + user2 
				+ " with password 'dogzarecool'");
		try {
			String site1 = "facebook";
			String webPassword = vault.addNewSite(user2, "dogzarecool", site1);
			System.out.println("Added site '" + site1 +"' for user '" + user2 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(InvalidSiteException | PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Trying again...");
		try {
			String site1 = "facebook";
			String webPassword = vault.addNewSite(user2, "dogzarecool", site1);
			System.out.println("Added site '" + site1 +"' for user '" + user2 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(InvalidSiteException | PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Trying again...");
		try {
			String site1 = "facebook";
			String webPassword = vault.addNewSite(user2, "dogzarecool", site1);
			System.out.println("Added site '" + site1 +"' for user '" + user2 
					+"' => generated site password: " + webPassword);
			System.out.println();
		} catch(InvalidSiteException | UserLockedOutException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println("--------------------------------------");
		
		System.out.println("Retrieving password for site 'amazon' for user " 
				+ user);
		try {
			String retrievePass = 
					vault.retrieveSitePassword(user, password, "amazon");
			System.out.println("Retrieve site password for 'amazon' "
					+ "for user '"+ user +"' => password: " + retrievePass);
			
		}catch(SiteNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Retrieving password for site 'amazon' for user "
				+ "dylank");
		try {
			String retrievePass = 
					vault.retrieveSitePassword("dylank", password, "amazon");
			System.out.println("Retrieve site password for 'amazon' "
					+ "for user '"+ user +"' => password: " + retrievePass);
			
		}catch(UserNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}

		System.out.println();
		System.out.println("Retrieving password for site 'facebook' for user " 
				+ user);
		try {
			String retrievePass = 
					vault.retrieveSitePassword(user, password, "facebook");
			System.out.println("Retrieve site password for 'facebook' "
					+ "for user '"+ user +"' => password: " + retrievePass);
			
		}catch(SiteNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		
		System.out.println("Retrieving password for site 'amazon' for user " 
				+ user2);
		try {
			String retrievePass = 
					vault.retrieveSitePassword(user2, password, "amazon");
			System.out.println("Retrieve site password for 'amazon' "
					+ "for user '"+ user2 +"' => password: " + retrievePass);
			
		}catch(UserLockedOutException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		
		System.out.println("--------------------------------------");
		
		System.out.println("Updating password 'amazon' for user " + user);
		try {
			String updatedPassword = 
					vault.updateSitePassword(user, password, "amazon");
			System.out.println("Update site password for 'amazon' for user '" 
					+ user + "' => updated password: " + updatedPassword);
			
		} catch (UserNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}

		System.out.println();
		System.out.println("Retrieving password for site 'amazon' for user " 
		+ user);
		try {
			String retrievePass = 
					vault.retrieveSitePassword(user, password, "amazon");
			System.out.println("Retrieve site password for 'amazon' "
					+ "for user '"+ user +"' => password: " + retrievePass);
			
		}catch(PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		String user3 = "dylankumar";
		String pass3 = "Beatsbears1!";
		String site3 = "youtube";
		vault.addNewUser(user3, pass3);
		System.out.println("Added user '" + user3 + "'");
		String webPass = vault.addNewSite(user3, pass3, site3);
		System.out.println("Added site '" + site3 +"' for user '" + user3 
				+"' => generated site password: " + webPass);
		
		System.out.println();
		System.out.println("Retrieving password for site 'youtube' for user " + 
				user3 + " with password 'frogzrule'");
		try {
			String retrievePass = 
					vault.retrieveSitePassword(user3, "frogzrule", "youtube");
			System.out.println("Retrieve site password for 'youtube' "
					+ "for user '"+ user3 +"' => password: " + retrievePass);
			
		}catch(PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		
		System.out.println("--------------------------------------");
		
		
		System.out.println("Updating password for user " + user 
				+ " for site gmail");
		try {
			String updatedPassword = 
					vault.updateSitePassword(user, password, "gmail");
			System.out.println("Update site password for 'gmail' for user '" + user 
					+"' => updated password: " + updatedPassword);
			
		} catch (SiteNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Updating password for user 'blueberryface'");
		try {
			String updatedPassword = 
					vault.updateSitePassword("blueberryface", password, "amazon");
			System.out.println("Update site password for 'amazon' for user '" 
					+ user + "' => updated password: " + updatedPassword);
			
		} catch (UserNotFoundException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Updating password 'youtube' for user " + user3);
		try {
			String updatedPassword = 
					vault.updateSitePassword(user3, "cupcake!1", "youtube");
			System.out.println("Update site password for 'youtube' for user '" 
					+ user3 + "' => updated password: " + updatedPassword);
			
		} catch (PasswordMismatchException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Updating password 'amazon' for user " + user2);
		try {
			String updatedPassword = 
					vault.updateSitePassword(user2, password, "amazon");
			System.out.println("Update site password for 'amazon' for user '" 
					+ user + "' => updated password: " + updatedPassword);
			
		} catch (UserLockedOutException e) {
			System.out.println("Caught Exception: " + e.getMessage());
		}
		
	}

}
