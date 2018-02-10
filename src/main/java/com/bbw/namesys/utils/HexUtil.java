package com.bbw.namesys.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides convenient functions to convert hex string to byte array
 * and vice versa.
 * 
 * @author 99bill
 *
 */
public class HexUtil {

	private static final String HEX_CHARS = "0123456789abcdef";
	private static Cipher cipher;
	private static Key key;

	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
			sb.append(HexUtil.HEX_CHARS.charAt(b[i] & 0x0F));
		}
		return sb.toString();
	}

	public static byte[] toByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		int j = 0;
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character.digit(s.charAt(j++), 16));
		}
		return buf;
	}

	public synchronized static String encryStr(String str) {
		try {
			Cipher cipher = getCipher();
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return HexUtil.toHexString(cipher.doFinal(str.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("加密出错", e);
		}
	}

	public synchronized static String decryptStr(String str) {
		try {
			Cipher cipher = getCipher();
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(HexUtil.toByteArray(str)), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("解密出错", e);
		}
	}

	private static Cipher getCipher() throws Exception {
		if(cipher==null){
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			ObjectInputStream dbKeyOut=new ObjectInputStream(HexUtil.class.getResourceAsStream("/db.key"));
			key=(Key) dbKeyOut.readObject();
		}
		return cipher;
	}

	public static void genKey(OutputStream outputStream) throws NoSuchAlgorithmException, IOException  {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey newKey = keyGen.generateKey();
		ObjectOutputStream objOutput=new ObjectOutputStream(outputStream);
		objOutput.writeObject(newKey);
	}
}
