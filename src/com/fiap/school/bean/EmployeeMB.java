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
import com.fiap.school.entity.person.Employee;
import com.fiap.school.entity.person.User;
import com.fiap.school.helper.Actions;
import com.fiap.school.helper.Contexts;
import com.fiap.school.helper.Messages;
import com.fiap.school.helper.Navigation;
import com.fiap.school.helper.Validator;

@ManagedBean(name = "employeeMB")
@ViewScoped
public class EmployeeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private Messages messages;
	private Actions actions;

	public Actions getActions() {
		return actions;
	}

	public Messages getMessages() {
		return messages;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeMB() {
		employee = new Employee();
		messages = new Messages();
		actions = new Actions();
		actions.setAddAction();
	}
	
	public String createEmployee() throws IOException {
		try {
			if (Validator.hasRequiredField(getRequiredFields())) {
				messages.setRequiredFieldMessage();
			} else {
				GenericDao<Employee> empDao = new GenericDao<Employee>(Employee.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				GenericDao<User> usrDao = new GenericDao<User>(User.class);

				if (employee.getId() != 0) {
					empDao.update(employee);
					usrDao.update(employee.getUser());
					addDao.update(employee.getAddress());
					messages.setUpdateMessage("Employee");
				} else {
					User employeeUser = new User(employee.getCpf(), employee.getCpf(), employee.getUser().getProfile());
					usrDao.insert(employeeUser);				
					employee.setUser(employeeUser);
					
					addDao.insert(employee.getAddress());
					employee.setAddress(employee.getAddress());

					employee.setPersonProfile(PersonProfile.EMPLOYEE);
					empDao.insert(employee);
					messages.setInsertMessage("Employee");
				}				
			}
		} catch (Exception er) {
			messages.setSomethingWentWrongMessage();
		}
		actions.setAddAction();
		employee = new Employee();
		return null;
	}

	public List<Employee> getEmployeeList() {
		try {
			messages.resetMessage();
			return new PersonDao().getEmployeeList();
		} catch (Exception er) {
			return null;
		}
	}

	public void removeEmployee() throws IOException {
		try {
			int id = Contexts.getParameterId();

			if (employee != null) {
				GenericDao<Employee> empDao = new GenericDao<Employee>(Employee.class);
				GenericDao<Address> addDao = new GenericDao<Address>(Address.class);
				Address address = empDao.search(id).getAddress();
				empDao.delete(id);
				addDao.delete(address.getId());
				
				messages.setDeleteMessage("Employee");
			}
		} catch (Exception er) {
			Navigation.redirectToPages("Home", "home");
		}
		employee = new Employee();
	}

	public String prepareToEdit() {
		try {
			int id = Contexts.getParameterId();

			if (employee != null || id != 0) {
				GenericDao<Employee> empDao = new GenericDao<Employee>(Employee.class);
				employee = empDao.search(id);
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
			fields.add(employee.getName());
			fields.add(employee.getCpf());
			fields.add(employee.getGender());
			fields.add(employee.getTelephone());
			fields.add(employee.getEmail());
			fields.add(employee.getAddress().getCountry());
			fields.add(employee.getAddress().getState());
			fields.add(employee.getAddress().getCity());
			fields.add(employee.getAddress().getAddress());
			fields.add(employee.getRole());
			fields.add(employee.getSalaryPerHour());
			fields.add(employee.getWeeklyHour());
			fields.add(employee.getEngagedStartDate());
			fields.add(employee.getSchool().getName());
			
			return fields;
		} catch (Exception ex) {
			return null;
		}
	}

}
