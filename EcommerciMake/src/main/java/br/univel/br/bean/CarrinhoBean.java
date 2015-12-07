package br.univel.br.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.univel.br.model.Pedido;
import br.univel.br.model.Produto;
import br.univel.br.model.ProdutoPedido;

//@SessionScoped
//@ManagedBean
@Named
@Stateful
@SessionScoped
public class CarrinhoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @PersistenceContext(type = PersistenceContextType.EXTENDED)
	 private EntityManager entityManager;
	
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
//		EntityManager em = JPAUtil.getEntityManager();

		Pedido pedido = new Pedido();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		em.persist(pedido);
		entityManager.persist(pedido);
		
		for (ProdutoPedido produtoPedido : this.produtos.values()) {
			produtoPedido.setProduto(entityManager.merge(produtoPedido.getProduto()));
			produtoPedido.setPedido(pedido);
			entityManager.persist(produtoPedido);
		}
		
		
//		tx.commit();
//		em.close();
		limpaCarrinho();
	}

	public Map<Long, ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Long, ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
}
