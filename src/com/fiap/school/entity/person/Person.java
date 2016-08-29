package com.fiap.school.entity.person;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fiap.school.entity.Address;
import com.fiap.school.entity.enums.Gender;
import com.fiap.school.entity.enums.PersonProfile;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME", length = 45)
	private String name;

	@Column(name = "CPF", length = 14)
	private String cpf;
	
	@Column(name = "GENDER")
	private Gender gender;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDATE")
	private Date birthdate;

	@Column(name = "TELEPHONE", length = 20)
	private String telephone;

	@Column(name = "EMAIL", length = 60)
	private String email;
	
	@Column(name = "PERSON_PROFILE")
	private PersonProfile personProfile;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ENGAGED_START_DATE")
	private Date engagedStartDate;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ENGAGED_END_DATE")
	private Date engagedEndDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address = new Address();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user = new User();
	
	public Person(){
		super();
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PersonProfile getPersonProfile() {
		return personProfile;
	}

	public void setPersonProfile(PersonProfile personProfile) {
		this.personProfile = personProfile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getEngagedStartDate() {
		return engagedStartDate;
	}

	public void setEngagedStartDate(Date engagedStartDate) {
		this.engagedStartDate = engagedStartDate;
	}

	public Date getEngagedEndDate() {
		return engagedEndDate;
	}

	public void setEngagedEndDate(Date engagedEndDate) {
		this.engagedEndDate = engagedEndDate;
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
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
