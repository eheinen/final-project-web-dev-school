package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Exam;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "examMB")
@ViewScoped
public class ExamMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Exam exam;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public ExamMB() {
		exam = new Exam();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createExam() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Exam> exaDao = new GenericDao<Exam>(Exam.class);

				if (exam.getId() != 0) {
					exaDao.update(exam);
					messages.setUpdateMessage("Exam");
				} else {
					exaDao.insert(exam);
					messages.setInsertMessage("Exam");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		exam = new Exam();
		return null;
	}

	public List<Exam> getExamList() {
		try {
			messages.resetMessage();
			return new GenericDao<Exam>(Exam.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeExam() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (exam != null) {
				GenericDao<Exam> exaDao = new GenericDao<Exam>(Exam.class);
				exaDao.delete(id);

				messages.setDeleteMessage("Exam");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		exam = new Exam();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (exam != null || id != 0) {
				GenericDao<Exam> exaDao = new GenericDao<Exam>(Exam.class);
				exam = exaDao.search(id);
			}
			messages.resetMessage();
			actions.setEditAction();
			return null;
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
			return null;
		}
	}

	private List<Object> getRequiredFields() {
		try {
			List<Object> fields = new ArrayList<>();
			fields.add(exam.getName());
			fields.add(exam.getPointsPercent());
			fields.add(exam.getSchool().getName());
			fields.add(exam.getCourse().getName());
			fields.add(exam.getSchoolClass().getName());
			fields.add(exam.getTeacher().getName());
			fields.add(exam.getDiscipline().getName());
			
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
