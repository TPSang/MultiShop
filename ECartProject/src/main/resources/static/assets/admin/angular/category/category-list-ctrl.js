var app = angular.module("category-app", []);
app.controller("category-ctrl", function($scope, $http) {
	var BACKEND_ADDRESS = "http://localhost:8080";
	var GET_ALL = "/admin/categories/getAll";
	var DELETE = "/admin/categories/delete";

	$scope.data = {};
	$scope.color = '';
	$scope.message = '';
	$scope.message_warning = '';

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

	$scope.update = function (item) {
        var path = "/admin/categories/update/" + item.id;
        $("a").attr("href", path);
      };

	$scope.getID = function(item) {
		$scope.delete = function() {
			$http.post(BACKEND_ADDRESS + DELETE, item.id).then(function(res) {
				$scope.message_warning = res.data.message;
				$scope.getAll();
			}, function(error) {
				$scope.message_warning = error.data.message;
				$scope.getAll();
			});
		};
	};

});