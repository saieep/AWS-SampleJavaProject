angular.module('trainingCompetency', ['ui.bootstrap','ui.router','ngAnimate','ngTable','angularTrix','googlechart','LocalStorageModule','ngSanitize']);

angular.module('trainingCompetency').config(function($stateProvider,$urlRouterProvider,localStorageServiceProvider) {

  	var loginState = {
        name: 'login',
        url: '/login',
        templateUrl: "app/ApplicationLogin/login.html",
        controller: "LoginController",
        controllerAs: 'login'
    };

    var applicationState = {
        name: 'application',
        url: '/:user',
        templateUrl: "app/ApplicationHome/container.html",
        controller: "ApplicationController",
        controllerAs: 'app'
    };

    var homeState = {
      	name: 'application.home',
      	url: '/home',
      	templateUrl: function($stateParams){
          return 'app/'+$stateParams.user+'Home/home.html';
        },
        controllerProvider: function($stateParams) {
          var ctrlName = $stateParams.user + "HomeController";
          return ctrlName;
        },
        controllerAs: 'home'
    };

    var logsState = {
        name: 'application.logs',
        url: '/:user/logs',
        templateUrl: "app/AdminLogs/logs.html",
        controller: "LogsController",
        controllerAs: 'logs'
    };

    var allGroupsState = {
        name: 'application.home.allgroups',
        url: '/allgroups',
        templateUrl: "app/AdminHome/allgroups.html"
    };

    var createGroupsState = {
        name: 'application.home.creategroups',
        url: '/creategroups',
        templateUrl: "app/AdminHome/creategroups.html"
    };

    var scenarioState = {
      name: 'application.home.scenario',
      url: '/scenario/:id',
      templateUrl: function($stateParams){
          return 'app/'+$stateParams.user+'Scenario/scenario.html'
      },
      controllerProvider: function($stateParams) {
          var ctrlName = $stateParams.user + "ScenarioController";
          return ctrlName;
      },
      controllerAs: 'scenario'
    };

    var trainerScoresState={
      name: 'application.scores',
      url: '/scores',
      templateUrl: 'app/TrainerScores/scores.html',
      controller: 'TrainerScoresController',
      controllerAs: 'trainerscores'
    };
  	
  	var states=[loginState,applicationState,homeState,scenarioState,trainerScoresState,createGroupsState,allGroupsState,logsState];
  	for(elementIndex in states){
  	  $stateProvider.state(states[elementIndex]);
  	}
    $urlRouterProvider.when('', '/login');
    localStorageServiceProvider.setPrefix('cyb');
});

angular.module('trainingCompetency').value('sidebar', {show:true,items:[],actionitems:[]});
angular.module('trainingCompetency').value('navbar', {items:[]});
angular.module('trainingCompetency').value('user', {});

