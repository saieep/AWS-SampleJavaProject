angular.module('trainingCompetency').controller('TrainerScoresController', function(sidebar,user,navbar,trainerFactory) {
	var viewModel = this;
	sidebar.items=[];
	sidebar.actionitems=[];
	sidebar.show=true;
	viewModel.charts=[]

	trainerFactory.getUserSession(user).then(function (response){
		console.log('here')
		viewModel.session=response.data[0];
		console.log(response.data)
		trainerFactory.getScenariosbyid(viewModel.session).then(function (response2) {
			viewModel.scenarios=response2.data;
			response.data.forEach( function(element1, index) {
					var chartObject2 = {};
					chartObject2.type = "ColumnChart";
					chartObject2.data = {"cols": [{id: "t", label: "Groups", type: "string"},
					        {id: "s", label: "Scores", type: "number"}], "rows": [
							    {"c": [
							        {"v": "Group 1"},
							        {"v": 20}
							    ]}	
							]};			

					trainerFactory.getResponses().then(function(response3) {
							chartObject2.data = {"cols": [{id: "t", label: "Groups", type: "string"},
					        {id: "s", label: "Scores", type: "number"}], "rows": [
							    {"c": [
							        {"v": "Group 1"},
							        {"v": 20}
							    ]}	
							]};
							
							response3.data.forEach( function(element, index) {
								if(element1.scenarioid==element.id.scenarioid){
								var col={"c": [
									        {"v": "Group "+element.id.groupid},
									        {"v": element.score}
									    ]};		
									chartObject2.data.rows.push(col)
								}
								});
						})

				viewModel.charts.push(chartObject2);


			});
		});
	});


	// trainerFactory.getResponses().then(function(response) {
	// 	console.log(response.data);
	// 	viewModel.chartObject2.data = {"cols": [{id: "t", label: "Groups", type: "string"},
 //        {id: "s", label: "Scores", type: "number"}], "rows": [
	// 	    {"c": [
	// 	        {"v": "Group 1"},
	// 	        {"v": 20}
	// 	    ]}	
	// 	]};
	// 	response.data.forEach( function(element, index) {
	// 	var col={"c": [
	// 		        {"v": "Group "+element.id.groupid},
	// 		        {"v": element.score}
	// 		    ]};		
	// 		viewModel.chartObject2.data.rows.push(col)
	// 	});
	// })

	

});