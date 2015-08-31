package com.markinster.listadecompras.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.markinster.listadecompras.models.Product;
import com.markinster.listadecompras.models.ShoppingList;

public class TesteShoppingList {

	@Test
	public void total_deve_retornar_120() {
		ShoppingList sp = new ShoppingList();
		sp.setId(1l);
		sp.setName("Lista de compras para teste");
		List<Product> items = new ArrayList<Product>();
		Product p = new Product();
		p.setPrice(10.33);
		p.setQuant(3);
		items.add(p);
		
		sp.setItems(items);
		
		Assert.assertEquals(30.99, sp.getTotal(), 0.0001);
	}

}
