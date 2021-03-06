package com.cdol.androidTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cdol.backoffice.common.service.LoginService;

@RequestMapping("/androidTest")
@Controller
public class AndroidTestController {
	@Autowired
	private LoginService loginService;

	@RequestMapping("/loginPage.do")
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("androidTest/login");
		
		mav.addObject("id", request.getParameter("id"));
		mav.addObject("passwd", request.getParameter("passwd"));
		
		return mav;
	}
}
