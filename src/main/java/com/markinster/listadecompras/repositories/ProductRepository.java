package com.markinster.listadecompras.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.markinster.listadecompras.models.Product;

public class ProductRepository extends Repository<Product> {
	
	@Override
	public Product getById(Long id) {
		EntityManager manager = getEntityManager();
		return manager.find(Product.class, id);
	}

	@Override
	public void removeById(Long id) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createQuery("DELETE FROM Product p WHERE p.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		manager.getTransaction().commit();
	}

	@Override
	public Product getByText(String text) {
		return null;
	}

	@Override
	public List<Product> getAll() {
		return null;
	}


}
