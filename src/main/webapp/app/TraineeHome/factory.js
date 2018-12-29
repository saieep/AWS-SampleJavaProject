angular.module('trainingCompetency').factory('traineeFactory', ['$http','localStorageService','user', function($http,localStorageService,user) {

    var urlBase = '/trainingcompetency/trainee';
    var traineeFactory = {};
    var config={};
    
    traineeFactory.config=function() {
        config={headers:  {
            'Authorization': localStorageService.get('token')
        }};
        return true;
    }

    traineeFactory.getScenarios = function () {
        return $http.get(urlBase+'/getscenariobyidactive/'+user.sessionid,config);
    };

    traineeFactory.addResponse = function (traineeResponse) {
        console.log(traineeResponse);
        return $http.post(urlBase+'/addresponse',traineeResponse,config);
    };

    traineeFactory.getScenariosScores = function (getScore) {
        return $http.get(urlBase+'/getresponse',getScore,config);
    };

    traineeFactory.addAudit = function (audit) {
        console.log(audit)
        audit.groupid=localStorageService.get('userid');
        return $http.post(urlBase+'/addaudit',audit,config);
    };

    traineeFactory.getTraineeDetails = function (){
       return $http.get(urlBase+'/gettraineebyid/'+localStorageService.get('userid'),config);
    };

    traineeFactory.getResponses = function (){
       return $http.get(urlBase+'/getresponse',config);
    };

    return traineeFactory;
}]);