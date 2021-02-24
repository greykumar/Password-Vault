package vault;
/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
import encrypt.Encryptor;
import java.util.Random;
import java.util.HashMap;
import exceptions.DuplicateSiteException;
import exceptions.DuplicateUserException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidSiteException;
import exceptions.InvalidUsernameException;
import exceptions.PasswordMismatchException;
import exceptions.SiteNotFoundException;
import exceptions.UserLockedOutException;
import exceptions.UserNotFoundException;
import java.util.regex.*;
import encrypt.CaesarCipher;

/**
 * This program adds users to the vault and the websites they want to store to the password vault.
 * Once a website is added, an encrypted password is generated and the plain text
 * version of that password is returned back to the user. It also checks for
 * correct credentials via verification of username and password. This class
 * also provides the functionality necessary to update site passwords and retrieve
 * decrypted existing passwords for the websites that are currently stored in 
 * the vault. 
 * 
 * 
 * @author Grey Kumar
 * @version 1.0
 *
 */
public class PasswordVault implements Vault {

	private HashMap<String, String> vault = new HashMap<>();
	private HashMap<String, String> site = new HashMap<>();
	private HashMap<String, 
		HashMap<String, String>> innerVault = new HashMap <>();
	private HashMap <String, Integer> jail = new HashMap<>();
	private Check check = new Check();
	
	public PasswordVault() {
		this(new CaesarCipher());
	}
	public PasswordVault(Encryptor e) {
	
	}
	

	@Override
	public void addNewUser(String username, String password)
			throws InvalidUsernameException, InvalidPasswordException,
			DuplicateUserException {
		
		if (!check.checkName(username, "^[a-z]{6,12}$")) {
			throw new InvalidUsernameException();
		}
		if (!check.checkName(password, "[A-Za-z]{6,15}") ||!check.checkName(password, "[0-9]")
				|| !check.checkName(password, "[!@#$%^&]") ) {
			throw new InvalidPasswordException();
		}
		if (vault.containsKey(username)) {
			throw new DuplicateUserException();
		}
		String vaultPass = CaesarCipher.getInstance().encrypt(password);
		vault.put(username, vaultPass);
		
	}

	@Override
	public String addNewSite(String username, String password, String sitename)
			throws DuplicateSiteException, UserNotFoundException,
			UserLockedOutException, PasswordMismatchException,
			InvalidSiteException {
	
		if (!vault.containsKey(username)) {
			throw new UserNotFoundException();
		}
		
		if (vault.containsKey(username) && !CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password))  {
			int strike = 0;
			if (!jail.containsKey(username)) {
				jail.put(username, 1);
			} else 
				strike = jail.get(username);
				strike++;
				jail.put(username, strike);
			if (jail.get(username) >= 3) {
				throw new UserLockedOutException();
			} else 
				throw new PasswordMismatchException();
		} else if (vault.containsKey(username) && CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password)) {
			if (!check.checkName(sitename, "^[a-z]{6,12}$")) {
				throw new InvalidSiteException();
			} else if (innerVault.get(username)!= null && 
					innerVault.get(username).containsKey(username + sitename)) {
				throw new DuplicateSiteException();
			}
			String plainText = check.genString();
			site.put(username + sitename, CaesarCipher.getInstance().encrypt(plainText));
			innerVault.put(username, site);
			return plainText;
		} 
		return null;
	}

	@Override
	public String updateSitePassword(String username, String password,
			String sitename) throws SiteNotFoundException, UserNotFoundException,
			UserLockedOutException, PasswordMismatchException {

		if (vault.containsKey(username) && CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password))  {
			if (!site.containsKey(username + sitename)) {
				throw new SiteNotFoundException();
			} else if (site.containsKey(username + sitename)) {
				String plainText = check.genString();
				site.put(username + sitename, CaesarCipher.getInstance().encrypt(plainText));
				innerVault.put(username, site);
				return plainText;
			}
		} else if (vault.containsKey(username) && !CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password))  {
			int strike = 0;
			if (!jail.containsKey(username)) {
				jail.put(username, 1);
			} else 
				strike = jail.get(username);
				strike++;
				jail.put(username, strike);
			if (jail.get(username) >= 3) {
				throw new UserLockedOutException();
			} else 
				throw new PasswordMismatchException();
		} else if (!vault.containsKey(username)) {
			throw new UserNotFoundException();
		} else if (jail.get(username) >= 3) {
			throw new UserLockedOutException();
		}
		
		return null;
	}

	
	@Override
	public String retrieveSitePassword(String username, String password,
			String sitename) throws SiteNotFoundException, UserNotFoundException,
			UserLockedOutException, PasswordMismatchException {

		if (vault.containsKey(username) && CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password))  {
			if (!site.containsKey(username + sitename)) {
				throw new SiteNotFoundException();
			} else if (site.containsKey(username + sitename)) {
				return CaesarCipher.getInstance().decrypt(innerVault.get(username).get(username + sitename));
			}
		} else if (vault.containsKey(username) && !CaesarCipher.getInstance().decrypt(vault.get(username)).equals(password))  {
			int strike = 0;
			if (!jail.containsKey(username)) {
				jail.put(username, 1);
			} else 
				strike = jail.get(username);
				strike++;
				jail.put(username, strike);
			if (jail.get(username) >= 3) {
				throw new UserLockedOutException();
			} else 
				throw new PasswordMismatchException();
		} else if (!vault.containsKey(username)) {
			throw new UserNotFoundException();
		} else if (jail.get(username) >= 3) {
			throw new UserLockedOutException();
		}
		return null;
	}
	
	

}
