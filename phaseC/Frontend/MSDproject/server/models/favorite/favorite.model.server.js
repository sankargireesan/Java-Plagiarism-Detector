module.exports = function () {
    var mongoose = require("mongoose");
    var FavoriteSchema = require("./favorite.schema.server.js")();
    var Favorite = mongoose.model("Favorite", FavoriteSchema);


    var api = {
         findFavorite: findFavorite,
         addFavorite: addFavorite,
         findAllFavoriteByUserId: findAllFavoriteByUserId,
         findAllFavoriteByMovieId: findAllFavoriteByMovieId,
         unFavoriteMovie: unFavoriteMovie,
deleteFavorite:deleteFavorite
    };
    return api;

    function findFavorite(userId, movieId) {
        return  Favorite.findOne({"_user": userId, "_movie": movieId});
    }

    function addFavorite(fav) {
        return Favorite.create(fav);
    }

    function findAllFavoriteByUserId(userId) {
        console.log("yaha tak aata hai");
        return Favorite.find({_user: userId});

    }

    function findAllFavoriteByMovieId(movieId) {
        return Favorite.find({_movie: movieId})
            .populate('_user', 'username');
    }

    function unFavoriteMovie(userId, movieId) {
        return Favorite.remove({"_user": userId, "_movie": movieId});
    }

    function deleteFavorite(userId) {
        return Like.remove({"_user" : userId});
    }

};