
(function() {
    angular
        .module("PDApp")
        .config(Config);

    function Config($routeProvider) {
        $routeProvider
            .when("/",{
                templateUrl:"client/views/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model"
            })
            .when("/home",{
                templateUrl:"client/views/projecthome.view.client.html",
                controller: "ProjecthomeController",
                controllerAs: "model"
            })
            .when("/login", {
                templateUrl: "client/views/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model"
            })
            .when("/register", {
                templateUrl: "client/views/register.view.client.html",
                controller: "RegisterController",
                controllerAs: "model"
            })
            .when("/user/:uid", {
                templateUrl: "client/views/projecthome.view.client.html",
                controller: "ProjecthomeController",
                controllerAs: "model"
            })

            .when("/user/:uid/reports", {
                templateUrl: "client/views/ownreports.view.client.html",
                controller: "OwnreportsController",
                controllerAs: "model"
            })

            .when("/user/admin/:uid", {
                templateUrl: "client/views/admin.view.client.html",
                controller: "AdminController",
                controllerAs: "model",

            })
            .when("/report/:uid", {
                templateUrl: "client/views/report.view.client.html",
                controller: "ReportController",
                controllerAs: "model"
            })

            .otherwise({
                redirectTo: "/login"
            });
    }
})();