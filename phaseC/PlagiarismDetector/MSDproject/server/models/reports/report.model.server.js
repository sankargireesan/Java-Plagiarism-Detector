module.exports = function () {
    var mongoose = require("mongoose");
    var ReportSchema = require("./report.schema.server.js")();
    var Report = mongoose.model("Report", ReportSchema);

    var api = {

        findAllReportsByUserId: findAllReportsByUserId,
        addReport: addReport
        //deleteReview: deleteReview,
            };
    return api;


    function findAllReportsByUserId(userId) {
        return Report.find({_user: userId});
    }


    function addReport(rep) {
        return Report.create(rep);
    }

    /*function deleteReview(rid) {
        return Review.remove({_id: rid});
    }*/

};

