<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 cdol에 있으며,
 * cdol이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * cdol의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 cdol All Rights Reserved.
 *
 *
 * @author ivrson
 * @since 2014-04-10
 * @version 1.0.0
 *
 *
 * Program		: com.cdol.spring
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /backoffice/config/managerWrite.jsp
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140410000000][ivrson][CREATE: Initial Release]
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	/**
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-13
	 *
	 * <p>DESCRIPTION 
	 * <p>IMPORTANT
	 */
	window.onload = function() {
	
		// 타이틀
		common.Text.setTextByID("TXT_COMMON_REQUIRED");
		common.Text.setTextByID("TXT_MANAGER_ID");
		common.Text.setTextByID("TXT_MANAGER_PASSWORD");
		common.Text.setTextByID("TXT_MANAGER_PASSWORD_");
		common.Text.setTextByID("TXT_MANAGER_NM");
		common.Text.setTextByID("TXT_MANAGER_EN");
		common.Text.setTextByID("TXT_MANAGER_TYPE");
		common.Text.setTextByID("TXT_MANAGER_ALI");
		common.Text.setTextByID("TXT_MANAGER_DPT");
		common.Text.setTextByID("TXT_MANAGER_TEL");
		common.Text.setTextByID("TXT_MANAGER_PHONE");
		common.Text.setTextByID("TXT_MANAGER_EMAIL");
		
		// 메일
		common.Form.Select.create("selectEmail", "emailList", common.User.getDomain(), "", "");
		document.getElementById("emailList").onchange = function() {
			if (this.options[this.selectedIndex].value != "") {
				document.getElementById("email2").style.display = "none";
				document.getElementById("email2").value = this.options[this.selectedIndex].value;
			}
			else {
				document.getElementById("email2").style.display = "";
				document.getElementById("email2").focus();
			}
		};
		
		document.getElementById("frmMain").id.focus();
	};
	
	/**
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-26
	 *
	 * <p>DESCRIPTION: 아이디 중복 확인
	 * <p>IMPORTANT
	 */
	function checkDuplication() {
	
		if (common.User.checkID(document.getElementById("id"))) return;
		if (common.User.isKeyword(document.getElementById("id"))) return;
		
		var value = document.getElementById("id").value;
		if (value != "") {
			common.Jquery.ajax("/console/common/ajax/managerDuplication.json"
				, "{\"id\":\"" + value + "\"}"
				, "setDuplication");
		}
	}
	
	/**
	 ********************************************************************************
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-26
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT: No use at Cross-Domain Region
	 ********************************************************************************
	 */
	function setDuplication(data) {
	
		var count	= 1;
		
		$(data).each(function() {
			count = this["count"];
		});
		
		if (count == 0) {
			alert(MSG_ERROR_INPUT_ID3);
			document.getElementById("checkDuplication").value = "true";
		}
		else {
			alert(MSG_ERROR_INPUT_ID4);
			document.getElementById("checkDuplication").value = "false";
		}
	}
	
	/**
	 ********************************************************************************
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-18
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 ********************************************************************************
	 */
	function checkDepartment(objThis) {
		
		if (objThis.options.length == 1) {
			alert(MSG_ERROR_REQUIRED);
			document.getElementById("frmMain").cd_ali.focus();
		}
	}
	
	/**
	 ********************************************************************************
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-17
	 * 
	 * <p>DESCRIPTION
	 * <p>IMPORTANT: No use at Cross-Domain Region
	 ********************************************************************************
	 */
	function getDepartment(objThis) {
		var value = objThis.options[objThis.selectedIndex].value;
		if (value != "") {
			common.Jquery.ajax("/console/common/ajax/departmentList.json"
				, "{\"cd_ali\":\"" + objThis.options[objThis.selectedIndex].value + "\"}"
				, "setDepartment");
		}
		else {
			var struct = new Array();
			common.Form.Select.createOption("cd_dpt", struct, TXT_COMMON_SELECT, "");
		}
	}
	
	/**
	 ********************************************************************************
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-18
	 * 
	 * <p>DESCRIPTION: this["cd_dpt"] = alert(this.cd_dpt)
	 * <p>IMPORTANT: No use at Cross-Domain Region
	 ********************************************************************************
	 */
	function setDepartment(data) {
		var struct = new Array();
		$(data).each(function() {
			<c:choose>
				<c:when test="${LANG == 'EN'}">
					struct.push({value:this["cd_dpt"],	text:this["dpt_en"]});	
				</c:when>
				<c:otherwise>
					struct.push({value:this["cd_dpt"],	text:this["dpt_nm"]});
				</c:otherwise>
			</c:choose>			
		});
		
		if (struct.length > 0) {
			common.Form.Select.createOption("cd_dpt", struct, TXT_COMMON_SELECT, "");
		}
	}
	
	/**
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-19
	 *
	 * <p>DESCRIPTION: 전송체크(Check sumbit)
	 * <p>IMPORTANT
	 */
	function checkSubmit() {
		var frmMain				= document.getElementById("frmMain");
		var checkDuplication	= document.getElementById("checkDuplication").value;
		
		if (common.Form.checkRequired(frmMain)) return;
		if (common.User.checkID(document.getElementById("id"))) return;
		if (common.User.isKeyword(document.getElementById("id"))) return;
		if (checkDuplication != "true") {
			alert(MSG_ERROR_INPUT_ID2);
			return;
		}
		if (common.User.checkPassword(document.getElementById("passwd"))) return;
		if (common.Form.comparePasswd("passwd", "re_passwd")) return;
		if (common.User.checkEmail(getEmail())) return;
				
		frmMain.action = getServerInfo() + "/console/config/manager/writeProc.iwf";
		frmMain.submit();
	}
	
	/**
	 * @author pluto#i-popcorn.co.kr
	 * @since 2014-03-20
	 *
	 * <p>DESCRIPTION: 이메일(Email)
	 * <p>IMPORTANT
	 */
	function getEmail() {
		var email = document.getElementById("email1").value + "@" + document.getElementById("email2").value;
		document.getElementById("email").setAttribute("value", email);
		
		return email;
	}
	</script>
