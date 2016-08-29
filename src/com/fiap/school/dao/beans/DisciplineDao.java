package com.fiap.school.dao.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.fiap.school.dao.jpa.JpaUtil;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.person.User;
import com.fiap.school.entity.school.Discipline;

public class DisciplineDao {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Discipline> getStudentDisciplines(User user) {
		try {
			em = JpaUtil.getEntityManager();
			
			Student student = (Student) new UserDao().getPersonByUser(user);			
			
			List<Discipline> disciplines = em.createQuery("From Discipline Where course = :course")
					.setParameter("course", student.getCourse()).getResultList();

			return disciplines;
		} catch (NullPointerException ex) {
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

}
