

angular.module('ecommerciMake').controller('EditProdutoPedidoController', function($scope, $routeParams, $location, ProdutoPedidoResource , PedidoResource, ProdutoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produtoPedido = new ProdutoPedidoResource(self.original);
            PedidoResource.queryAll(function(items) {
                $scope.pedidoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produtoPedido.pedido && item.id == $scope.produtoPedido.pedido.id) {
                        $scope.pedidoSelection = labelObject;
                        $scope.produtoPedido.pedido = wrappedObject;
                        self.original.pedido = $scope.produtoPedido.pedido;
                    }
                    return labelObject;
                });
            });
            ProdutoResource.queryAll(function(items) {
                $scope.produtoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produtoPedido.produto && item.id == $scope.produtoPedido.produto.id) {
                        $scope.produtoSelection = labelObject;
                        $scope.produtoPedido.produto = wrappedObject;
                        self.original.produto = $scope.produtoPedido.produto;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/ProdutoPedidos");
        };
        ProdutoPedidoResource.get({ProdutoPedidoId:$routeParams.ProdutoPedidoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produtoPedido);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produtoPedido.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/ProdutoPedidos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/ProdutoPedidos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produtoPedido.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("pedidoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produtoPedido.pedido = {};
            $scope.produtoPedido.pedido.id = selection.value;
        }
    });
    $scope.$watch("produtoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produtoPedido.produto = {};
            $scope.produtoPedido.produto.id = selection.value;
        }
    });
    
    $scope.get();
});