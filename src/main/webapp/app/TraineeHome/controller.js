angular.module('trainingCompetency').controller('TraineeHomeController',function(traineeFactory,sidebar,navbar,$timeout,user){
	var viewModel = this;
	sidebar.actionitems=[{"title":"Dashboard","link":"application.home({user: 'Trainee'})","class":"fa-home"}];
	sidebar.show=false;
	sidebar.items=[];
	viewModel.hide=false;
	viewModel.auditlog = {};

	viewModel.chartObject2 = {type: "ColumnChart",data:{"cols": [{id: "t", label: "Scenario", type: "string"},
	        {id: "s", label: "Scores", type: "number"}]}};			

	traineeFactory.getResponses().then(function(response1) {							
			response1.data.forEach( function(element, index) {
				if(element.id.groupid==parseInt(user.id)){
				var col={"c": [
					        {"v": "Scenario "+element.id.senarioid},
					        {"v": element.score}
					    ]};		
					viewModel.chartObject2.data.rows.push(col)
				}
				});
	});



	traineeFactory.getTraineeDetails().then(function(response){
		viewModel.trainee = response.data[0];
	});

	traineeFactory.config();

	viewModel.addAudit=function(actionid){
		if (user.scenarioid) {
			viewModel.auditlog = {"scenarioid":user.scenarioid,"actionid":actionid};
	    	traineeFactory.addAudit(viewModel.auditlog);
	    }
	}


	scenarioPoller = function() {
		console.log("here")
		viewModel.hide=false;
		sidebar.items=[];
	    traineeFactory.getScenarios().then(function(response) {
	      if (response.data.length!=0) {
	    		viewModel.scenarioData=response.data;
	      		sidebar.items=[];
	      		viewModel.hide=true;
		      	response.data.forEach( function(element, index) {
		      		if (user.scenarios.indexOf(element.scenarioid) == -1) {
						sidebar.items.push({"link": "application.home.scenario({id:"+element.scenarioid+"})","label": "Scenario"+element.scenarioid});
		      		}
				})
	      }
	      else {
	      		sidebar.items=[];
				$timeout(scenarioPoller, 5000);
			}
	    });    
	};

    scenarioPoller();
});