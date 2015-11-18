

angular.module('ecommerciMake').controller('EditCategoriaController', function($scope, $routeParams, $location, CategoriaResource , ProdutoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.categoria = new CategoriaResource(self.original);
            ProdutoResource.queryAll(function(items) {
                $scope.produtosSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.categoria.produtos){
                        $.each($scope.categoria.produtos, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produtosSelection.push(labelObject);
                                $scope.categoria.produtos.push(wrappedObject);
                            }
                        });
                        self.original.produtos = $scope.categoria.produtos;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Categoria");
        };
        CategoriaResource.get({CategoriaId:$routeParams.CategoriaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.categoria);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.categoria.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Categoria");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Categoria");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.categoria.$remove(successCallback, errorCallback);
    };
    
    $scope.produtosSelection = $scope.produtosSelection || [];
    $scope.$watch("produtosSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.categoria) {
            $scope.categoria.produtos = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categoria.produtos.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});