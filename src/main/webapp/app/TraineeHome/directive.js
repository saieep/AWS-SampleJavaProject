angular.module('trainingCompetency').directive('preventRightClick', [  
            function () { 
                return {  
                    restrict: 'A',
                    scope: {
                      audit: '&audit'
                    },
                    link: function ($scope, $ele,traineeFactory) {  
                        $ele.bind("contextmenu", function (e) {
                            $scope.audit({actionid: 1})
                            e.preventDefault();  
                            alert("Right click is disabled");
                        });
                        $ele.bind("keydown keypress keyup mousedown", function (event) {
            				if(event.which === 123) {
                                $scope.audit({actionid: 3})
                				event.preventDefault();
                            };
                	
                			if(event.which === 17) {
                                $scope.audit({actionid: 1})
                				event.preventDefault();
                			};
                			
                			if(event.which === 44) {
                                $scope.audit({actionid: 2})   
                				event.preventDefault();
                			};
                			if(event.ctrlKey){
                                $scope.audit({actionid: 1})
                				event.preventDefault();
                			};
                    	})  
                	}
                }
            }  
        ])