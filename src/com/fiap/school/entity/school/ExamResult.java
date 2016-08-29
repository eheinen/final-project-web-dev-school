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

import com.fiap.school.entity.person.Student;

@Entity
@Table(name = "exam_results")
public class ExamResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "GRADE")
	private float grade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EXAM_ID")
	private Exam exam = new Exam();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_ID")
	private Student student = new Student();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
