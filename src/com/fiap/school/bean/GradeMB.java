package com.fiap.school.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.beans.ExamResultDao;
import com.fiap.school.entity.person.Person;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.school.Discipline;
import com.fiap.school.entity.school.ExamResult;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Messages;

@ManagedBean(name = "gradeMB")
@ViewScoped
public class GradeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Discipline discipline;
	private Messages messages;
	private Actions actions;

	public GradeMB() {
		discipline = new Discipline();
		messages = new Messages();
		actions = new Actions();
		actions.setSearchAction();
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

	public List<ExamResult> getExamResults() {
		Person person = new UserMB().getPersonAuthenticated();
		Student student = null;
		List<ExamResult> examResults = null;
		
		if (person != null && person.getClass().getSimpleName().equals("Student")) {
			student = (Student) new UserMB().getPersonAuthenticated();
			examResults = new ExamResultDao().getStudentGrades(student, discipline);
		} else {
			if(discipline == null || discipline.getName() == null || discipline.getName().isEmpty())
				examResults = new ExamResultMB().getExamResultList();
			else
				examResults = new ExamResultDao().getGradesByDiscipline(discipline);
		}
		
		return examResults;
	}
}
