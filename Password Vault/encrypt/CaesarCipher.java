package encrypt;

import java.util.Random;

public class CaesarCipher implements Encryptor {

	public CaesarCipher() {
		shift = getShift();
	}
	
	/**
	 * Utilizes the singleton pattern to prevent more than one instance at a 
	 * time
	 * 
	 * @return a static instance of the class CaesarCipher
	 */
	public static CaesarCipher getInstance() {
		return INSTANCE;
	}

	@Override
	public String encrypt(String s) {
		return encryptDecrypt(s, true);
	}

	@Override
	public String decrypt(String s) {
		return encryptDecrypt(s, false);
	}
	
	private static int getShift() {
		Random r = new Random();
		int low = 1;
		int high = OFFSET_MAX - OFFSET_MIN;
		return r.nextInt(high - low) + low;
	}

	private String encryptDecrypt(String s, boolean encrypt) throws IllegalArgumentException {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			int indx = c, cpos;
			if (!isPositionInRange(indx))
				throw new IllegalArgumentException("String to be encrypted has unrecognized character " + c);

			if (encrypt) {
				cpos = indx + shift;
				if (cpos > OFFSET_MAX)
					cpos = OFFSET_MIN + (cpos - OFFSET_MAX);
			} else {
				cpos = indx - shift;
				if (cpos < OFFSET_MIN)
					cpos = OFFSET_MAX - (OFFSET_MIN - cpos);	
			}
			sb.append((char)cpos);
		}
		return sb.toString();		
	}	
	
	private boolean isPositionInRange(int indx) {
		return indx >= OFFSET_MIN && indx <= OFFSET_MAX;
	}

	private int shift;
    private static final int OFFSET_MIN = 32;
    private static final int OFFSET_MAX = 126;
    private static final CaesarCipher INSTANCE  = new CaesarCipher();
}
