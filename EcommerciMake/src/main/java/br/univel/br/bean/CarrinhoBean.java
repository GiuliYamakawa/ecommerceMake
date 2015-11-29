package br.univel.br.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.univel.br.model.Pedido;
import br.univel.br.model.Produto;
import br.univel.br.model.ProdutoPedido;
import br.univel.br.util.JPAUtil;

@SessionScoped
@ManagedBean
public class CarrinhoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Long, ProdutoPedido> produtos;

	@PostConstruct
	public void init() {
		this.setProdutos(new HashMap<Long, ProdutoPedido>());
	}

	/**
	 * Adiciona um produto no carrinho
	 * @param produto
	 */
	public void addProduto(Produto produto) {
		ProdutoPedido pp = produtos.get(produto.getId());
		
		if ( pp == null){
			pp = new ProdutoPedido();
			pp.setProduto(produto);
			pp.setPreco(produto.getPreco());
			pp.addProduto();
		} else {
			pp.addProduto();
		}
				
		produtos.put(produto.getId(), pp);
	}
	
	public void addPr(String produto) {
		if (true) {

			throw new NullPointerException();
		}
		Long id = System.currentTimeMillis();
		Produto p = new Produto();
		p.setDescricao(produto);
		ProdutoPedido pp = new ProdutoPedido();
		pp.setProduto(p);
		pp.setQuantidade(1);
		pp.setId(id);

		// produtos.put(id, pp);

		EntityManager em = JPAUtil.getEntityManager();

		Produto pr = new Produto();
		pr.setDescricao(produto);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(pr);
		tx.commit();
		em.close();

	}

	/**
	 * Remove um produto do carrinho
	 * @param id
	 */
	public void removeProduto(Long id) {
		produtos.remove(id);
	}
	
	public void limpaCarrinho() {
		produtos = new HashMap<Long, ProdutoPedido>();
	}
	
	/**
	 * Finaliza o pedido
	 * @param id
	 */
	public void finalizarPedido() {
		EntityManager em = JPAUtil.getEntityManager();

		Pedido pedido = new Pedido();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(pedido);
		
		for (ProdutoPedido produtoPedido : this.produtos.values()) {
			produtoPedido.setProduto(em.merge(produtoPedido.getProduto()));
			produtoPedido.setPedido(pedido);
			em.persist(produtoPedido);
		}
		
		
		tx.commit();
		em.close();
		limpaCarrinho();
	}

	public Map<Long, ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Long, ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
}
