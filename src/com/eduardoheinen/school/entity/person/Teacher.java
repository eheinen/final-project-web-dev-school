package com.eduardoheinen.school.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Teacher extends Person{

	public Teacher(){
		super();
	}
	
	@Column(name="SALARY")
	private double salary;
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
