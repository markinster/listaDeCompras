package com.markinster.listadecompras.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.markinster.listadecompras.models.Product;
import com.markinster.listadecompras.models.ShoppingList;

public class ShoppingListRepository extends Repository<ShoppingList> {

	//retorna todas as listas de compras cadastradas
	@Override
	public List<ShoppingList> getAll() {				
		EntityManager manager = getEntityManager();
		TypedQuery<ShoppingList> query = manager.createNamedQuery("ShoppingList.findAll", ShoppingList.class);		
		List<ShoppingList> list = query.getResultList();
		for (ShoppingList s : list) {
			for (Product product : s.getItems()) {
				
				product.setShoppingList(null);
			}
		}
		
		return list;
	}

	//retorna uma lista de compras pelo seu ID
	@Override
	public ShoppingList getById(Long id) {
		EntityManager manager = getEntityManager();
		TypedQuery<ShoppingList> query = manager.createNamedQuery("ShoppingList.findById", ShoppingList.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	

	@Override
	public void removeById(Long id) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("DELETE FROM Product p WHERE p.shoppingList.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		
		query = manager.createQuery("DELETE FROM ShoppingList s WHERE s.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		
		manager.getTransaction().commit();
		
	}

	
	@Override
	public ShoppingList getByText(String name) {
		EntityManager manager = getEntityManager();
		
		TypedQuery<ShoppingList> query = manager.createNamedQuery("ShoppingList.findByName", ShoppingList.class);
		
		query.setParameter("name", name);
		List<ShoppingList> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList.get(0);
		
		return null;
	}

}
