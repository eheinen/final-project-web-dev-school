package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.fiap.school.dao.beans.UserDao;
import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.enums.Profile;
import com.fiap.school.entity.person.Person;
import com.fiap.school.entity.person.User;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Sessions;

@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserMB() {
		user = new User();
		messages = new Messages();
		actions = new Actions("user.png", "Enter");
	}

	public String authenticate() throws IOException {
		try {
			User userLogin = new UserDao().authenticate(user.getUsername(), user.getPassword());
			if (userLogin == null) {
				messages.setMessage("warning", "Username or password wrong");
				Navigation.redirectToPages("Login", "login");
			} else {
				Sessions.getSession().setAttribute("user", userLogin);
				Sessions.getSession().setAttribute("username", userLogin.getUsername());
				Sessions.getSession().setAttribute("profile", userLogin.getProfile());
				Navigation.redirectToPages("Home", "home");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Navigation.redirectToPages("Login", "login");
		}
		return null;
	}
	
	public void logout() throws IOException {
		Sessions.getSession().invalidate();
		Navigation.redirectToPages("Login", "login");
	}
	
	public String firstAccess() throws IOException {
		GenericDao<User> dao = new GenericDao<User>(User.class);
		List<User> users = dao.list();
		if(users == null || users.size() == 0){
			User user = new User();
			user.setUsername("master");
			user.setPassword("master");
			user.setProfile(Profile.MASTER);
			dao.insert(user);
			messages.setMessage("success", "Your user was created successfully");
		} else {
			messages.setMessage("warning", "You already have an user registered");
		}
		return null;
	}
	
	
	public User getUserAuthenticated() {
		return (User) Contexts.getSessionAttribute("user");
	}

	public Person getPersonAuthenticated() {
		return new UserDao().getPersonByUser(getUserAuthenticated());
	}
	
}
