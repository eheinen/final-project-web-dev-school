package com.eduardoheinen.school.mocks.person;

import java.util.Calendar;

import com.eduardoheinen.school.entity.person.Student;
import com.eduardoheinen.school.mocks.AddressMock;

public class StudentMock {

	public Student getMock(){
		Student mock = new Student();
		mock.setName("Eduardo Heinen");
		Calendar birthday = Calendar.getInstance();
		birthday.set(1991, 05, 26);
		mock.setBirthday(birthday.getTime());
		mock.setTelephone("11941102030");
		mock.setEmail("eduardogheinen@gmail.com");
		mock.setAddress(new AddressMock().getMock());
		mock.setUser(new UserMock().getUserStudent());
		return mock;
	}
	
}
