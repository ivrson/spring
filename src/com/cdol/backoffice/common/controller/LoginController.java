package com.cdol.backoffice.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				mav.setViewName("backoffice/main");
			}
		}
		
		return mav;
	}
}
