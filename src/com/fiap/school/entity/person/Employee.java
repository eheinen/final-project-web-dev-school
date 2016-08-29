package com.fiap.school.entity.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fiap.school.entity.school.School;

@Entity
@Table(name = "employees")
public class Employee extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SALARY_PER_HOUR")
	private double salaryPerHour;

	@Column(name = "WEEKLY_HOUR")
	private float weeklyHour;

	@Column(name = "ROLE", length = 50)
	private String role;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_ID")
	private School school = new School();
	
	public Employee(){
		super();
	}

	public double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}

	public float getWeeklyHour() {
		return weeklyHour;
	}

	public void setWeeklyHour(float weeklyHour) {
		this.weeklyHour = weeklyHour;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
