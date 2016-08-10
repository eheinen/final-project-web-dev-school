package com.eduardoheinen.school.mocks.school;

import com.eduardoheinen.school.entity.school.KnowledgeArea;

public class KnowledgeAreaMock {

	public KnowledgeArea getMock(){
		KnowledgeArea mock = new KnowledgeArea();
		mock.setName("Exatas");
		return mock; 
	}
	
}
