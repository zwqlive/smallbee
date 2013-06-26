package com.will.smallbee.core.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	private static final String ALGORITHM_MD5="MD5"; 
	
	public static byte[] encryptMD5(byte[] data) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(ALGORITHM_MD5);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} 
		md5.update(data); 
		return md5.digest();
	}
	
	public static String encryptMD5(String data){
		byte[] bytes = encryptMD5(data.getBytes());
		StringBuffer sb = new StringBuffer();
		int n=0;		
		for(int i=0;i<bytes.length;i++){
			n=bytes[i];
			if(n<0){
				n=256+n;
			}
			sb.append(Integer.toHexString(n));
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		String name="admin";
		System.out.println(encryptMD5(name.getBytes()));
		System.out.println(encryptMD5(name));
	}
}