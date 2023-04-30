var app = angular.module("user-app", []);
app.controller("user-ctrl", function($scope, $http) {
	var BACKEND_ADDRESS = "http://localhost:8080";
	var GET_ALL = "/admin/user/getAll";

	$scope.data = {};
	$scope.color = '';
	$scope.message = '';

	$scope.getAll = function() {
		$http.get(BACKEND_ADDRESS + GET_ALL).then(function(res) {
			$scope.color = 'success';
			$scope.message = res.data.message;
			$scope.data = res.data.data;
		}, function(error) {
			$scope.message = error.data.message;
			$scope.color = 'danger';
		});
	};
});