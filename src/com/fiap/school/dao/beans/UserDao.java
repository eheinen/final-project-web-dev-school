package com.fiap.school.dao.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.fiap.school.dao.jpa.JpaUtil;
import com.fiap.school.entity.person.Person;
import com.fiap.school.entity.person.User;

public class UserDao {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public User authenticate(String username, String password) {
		em = JpaUtil.getEntityManager();

		List<User> users = (List<User>) em.createQuery("From User Where username = :username and password = :password")
				.setParameter("username", username).setParameter("password", password).getResultList();

		if (users == null || users.size() == 0)
			return null;

		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	public Person getPersonByUser(User user) {
		try {
			em = JpaUtil.getEntityManager();
			
			List<Person> persons = em.createQuery("From Person Where user = :user").setParameter("user", user).getResultList();
			
			if(persons != null && persons.size() > 0)
				return persons.get(0);
				
		} catch (Exception ex) {
			if (em.isOpen())
				em.close();
		}
		
		return null;
	}
}
