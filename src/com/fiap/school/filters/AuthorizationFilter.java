package com.fiap.school.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fiap.school.entity.enums.Profile;

@WebFilter(filterName = "authFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);

			String reqURI = req.getRequestURI();

			if (reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)) {
				if (reqURI.indexOf("/login.xhtml") >= 0) {
					chain.doFilter(request, response);
				} else {
					if (reqURI.indexOf("/home.xhtml") >= 0) {
						chain.doFilter(request, response);
					} else {
						Map<String, Profile> rules = getNavigationRules();
						boolean hasValidProfile = false;

						for (Map.Entry<String, Profile> entry : rules.entrySet()) {
							int hasUrl = reqURI.indexOf("/" + entry.getKey() + ".xhtml");
							Profile profile = (Profile) ses.getAttribute("profile");
							if (hasUrl >= 0 && (profile.equals(entry.getValue()) || (profile.equals(Profile.MASTER)))) {
								hasValidProfile = true;
							}
						}

						if (hasValidProfile)
							chain.doFilter(request, response);
						else
							res.sendRedirect(req.getContextPath() + "/faces/Pages/Home/home.xhtml");
					}
				}
			} else if (reqURI.indexOf("/login.xhtml") <= 0 || (ses != null && ses.getAttribute("username") != null)) {
				res.sendRedirect(req.getContextPath() + "/faces/Pages/Login/login.xhtml");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Profile> getNavigationRules() {
		Map<String, Profile> rules = new HashMap<>();
		rules.put("department", Profile.ADMIN);
		rules.put("school", Profile.ADMIN);
		rules.put("employee", Profile.ADMIN);
		rules.put("teacher", Profile.ADMIN);
		rules.put("student", Profile.ADMIN);

		rules.put("course", Profile.SCHOOL);
		rules.put("discipline", Profile.SCHOOL);
		rules.put("knowledgeArea", Profile.SCHOOL);
		rules.put("schoolClass", Profile.SCHOOL);
		rules.put("classroom", Profile.SCHOOL);
		
		rules.put("exam", Profile.TEACHER);
		rules.put("examResult", Profile.TEACHER);
		
		rules.put("grade", Profile.STUDENT);
		rules.put("schoolReport", Profile.STUDENT);

		return rules;
	}

	@Override
	public void destroy() {

	}
}
