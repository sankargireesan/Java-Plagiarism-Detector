module.exports=function () {
    var mongoose=require("mongoose");

    var ReviewSchema = mongoose.Schema({
            _user: {type: mongoose.Schema.ObjectId, ref: "User"},
            _movie: {type: mongoose.Schema.ObjectId, ref: "Movie"},
             reviewText:String,
             moviename: {type:String, default:"Movie"},
            dateCreated: {type: Date, default: Date.now}
        }, {collection: "project.review"});

    ;
    return ReviewSchema;
};
