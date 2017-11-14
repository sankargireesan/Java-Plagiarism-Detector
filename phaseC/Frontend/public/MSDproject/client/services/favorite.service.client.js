(function () {
    angular
        .module("PDApp")
        .factory("FavoriteService", FavoriteService );

    function FavoriteService($http) {


        var api = {

            findThisFavoriteByUserId: findThisFavoriteByUserId,
            unFavoriteMovie: unFavoriteMovie,
            favoriteMovie: favoriteMovie,
            findAllFavoriteByUserId: findAllFavoriteByUserId,
            findAllFavoriteByMovieId: findAllFavoriteByMovieId,
            addMovie : addMovie,
            findMovie: findMovie,
            deleteFavoriteByUserId: deleteFavoriteByUserId
        };

        return api;

        function addMovie(movie) {
            return $http.post("/api/project/movie/user/viewed", movie);
        }

       function favoriteMovie(userId, movie) {

            return $http.post("/api/user/" + userId  + "/favorite", movie);

        }

        function findMovieByName(movieId) {
            return $http.get("/api/user/" + movieId);
        }

        function findAllFavoriteByUserId(userId) {
            return $http.get("/api/user/fetchFavoriteMovie/" + userId);
        }

        function findAllFavoriteByMovieId(movieId) {
            return $http.get("/api/user/movie/" + movieId);
        }

        function findThisFavoriteByUserId(userId, movieId) {
            return $http.get("/api/user/checkLike/" + userId + "/movie/" + movieId);
        }

        function unFavoriteMovie(userId, movieId) {
            return $http.delete("/api/user/" + userId + "/removeFavorite/" + movieId);
        }

        function findMovie(movieId) {
            return $http.get("/api/user/movie/" +movieId + "/imdbId");
        }

        function deleteFavoriteByUserId(userId) {
            return $http.delete("/api/unFavorite/user/movie/" + userId);
        }


    }})();