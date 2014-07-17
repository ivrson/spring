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
 * @since 2014-04-17
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SKwithAES.java
 * Function		: Symmetric Key-based encryption(SK) with AES(Data Encryption Standard for the next generation of DES)
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [2014417141900][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-04-17
 * 
 * <p>DESCRIPTION: AES-128
 * <p>IMPORTANT
 */
public class SKwithAES {

	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-17
	 * 
	 * @param String, String
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String encode(String string, String key) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] textBytes = string.getBytes("UTF-8");
	
		AlgorithmParameterSpec ivSpec	= new IvParameterSpec(ivBytes);
		SecretKeySpec newKey			= new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher					= null;
		cipher							= Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
		
		return Base64.encodeBase64String(cipher.doFinal(textBytes));
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-17
	 * 
	 * @param String, String
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String decode(String string, String key) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		byte[] textBytes = Base64.decodeBase64(string);
		// byte[] textBytes = string.getBytes("UTF-8");
		
		AlgorithmParameterSpec ivSpec	= new IvParameterSpec(ivBytes);
		SecretKeySpec newKey			= new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher					= Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
		
		return new String(cipher.doFinal(textBytes), "UTF-8");
	}
	
	/*
	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{

		String key = "1234567890123456";

		String plainText	= "";
		String encodeText	= "";
		String decodeText	= "";
		
		// Encrypt
		plainText  = "www.i-popcorn.co.kr";
		encodeText = SKwithAES.encode(plainText, key);		
		System.out.println("Encode : "+ encodeText);
		 
		// Decrypt
		decodeText = SKwithAES.decode(encodeText, key);
		System.out.println("Decode : "+ decodeText);

	}
	*/
}
