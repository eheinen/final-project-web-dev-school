package com.fiap.school.helper;

import java.io.Serializable;

public class Actions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String image;
	private String value;

	public Actions(){}
	
	public Actions(String image, String value) {
		super();
		this.image = image;
		this.value = value;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setAddAction(){
		image = "add.png";
		value = "Add";
	}
	
	public void setEditAction(){
		image = "edit.png";
		value = "Edit";
	}
	
	public void setSearchAction(){
		image = "seacrh.png";
		value = "Search";
	}
	
}
