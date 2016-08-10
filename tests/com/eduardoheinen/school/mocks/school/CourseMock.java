package com.eduardoheinen.school.mocks.school;

import com.eduardoheinen.school.entity.school.Course;

public class CourseMock {

	public Course getMock(){
		Course mock = new Course();
		mock.setName("Java, SOA e IoT");
		return mock;
	}
	
}
