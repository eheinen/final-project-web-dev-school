package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.beans.PersonDao;
import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.Address;
import com.fiap.school.entity.enums.PersonProfile;
import com.fiap.school.entity.enums.Profile;
import com.fiap.school.entity.person.Teacher;
import com.fiap.school.entity.person.User;
import com.fiap.school.entity.school.School;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "teacherMB")
@ViewScoped
public class TeacherMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Teacher teacher;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeacherMB() {
		teacher = new Teacher();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createTeacher() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Teacher> teaDao = new GenericDao<Teacher>(Teacher.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				GenericDao<User> usrDao = new GenericDao<User>(User.class);

				if (teacher.getId() != 0) {
					teaDao.update(teacher);
					addDao.update(teacher.getAddress());
					messages.setUpdateMessage("Teacher");
				} else {
					List<School> schools = new SchoolMB().getSchoolList();
					if (schools == null || schools.size() == 0) {
						messages.setMessage("warning", "You need to create a school first!");
					} else {
						User teacherUser = new User(teacher.getCpf(), teacher.getCpf(), Profile.TEACHER);
						usrDao.insert(teacherUser);
						teacher.setUser(teacherUser);

						addDao.insert(teacher.getAddress());
						teacher.setAddress(teacher.getAddress());

						teacher.setPersonProfile(PersonProfile.TEACHER);
						teaDao.insert(teacher);
						messages.setInsertMessage("Teacher");
					}
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		teacher = new Teacher();
		return null;
	}

	public List<Teacher> getTeacherList() {
		try {
			messages.resetMessage();
			return new PersonDao().getTeacherList();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeTeacher() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (teacher != null) {
				GenericDao<Teacher> teaDao = new GenericDao<Teacher>(Teacher.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				Address address = teaDao.search(id).getAddress();
				teaDao.delete(id);
				addDao.delete(address.getId());

				messages.setDeleteMessage("Teacher");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		teacher = new Teacher();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (teacher != null || id != 0) {
				GenericDao<Teacher> teaDao = new GenericDao<Teacher>(Teacher.class);
				teacher = teaDao.search(id);
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
			fields.add(teacher.getName());
			fields.add(teacher.getCpf());
			fields.add(teacher.getGender());
			fields.add(teacher.getTelephone());
			fields.add(teacher.getEmail());
			fields.add(teacher.getAddress().getCountry());
			fields.add(teacher.getAddress().getState());
			fields.add(teacher.getAddress().getCity());
			fields.add(teacher.getAddress().getAddress());
			fields.add(teacher.getSalaryPerHour());
			fields.add(teacher.getWeeklyHour());
			fields.add(teacher.getEngagedStartDate());
			fields.add(teacher.getSchool().getName());

			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
