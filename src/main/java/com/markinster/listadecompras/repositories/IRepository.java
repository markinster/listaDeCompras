package com.markinster.listadecompras.repositories;

import java.util.List;

public interface IRepository<T> {
	
	public T persist(T entity);
	public T getById(Long id);
	public T getByText(String text);
	public List<T> getAll();
	public void removeById(Long id);

}
