
angular.module('ecommerciMake').controller('NewPedidoController', function ($scope, $location, locationParser, PedidoResource , ProdutoPedidoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.pedido = $scope.pedido || {};
    
    $scope.produtosPedidoList = ProdutoPedidoResource.queryAll(function(items){
        $scope.produtosPedidoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtosPedidoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.pedido.produtosPedido = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.pedido.produtosPedido.push(collectionItem);
            });
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Pedidos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        PedidoResource.save($scope.pedido, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Pedidos");
    };
});