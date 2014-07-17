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
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-06-25
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Files {
	
	static String DirSym = File.separator;
	
	public static String savePath(int usr_idx){
		
		String savePath = "/" 
				 + Datetime.getDatetimeToString("yyyyMMdd") + "/" 
				 + Datetime.getDatetimeToString("HH") + "/"
				 + Integer.toString(usr_idx);
		
		File Folder = null;
		
		Folder = new File(savePath);
		
		if(!Folder.exists()){
			Folder.mkdirs();
		}
		
		return savePath;
	}
	
	public static String uploadFile(MultipartFile file, String path, int usr_idx){
		
		FileOutputStream fos	= null;
		
		if(file != null){
			String fullPath = path + savePath(usr_idx) + "/" + file.getOriginalFilename();
			
			try{
				byte fileData[] = file.getBytes();
				fos = new FileOutputStream(fullPath);  
				fos.write(fileData);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(fos != null){
					try{
						fos.close();
					}catch(Exception e){}
				}
			}
			return fullPath;
		}else{
			return "";
		}
	}
	
	public static String findPath(int usr_idx, String gbn){
		
		String path = "/"; 
		
		if(gbn.equals("p")){
			path = path + Integer.toString(usr_idx) + "_P_" + Datetime.getDatetimeToString("mmss")+ ".png";
		}else if(gbn.equals("g")){
			path = path + Integer.toString(usr_idx) + "_G_" + Datetime.getDatetimeToString("mmss")+ ".png";
		}else{
			path = path + Integer.toString(usr_idx) + Datetime.getDatetimeToString("mmss")+ ".png";
		}
		return path;
		
	}
}
