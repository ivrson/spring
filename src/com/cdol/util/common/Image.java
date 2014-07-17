/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 i-popcorn.co.kr에 있으며,
 * i-popcorn.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * i-popcorn.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-06-25
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Image.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140625144100][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-06-25
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Image {
	
	static String DirSym = File.separator;
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-06-25
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getBase64(String fullName) {
		
		File imageFile = new File(fullName);
		
		if (!imageFile.exists()) return "333";
		
		long length = imageFile.length();
		
		byte[] buffer = new byte[(int)length];
		
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(imageFile);
			fin.read(buffer);
			fin.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		
		byte[] base64Encoded = Base64.encodeBase64(buffer);
				
		return new String(base64Encoded);
		
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-06-25
	 * 
	 * @param String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static void getImage(String base64String, String saveFullName) {
		
		File imageFile = new File(saveFullName);
		
		if (!imageFile.exists()) {
			
			try {
				imageFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(imageFile);
				
				fos.write(Base64.decodeBase64(base64String));
				fos.flush();
				fos.close();				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/*
	public static void main(String[] arg) {
		String result = getBase64("C:/sample.jpg");
		System.out.println(result);
		getImage(result, "C:/copy.jpg");
	}
	*/
	
}
