package com.cdol.backoffice.common.vo;

import java.io.Serializable;

public class LoginVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = "";
	private String passwd = "";
	private String userName = "";

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswd() {
		return passwd;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}