(function () {
    angular
        .module("PDApp")
        .controller("ProjecthomeController", ProjecthomeController);

    var inps = document.querySelectorAll('input');
    [].forEach.call(inps, function(inp) {
        inp.onchange = function(e) {
            console.log(this.files);
        };
    });


    function ProjecthomeController(MovieService, $routeParams, $location) {
        var vm = this;
        vm.searchMovieByTitle = searchMovieByTitle;
        vm.fetchGithubProject = fetchGithubProject;
        //vm.link1=$routeParams.link1;
        //vm.link2 = $routeParams.link2;
        vm.userId=$routeParams['uid'];

        function init() {
           window.alert("bhbghfvgvhkfdghkfdgkhfd");
        }
        init();

        function searchMovieByTitle(title) {
            MovieService
                .searchMovieByTitle(title)
                .success(function (result) {
                    vm.movies = result.Search;
                })
                .error(function (error) {
                   vm.error="Movie not found";
                });
        }
        function fetchGithubProject(link) {
            var command = "git clone "+link;
            //var cmd = ""+link;
            //try{var wsh = new ActiveXObject('WScript.Shell');} catch(err){}
            //wsh.Run(command);

            //var run=new ActiveXObject('WSCRIPT.Shell').Run(command);
            //gitscript.clone(cmd, function(){
              //  console.log('Cloned');
            window.alert("I am here");
            //var objShell = new ActiveXObject("shell.application");



        }


    }
    })();