

angular.module('ecommerciMake').controller('EditPedidoController', function($scope, $routeParams, $location, PedidoResource , ProdutoPedidoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.pedido = new PedidoResource(self.original);
            ProdutoPedidoResource.queryAll(function(items) {
                $scope.produtosPedidoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.pedido.produtosPedido){
                        $.each($scope.pedido.produtosPedido, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produtosPedidoSelection.push(labelObject);
                                $scope.pedido.produtosPedido.push(wrappedObject);
                            }
                        });
                        self.original.produtosPedido = $scope.pedido.produtosPedido;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Pedidos");
        };
        PedidoResource.get({PedidoId:$routeParams.PedidoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.pedido);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.pedido.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Pedidos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Pedidos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.pedido.$remove(successCallback, errorCallback);
    };
    
    $scope.produtosPedidoSelection = $scope.produtosPedidoSelection || [];
    $scope.$watch("produtosPedidoSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.pedido) {
            $scope.pedido.produtosPedido = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.pedido.produtosPedido.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});