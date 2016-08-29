package com.fiap.school.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.beans.DisciplineDao;
import com.fiap.school.dao.beans.ExamResultDao;
import com.fiap.school.entity.enums.Status;
import com.fiap.school.entity.person.Person;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.school.Discipline;
import com.fiap.school.entity.school.ExamResult;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Messages;

@ManagedBean(name = "schoolReportMB")
@ViewScoped
public class SchoolReportMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Discipline discipline;
	private float finalGrade;
	private Status status;
	private Messages messages;
	private Actions actions;

	public SchoolReportMB() {
		discipline = new Discipline();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public float getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(float finalGrade) {
		this.finalGrade = finalGrade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ExamResult> getExamResults() {
		new DisciplineDao().getPointsPercentByDiscipline(discipline);
		Person person = new UserMB().getPersonAuthenticated();
		Student student = null;
		List<ExamResult> examResults = null;

		if (person != null && person.getClass().getSimpleName().equals("Student")) {
			student = (Student) new UserMB().getPersonAuthenticated();
			examResults = new ExamResultDao().getStudentGrades(student, discipline);
			finalGrade = calculateFinalGrade(examResults);
			
		} else {
			if (discipline == null || discipline.getName() == null || discipline.getName().isEmpty())
				examResults = new ExamResultMB().getExamResultList();
			else
				examResults = new ExamResultDao().getGradesByDiscipline(discipline);
		}

		return examResults;
	}

	private float calculateFinalGrade(List<ExamResult> examResults) {
		float gradeCalculated = 0f;
		if (examResults == null || examResults.size() == 0) {
			gradeCalculated = 0;
		} else {
			for (ExamResult examResult : examResults) {
				if (examResult != null) {
					if (examResult.getId() > 0) {
						gradeCalculated += examResult.getGrade();
					}
				}
			}
		}

		return gradeCalculated;
	}
	
//	private float checkStudentStatus(List<ExamResult> examResults) {
//		if (examResults == null || examResults.size() == 0) {
//			gradeCalculated = 0;
//		} else {
//			for (ExamResult examResult : examResults) {
//				if (examResult != null) {
//					if (examResult.getId() > 0) {
//						gradeCalculated += examResult.getGrade();
//					}
//				}
//			}
//		}
//
//		return gradeCalculated;
//	}
}
