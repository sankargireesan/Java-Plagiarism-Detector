module.exports = function(app, models) {
    var userModel=models.userModel;



    app.get("/api/user", findUser);
    app.post("/api/msd/user", createUser);

    app.get("/api/user/:userId", findUserById);
    app.put("/api/user/:userId", updateUser);
    app.delete("/api/user/:userId", unregisterUser);
    app.post("/api/user/logout", logout);
    console.log("yash123333");


    function unregisterUser(req, res) {
        var uid = req.params.userId;
        userModel.deleteUser(uid)
            .then(function (status) {
                res.send(200);
            }, function (error) {
                res.statusCode(404).send(error);
            })
    }

    function logout(req, res) {
        req.logout();
        res.send(200);
    }

    function updateUser(req, res) {
        var user = req.body;
        var uid = req.params.userId;
        userModel.updateUser(uid, user)
            .then(function (status) {
                res.send(200);
            }, function (error) {
                res.statusCode(404).send(error);
            })
    }

    function createUser(req, res) {

        var user = req.body;
        userModel.createUser(user)
            .then(function (user) {
                res.json(user);
            }, function (error) {
                res.statusCode(400).send(error);
            })
    }

    function findUser(req, res) {

        console.error("In server findUser of project");
        var email=req.query.email;
        var password=req.query.password;
        if(email&&password){
            findUserByCredentials(email,password,res);
        }
        else if(email){
            findUserByEmail(email,res);
        }

    else {
            userModel
                .findAllUsers()
                .then(
                    function (users) {
                        res.json(users);
                    },function(error) {
                        res.status(400).send(error);
                    });
        }

    }


    function findUserByCredentials(email,password,res) {
        userModel.findUserByCredentials(email, password)
            .then(function (user) {
                res.json(user);
            }, function (error) {
                res.statusCode(404).send(error);
            })
    }

    function findUserByEmail(email,res) {

        userModel.findUserByEmail(email)
            .then(function (user) {
                console.log("In findUserByUsername of project");
                res.json(user);
                console.log(user.email);
            }, function (error) {
                res.statusCode(404).send(error);
            })
    }



    function findUserById(req,res) {
        var id=req.params.userId;
        userModel.findUserById(id)
            .then(function (user) {
                res.send(user);
            },function (error) {
                res.statusCode(404).send(error);
            })
    }

};