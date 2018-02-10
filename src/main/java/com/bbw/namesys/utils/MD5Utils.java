package com.bbw.namesys.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Static functions to simplifiy common {@link MessageDigest} tasks.  This
 * class is thread safe.
 */
public class MD5Utils {
    private MD5Utils() {
    }

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data));
    }
    
    /**
     * 获得文件的md5
     * @param file
     * @return 32个字符的16进制数字字符串
     * @throws IOException 
     */
    public static String getFileMD5HexString(File file) throws IOException {
    	
    	if(file == null){
    		return null;
    	}
    	
		String md5HexString = null;
		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		MessageDigest messageDigest = getDigest();
		try {
			fileInputStream = new FileInputStream(file);
			fileChannel = fileInputStream.getChannel();
			ByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			messageDigest.update(byteBuffer);
			md5HexString = HexUtil.toHexString(messageDigest.digest());
		} catch (IOException e) {
			throw e;
		} finally {
			if (fileChannel != null) {
				fileChannel.close();
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
		return md5HexString;
	}
}
