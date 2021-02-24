package encrypt;
/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class runs tests to test the methods in the CaesarCipher class. 
 * 
 * @author Grey Kumar
 *
 */
public class CaesarCipherTest {

	@Test
	public void testCaesarCipher() {
		CaesarCipher encryptor = new CaesarCipher();
		CaesarCipher encryptor2 = new CaesarCipher();
		assertNotEquals(encryptor, encryptor2);
	}

	@Test
	public void testGetInstance() {
		CaesarCipher encryptor = CaesarCipher.getInstance();
		CaesarCipher encryptor2 = CaesarCipher.getInstance();
		assertEquals(encryptor, encryptor2);
	}
	
	@Test
	public void testEncrypt() {
		CaesarCipher encryptor = new CaesarCipher();
		String word = "dog";
		String encrypted = encryptor.encrypt(word);
		assertNotEquals(word, encrypted);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testException() {

			CaesarCipher encryptor = new CaesarCipher();
			encryptor.encrypt("मुझे कुत्ते पसंद है");
	}

	@Test
	public void testDecrypt() {
		CaesarCipher encryptor = new CaesarCipher();
		String word = "dog";
		String encrypted = encryptor.encrypt(word);
		assertEquals(word, encryptor.decrypt(encrypted));
		String decrypted = encryptor.decrypt(encrypted);
		assertEquals("dog", decrypted.toString());
	}

}
