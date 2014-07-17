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
 * @since 2014-05-28
 * @version 1.0.0
 *
 *
 * Program 		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Result.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdol.util.common.Strings;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Result extends HttpServlet {
	
	/** Serial version UID */
	private static final long serialVersionUID = 20140225000009L;	
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	{
		
		String charset = Strings.getString(request.getAttribute("charset"));
		
		if (charset.length() > 0)
			response.setContentType("text/html; charset=" + charset);
		else
			response.setContentType("text/html; charset=UTF-8");
		
		Writer out = response.getWriter();
		
		out.write("<html>\n");
		out.write("<head>\n");
		// **************************************************
		// Script SRC
		// **************************************************
		String scriptSRC 	= Strings.getString(request.getAttribute("scriptSRC"));
		if (scriptSRC.length() > 0)
			out.write(scriptSRC + "\n");
		
		
		// **************************************************
		// # Script Start
		// **************************************************
		out.write("<script>\n");
		
		// Script Message
		String message 	= Strings.getString(request.getAttribute("message"));
		if (message.length() > 0)
			out.write("alert(\"" + message + "\");\n");				
		
		// Script Code
		String script 	= Strings.getString(request.getAttribute("script"));
		if (script.length() > 0)
			out.write(script + "\n");
		
		// HTML Target & Redirect
		String target 	= Strings.getString(request.getAttribute("target"));
		String redirect = Strings.getString(request.getAttribute("redirect"));
		if (redirect.length() > 0) {
			if (target.length() > 0)
				out.write(target + ".window.location.href=\"" + redirect + "\";\n");
			else
				out.write("window.location.href=\"" + redirect + "\";\n");
		}
		
		// Script Back
		String back 	= Strings.getString(request.getAttribute("back"));
		if (back.equalsIgnoreCase("true"))
			out.write("window.history.back();\n");
		
		// Script Close
		String close 	= Strings.getString(request.getAttribute("close"));
		if (close.equalsIgnoreCase("true"))
			out.write("top.window.self.close();\n");
		
		out.write("</script>\n");
		// **************************************************
		// # Script End
		// **************************************************
		out.write("</head>\n");
		out.write("<body>\n");
		
		// Post
		String post = Strings.getString(request.getAttribute("post"));
		if (post.length() > 0) {
			
			out.write("<form name=\"frmPost\" id=\"frmPost\" method=\"post\" action=\"" + Strings.getString(request.getAttribute("post")) + "\">\n");
			
			String[] name 	= (String[])request.getAttribute("name");
			String[] value 	= (String[])request.getAttribute("value");
			
			for (int nLoop = 0; nLoop < name.length; nLoop++) {
				out.write("<input type=\"hidden\" name=\"" + Strings.getString(name[nLoop]) 
						+ "\" id=\"" + Strings.getString(name[nLoop]) 
						+ "\" value=\"" + Strings.getString(value[nLoop]) 
						+ "\" />\n");
			}
			out.write("<script>document.getElementById(\"frmPost\").submit();</script>");
		}
		out.write("</body>\n");
		out.write("</html>\n");
	}
}