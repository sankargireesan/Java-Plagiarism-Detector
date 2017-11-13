
module.exports = function(app) {

    var models = require("./server/models/models.server.js")();
    require("./server/services/user.service.server.js")(app,models);

};
