package com.fiap.school.dao.jpa;

import java.util.List;

public interface Dao<T> {
	
	void insert(T entity);
	void update(T entity);
	List<T> list();
	T search(int id);
	void delete(int id);
}
