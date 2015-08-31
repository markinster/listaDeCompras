package com.markinster.listadecompras.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Repository<T> implements IRepository<T>{
	
	
	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("listadecompras");
	}

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	@Override
	public T persist(T entity) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		T t = manager.merge(entity);
		manager.getTransaction().commit();
		return t;
	}

}
