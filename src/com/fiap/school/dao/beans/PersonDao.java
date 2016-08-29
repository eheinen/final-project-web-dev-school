package com.fiap.school.dao.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.fiap.school.dao.jpa.JpaUtil;
import com.fiap.school.entity.enums.PersonProfile;
import com.fiap.school.entity.person.Employee;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.person.Teacher;

public class PersonDao {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeList() {
		try {
			em = JpaUtil.getEntityManager();

			List<Employee> employees = em.createQuery("From Person Where personProfile = :profile")
					.setParameter("profile", PersonProfile.EMPLOYEE).getResultList();

			return employees;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> getTeacherList() {
		try {
			em = JpaUtil.getEntityManager();

			List<Teacher> teachers = em.createQuery("From Person Where personProfile = :profile")
					.setParameter("profile", PersonProfile.TEACHER).getResultList();

			return teachers;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudentList() {
		try {
			em = JpaUtil.getEntityManager();

			List<Student> students = em.createQuery("From Person Where personProfile = :profile")
					.setParameter("profile", PersonProfile.STUDENT).getResultList();

			return students;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

}
