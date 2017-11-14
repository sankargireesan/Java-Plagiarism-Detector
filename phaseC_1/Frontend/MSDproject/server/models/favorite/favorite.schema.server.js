module.exports = function () {
    var mongoose = require("mongoose");
    var FavoriteSchema = mongoose.Schema({
            _user: {type: mongoose.Schema.ObjectId, ref: "User"},
            _movie: {type: mongoose.Schema.ObjectId, ref: "Movie"},
            moviename:{type:String, default:"Movie"}
        },
        {collection: "project.favorite"});
    return FavoriteSchema;
};