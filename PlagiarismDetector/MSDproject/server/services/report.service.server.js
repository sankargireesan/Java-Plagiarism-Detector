module.exports = function (app, models) {
    var reportModel = models.reportModel;


    app.post("/api/adduserreport/:userId", addReport);
    app.get("/api/userreports/user/:userId", findAllReportsByUserId);

    //app.delete("/api/revuser/:reviewId", deleteReview);

    /*function deleteReview(req, res) {
        reviewModel
            .deleteReview(req.params.reviewId)
            .then(
                function (stats) {
                    console.log(stats);
                    res.send(200);
                },
                function (error) {
                    res.statusCode(404).send(error);
                }
            );
    }*/

    function addReport(req, res) {
        var userId = req.params.userId;
        //  var username = req.params.username;
        var rep = req.body;

        reportModel.addReport(rep)
                                .then(
                                    function (rob) {
                                        res.json(rob);
                                    }, function (err) {
                                        res.status(400).send(err);
                                    }
                                );
    }






    function findAllReportsByUserId(req, res) {
        reportModel
            .findAllReportsByUserId(req.params.userId)
            .then( function (omdata) {
                    res.json(omdata);
                },
                function (error) {
                    res.statusCode(400).send(error);
                }
            );
    }


}

