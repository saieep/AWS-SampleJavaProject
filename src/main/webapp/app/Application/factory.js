angular.module('trainingCompetency').factory('applicationFactory', ['$http','localStorageService','$uibModal', function($http,localStorageService,$uibModal) {

    var applicationFactory = {};

    applicationFactory.simpleModal=function (title,body) {
        $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'app/TrainerHome/modal.html',
            controller: function($scope,$uibModalInstance){
                var modalViewModal=this;
                modalViewModal.body=body;
                modalViewModal.title=title;
                $scope.$on('modal.closing', function(event, reason, closed) {
                    if (reason=="OK") {
                        
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

    return applicationFactory;
}]);