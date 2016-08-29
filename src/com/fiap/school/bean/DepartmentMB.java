package com.fiap.school.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.dao.jpa.GenericDao;
import com.fiap.school.entity.school.Department;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "departMB")
@ViewScoped
public class DepartmentMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Department department;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DepartmentMB() {
		department = new Department();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}

	public String createDepartment() throws IOException {
		try {
			if (Validator.hasRequiredField(department.getName())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Department> dao = new GenericDao<Department>(Department.class);
				if (department.getId() != 0) {
					dao.update(department);
					messages.setUpdateMessage("Department");
				} else {
					dao.insert(department);
					messages.setInsertMessage("Department");
				}
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		department = new Department();
		return null;
	}

	public List<Department> getDepartmentList() {
		try {
			messages.resetMessage();
			return new GenericDao<Department>(Department.class).list();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeDepartment() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (department != null) {
				GenericDao<Department> dao = new GenericDao<Department>(Department.class);
				dao.delete(id);
				messages.setDeleteMessage("Department");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		department = new Department();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (department != null || id != 0) {
				GenericDao<Department> dao = new GenericDao<Department>(Department.class);
				department = dao.search(id);
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
