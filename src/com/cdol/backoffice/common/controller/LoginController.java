/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOl.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * �ϱ� ���α׷��� ���� ���۱��� ������ ���������� cdol�� ������,
 * cdol�� ��������� ������� �ʴ� ���, ����, ���� �� �� 3�ڿ� ���� ����, ������ ������ �����Ǹ�
 * cdol�� �������� ħ�ؿ� �ش�ȴ�.
 * Copyright (C) 2014 cdol All Rights Reserved.
 *
 *
 * @author ivrson
 * @since 2014-02-25
 * @version 1.0.0
 *
 *
 * Program		: cdol_springTest
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: LoginController.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140225000000][ivrson@naver.com][CREATE: Initial Release]
 */
package com.cdol.backoffice.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdol.backoffice.common.service.LoginService;
import com.cdol.backoffice.common.vo.LoginVO;

@RequestMapping("/backoffice")
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("/loginPage.do")
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backoffice/login");
		
		return mav;
	}
	
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		LoginVO param = new LoginVO();
		param.setId(id);
		param.setPasswd(passwd);

		LoginVO loginVO = loginService.getLoginAuthorizationCheck(id);
		
		if(loginVO == null){
			mav.setViewName("backoffice/login");
		}
		else {
			if(!passwd.equals(loginVO.getPasswd())){
				mav.setViewName("backoffice/login");
			}
			else {
				HttpSession session		= request.getSession(true);
				session.setAttribute("id", id);
				session.setAttribute("userName", loginVO.getUserName());
				System.out.println(session.getAttribute("userName"));
				mav.setViewName("backoffice/main");
			}
		}
		
		return mav;
	}
}
