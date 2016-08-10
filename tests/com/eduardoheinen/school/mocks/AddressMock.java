package com.eduardoheinen.school.mocks;

import com.eduardoheinen.school.entity.Address;

public class AddressMock {

	public Address getMock(){
		Address mock = new Address();
		mock.setCountry("Brasil");
		mock.setState("SP");
		mock.setCity("São Paulo");
		mock.setAddress("Rua Vergueiro 961");
		return mock;
	}
	
}
