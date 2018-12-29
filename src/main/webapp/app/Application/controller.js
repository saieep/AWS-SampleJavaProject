angular.module('trainingCompetency').controller('ApplicationController', function(sidebar,user,navbar,$stateParams,$state,loginFactory,localStorageService) {
	var viewModel = this;
	viewModel.sidebar=function(){
		return sidebar;
	}
	viewModel.navbar=function(){
		return navbar;
	}
	if (user.username) {
		viewModel.username=user.username;
	}

	viewModel.logout=function () {
    	localStorageService.clearAll();
    	$state.go('login');
	}

	if(angular.equals(user, {})){
		viewModel.logout();
	}else {	
		viewModel.user=user;
		$state.go('application.home');
	}
});