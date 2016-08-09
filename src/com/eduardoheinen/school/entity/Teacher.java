package com.eduardoheinen.school.entity;

import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="BIRTHDAY")
	private String birthday;
	
	@Column(name="TELEPHONE")
	private String telephone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="SALARY")
	private Currency salary;

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Currency getSalary() {
		return salary;
	}

	public void setSalary(Currency salary) {
		this.salary = salary;
	}
}
