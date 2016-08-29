package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.SchoolClass;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "schoolClassMB")
@ViewScoped
public class SchoolClassMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private SchoolClass schoolClass;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public SchoolClassMB() {
		schoolClass = new SchoolClass();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createSchoolClass() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<SchoolClass> schcDao = new GenericDao<SchoolClass>(SchoolClass.class);

				if (schoolClass.getId() != 0) {
					schcDao.update(schoolClass);
					messages.setUpdateMessage("School Class");
				} else {
					schcDao.insert(schoolClass);
					messages.setInsertMessage("School Class");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		schoolClass = new SchoolClass();
		return null;
	}

	public List<SchoolClass> getSchoolClassList() {
		try {
			messages.resetMessage();
			return new GenericDao<SchoolClass>(SchoolClass.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeSchoolClass() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (schoolClass != null) {
				GenericDao<SchoolClass> schcDao = new GenericDao<SchoolClass>(SchoolClass.class);
				schcDao.delete(id);
				
				messages.setDeleteMessage("School Class");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		schoolClass = new SchoolClass();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (schoolClass != null || id != 0) {
				GenericDao<SchoolClass> schcDao = new GenericDao<SchoolClass>(SchoolClass.class);
				schoolClass = schcDao.search(id);
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
			fields.add(schoolClass.getName());
			fields.add(schoolClass.getStatus());
			fields.add(schoolClass.getEducationType());
			fields.add(schoolClass.getStartDate());
			fields.add(schoolClass.getEndDate());
			fields.add(schoolClass.getCourse().getName());
			fields.add(schoolClass.getSchool().getName());
			
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
