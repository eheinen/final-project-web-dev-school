package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.school.ExamResult;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "examResultMB")
@ViewScoped
public class ExamResultMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExamResult examResult;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public ExamResult getExamResult() {
		return examResult;
	}

	public void setExamResult(ExamResult examResult) {
		this.examResult = examResult;
	}

	public ExamResultMB() {
		examResult = new ExamResult();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createExamResult() throws IOException {
		try {
			//int studentId = Integer.parseInt(Contexts.getParameter("student-id"));
			//examResult.setStudent(new GenericDao<Student>(Student.class).search(studentId));
			
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<ExamResult> exaDao = new GenericDao<ExamResult>(ExamResult.class);

				if (examResult.getId() != 0) {
					exaDao.update(examResult);
					messages.setUpdateMessage("Exam Result");
				} else {
					exaDao.insert(examResult);
					messages.setInsertMessage("Exam Result");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		examResult = new ExamResult();
		return null;
	}

	public List<ExamResult> getExamResultList() {
		try {
			messages.resetMessage();
			return new GenericDao<ExamResult>(ExamResult.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeExamResult() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (examResult != null) {
				GenericDao<ExamResult> exaDao = new GenericDao<ExamResult>(ExamResult.class);
				exaDao.delete(id);

				messages.setDeleteMessage("Exam Result");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		examResult = new ExamResult();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (examResult != null || id != 0) {
				GenericDao<ExamResult> exaDao = new GenericDao<ExamResult>(ExamResult.class);
				examResult = exaDao.search(id);
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
			fields.add(examResult.getGrade());
			fields.add(examResult.getDate());			
			fields.add(examResult.getStudent().getName());
			fields.add(examResult.getExam().getName());

			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
