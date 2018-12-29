angular.module('trainingCompetency').factory('loginFactory', ['$http','$state','localStorageService', function($http,localStorageService,$state) {

    var urlBase = '/trainingcompetency';
    var loginFactory = {};

    loginFactory.doLogin = function (user) {
        return $http.post(urlBase+'/login',user);
    };

    loginFactory.logout = function () {
        console.log('damn!')
    	$state.go('/');
    };

    loginFactory.getDetails=function(id){
        return $http.get(urlBase+'/getuserdetails/'+id);
    }

    return loginFactory;
}]);