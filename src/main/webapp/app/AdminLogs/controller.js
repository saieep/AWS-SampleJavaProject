angular.module('trainingCompetency').controller('LogsController', function(sidebar,user,navbar,adminFactory,NgTableParams) {
	var viewModel=this;
	sidebar.show=true;
	sidebar.items=[];

	adminFactory.getLogs().then(function(response){
		viewModel.logs=response.data;
		//adminFactory.getActionDetails().then(){

			viewModel.logsTable = new NgTableParams({page: 1, count: 5}, { dataset: viewModel.logs});
		//}
	});
});