package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Course;
import com.fiap.school.entity.school.School;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "courseMB")
@ViewScoped
public class CourseMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Course course;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseMB() {
		course = new Course();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createCourse() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Course> couDao = new GenericDao<Course>(Course.class);

				if (course.getId() != 0) {
					couDao.update(course);
					messages.setUpdateMessage("Course");
				} else {
					List<School> schools = new SchoolMB().getSchoolList();
					if(schools == null || schools.size() == 0){
						messages.setMessage("warning", "You need to create a school first!");
					} else {
						couDao.insert(course);
						messages.setInsertMessage("Course");
					}
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		course = new Course();
		return null;
	}

	public List<Course> getCourseList() {
		try {
			messages.resetMessage();
			return new GenericDao<Course>(Course.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeCourse() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (course != null) {
				GenericDao<Course> couDao = new GenericDao<Course>(Course.class);
				couDao.delete(id);

				messages.setDeleteMessage("Course");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		course = new Course();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (course != null || id != 0) {
				GenericDao<Course> couDao = new GenericDao<Course>(Course.class);
				course = couDao.search(id);
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
			fields.add(course.getName());
			fields.add(course.getSchool().getName());
			fields.add(course.getKnowledgeArea().getName());
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
