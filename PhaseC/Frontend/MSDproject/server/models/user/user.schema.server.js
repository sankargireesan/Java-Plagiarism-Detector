module.exports=function () {
    var mongoose=require("mongoose");

    var UserSchema= mongoose.Schema({
        password:String,
        email:String,
        dateCreated: {type:Date,default:Date.now}
    },{collection:"msdproject.user"});

    return UserSchema;
};