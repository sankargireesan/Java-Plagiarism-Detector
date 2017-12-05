(function () {
    angular
        .module("PDApp")
        .controller("ProjecthomeController", ProjecthomeController);


    function ProjecthomeController($routeParams, $location, $http, $rootScope) {
        var vm = this;
        vm.performtest=performtest;

        vm.userId=$routeParams['uid'];

        function init() {

        }
        init();


        /*function performtest(link1 , link2) {
            $rootScope.first = vm.firststudent;
            $rootScope.second = vm.secondstudent;

            var str = {
                Pathone : link1,
                Pathtwo : link2
            }

            $http.post('http://localhost:8080/path1', str, {headers: { 'Accept': 'text/plain, text/html' }, transformResponse: [function (data) { return data.toString(); }] }


            ).then(function(response){


                $rootScope.plagiarismdata = response.data;
                $location.url("/report/"+vm.userId)
            })
        }*/

        function performtest(link1 , link2) {

            if(link1 == null || link2 == null){
                window.alert("Missing directory path");
            }

            $rootScope.first = vm.firststudent;
            $rootScope.second = vm.secondstudent;

            var str = {
                Pathone : link1,
                Pathtwo : link2
            }

            $http.post('http://localhost:3000/dowork',str,{headers: { 'Accept': 'text/plain, text/html' }, transformResponse: [function (data) { return data.toString(); }]}).then(function(response){


                $rootScope.plagiarismdata = response.data;

                if(response.data.toString() == "no files found"){
                   window.alert("Error: No files found")
                }
                else{
                    $location.url("/report/"+vm.userId)
                }



            });




        }

    }
    })();