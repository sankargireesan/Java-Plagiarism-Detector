(function() {
    angular
        .module("PDApp")
        .controller("RegisterController", RegisterController);


    function RegisterController($location, UserService) {

        var vm = this;
        vm.createUser = createUser;

        function createUser(email, password, vpassword) {
            window.alert("in func");
            if (password === vpassword) {
                var user = {
                    //_id: (new Date).getTime(),
                    email: email,
                    password: password

                };

                UserService.createUser(user)
                    .then(function (response) {
                        var success = response.data;
                        console.log("harshil");
                        if (success) {
                            console.log("Kuch aaya");
                            $location.url("/user/" + success._id)
                        }
                        else {
                            $location.url("/login");
                        }

                    })
            }
            else {
                vm.error = "Passwords do not match";
            }
        }
    }

})();