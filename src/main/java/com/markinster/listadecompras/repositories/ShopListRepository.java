package com.markinster.listadecompras.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.markinster.listadecompras.models.Product;
import com.markinster.listadecompras.models.ShopList;

public class ShopListRepository extends Repository<ShopList> {

	//retorna todas as listas de compras cadastradas
	@Override
	public List<ShopList> getAll() {				
		EntityManager manager = getEntityManager();
		TypedQuery<ShopList> query = manager.createNamedQuery("ShopList.findAll", ShopList.class);		
		List<ShopList> list = query.getResultList();
		for (ShopList s : list) {
			for (Product product : s.getItems()) {				
				product.setShopList(null);
			}
		}
		
		return list;
	}

	//retorna uma lista de compras pelo seu ID
	@Override
	public ShopList getById(Long id) {
		EntityManager manager = getEntityManager();
		TypedQuery<ShopList> query = manager.createNamedQuery("ShopList.findById", ShopList.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	

	@Override
	public void removeById(Long id) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		
		Query query = manager.createQuery("DELETE FROM Product p WHERE p.shopList.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		
		query = manager.createQuery("DELETE FROM ShopList s WHERE s.id =:id");
		query.setParameter("id", id);
		query.executeUpdate();	
		
		manager.getTransaction().commit();
		
	}

	
	@Override
	public ShopList getByText(String name) {
		EntityManager manager = getEntityManager();
		
		TypedQuery<ShopList> query = manager.createNamedQuery("ShopList.findByName", ShopList.class);
		
		query.setParameter("name", name);
		List<ShopList> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0)
			return resultList.get(0);
		
		return null;
	}

}
