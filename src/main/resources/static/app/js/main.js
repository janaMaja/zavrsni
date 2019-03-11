var rentApp = angular.module("rentApp", ["ngRoute"]);


rentApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/automobili.html'
		})
		.when('/automobili/edit/:id', {
			templateUrl : '/app/html/edit-automobil.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

rentApp.controller("automobiliCtrl", function($scope, $http, $location){
	var urlAutomobili = "/api/automobili";
	var urlKompanije = "/api/kompanije";
	
	$scope.automobili = [];
	$scope.kompanije = [];
	
	$scope.pageNum = 0;
    $scope.totalPages = 0;
	
	$scope.novAutomobil = {};
	$scope.novAutomobil.model = "";
	$scope.novAutomobil.registracija = "";
	$scope.novAutomobil.godiste = "";
	$scope.novAutomobil.potrosnja = "";
	$scope.novAutomobil.kompanijaId = "";
	
	$scope.trazeniAutomobil = {};
	$scope.trazeniAutomobil.model = "";
	$scope.trazeniAutomobil.godiste = "";
	$scope.trazeniAutomobil.potrosnja = "";
	
	
	var getAutomobili = function() {
		
		var config = {params: {}};

        config.params.pageNum = $scope.pageNum;
        
        if($scope.trazeniAutomobil.model != ""){
        	config.params.model = $scope.trazeniAutomobil.model;
        }
        if($scope.trazeniAutomobil.godiste != ""){
        	config.params.godiste = $scope.trazeniAutomobil.godiste;
        }
        if($scope.trazeniAutomobil.potrosnja != ""){
        	config.params.potrosnja = $scope.trazeniAutomobil.potrosnja;
        }
		
		$http.get(urlAutomobili, config).then(
				function success(res) {
					$scope.automobili = res.data;
            		$scope.totalPages = res.headers('totalPages');
				},
				function error(res) {
					alert("Neuspesno dobavljanje automobila!");
				});
	};
	
	
	var getKompanije = function() {
		var promise = $http.get(urlKompanije);
		promise.then(
				function success(res) {
					$scope.kompanije = res.data;
				},
				function error(res) {
					alert("Neuspesno dobavljanje kompanija!");
				});
	};
	getKompanije();
	getAutomobili();
	
	  $scope.nazad = function(){
	        if($scope.pageNum > 0) {
	            $scope.pageNum = $scope.pageNum - 1;
	            getAutomobili();
	        }
	    };

	    $scope.napred = function(){
	        if($scope.pageNum < $scope.totalPages - 1){
	            $scope.pageNum = $scope.pageNum + 1;
	            getAutomobili();
	        }
	    };
	
	$scope.dodaj = function() {
		$http.post(urlAutomobili, $scope.novAutomobil).then(
				function success(res) {
					getAutomobili();
					
					$scope.novAutomobil.model = "";
					$scope.novAutomobil.registracija = "";
					$scope.novAutomobil.godiste = "";
					$scope.novAutomobil.potrosnja = "";
					$scope.novAutomobil.kompanijaId = "";
				},
				function error(res) {
					alert("Neuspjesno dodavanje!");
				});
	}
	
	$scope.trazi = function() {
		$scope.pageNum = 0;
		getAutomobili();
	}
	
	$scope.obrisi = function(id) {
		$http.delete(urlAutomobili + "/" + id).then(
				function success(res) {
					getAutomobili();
				},
				function errror() {
					alert("Neuspjesno brisanje!");
				});
	}
	
	$scope.izmeni = function(id) {
		$location.path("/automobili/edit/" + id);
	}
	
    $scope.iznajmi = function(id){
    	$http.post(urlAutomobili + "/" + id + "/najam").then(
    		function success(data){
    			alert("Automobil je uspesno iznajmljen.");
    			getAutomobili();
    		},
    		function error(data){
    			alert("Nije uspelo iznajmljivanje")
    		}
    	)
    }
});

rentApp.controller("editAutomobilCtrl", function($scope, $http, $routeParams, $location){
	var urlAutomobil = "/api/automobili";
	
	$scope.stariUtomobil = null;
	
	var getStariAutomobil = function() {
		$http.get(urlAutomobil + "/" + $routeParams.id).then(
				function success(res) {
					$scope.stariUtomobil = res.data;
				},
				function error(res) {
					alert("Neuspesno dobavljanje automobila!!")
				});
	}
	getStariAutomobil();
	
	$scope.izmeni = function() {
		$http.put(urlAutomobil + "/" + $scope.stariUtomobil.id, $scope.stariUtomobil).then(
				function success(res) {
					alert("Uspesno izmenjen automobil!");
					$location.path("/");
				},
				function error(res) {
					alert("Neuspesna izmena automobila");
				});
	}
});


