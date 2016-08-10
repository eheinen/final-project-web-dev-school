package com.eduardoheinen.school.mocks.person;

import java.util.Calendar;

import com.eduardoheinen.school.entity.person.Employee;
import com.eduardoheinen.school.mocks.AddressMock;

public class EmployeeMock {

	public Employee getMock(){
		Employee mock = new Employee();
		mock.setName("Giovani Amorim");
		Calendar birthday = Calendar.getInstance();
		birthday.set(1975, 06, 12);
		mock.setBirthday(birthday.getTime());
		mock.setTelephone("11746283911");
		mock.setEmail("giovani.amorima@gmail.com");
		mock.setSalary(35.55d);
		mock.setRole("Web Design");
		mock.setAddress(new AddressMock().getMock());
		mock.setUser(new UserMock().getUserTeacher());
		return mock;
	}
	
}
