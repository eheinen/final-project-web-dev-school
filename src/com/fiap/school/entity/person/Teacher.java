package com.fiap.school.entity.person;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiap.school.entity.school.Classroom;
import com.fiap.school.entity.school.SchoolClass;

@Entity
@Table(name = "teachers")
public class Teacher extends Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOL_CLASS_ID")
	private SchoolClass schoolClass;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
	private Set<Classroom> classrooms = new LinkedHashSet<Classroom>();
	
	public Teacher(){
		super();
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
