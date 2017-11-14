(function(){
    angular
        .module("PDApp")
        .controller("LoginController", LoginController);

    function LoginController($location, UserService) {
        var vm = this;


        vm.login=function (username,password) {
            console.log(username + " : " + password);
            UserService
                .findUserByCredentials(username,password)
                .then(function (response) {
                    var user=response.data;
                    if(user){

                        $location.url("/user/"+user._id);
                    }
                    else{
                        vm.error="User not found";
                    }
                });


        }
    }
window.alert("I am here");
})();