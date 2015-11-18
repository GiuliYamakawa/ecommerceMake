
angular.module('ecommerciMake').controller('NewCategoriaController', function ($scope, $location, locationParser, CategoriaResource , ProdutoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.categoria = $scope.categoria || {};
    
    $scope.produtosList = ProdutoResource.queryAll(function(items){
        $scope.produtosSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtosSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.categoria.produtos = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.categoria.produtos.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Categoria/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        CategoriaResource.save($scope.categoria, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Categoria");
    };
});