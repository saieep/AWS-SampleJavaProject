angular.module('trainingCompetency').controller('TraineeScenarioController',function($timeout,$uibModal,$stateParams,traineeFactory,applicationFactory,user,$state){
	
	var viewModel=this;
	viewModel.added = true;

	var timer = 0;
	traineeFactory.getscenario
	user.scenarioid=$stateParams.id;
	user.scenarios.push($stateParams.id);
	viewModel.started=false;

	viewModel.timerFunction = function() {
			if(timer>0 && viewModel.added){
				viewModel.minute=Math.floor(timer / 60);
				viewModel.seconds=timer-(Math.floor(timer / 60)*60); 
				timer--;
				$timeout(viewModel.timerFunction, 1000);
			}else{
				viewModel.addResponse();
			}
	};

	viewModel.startScenario=function (argument) {
		viewModel.started=true;
		traineeFactory.getScenarios().then(function(response) {
			response.data.forEach( function(element, index) {
				if(element.scenarioid==user.scenarioid){
					viewModel.scenarioData=element;
					timer=(viewModel.scenarioData.duration*60);
					viewModel.timerFunction();
				}
			});
		});
	}

	viewModel.openModel = function () {
	    $uibModal.open({
		    animation: viewModel.animationsEnabled,
		    ariaLabelledBy: 'modal-title',
      		ariaDescribedBy: 'modal-body',
		    templateUrl: 'app/TraineeHome/timeovermodel.html',
		    controller: function($scope,$uibModalInstance){
		    	var modalViewModal=this;
		    	$scope.$on('modal.closing', function(event, reason, closed) {
		    		if (reason=="OK") {
		    			scenarioPoller();

		    		}else{
		    			event.preventDefault();
		    		}
		    	})
		    	modalViewModal.ok = function () {
    				$uibModalInstance.close("OK");
  				};
		    },
		    controllerAs: 'modal'
	    });
  	};
  	viewModel.traineeResponse = {groupid:user.id,scenarioid:user.scenarioid};
  	viewModel.addResponse= function() {
  		console.log(viewModel.traineeResponse);
		traineeFactory.addResponse(viewModel.traineeResponse).then(function() {
			applicationFactory.simpleModal("Response Added","response Added!");
			viewModel.added = false;
			scenarioPoller();
			$state.go("application.home",{user: "Trainee"});
		},function(statusText) {
				applicationFactory.simpleModal("Error!","There was an error while adding your response");
		});
	}
});