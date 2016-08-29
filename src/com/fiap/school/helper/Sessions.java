package com.fiap.school.helper;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Sessions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static HttpSession getSession() {
		return (HttpSession) Contexts.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) Contexts.getExternalContext().getRequest();
	}
	
	public static String getUsername() {
		return getSession().getAttribute("username").toString();
	}
	
	public static String getProfile() {
		return getSession().getAttribute("profile").toString();
	}

}
