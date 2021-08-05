package example.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ToEncrypted {
	int a = 5;
	
	private static final String ALGO = "SHA-256";
	private static final char[] HEXARRAY = "0123456789ABCDEF".toCharArray();
	
	
	public static String generateHash(String data, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(ALGO);
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	private static String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length*2];
		for(int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j*2] = HEXARRAY[v >>> 4];
			hexChars[j*2 + 1] = HEXARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	public static byte[] createSalt() {
		int saltSize = 5;
		
		byte[] bytes = new byte[saltSize];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
	
	
	
	
	
	
	
	
	
	
	
//	private static final String ALGO = "MD5";

//	public static void main(String[] args) throws NoSuchAlgorithmException {
//		byte[] myArr = {59,-107,23,45,80};
//		String pass = "password";
//		System.out.println(generateHash(pass, myArr));
//	}
}