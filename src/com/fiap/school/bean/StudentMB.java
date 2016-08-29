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
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.person.User;
import com.fiap.school.entity.school.Course;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "studentMB")
@ViewScoped
public class StudentMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Student student;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentMB() {
		student = new Student();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createStudent() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Student> stuDao = new GenericDao<Student>(Student.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				GenericDao<User> usrDao = new GenericDao<User>(User.class);

				if (student.getId() != 0) {
					stuDao.update(student);
					addDao.update(student.getAddress());
					messages.setUpdateMessage("Student");
				} else {
					List<Course> courses = new CourseMB().getCourseList();
					if (courses == null || courses.size() == 0) {
						messages.setMessage("warning", "You need to create a course first!");
					} else {
						User studentUser = new User(student.getCpf(), student.getCpf(), Profile.STUDENT);
						usrDao.insert(studentUser);
						student.setUser(studentUser);

						addDao.insert(student.getAddress());
						student.setAddress(student.getAddress());

						student.setPersonProfile(PersonProfile.STUDENT);
						stuDao.insert(student);
						messages.setInsertMessage("Student");
					}
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		student = new Student();
		return null;
	}

	public List<Student> getStudentList() {
		try {
			messages.resetMessage();
			return new PersonDao().getStudentList();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeStudent() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (student != null) {
				GenericDao<Student> stuDao = new GenericDao<Student>(Student.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				Address address = stuDao.search(id).getAddress();
				stuDao.delete(id);
				addDao.delete(address.getId());

				messages.setDeleteMessage("Student");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		student = new Student();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (student != null || id != 0) {
				GenericDao<Student> stuDao = new GenericDao<Student>(Student.class);
				student = stuDao.search(id);
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
			fields.add(student.getName());
			fields.add(student.getCpf());
			fields.add(student.getGender());
			fields.add(student.getTelephone());
			fields.add(student.getEmail());
			fields.add(student.getAddress().getCountry());
			fields.add(student.getAddress().getState());
			fields.add(student.getAddress().getCity());
			fields.add(student.getAddress().getAddress());
			fields.add(student.getSchool().getName());
			fields.add(student.getCourse().getName());
			fields.add(student.getSchoolClass().getName());

			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
