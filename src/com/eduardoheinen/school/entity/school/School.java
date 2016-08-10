package com.eduardoheinen.school.entity.school;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.eduardoheinen.school.entity.Address;

@Entity
public class School {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS_ID")
	private Address address;
	

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
