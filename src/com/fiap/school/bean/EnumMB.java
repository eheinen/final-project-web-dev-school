package com.fiap.school.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fiap.school.entity.enums.EducationType;
import com.fiap.school.entity.enums.Gender;
import com.fiap.school.entity.enums.Profile;
import com.fiap.school.entity.enums.SchoolClassStatus;
import com.fiap.school.entity.enums.Shift;
import com.fiap.school.entity.enums.Status;

@ManagedBean(name = "enumMB")
@ViewScoped
public class EnumMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public EducationType[] getEducationTypeList() {
		try {
			return EducationType.values();
		} catch (Exception er) {
			return null;
		}
	}
	
	public Gender[] getGenderList() {
		try {
			return Gender.values();
		} catch (Exception er) {
			return null;
		}
	}
	
	public Profile[] getProfileList() {
		try {
			return Profile.values();
		} catch (Exception er) {
			return null;
		}
	}
	
	public Profile[] getProfileEmployeeList(){
		try {
			Profile[] profiles = { Profile.ADMIN, Profile.MASTER, Profile.SCHOOL };
			return profiles;
		} catch (Exception er) {
			return null;
		}
	}
	
	public SchoolClassStatus[] getSchoolClassStatusList() {
		try {
			return SchoolClassStatus.values();
		} catch (Exception er) {
			return null;
		}
	}
	
	public Shift[] getShiftList() {
		try {
			return Shift.values();
		} catch (Exception er) {
			return null;
		}
	}
	
	public Status[] getStatusList() {
		try {
			return Status.values();
		} catch (Exception er) {
			return null;
		}
	}

}
