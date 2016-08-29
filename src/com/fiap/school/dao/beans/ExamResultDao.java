package com.fiap.school.dao.beans;

import java.util.List;

import javax.persistence.EntityManager;

import com.fiap.school.dao.jpa.JpaUtil;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.school.Discipline;
import com.fiap.school.entity.school.Exam;
import com.fiap.school.entity.school.ExamResult;

public class ExamResultDao {
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ExamResult> getStudentGrades(Student student, Discipline discipline) {
		try {
			em = JpaUtil.getEntityManager();

			List<Exam> exams = new ExamDao().getExamsByDiscipline(discipline);
			List<ExamResult> examResults = em
					.createQuery("From ExamResult Where student = :student and exam In :exams")
					.setParameter("student", student).setParameter("exams", exams).getResultList();

			if (examResults != null && examResults.size() > 0)
				return examResults;

		} catch (Exception ex) {
			if (em.isOpen())
				em.close();
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExamResult> getGradesByDiscipline(Discipline discipline) {
		try {
			em = JpaUtil.getEntityManager();

			List<Exam> exams = new ExamDao().getExamsByDiscipline(discipline);
			List<ExamResult> examResults = em
					.createQuery("From ExamResult Where exam In :exams")
					.setParameter("exams", exams).getResultList();

			if (examResults != null && examResults.size() > 0)
				return examResults;

		} catch (Exception ex) {
			if (em.isOpen())
				em.close();
		}

		return null;
	}

}
