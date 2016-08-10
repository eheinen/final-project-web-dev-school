package com.eduardoheinen.school.mocks.person;

import com.eduardoheinen.school.entity.person.Profile;
import com.eduardoheinen.school.entity.person.User;

public class UserMock {

	public User getUserMaster(){
		User mock = new User();
		mock.setUsername("master");
		mock.setPassword("master");
		mock.setProfile(Profile.MASTER);
		return mock;
	}
	
	public User getUserAdmin(){
		User mock = new User();
		mock.setUsername("admin");
		mock.setPassword("admin");
		mock.setProfile(Profile.ADMIN);
		return mock;
	}
	
	public User getUserTeacher(){
		User mock = new User();
		mock.setUsername("teacher");
		mock.setPassword("teacher");
		mock.setProfile(Profile.TEACHER);
		return mock;
	}
	
	public User getUserStudent(){
		User mock = new User();
		mock.setUsername("student");
		mock.setPassword("student");
		mock.setProfile(Profile.STUDENT);
		return mock;
	}
	
}
