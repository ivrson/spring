/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * �ϱ� ���α׷��� ���� ���۱��� ������ ���������� i-popcorn.co.kr�� ������,
 * i-popcorn.co.kr�� ��������� ������� �ʴ� ���, ����, ���� �� �� 3�ڿ� ���� ����, ������ ������ �����Ǹ�
 * i-popcorn.co.kr�� �������� ħ�ؿ� �ش�ȴ�.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SKwithMD5andDES.java
 * Function		: Symmetric Key-based encryption(SK) with MD5 and DES(Data Encryption Standard)
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.security;

import java.io.*;

import javax.crypto.*; 
import javax.crypto.spec.*;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public final class SKwithMD5andDES {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(SKwithMD5andDES.class);
	
	/** Character set */
	private String character	= "UTF-8";
	/** Encrypted data */
	private byte[]				encryptedData; 	
	/** Decrypted data */
	private byte[]				decryptedData;
	
	/** Public key specification */
	private PBEKeySpec 			keySpec;
	/** Secret key */
	private SecretKey 			key;
	/** Secret key factory */
	private SecretKeyFactory	keyFactory;
	/** Public parameter specification */
	private PBEParameterSpec 	parameterSpec;
	/** Cipher */
	private Cipher 				cipher;
	
	/** Salt */
	private byte[] salt = { 
			(byte)0x24, (byte)0x85, (byte)0x62, (byte)0x79,
			(byte)0xFE, (byte)0x10, (byte)0xA6, (byte)0xB2 
	};
	private int iteration = 9; 
	
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * <p>DESCRIPTION: ������(Constructor)
	 * <p>IMPORTANT
	 */
	public SKwithMD5andDES(String character, String password) {
		try {
			init(password);
			this.character = character;
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.security.SKwithMD5andDES()]" + e.getMessage(), e);
		}
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @throws Exception
	 * <p>DESCRIPTION: �ʱ�ȭ(Initialize)
	 * <p>IMPORTANT
	 */
	private void init(String password) throws Exception {
		// Gain a keyFactory 
		keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		
		// Generate a secret key 
		keySpec = new PBEKeySpec(password.toCharArray());
		key 	= keyFactory.generateSecret(keySpec);
		
		// Set a parameter for PBE 
		parameterSpec 	= new PBEParameterSpec(salt, iteration);
		cipher 			= Cipher.getInstance("PBEWithMD5AndDES");
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @return byte[]
	 * @throws Exception
	 * <p>DESCRIPTION: ��ȣȭ(Encrypt)
	 * <p>IMPORTANT
	 */
	private byte[] encrypt(String plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		encryptedData = cipher.doFinal(plainText.getBytes(this.character));
		
		return encryptedData;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param byte[]
	 * @return byte[]
	 * @throws Exception
	 * <p>DESCRIPTION: ��ȣȭ(Decrypt)
	 * <p>IMPORTANT
	 */
	private byte[] decrypt(byte[] encryptedText) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec); 
		decryptedData = cipher.doFinal(encryptedText); 
		
		return decryptedData;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION: ��ȭȭ ����(Set encrypt)
	 * <p>IMPORTANT
	 */
	public String setEncrypt(String input) {
		
		if (input == null || input.equals("")) return "";
			
		String output = null;
		byte[] returnData;
		
		try {
			returnData 	= encrypt(input);
			output 		= new BASE64Encoder().encode(returnData);
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.security.SKwithMD5andDES.setEncrypt()]" + e.getMessage(), e);
		} 
		
		return output;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION: ��ȣȭ ���(Get Decrypt)
	 * <p>IMPORTANT
	 */
	public String getDecrypt(String input) {
		
		if (input == null || input.equals("")) return "";
		
		String output = null;
		byte[] returnData;
		
		try {
			
			BASE64Decoder base64Decoder = new BASE64Decoder();
			ByteArrayInputStream bin 	= new ByteArrayInputStream(input.getBytes());
			ByteArrayOutputStream bout 	= new ByteArrayOutputStream();
			byte[] buffer 				= null;
			try {		
					base64Decoder.decodeBuffer(bin, bout);
			}
			catch (Exception e) {
					e.printStackTrace();
			}
			buffer = bout.toByteArray();
			returnData = decrypt(buffer);
			
			output = new String(returnData, this.character);
		}
		catch (Exception e) {
			logger.error("[kr.co.ipopcorn.util.security.SKwithMD5andDES.getDecrypt()]" + e.getMessage(), e);
		}
		
		return output;
	}
	
	/*
	public static void main(String[] args) {
		
		SKwithMD5andDES des = new SKwithMD5andDES("UTF-8", "123456789012345678");
		
		try {
	
			String insertDB	= "12345678";
			System.out.println("Encrypted text: " 	+ des.setEncrypt(insertDB));
		} 
		catch (Exception e) {
			e.printStackTrace(); 
		}
		
		try {
			
			String selectDB	= "k+gpaMcUnfzI5xPyDXE3NA==";
			System.out.println("Plan text: " 		+ des.getDecrypt(selectDB));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}