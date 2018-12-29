angular.module('trainingCompetency').controller('TrainerHomeController',function(NgTableParams,trainerFactory,sidebar,navbar,$uibModal,applicationFactory,user){
	var viewModel = this;

	trainerFactory.config();

	viewModel.started=false;

	navbar.items=[{"title":"Scores","link":"application.scores"}];
	sidebar.actionitems=[{"title":"Add New Scenario","link":"application.home({user: 'Trainer'})","class":"fa-plus"}];
	sidebar.items=[];

	
	/*viewModel.getSession=function(){
		trainerFactory.getSession().then();
	}*/

	viewModel.startSession=function(){
		trainerFactory.toggleSession(1,{isactive:1}).then(function () {
			sidebar.show=false;
			viewModel.started=true;
		});
	}
	viewModel.endSession=function(){
		trainerFactory.toggleSession(1,{isactive:0}).then(function () {
			sidebar.show=false;
			viewModel.started=true;
		});
	}

	viewModel.refreshScenarios=function(sess){
		trainerFactory.getScenariosbyid(sess).then(function(response){
			viewModel.scenarios=response.data;
			viewModel.scenarios.forEach( function(element, index) {
				sidebar.items.push({"link": "application.home.scenario({id:"+element.scenarioid+"})","label": "Scenario"+element.scenarioid});
			});
			viewModel.scenariosTable = new NgTableParams({page: 1, count: 5}, { dataset: viewModel.scenarios});
		});
	};
	trainerFactory.getUserSession(user).then(function (response){
		viewModel.refreshScenarios(response.data[0]);

	});


	
	viewModel.newScenario={sessionid: 1};
	viewModel.addScenario=function(){
		trainerFactory.addScenario(viewModel.newScenario).then(function() {
			applicationFactory.simpleModal("Scenario Added","Scenario Added!");
			viewModel.newScenario.content="";
		});
	}

	viewModel.showScenarioContent=function(scenario){
		applicationFactory.simpleModal("Scenario Content",scenario.content);
	}

	viewModel.showResponse=function(response){
		applicationFactory.simpleModal("Response",response);
	}

	trainerFactory.getResponses().then(function(response) {
		viewModel.responses=response.data;
		viewModel.responsesTable = new NgTableParams({page: 1, count: 5}, { dataset: viewModel.responses});
	});
	viewModel.scores={};
	
})