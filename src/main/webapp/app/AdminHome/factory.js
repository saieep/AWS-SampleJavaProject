angular.module('trainingCompetency').factory('adminFactory', ['$http','localStorageService', function($http,localStorageService) {

    var urlBase = '/trainingcompetency/admin';
    var adminFactory = {};
    var config={};
    
    adminFactory.config=function() {
        config={headers:  {
            'Authorization': localStorageService.get('token')
        }};
        return true;
    }

    adminFactory.getUsers = function () {
        console.log(localStorageService.get('token'));
        console.log(config);
        return $http.get(urlBase+'/getuser',config);
    };

    adminFactory.getSession = function () {
        return $http.get(urlBase+'/getsession',config);
    };

    adminFactory.getGroups = function () {
        return $http.get(urlBase+'/getgroup',config);
    };

    adminFactory.getLogs = function () {
        return $http.get(urlBase+'/getaudit',config);
    };

    adminFactory.getActionDetails = function () {
        return $http.get(urlBase+'/getaction',config);
    };

    adminFactory.addGroup = function (group) {
        return $http.post(urlBase+'/addgroup',group,config);
    };

    adminFactory.addSession = function (session) {
        return $http.post(urlBase+'/addsession',session,config);
    };

     adminFactory.addUser = function (user) {
        return $http.post(urlBase+'/adduser',user,config);
    };

    adminFactory.activateRole = function (activate,user){
        return $http.put(urlBase+'/updateroleandstatus/'+user.id,activate,config);
    }

    adminFactory.deactivateRole = function (deactivate,user){
        return $http.put(urlBase+'/updateroleandstatus/'+user.id,deactivate,config);
    }

    adminFactory.toTrainer = function (totrainer,user){
        return $http.put(urlBase+'/updateroleandstatus/'+user.id,totrainer,config);
    }

    adminFactory.toTrainee = function (totrainee,user){
        return $http.put(urlBase+'/updateroleandstatus/'+user.id,totrainee,config);
    }

    return adminFactory;
}]);