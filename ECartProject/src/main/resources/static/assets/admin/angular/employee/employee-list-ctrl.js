var app = angular.module("employee-app", []);
app.controller("employee-ctrl", function($scope, $http) {
	var BACKEND_ADDRESS = "http://localhost:8080";
	var GET_ALL = "/admin/employee/getAll";

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

	$scope.update = function(item) {
		if (item.user.email == $("#username").val()) {
			$scope.color = 'danger';
			$scope.message = "Bạn không được phép cập nhật tài khoản đang đăng nhập!";
			$("#modalInfo").modal("show");
		} else {
			var path = "/admin/employees/update/" + item.user.id;
			$("a").attr("href", path);
		}
	};

});