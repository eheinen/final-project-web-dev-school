package com.fiap.school.helper;

import java.io.Serializable;

public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setMessage(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public void setRequiredFieldMessage(){
		type = "warning";
		value = "Ops! You need to fill all required fields";
	}
	
	public void setSomethingWentWrongMessage(){
		type = "fail";
		value = "Ops! Something went wrong";
	}
	
	public void setInsertMessage(String entity){
		type = "success";
		value = entity + " was created successfully!";
	}
	
	public void setUpdateMessage(String entity){
		type = "success";
		value = entity + " was updated successfully!";
	}
	
	public void setDeleteMessage(String entity){
		type = "success";
		value = entity + " was deleted successfully!";
	}
	
	public void resetMessage(){
		type = "";
		value = "";		
	}
}
