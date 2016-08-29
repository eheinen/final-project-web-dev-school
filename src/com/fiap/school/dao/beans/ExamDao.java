package com.fiap.school.dao.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.fiap.school.dao.jpa.JpaUtil;
import com.fiap.school.entity.school.Discipline;
import com.fiap.school.entity.school.Exam;

public class ExamDao {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Exam> getExamsByDiscipline(Discipline discipline) {
		try {
			em = JpaUtil.getEntityManager();

			List<Exam> exams = em
					.createQuery("From Exam Where discipline = :discipline")
					.setParameter("discipline", discipline).getResultList();

			if (exams != null && exams.size() > 0)
				return exams;

		} catch (Exception ex) {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

}
