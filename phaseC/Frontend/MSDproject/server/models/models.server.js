
module.exports = function () {

    var userModel = require("./user/user.model.server")();
    var reportModel = require("./reports/report.model.server")();
//    var reviewModel = require("./reviews/review.model.server")();


    var models = {
        userModel: userModel,
        reportModel:reportModel
        //reviewModel:reviewModel,

    };

    return models;
};
