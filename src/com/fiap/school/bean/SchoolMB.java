package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.Address;
import com.fiap.school.entity.school.Department;
import com.fiap.school.entity.school.School;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "schoolMB")
@ViewScoped
public class SchoolMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private School school;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public SchoolMB() {
		school = new School();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createSchool() throws IOException {
		try {
			if (Validator.hasRequiredField(school.getName())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<School> schDao = new GenericDao<School>(School.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);

				if (school.getId() != 0) {
					schDao.update(school);
					addDao.update(school.getAddress());
					messages.setUpdateMessage("School");
				} else {
					List<Department> departments = new DepartmentMB().getDepartmentList();
					if (departments == null || departments.size() == 0) {
						messages.setMessage("warning", "You need to create a departments first!");
					} else {
						addDao.insert(school.getAddress());
						school.setAddress(school.getAddress());

						schDao.insert(school);
						messages.setInsertMessage("School");
					}
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		school = new School();
		return null;
	}

	public List<School> getSchoolList() {
		try {
			messages.resetMessage();
			return new GenericDao<School>(School.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeSchool() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (school != null) {
				GenericDao<School> schDao = new GenericDao<School>(School.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				Address address = schDao.search(id).getAddress();
				schDao.delete(id);
				addDao.delete(address.getId());

				messages.setDeleteMessage("School");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		school = new School();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (school != null || id != 0) {
				GenericDao<School> schDao = new GenericDao<School>(School.class);
				school = schDao.search(id);
			}
			messages.resetMessage();
			actions.setEditAction();
			return null;
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
			return null;
		}
	}

}
