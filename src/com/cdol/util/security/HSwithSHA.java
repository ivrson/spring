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
 * @since 2014-04-16
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: HSwithSHA.java
 * Function		: HaSh with SHA(Secure Hash Algorithm): http://www.example-code.com/java/crypt_hash_algorithms.asp
 * Notes		: 
 * History		: [NO][Programmer][Description]
 *				: [20140416180500][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-04-16
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class HSwithSHA {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(HSwithSHA.class);
	
	private static volatile HSwithSHA INSTANCE;
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-16
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static HSwithSHA getInstance() {
		if (INSTANCE == null) {
			synchronized (HSwithSHA.class) {
				if (INSTANCE == null) INSTANCE = new HSwithSHA();
			}
		}
		return INSTANCE;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-16
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String encode(String message) throws NoSuchAlgorithmException {
		return encode(message, "SHA-256");
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param String
	 * @param String
	 * @return String
	 * <p>DESCRIPTION: MD5(����� length:32), SHA(����� length:40), SHA-1(����� length:40), SHA-256(���� length:64), SHA-384(���� length:96), SHA-512(���� length:128)
	 * <p>IMPORTANT
	 */
	public static String encode(String message, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest md;
		String out = "";
		
		if (algorithm == null || algorithm.equals("")) {
			algorithm = "SHA-256";
		}
		
		try {
			md = MessageDigest.getInstance(algorithm);
			
			md.update(message.getBytes());
			byte[] mb = md.digest();
			
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}			
			// System.out.println("CRYPTO(" + algorithm + "): " + out + ", length is : " + out.length());
			
			}
			catch (NoSuchAlgorithmException e) {
				logger.error("[kr.co.ipopcorn.util.security.HSwithSHA.encode()]" + e.getMessage(), e);
			}
		
		return out.toUpperCase();
	}
	
	/*
	public static void main(String[] args) throws NoSuchAlgorithmException {
		HSwithSHA hash = HSwithSHA.getInstance();
		
		System.out.println("CRYPTO(SHA-256): "	+ hash.encode("12345678"));
		System.out.println("CRYPTO(SHA-1): "	+ hash.encode("pluto", "SHA-1"));
	}
	*/
}
