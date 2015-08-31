<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="pt" ng-app="Compras">

	<head>
		<spring:url value="/resources/css" var="css" />
		<spring:url value="/resources/js" var="js" />
		<spring:url value="/resources/img" var="img" /> 
	
		<link href="${css}/bootstrap.css" rel="stylesheet">
		<link href="${css}/bootstrap.min.css" rel="stylesheet">	
		<link href="${css}/mine.css" rel="stylesheet">	
		
		
		<script type="text/javascript">
			window.onload = function() {
				document.getElementById("produto").focus();
			}
			
		</script>
		
		<meta charset="UTF-8">
		<title>Compras</title>
	</head>

	<body ng-controller="ListaController">
		
		<!-- Logo marca -->
		<header>
			<a href="index" style="margin-left: 2%;"><img src="${img}/logo.png"></a> 
			
			<hr />
		</header>	

		<div class="corpo">
			<p ng-hide="message == null" class="alert {{alertType}}">{{message}}</p>
			<li><h3 class="btn-primary disabled" >{{listaDeCompras.name}} - Total: R$ {{listaDeCompras.total}}</h3></li>			
			<h3><i>Itens</i></h3>
			<div class="form-inline">
			  <div class="form-group">
						<input id="produto" type="search" class="form-control" style="width: 300px;" placeholder="Produto" ng-model="productName" >
						<input type="number" class="form-control" placeholder="Quantidade" style="width: 120px;" ng-model="productQuant" >
						<input type="number" class="form-control" placeholder="Preço" style="width: 120px;" ng-model="productPrice" >
						<button class="btn btn-success" ng-click="cadastrar()" onclick="document.getElementById('produto').focus()">+</button>
			  </div>
	   		</div>
	   		<hr style="margin-left: 1%; margin-right: 40%;" />	   		
	 
	 	
  			<li class="form-inline btn-info disabled">
				<input disabled="disabled" class="btn-info" readonly="readonly" style="border: none; width: 299px; color: white;" value="PRODUTO" /> 
				<input disabled="disabled" class="btn-info" type="text" readonly="readonly" style="border: none; width: 119px; color: white;" value="QUANTIDADE" >
				<input disabled="disabled" class="btn-info" type="text" readonly="readonly" style="border: none; width: 119px; color: white;" value="PREÇO" >
			</li>	
						
			<li ng-repeat="produto in listaDeCompras.items | filter: productName" class="form-inline" style="padding-top: 5px;"> 			
					<input class="form-control" type="text" readonly="readonly" style="width: 300px;" value="{{produto.name}}" />
					<input class="form-control" type="number" style="width: 120px;" value="{{produto.quant}}" ng-model="produto.quant">
					<input class="form-control" type="number" style="width: 120px;" value="{{produto.price}}" ng-model="produto.price">
					<button class="btn btn-default" ng-click="alterar(produto.id, produto.quant, produto.price)">Salvar alteração</button>
					<button class="btn btn-success" ng-show="produto.checked == null || produto.checked == false"
						style="width: 150px;" ng-click="setChecked(produto.id)">Comprar</button> 
					<button class="btn btn-warning" ng-show="produto.checked == true"
						style="width: 150px;" ng-click="setChecked(produto.id)">Cancelar compra</button> 
					<button class="btn btn-danger" ng-click="deletar(produto.id)">Excluir</button>
			</li>	
	 	</div>

		<footer>
			<hr />
			<p style="margin-left: 10px;">Copyright - Markinster 2015</p>
		</footer>

		<script type="text/javascript" src="${js }/angular.min.js"></script>
		<script type="text/javascript" src="${js }/angular-route.min.js"></script>		
		<script type="text/javascript" src="${js }/angular-resource.min.js"></script>
		<script type="text/javascript" src="${js }/main.js"></script>
		<script type="text/javascript" src="${js }/controllers/ListaController.js"></script>
	</body>

</html>