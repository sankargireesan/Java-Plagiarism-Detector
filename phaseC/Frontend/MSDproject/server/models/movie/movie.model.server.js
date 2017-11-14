module.exports = function () {
    var mongoose = require("mongoose");
    var MovieSchema = require("./movie.schema.server.js")();
    var Movie = mongoose.model("Movie", MovieSchema);


    var api = {
        findMovie: findMovie,
        addMovie: addMovie,
        findMovieByMovieId: findMovieByMovieId,
        findMovieByImdbId:findMovieByImdbId
    };
    return api;

    function findMovie(movieId) {
        console.log("aaaya bhai");
        return  Movie.findOne({ imdbId: movieId});

    }

    function addMovie(movie) {
        return Movie.create(movie);
    }

    function findMovieByMovieId(id) {
        return Movie.findOne({_id : id});
    }
    function findMovieByImdbId(imdbid) {
        return  Movie.findOne({ imdbId: movieId});
    }

};
