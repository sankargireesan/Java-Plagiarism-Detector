/*
(function() {
    angular
        .module("TestApp", [])
        .controller("TestController", TestController)
        .filter('reverse', function() {
            return function(items) {
                return items.slice().reverse();
            };
        });

    function TestController($http) {
        var vm = this;
        vm.createMessage = createMessage;
        vm.deleteMessage = deleteMessage;

        function init() {
            findAllMessages();
        }
        init();

        function createMessage(message) {
            vm.message = "";
            var obj = {
                message: message
            };
            $http.post("/api/test", obj)
                .then(
                    findAllMessages,
                    function(err) {
                        vm.error = err;
                    }
                );
        }

        function deleteMessage(message) {
            $http.delete("/api/test/" + message._id)
                .then(
                    findAllMessages,
                    function(err) {
                        vm.error = err;
                    }
                );
        }

        function findAllMessages() {
            $http.get("/api/test")
                .then(
                    function(response) {
                        vm.messages = response.data;
                    },
                    function(err) {
                        vm.error = err;
                    }
                );
        }
    }
})();*/


module.exports = function(app)
{
    app.get("/api/test", findAllMessages);
    app.post("/api/test", createMessage);
    app.delete("/api/test/:id", deleteMessage);

    var connectionString = 'mongodb://127.0.0.1:27017/test';

    /*if(process.env.MLAB_USERNAME) {
        var username = process.env.MLAB_USERNAME;
        var password = process.env.MLAB_PASSWORD;
        connectionString = 'mongodb://'+
            process.env.MLAB_USERNAME + ':' +
            process.env.MLAB_PASSWORD +
            '@ds053459.mlab.com:53459/jannunzi_test';
    }*/

    var mongoose = require("mongoose");  // npm install mongoose --save
    mongoose.connect(connectionString);

    var TestSchema = mongoose.Schema({
        message: String
    }, {collection: 'messages'});

    var TestModel = mongoose.model("TestModel", TestSchema);

    function findAllMessages(req, res) {
        TestModel
            .find() // select * from messages -- where
            .then(
                function(tests) {
                    res.json(tests);
                },
                function(err) {
                    res.status(400).send(err);
                }
            );
    }

    function createMessage(req, res) {
        TestModel
            .create(req.body)
            .then(
                function(test) {
                    res.json(test);
                },
                function(err) {
                    res.status(400).send(err);
                }
            );
    }

    function deleteMessage(req, res) {
        TestModel
            .remove({_id: req.params.id})
            .then(
                function(result) {
                    res.json(result);
                },
                function(err) {
                    res.status(400).send(err);
                }
            );
    }
};

