package com.fiap.school.entity.school;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fiap.school.entity.enums.EducationType;
import com.fiap.school.entity.enums.SchoolClassStatus;
import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.person.Teacher;

@Entity
@Table(name = "school_classes")
public class SchoolClass implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME", length = 15)
	private String name;

	@Column(name = "STATUS")
	private SchoolClassStatus status;

	@Column(name = "EDUCATION_TYPE")
	private EducationType educationType;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	private Date startDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "END_DATE")
	private Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSE_ID")
	private Course course = new Course();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_ID")
	private School school = new School();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolClass")
	private Set<Discipline> disciplines = new LinkedHashSet<Discipline>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolClass")
	private Set<Teacher> teachers = new LinkedHashSet<Teacher>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolClass")
	private Set<Student> students = new LinkedHashSet<Student>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolClass")
	private Set<Classroom> classrooms = new LinkedHashSet<Classroom>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public SchoolClassStatus getStatus() {
		return status;
	}

	public void setStatus(SchoolClassStatus status) {
		this.status = status;
	}

	public EducationType getEducationType() {
		return educationType;
	}

	public void setEducationType(EducationType educationType) {
		this.educationType = educationType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolClass other = (SchoolClass) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