</head>
<body>
<form name="frmMain" id="frmMain" method="post">
<input type="hidden" id="email"				name="email"			value="" />
<input type="hidden" id="checkDuplication"	name="checkDuplication"	value="false" />
<% /****************************** wrap start ******************************/ %>
<div id="wrap">
	<% /********** container start **********/ %>
	<div id="container">
		<!-- THIS CONTENTS -->
		<div class="adminForm">
			<p id="TXT_COMMON_REQUIRED" class="pB10 alR"></p> 
			<ul>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_ID"></span></label></div>
					<div class="value">
						<input type="text" id="id" name="id" style="width: 160px;" required="true" minlength="8" maxlength="16" autocomplete="off" tabindex="1" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 16, 'passwd');" onClick="common.Form.removeValue(this);" />
						<a id="submit" href="javascript:checkDuplication();" class="btnBasic02">중복 확인</a>
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_PASSWORD"></span></label></div>
					<div class="value">
						<input type="password" id="passwd" name="passwd" style="width: 160px;" required="true" minlength="8" maxlength="16" autocomplete="off" tabindex="2" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 16, 're_passwd');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_PASSWORD_"></span></label></div>
					<div class="value">
						<input type="password" id="re_passwd" name="re_passwd" style="width: 160px;" required="true" minlength="8" maxlength="16" autocomplete="off" tabindex="3" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 16, 'mng_nm');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_NM"></span></label></div>
					<div class="value">
						<input type="text" id="mng_nm" name="mng_nm" style="width: 200px;" required="true" minlength="3" maxlength="32" autocomplete="off" tabindex="4" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 32, 'mng_en');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_EN"></span></label></div>
					<div class="value">
						<input type="text" id="mng_en" name="mng_en" style="width: 200px;" required="true" minlength="3" maxlength="32" autocomplete="off" tabindex="5" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 32, 'cd_usr_type');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for=""><span id="TXT_MANAGER_TEL"></span></label></div>
					<div class="value">
						<input type="text" id="tel_num1" name="tel_num1" style="width: 30px;" minlength="3" maxlength="3" autocomplete="off" tabindex="9" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 3, 'tel_num2');" onClick="common.Form.removeValue(this);" /> -
						<input type="text" id="tel_num2" name="tel_num2" style="width: 40px;" minlength="4" maxlength="4" autocomplete="off" tabindex="10" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 4, 'tel_num3');" onClick="common.Form.removeValue(this);" /> -
						<input type="text" id="tel_num3" name="tel_num3" style="width: 40px;" minlength="4" maxlength="4" autocomplete="off" tabindex="11" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 4, 'phone_num1');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_PHONE"></span></label></div>
					<div class="value">
						<input type="text" id="phone_num1" name="phone_num1" style="width: 30px;" required="true" minlength="3" maxlength="3" autocomplete="off" tabindex="12" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 3, 'phone_num2');" onClick="common.Form.removeValue(this);" /> -
						<input type="text" id="phone_num2" name="phone_num2" style="width: 40px;" required="true" minlength="4" maxlength="4" autocomplete="off" tabindex="13" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 4, 'phone_num3');" onClick="common.Form.removeValue(this);" /> -
						<input type="text" id="phone_num3" name="phone_num3" style="width: 40px;" required="true" minlength="4" maxlength="4" autocomplete="off" tabindex="14" onkeypress="common.Number.checkNumber(event);" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 4, 'email1');" onClick="common.Form.removeValue(this);" />
					</div>
				</li>
				<li>
					<div class="title"><label for="">(*)<span id="TXT_MANAGER_EMAIL"></span></label></div>
					<div class="value">
						<input type="text" id="email1" name="email1" style="width: 80px;" required="true" minlength="1" maxlength="16" autocomplete="off" tabindex="15" onkeyup="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 16, 'email2');" onClick="common.Form.removeValue(this);" /> @
						<input type="text" id="email2" name="email2" style="width: 80px;" required="true" minlength="3" maxlength="16" autocomplete="off" tabindex="16" onkeyup="if (event.keyCode==13) checkSubmit();" onClick="common.Form.removeValue(this);" />
						<span id="selectEmail"></span>
					</div>
				</li>
			</ul>
		</div>
		<div class="btnRegister">
			<a id="submit" href="javascript:checkSubmit();" class="btnBasic02">등록</a>
		</div>
	</div>
</div>
</form>
</body>
</html>