
app.controller('ListaController', function ($scope, $http, $resource) {
	
	$scope.message;
	$scope.listaDeCompras
	$scope.id;
	
	$scope.productName;
	$scope.productQuant;
	$scope.productPrice;	
	
		
	//pegando o ID passado por parametro
	var foo =location.search.split("?"); 
	var bar = foo[1].split("="); 	
	$scope.id = bar[1];	
		
	
	//lista produtos da lista de compras
	listar();		
	
	//grava alterações de quantidade e preçp
	$scope.alterar = function (id, quant, price) {
				
		dataObj = '{\"id\":' + id + ', \"price\":' + price + ', \"quant\":' + quant + '}';
		
		var res = $http.post('/compras/rest/shoplist/product/update', dataObj);
		res.success(function(status) {
			listar();
		});		
	}	
	
	// altera do status de comprado
	$scope.setChecked = function (id) {			
		var res = $http.post('/compras/rest/shoplist/product/checked', id);
		res.success(function(status) {
			listar();
		});		
	}	
	
	// altera do status de comprado
	$scope.deletar = function (aid) {	
		var resource = $resource('/compras/rest/shoplist/product/delete/:id');	
		
		resource.delete({id: aid}, function(status) {
			listar();
		});
	}
	
	
	//Insere um novo produto
	$scope.cadastrar = function () {	
		
		if (!formularioValido()) {
			$scope.alertType = "alert-danger";
			return;
		}
		$scope.alertType = null;
		
		dataObj = '{\"id\":' + null + 
					', \"name\":\"'+ $scope.productName + '\"' +
					', \"price\":' + $scope.productPrice + 
					', \"quant\":' + $scope.productQuant + 
					', \"shopList\": {\"id\": ' + $scope.id +
					'}}';
		
		
		var res = $http.post('/compras/rest/shoplist/product/insert', dataObj);
		res.success(function(status) {
			listar();
		});		
		
		$scope.productName = null;
		$scope.productPrice = null;
		$scope.productQuant = null;

		$scope.message = null;
	}	
	
	
	function formularioValido() {
		if ($scope.productName == null) {
			$scope.message = 'O campo PRODUTO deve ser preenchido!';
			return false;
		}
		
		if ($scope.productQuant == null || $scope.productQuant <= 0) {
			$scope.message = 'O campo QUANTIDADE deve ser maior que zero!';
			return false;
		}
		
		if ($scope.productPrice == null || $scope.productPrice <= 0) {
			$scope.message = 'O campo PREÇO deve ser maior que zero!';
			return false;
		}
			
		return true;
	}

	
	function listar() {
		var resLista = $resource('/compras/rest/shoplist/:id');	
		
		resLista.get({id: $scope.id}, function(retorno) {
			$scope.listaDeCompras = retorno
		});
	}
	
});
