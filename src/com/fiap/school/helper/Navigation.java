package com.fiap.school.helper;

import java.io.IOException;
import java.io.Serializable;

public class Navigation implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getLayouts(String pageName){
		return Contexts.getRootContext() + "/faces/Layouts/" + pageName + ".xhtml";
	}
	
	public static String getPages(String pagePath, String pageName){
		return Contexts.getRootContext() + "/faces/Pages/" + pagePath + "/" + pageName + ".xhtml";
	}
	
	public static void redirectToLayout(String pageName) throws IOException {
		Contexts.getExternalContext().redirect(getLayouts(pageName));
	}
	
	public static void redirectToPages(String pagePath, String pageName) throws IOException {
		Contexts.getExternalContext().redirect(getPages(pagePath, pageName));
	}

}