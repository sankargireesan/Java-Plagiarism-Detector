(function () {
    angular
        .module("PDApp")
        .controller("ReportController", ReportController);


    function ReportController($routeParams, $http, $rootScope, ReportService) {
        var vm = this;
        vm.report = $rootScope.plagiarismdata;
        vm.exportreport=exportreport;
        vm.savereport=savereport;
        vm.userId=$routeParams['uid'];
        vm.studentonename = $rootScope.first;
        vm.studenttwoname = $rootScope.second;

        function init() {
        //window.alert($rootScope.plagiarismdata.toString());d
            var student1 = "";
            var student2 = "";
            var responseString = vm.report;
            var respString = responseString.substring(1,responseString.length - 1);
            var arr = respString.split(",");

            for(var i=0;i<=arr.length;i++){
                if(i% 2 == 0){
                    student1 = student1 +"  "+arr[i];

                }
                else{
                    student2 = student2 +"  "+arr[i];

                }
            }
            vm.studentone = student1;
            vm.studenttwo = student2;

        }
        init();

         function exportreport() {
             html2canvas(document.getElementById('exportthis'), {
                 onrendered: function (canvas) {
                     var data = canvas.toDataURL();
                     var docDefinition = {
                         content: [{
                             image: data,
                             width: 500,
                         }]
                     };
                     pdfMake.createPdf(docDefinition).download("test-report.pdf");
                 }
             });
         }
         
         function savereport() {

                 var plagrep = {
                     _user: vm.userId,
                     reportText: vm.report

                 };

                 ReportService
                     .addReport(vm.userId, plagrep)
                     .then(
                         function (response) {
                             alert("report was successfully saved");

                         }, function (error) {
                             vm.error = "some error ocurred";
                         }
                     )
             }

         }



})();