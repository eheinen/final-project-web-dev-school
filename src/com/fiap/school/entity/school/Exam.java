package com.fiap.school.entity.school;

import java.io.Serializable;
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

import com.fiap.school.entity.person.Student;
import com.fiap.school.entity.person.Teacher;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "POINTS_PERCENT")
	private float pointsPercent;

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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "exam")
	private Set<ExamResult> examResults = new LinkedHashSet<ExamResult>();

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

	public float getPointsPercent() {
		return pointsPercent;
	}

	public void setPointsPercent(float pointsPercent) {
		this.pointsPercent = pointsPercent;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
