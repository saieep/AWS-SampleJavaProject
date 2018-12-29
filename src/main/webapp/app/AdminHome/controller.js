angular.module('trainingCompetency').controller('AdminHomeController',function(adminFactory,sidebar,navbar,NgTableParams,applicationFactory){
	var viewModel = this;
	sidebar.show=false; 
	viewModel.submit_success = false;
	viewModel.adduser = {dateofcreation:new Date(),isactive:0};
	
	adminFactory.config();
	
	viewModel.addUser = function () {
		console.log(viewModel.adduser);
		adminFactory.addUser(viewModel.adduser).then(function(){
				applicationFactory.simpleModal("Success!","User Created");
				viewModel.adduser = {dateofcreation:new Date(),isactive:0};
		});
		viewModel.server = angular.copy(viewModel.user);
		viewModel.submit_success = true;
	};
	sidebar.actionitems=[{"title":"Dashboard","link":"application.home","class":"fa-home"},{"title":"All Groups","link":"application.home.allgroups","class":"fa-users"},{"title":"Create Groups","link":"application.home.creategroups","class":"fa-user-plus"}];
	sidebar.items=[];
	navbar.items=[{"title":"Session Logs","link":"application.logs({user: 'Admin'})"}];

	adminFactory.getUsers().then(function(response){
			viewModel.users=response.data;
			viewModel.usersForGroup=response.data;
			viewModel.usersTable = new NgTableParams({page: 1, count: 5}, { dataset: viewModel.users});
		});

	adminFactory.getGroups().then(function(response){
		viewModel.groups=response.data;
		viewModel.groupsTable = new NgTableParams({page: 1, count: 5}, { dataset: viewModel.groups});
	});

	viewModel.newSession={isactive:1}
	
	viewModel.startTime = new Date();
	viewModel.endTime = new Date();
	viewModel.newSession.starttime=viewModel.startTime.getHours()+":"+viewModel.startTime.getMinutes()+":00";
	viewModel.newSession.endtime=viewModel.endTime.getHours()+":"+viewModel.endTime.getMinutes()+":00";

	viewModel.addSession=function () {
		adminFactory.addSession(viewModel.newSession).then(function (response) {
			applicationFactory.simpleModal("Success!","Session Created");
			viewModel.newSession={isactive:1}
		});
	}

	viewModel.newGroup={"isactive":1,"userid":[],"sessionid":1};
	viewModel.members=[];
	viewModel.add=function(user){
		viewModel.members.push(user);
		viewModel.newGroup.userid.push(user.id);
		viewModel.usersForGroup.forEach( function(element, index) {
			if (user.id==element.id) {
				viewModel.usersForGroup.splice(index,1);
			}
		});
	};

	viewModel.remove=function(user){
		viewModel.usersForGroup.push(user);
		viewModel.members.forEach( function(element, index) {
			if (user.id==element.id) {
				viewModel.members.splice(index,1);
			}
		});
		viewModel.newGroup.userid.forEach( function(element, index) {
			if (user.id==element) {
				viewModel.members.splice(index,1);
			}
		});
	};

	viewModel.createGroup=function () {
			adminFactory.addGroup(viewModel.newGroup).then(function(){
				applicationFactory.simpleModal("Success!","Group was created.");
				viewModel.newGroup={"isactive":1,"userid":[],"sessionid":1};
				viewModel.members=[];
			});
	};


	viewModel.toTrainer=function (user) {
		viewModel.totrainer = {isactive:user.isactive,role:"Trainer"};
			adminFactory.toTrainer(viewModel.totrainer,user).then(function () {
				// body...
			adminFactory.getUsers().then(function(response){
				viewModel.users=response.data;
				viewModel.usersForGroup=response.data;
				console.log(viewModel.users)
				//viewModel.usersTable.reload();
				viewModel.usersTable=new NgTableParams({page: 1, count: 5}, { dataset: viewModel.users});
			});
			
			});
			
	};
	
	viewModel.toTrainee=function (user) {
		viewModel.totrainee = {isactive:user.isactive,role:"Trainee"};
			adminFactory.toTrainee(viewModel.totrainee,user).then(function () {
				// body...
			adminFactory.getUsers().then(function(response){
				viewModel.users=response.data;
				viewModel.usersForGroup=response.data;
				console.log(viewModel.users)
				//viewModel.usersTable.reload();
				viewModel.usersTable=new NgTableParams({page: 1, count: 5}, { dataset: viewModel.users});
			});
			
			});
			
	};


	viewModel.activateRole=function (user) {
		viewModel.activate = {isactive:1,role:user.role};
			adminFactory.activateRole(viewModel.activate,user).then(function () {
				// body...
			adminFactory.getUsers().then(function(response){
				viewModel.users=response.data;
				viewModel.usersForGroup=response.data;
				console.log(viewModel.users)
				//viewModel.usersTable.reload();
				viewModel.usersTable=new NgTableParams({page: 1, count: 5}, { dataset: viewModel.users});
			});
			
			});
			
	};
	
	viewModel.deactivateRole=function (user) {
		viewModel.deactivate = {isactive:0,role:user.role};
			adminFactory.deactivateRole(viewModel.deactivate,user).then(function () {
				// body...
			adminFactory.getUsers().then(function(response){
				viewModel.users=response.data;
				viewModel.usersForGroup=response.data;
				console.log(viewModel.users)
				//viewModel.usersTable.reload();
				viewModel.usersTable=new NgTableParams({page: 1, count: 5}, { dataset: viewModel.users});
			});
			
			});
	};

});