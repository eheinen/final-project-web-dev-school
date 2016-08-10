package com.eduardoheinen.school.mocks.school;

import java.util.HashSet;
import java.util.Set;

import com.eduardoheinen.school.entity.school.School;
import com.eduardoheinen.school.mocks.AddressMock;

public class SchoolMock {

	public School getMock(){
		School mock = new School();
		mock.setName("FIAP");
		mock.setAddress(new AddressMock().getMock());
		return mock;
	}
	
	public Set<School> getMocks(){
		Set<School> mocks = new HashSet<School>();
		
		School mock = new School();
		mock.setName("FIAP I");
		mock.setAddress(new AddressMock().getMock());
		mocks.add(mock);
		
		mock = new School();
		mock.setName("FIAP II");
		mock.setAddress(new AddressMock().getMock());
		mocks.add(mock);
		
		mock = new School();
		mock.setName("FIAP III");
		mock.setAddress(new AddressMock().getMock());
		mocks.add(mock);
		
		return mocks;
	}
	
}
