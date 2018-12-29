angular.module('trainingCompetency').controller('LoginController', function(sidebar,$state,user,loginFactory,localStorageService,adminFactory,applicationFactory,traineeFactory) {
	user.role="application";
	var viewModel=this;
	viewModel.user={};
	viewModel.login =function(){
		loginFactory.doLogin(viewModel.user).then(function(response) {
			localStorageService.set("token",response.headers("Authorization").substr(7));
			localStorageService.set("userid",viewModel.user.id);
			loginFactory.getDetails(viewModel.user.id).then(function(response){
				if(response.data.length!=0){
					user.username=response.data[0].username;
					user.id=response.data[0].id;
					user.role=response.data[0].role;
					$state.go("application.home",{user: response.data[0].role});
				}else {
					user.role="Trainee";
					traineeFactory.config();
					traineeFactory.getTraineeDetails().then(function(response){
						user.username=response.data[0].groupname;
						user.id=response.data[0].id;
						user.sessionid=response.data[0].sessionid;
						user.scenarios=[];
						console.log(response.data)
						$state.go("application.home",{user: user.role});
					});
				}
			})
		},function(statusText) {
			if (statusText.status==500) {
				applicationFactory.simpleModal("Error!","Can not login! Status: Invalid Credentials");
			}else {
				applicationFactory.simpleModal("Error!","Can not login! Status:"+statusText.statusText);
			}
		});
	}
});