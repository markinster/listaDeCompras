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
		<link href="${css}/bootstrap.min.css" rel="stylesheet" media="all">	
		<link href="${css}/mine.css" rel="stylesheet">	
		
		<script type="text/javascript">

			window.onload = function () {
				document.getElementById("buscador").focus();
			}
		
		</script>
		
		<meta charset="UTF-8">
		<title>Compras</title>
	</head>

	<body ng-controller="IndexController">
		
		<!-- Logo marca -->
		<header>
			<a href="index"><img src="${img}/logo.png"></a> 
		
			<hr /> 
		</header>

		<p ng-hide="alertType == null" class="alert {{alertType}}">{{message}}</p>
		<div class="corpo">
			<!-- corpo -->				
		
			<div style="margin-left: 2%; margin-right: 50%;" class="input-group"> 
		      <input id="buscador" type="search" class="form-control" placeholder="Buscar por..." ng-model="listaName">
		      <span class="input-group-btn">
		        	<button class="btn btn-success" ng-click="criar()">Criar</button> 
		      </span>
		    </div>  		    
		
			
			<ul style="margin-top: 10px;">
				<h3>Minhas listas de compras</h3>
				<li ng-repeat="lista in listaDeCompras | filter: listaName" style="padding-top: 5px;">
					<a class="btn btn-default" href="/compras/lista?id={{lista.id}}"
						style="width: 60%; text-align: left;"> {{lista.name}} </a>	 
					<button class="btn btn-danger" ng-click="excluir(lista.id)">Excluir</button>				
				</li>
			</ul> 
		</div>

		<footer>
			<hr />
			<p style="margin-left: 10px;">Copyright - Markinster 2015</p>
		</footer>

		<script type="text/javascript" src="${js }/angular.min.js"></script>
		<script type="text/javascript" src="${js }/angular-route.min.js"></script>		
		<script type="text/javascript" src="${js }/angular-resource.min.js"></script>
		<script type="text/javascript" src="${js }/main.js"></script>
		<script type="text/javascript" src="${js }/controllers/IndexController.js"></script>
	</body>

</html>