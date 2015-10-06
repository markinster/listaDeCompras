app.controller('IndexController', function ($scope, $http, $resource) {
		
	$scope.message;
	
	$scope.listaName;
	
	$scope.listaDeCompras = [];	
	
	listar();
	
	
	$scope.criar = function () {	
		
		if ($scope.listaName == null || $scope.listaName == "") {
			
			$scope.message = 'Não é possível criar uma LISTA DE COMPRAS sem nome';
			$scope.alertType = "alert-danger";
			return;
		}
		
		$scope.alertType = null;
		
		var res = $http.post('/compras/rest/shoplist/create', $scope.listaName);
		res.success(function(status) {
			listar();
		});				
	}
	
	$scope.excluir = function (aid) {		
		var resource = $resource('/compras/rest/shoplist/delete/:id');	
		
		resource.delete({id: aid}, function(status) {
			listar();
		});
	}
	
	function listar() {
		$http.get('/compras/rest/shoplist').success(function(retorno) {
			$scope.listaDeCompras = retorno;
		}).error(function(msg) {
			$scope.message = error;
		});	
	}
	
});
