angular.module('trainingCompetency').factory('trainerFactory', ['$http','localStorageService', function($http,localStorageService,$uibModal) {

    var urlBase = '/trainingcompetency/trainer';
    var trainerFactory = {};
    var config={};
    
    trainerFactory.config=function() {
        config={headers:  {
            'Authorization': localStorageService.get('token')
        }};
        return true;
    }

    trainerFactory.getScenarios = function () {
        return $http.get(urlBase+'/getscenario',config);
    };

    trainerFactory.getSession = function () {
        return $http.get(urlBase+'/getsession/',config);
    };

    trainerFactory.getUserSession =function (user) {    
        return $http.get(urlBase+'/getsessionbyid/'+user.id,config);
    }

    trainerFactory.getScenariosbyid =function (sess) {
        return $http.get(urlBase+'/getscenariobyid/'+sess.sessionid,config);
    }

	trainerFactory.addScenario = function (newScenario) {
        return $http.post(urlBase+'/addscenario',newScenario,config);
    };
	
	trainerFactory.getResponses = function () {
        return $http.get(urlBase+'/getresponse',config);
    };

	trainerFactory.addScore = function (groupScore,id) {
        return $http.put(urlBase+'/addscore/'+id.groupid+'/'+id.scenarioid,groupScore,config);
    };

    trainerFactory.toggleSession =function (id,status) {
        return $http.put(urlBase+'/updatesession/'+id,status,config);
    }

    trainerFactory.toggleScenario =function (id,status) {
        return $http.put(urlBase+'/updatescenario/'+id,status,config);
    }


    return trainerFactory;
}]);