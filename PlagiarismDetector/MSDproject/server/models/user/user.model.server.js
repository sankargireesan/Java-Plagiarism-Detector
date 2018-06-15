module.exports=function () {

    var mongoose=require("mongoose");
    var UserSchema=require("./user.schema.server.js")();
    var User=mongoose.model("User",UserSchema);

    var api={

        createUser:createUser,
        findUserById:findUserById,
        findUserByCredentials:findUserByCredentials,
        findUserByEmail:findUserByEmail,
        deleteUser:deleteUser,
        updateUser:updateUser,
        findAllUsers:findAllUsers
    };
    return api;


    function createUser(user) {
        console.log("In mongodb");
        return User.create(user);
    }

    function findAllUsers() {
        return User.find();
    }
    function findUserById(userId) {
        return User.findById(userId);
    }

    function findUserByCredentials(email,password) {
        return User.findOne({email:email,password:password});

    }

    function findUserByEmail(email) {
        return User.findOne({email: email});
    }

        function deleteUser(userId) {
        return User.remove({_id:userId});
    }

    function updateUser(userId,newUser) {
        return User.update({_id:userId},{
                $set: {
                    firstName:newUser.firstName,
                    lastName:newUser.lastName,
                    email:newUser.email
                }
            });
    }
}