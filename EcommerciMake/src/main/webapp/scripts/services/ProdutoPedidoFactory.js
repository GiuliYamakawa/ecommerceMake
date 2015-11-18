angular.module('ecommerciMake').factory('ProdutoPedidoResource', function($resource){
    var resource = $resource('rest/produtopedidos/:ProdutoPedidoId',{ProdutoPedidoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});