package com.eduardoheinen.school.mocks.person;

import java.util.Calendar;

import com.eduardoheinen.school.entity.person.Teacher;
import com.eduardoheinen.school.mocks.AddressMock;

public class TeacherMock {

	public Teacher getMock(){
		Teacher mock = new Teacher();
		mock.setName("Emilio Celso de Souza");
		Calendar birthday = Calendar.getInstance();
		birthday.set(1980, 04, 11);
		mock.setBirthday(birthday.getTime());
		mock.setTelephone("11912384753");
		mock.setEmail("emilio.souza@gmail.com");
		mock.setSalary(85.55d);
		mock.setAddress(new AddressMock().getMock());
		mock.setUser(new UserMock().getUserTeacher());
		return mock;
	}
	
}
