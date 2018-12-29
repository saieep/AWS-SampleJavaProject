angular.module('trainingCompetency').controller('TrainerScenarioController', function($stateParams,trainerFactory,applicationFactory,user) {
	var viewModel = this;
	viewModel.loadScenario = function(sess){
		viewModel.id=$stateParams.id;
		trainerFactory.getScenariosbyid(sess).then(function(response){
			response.data.forEach(function(element, index){
				if(element.scenarioid==viewModel.id){
					viewModel.scenario=element;
				}
			});
		});
	}

	trainerFactory.getUserSession(user).then(function (response){
		viewModel.loadScenario(response.data[0]);

	});
	viewModel.Score = {};

  	viewModel.addScore= function(id,score) {

  		viewModel.Score.score = score;

		trainerFactory.addScore(viewModel.Score,id).then(function() {
			applicationFactory.simpleModal("Score Added","score Added!");
			
		});
	}
	viewModel.started = false;
	viewModel.alreadyStarted = false;
	viewModel.startScenario=function(){
		trainerFactory.toggleScenario(1,{isactive:1}).then(function () {
			viewModel.started = true;
		});
	}
	viewModel.stopScenario=function(){
		trainerFactory.toggleScenario(1,{isactive:0}).then(function () {
			viewModel.started = false;
			viewModel.alreadyStarted = true;
		});
	}
});