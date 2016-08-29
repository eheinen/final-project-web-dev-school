package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.beans.DisciplineDao;
import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.person.User;
import com.fiap.school.entity.school.Course;
import com.fiap.school.entity.school.Discipline;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "discMB")
@ViewScoped
public class DisciplineMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Discipline discipline;
	private Messages messages;
	private Actions actions;

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

	public DisciplineMB() {
		discipline = new Discipline();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createDiscipline() throws IOException {
		try {
			if (Validator.hasRequiredField(requiredFiedls())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Discipline> discDao = new GenericDao<Discipline>(Discipline.class);

				if (discipline.getId() != 0) {
					discDao.update(discipline);
					messages.setUpdateMessage("Discipline");
				} else {
					List<Course> courses = new CourseMB().getCourseList();
					if (courses == null || courses.size() == 0) {
						messages.setMessage("warning", "You need to create a course first!");
					} else {
						discDao.insert(discipline);
						messages.setInsertMessage("Discipline");
					}
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		discipline = new Discipline();
		return null;
	}

	public List<Discipline> getDisciplineList() {
		try {
			messages.resetMessage();
			return new GenericDao<Discipline>(Discipline.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeDiscipline() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (discipline != null) {
				GenericDao<Discipline> discDao = new GenericDao<Discipline>(Discipline.class);
				discDao.delete(id);

				messages.setDeleteMessage("Discipline");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		discipline = new Discipline();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (discipline != null || id != 0) {
				GenericDao<Discipline> discDao = new GenericDao<Discipline>(Discipline.class);
				discipline = discDao.search(id);
			}
			messages.resetMessage();
			actions.setEditAction();
			return null;
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
			return null;
		}
	}

	public List<Object> requiredFiedls() {
		try {
			List<Object> fields = new ArrayList<>();
			fields.add(discipline.getName());
			fields.add(discipline.getCourse().getName());
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

	public List<Discipline> getStudentDisciplines() {
		User user = new UserMB().getUserAuthenticated();
		List<Discipline> disciplines = new DisciplineDao().getStudentDisciplines(user);
		if (disciplines == null) {
			disciplines = new ArrayList<>();
		}
		return disciplines;
	}

}
