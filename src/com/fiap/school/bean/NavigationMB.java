package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Navigation;

@ManagedBean(name = "navMB")
public class NavigationMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public void navigateToLayout() throws IOException {
		String pageName = Contexts.getParameterName();

		Navigation.redirectToLayout(pageName);
	}

	public void navigateToPage() throws IOException {
		String pagePath = Contexts.getParameter("path");
		String pageName = Contexts.getParameterName();

		Navigation.redirectToPages(pagePath, pageName);
	}

}
