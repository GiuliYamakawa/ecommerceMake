package br.univel.controller;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.univel.br.bean.CarrinhoBean;
import br.univel.br.model.Pedido;
import br.univel.br.model.Produto;
import br.univel.br.rest.ProdutoEndpoint;
import br.univel.br.rest.VendaEndpoint;

public class CarrinhoController {
	
	@Inject
	private CarrinhoBean carrinhobean;
	
	
	@Inject
	private ProdutoEndpoint pe;
	
	@Inject
	private VendaEndpoint vendaEp;
	
	@Path("/adicionar/{id:[0-9][0-9]*}")
	public void adicionarProduto(@PathParam("id") long id){
		Produto p = pe.findById(id).readEntity(Produto.class);
	

}
	
	public void limpar(){
		carrinhobean.limpar();
	}
	
	public void finalizar(){
		Pedido pedido = new Pedido();
		Iterator<Produto> it = (Iterator<Produto>) carrinhobean.getProdutos();
		while(it.hasNext()){
			
		}
	
	}
	
}