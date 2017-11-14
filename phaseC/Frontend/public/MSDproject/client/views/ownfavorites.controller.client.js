(function() {
    angular
        .module("PDApp")
        .controller("OwnfavoriteController", OwnfavoriteController);

    function OwnfavoriteController($routeParams, MovieService, FavoriteService) {
        var vm = this;
        vm.imdbId = $routeParams['movieid'];
        vm.title = $routeParams['movietitle'];
        vm.userId=$routeParams['uid'];
vm.unFavoriteMovie=unFavoriteMovie;


        function init() {
            FavoriteService
                .findAllFavoriteByUserId(vm.userId)
                .then
                (function (response) {

                            vm.favorites = response.data;



                    }, function (error) {
                        vm.error = "No favorites found";
                    })
        }
        init();

        function unFavoriteMovie(uid,mid){
            FavoriteService
                .unFavoriteMovie(uid, mid)
                .then(
                    function (response) {
                        console.log("successfully removed");
                        console.log(response.data);
                    },
                    function (error) {
                        console.log("error");
                    })



        }

    }
})();