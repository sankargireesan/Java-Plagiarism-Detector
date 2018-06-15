(function () {
    angular
        .module("PDApp")
        .controller("OwnreportsController", OwnreportsController);

    function OwnreportsController(ReportService, $routeParams, $location) {
        var vm = this;
        vm.userId=$routeParams['uid'];
        //vm.deleteReview=deleteReview;
        function init() {
            ReportService
                .findAllReportsByUserId(vm.userId)
                .then(function (resp) {
                    vm.userreports=resp.data;
                },function (error) {
                    vm.error="No reports found";
                })
        }
        init();

        /*function deleteReview(rev){
            ReviewService
                .deleteReview(rev)
                .then(
                    function (response) {
                        console.log("Review removed");
                        console.log(response.data);
                    },
                    function (error) {
                        console.log("error");
                    })



        }
*/

    }
})();