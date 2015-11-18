package br.univel.br.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.JPAUtil;
import br.univel.br.model.Produto;
import br.univel.br.model.ProdutoPedido;

@SessionScoped
@Named
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

	public void addProduto(String produto) {
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

	public void removeProduto(Long id) {
		System.out.println("remove produto: " + id);
		produtos.remove(id);
	}

	public Map<Long, ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Long, ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
	
	public void limpar(){
		this.produtos.clear();
	}
}
