package com.fiap.school.helper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Contexts implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String getRootContext() {
		return getExternalContext().getRequestContextPath();
	}

	public static ExternalContext getExternalContext() {
		return getCurrentInstance().getExternalContext();
	}
	
	public static FacesContext getCurrentInstance() {
		return FacesContext.getCurrentInstance();
	}
	
	public static Map<String, String> getRequestParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}
	
	public static Map<String, String[]> getRequestParameterValuesMap() {
		return getExternalContext().getRequestParameterValuesMap();
	}
	
	public static int getParameterId(){
		return Integer.parseInt(getRequestParameterMap().get("id"));
	}
	
	public static String getParameterName(){
		return getRequestParameterMap().get("name");
	}
	
	public static String getParameter(String parameter){
		return getRequestParameterMap().get(parameter);
	}
	
	public static List<String> getParameters(String parameter){
		return Arrays.asList(getRequestParameterValuesMap().get(parameter));
	}
	
	public static Object getSessionAttribute(String attribute){
		return Sessions.getSession().getAttribute(attribute);
	}
}
