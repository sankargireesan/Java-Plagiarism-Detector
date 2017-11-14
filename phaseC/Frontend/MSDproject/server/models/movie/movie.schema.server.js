module.exports = function () {
    var mongoose = require("mongoose");
    var MovieSchema = mongoose.Schema({
        imdbId:String,
        name: String,
        poster: String,
        director: String,
        released: String,
        plot: String,
        actors: String,
        runtime:String,
        genre:String,
        country:String,
        language:String,
        imdbrating:Number,
        awards:String
    }, {collection: 'project.movie'});
    return MovieSchema;
};