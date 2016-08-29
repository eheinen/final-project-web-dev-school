package com.fiap.school.entity.person;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiap.school.entity.enums.Status;
import com.fiap.school.entity.school.Course;
import com.fiap.school.entity.school.Exam;
import com.fiap.school.entity.school.ExamResult;
import com.fiap.school.entity.school.School;
import com.fiap.school.entity.school.SchoolClass;

@Entity
@Table(name = "students")
public class Student extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "STATUS")
	private Status status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_ID")
	private School school = new School();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSE_ID")
	private Course course = new Course();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_CLASS_ID")
	private SchoolClass schoolClass = new SchoolClass();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "student")
	private Set<ExamResult> examResults = new LinkedHashSet<ExamResult>();
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<ExamResult> getExamResults() {
		return examResults;
	}

	public void setExamResults(Set<ExamResult> examResults) {
		this.examResults = examResults;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
