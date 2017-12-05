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

        var stringtosave = "";

        function init() {


            var student1cp = [];
            var student2cp = [];
            var student1vc = [];
            var student2vc = [];
            var student1hc = [];
            var student2hc = [];
            //var responseString = "meth1/nmeth2~NM~meth3~NM~meth4~NM~~VC~meth5~NM~meth6~NM~meth7~NM~meth8~NM~~HC~meth9~NM~meth10~NM~meth11~NM~meth12~NM~";
            var responseString = vm.report.replace('~nl~',"<br/>");
            console.log(responseString);

            var respString = responseString.substring(0,responseString.length);
            var arrcp =respString.split("~VC~");
            var arrcptemp = arrcp[0];
            arrcptemp = arrcptemp.substring(0,arrcptemp.length - 4);
            var arr = arrcptemp.split("~NM~");
            for(var i=0;i<arr.length;i++){
                if(i% 2 == 0){
                    student1cp.push(arr[i]);

                }
                else{
                    student2cp.push(arr[i]);

                }
            }
            vm.studentonecp = student1cp;
            vm.studenttwocp = student2cp;

            var arrvc = arrcp[1].split("~HC~");
            var arrvctemp = arrvc[0];
            var arrhctemp = arrvc[1];
            arrvctemp = arrvctemp.substring(0,arrvctemp.length -4);
            var arr2 = arrvctemp.split("~NM~");
            for(var i=0;i<arr2.length;i++){
                if(i% 2 == 0){
                    student1vc.push(arr2[i]);

                }
                else{
                    student2vc.push(arr2[i]);

                }
            }
            vm.studentonevc = student1vc;
            vm.studenttwovc = student2vc;
            arrhctemp = arrhctemp.substring(0,arrhctemp.length - 6);
            console.log(arrhctemp);
            var arr3 = arrhctemp.split("~NM~");
            for(var i=0;i<arr3.length;i++){
                if(i% 2 == 0){
                    student1hc.push(arr3[i]);

                }
                else{
                    student2hc.push(arr3[i]);

                }
            }
            vm.studentonehc = student1hc;
            vm.studenttwohc = student2hc;

            /*console.log(student1cp);
            console.log(student2cp);
            console.log(student1vc);
            console.log(student2vc);
            console.log(student1hc);
            console.log(student2hc);
*/
            stringtosave ="copy paste methods:"+vm.studentonecp+" "+vm.studenttwocp+" variable change methods:"+vm.studentonevc+" "+vm.studenttwovc+" hash compare methods:"+vm.studentonehc+" "+vm.studenttwohc;



        }
        init();

         function exportreport() {

             var element = document.getElementById('exportthis');
             html2pdf(element);
         }
         
         function savereport() {

                 var plagrep = {
                     _user: vm.userId,
                     reportText: stringtosave

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