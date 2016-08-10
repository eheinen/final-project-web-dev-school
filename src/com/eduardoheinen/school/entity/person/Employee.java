package com.eduardoheinen.school.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends Person{

	public Employee(){
		super();
	}
	
	@Column(name="SALARY")
	private double salary;
	
	@Column(name="ROLE")
	private String role;
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
