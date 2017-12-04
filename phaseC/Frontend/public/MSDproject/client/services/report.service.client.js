(function () {
    angular
        .module("PDApp")
        .factory("ReportService", ReportService );


    function ReportService($http) {


        var api = {
            addReport: addReport,
            findAllReportsByUserId: findAllReportsByUserId
            //deleteReview: deleteReview,

        };

        return api;


        function addReport(userId, rep) {
            return $http.post("/api/adduserreport/" + userId, rep);
        }

        function findAllReportsByUserId(userId) {
            return $http.get("/api/userreports/user/" + userId);
        }

        /*function deleteReview(reviewId) {
            return $http.delete("/api/revuser/" + reviewId);
        }*/


    }

})();