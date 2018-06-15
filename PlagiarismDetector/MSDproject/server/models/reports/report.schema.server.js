module.exports=function () {
    var mongoose=require("mongoose");

    var ReportSchema = mongoose.Schema({
            _user: {type: mongoose.Schema.ObjectId, ref: "User"},
             reportText:String,
             dateCreated: {type: Date, default: Date.now}
        }, {collection: "msdproject.report"});

    ;
    return ReportSchema;
};
