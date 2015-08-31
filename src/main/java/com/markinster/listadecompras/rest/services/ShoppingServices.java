package com.markinster.listadecompras.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.markinster.listadecompras.models.Product;
import com.markinster.listadecompras.models.ShoppingList;
import com.markinster.listadecompras.repositories.IRepository;
import com.markinster.listadecompras.repositories.ProductRepository;
import com.markinster.listadecompras.repositories.ShoppingListRepository;

@Path("/shopping")
public class ShoppingServices {

	IRepository<ShoppingList> repo = new ShoppingListRepository();
	IRepository<Product> pRepo = new ProductRepository();

	//retorna todas a listas cadastradas
	@GET @Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingList> getAllShooppingList() {
		// neste caso ira retornar todas as listas de compras
		return repo.getAll();
	}
	
	//busca uma lista de compras pelo ID
	@GET @Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingList getShooppingListById(@PathParam("id") String id_param) {
		Long id = 0l;
		try {
			id = Long.valueOf(id_param);
		} catch (Exception e) {
			return null;
		}
		ShoppingList shoppingList = repo.getById(id);
		for (Product product : shoppingList.getItems()) {
			product.setShoppingList(null);
		}
		return shoppingList;
	}

	
	//Cria uma nova lista de compras
	@POST @Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createShooppingList(String name) {

		//se ja foi cadastrada uma lista com este nome então vaza fora
		if (repo.getByText(name) != null)
			return;		
		
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setName(name);
		repo.persist(shoppingList);
	}
	
	//Remove uma lista de compras pelo ID
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void removeShooppingList(@PathParam("id") String id) {

		Long id_product = 0l;
		try {
			id_product = Long.valueOf(id);
		} catch (Exception e) {
			return;
		}
		repo.removeById(id_product);
	}
	
	//Retorna todos os produtos de uma lista de compras
	@GET @Path("/{id}/products")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts(@PathParam("id") int id) {
		
		long idl = id;
		
		// neste caso vai retornar os produtos da lista do id
		ShoppingList listaDeCompras = (ShoppingList) repo.getById(idl);
		for (Product product : listaDeCompras.getItems())
			product.setShoppingList(null);

		return listaDeCompras.getItems();
	}

	//altera um produto de uma lista de compras
	@POST @Path("/product/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void alterarProduct(Product product) {
		Product newPro = (Product) pRepo.getById(product.getId());
		newPro.setPrice(product.getPrice());
		newPro.setQuant(product.getQuant());
		pRepo.persist(newPro);
	}
	

	//marca um produto como comprado
	@POST @Path("/product/checked")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void setProductChecked(String id) {

		Long id_product = 0l;
		try {
			id_product = Long.valueOf(id);
		} catch (Exception e) {
			return;
		}

		Product product = (Product) pRepo.getById(id_product);
		product.setChecked(!product.getChecked());

		if (product != null)
			pRepo.persist(product);
	}

	//Insere um produto em uma lista de compras
	@POST @Path("/product/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void inserirProduct(Product product) {

		product.setChecked(false);

		ShoppingList shoppingList = (ShoppingList) repo.getById(product.getShoppingList().getId());
		product.setShoppingList(shoppingList);

		pRepo.persist(product);
	}
	
	
	
	//remove um produto de uma lista de compras
	@DELETE @Path("/product/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void removeProduct(@PathParam("id") String id) {

		Long id_product = Long.valueOf(id);
		pRepo.removeById(id_product);
	}

}
