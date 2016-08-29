package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Classroom;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "classroomMB")
@ViewScoped
public class ClassroomMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Classroom classroom;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public ClassroomMB() {
		classroom = new Classroom();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createClassroom() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Classroom> claDao = new GenericDao<Classroom>(Classroom.class);

				if (classroom.getId() != 0) {
					claDao.update(classroom);
					messages.setUpdateMessage("Classroom");
				} else {
					claDao.insert(classroom);
					messages.setInsertMessage("Classroom");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		classroom = new Classroom();
		return null;
	}

	public List<Classroom> getClassroomList() {
		try {
			messages.resetMessage();
			return new GenericDao<Classroom>(Classroom.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeClassroom() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (classroom != null) {
				GenericDao<Classroom> claDao = new GenericDao<Classroom>(Classroom.class);
				claDao.delete(id);

				messages.setDeleteMessage("Classroom");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		classroom = new Classroom();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (classroom != null || id != 0) {
				GenericDao<Classroom> claDao = new GenericDao<Classroom>(Classroom.class);
				classroom = claDao.search(id);
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
			List<Object> fields = new ArrayList<Object>();
			fields.add(classroom.getRoomNumber());
			fields.add(classroom.getShift());
			fields.add(classroom.getClassDate());
			fields.add(classroom.getSchool().getName());
			fields.add(classroom.getSchoolClass().getName());
			fields.add(classroom.getTeacher().getName());
			fields.add(classroom.getDiscipline().getName());
			fields.add(classroom.getCourse().getName());
			
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
