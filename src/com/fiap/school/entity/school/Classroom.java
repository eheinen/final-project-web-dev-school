package com.fiap.school.entity.school;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fiap.school.entity.enums.Shift;
import com.fiap.school.entity.person.Teacher;

@Entity
@Table(name = "classrooms")
public class Classroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "ROOM_NUMBER")
	private int roomNumber;

	@Column(name = "SHIFT")
	private Shift shift;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CLASSDATE")
	private Date classDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_ID")
	private School school = new School();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSE_ID")
	private Course course = new Course();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_CLASS_ID")
	private SchoolClass schoolClass = new SchoolClass();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEACHER_ID")
	private Teacher teacher = new Teacher();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DISCIPLINE_ID")
	private Discipline discipline = new Discipline();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public Date getClassDate() {
		return classDate;
	}

	public void setClassDate(Date classDate) {
		this.classDate = classDate;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
