
angular.module('ecommerciMake').controller('NewProdutoPedidoController', function ($scope, $location, locationParser, ProdutoPedidoResource , PedidoResource, ProdutoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produtoPedido = $scope.produtoPedido || {};
    
    $scope.pedidoList = PedidoResource.queryAll(function(items){
        $scope.pedidoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("pedidoSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produtoPedido.pedido = {};
            $scope.produtoPedido.pedido.id = selection.value;
        }
    });
    
    $scope.produtoList = ProdutoResource.queryAll(function(items){
        $scope.produtoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtoSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produtoPedido.produto = {};
            $scope.produtoPedido.produto.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/ProdutoPedidos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProdutoPedidoResource.save($scope.produtoPedido, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/ProdutoPedidos");
    };
});