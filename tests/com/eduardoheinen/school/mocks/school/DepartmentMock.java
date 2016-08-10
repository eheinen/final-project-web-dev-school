package com.eduardoheinen.school.mocks.school;

import com.eduardoheinen.school.entity.school.Department;

public class DepartmentMock {

	public Department getMock(){
		Department mock = new Department();
		mock.setName("FIAP Matriz");
		mock.setSchools(new SchoolMock().getMocks());
		return mock;
	}
	
}
