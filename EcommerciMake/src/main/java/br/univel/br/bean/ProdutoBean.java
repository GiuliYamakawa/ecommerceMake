package br.univel.br.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import util.JPAUtil;
import br.univel.br.model.Categoria;
import br.univel.br.model.Produto;

@ManagedBean
public class ProdutoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	private List<Produto> produtos;

	@PostConstruct
	public void init() {

	}

	public void gravar() {
		EntityManager em = JPAUtil.getEntityManager();
		produto.setCategoria(em.merge(produto.getCategoria()));
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(produto);
		tx.commit();
		em.close();

		setProdutos(null);
	}

	public void remove(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Produto produto = em.getReference(Produto.class, id);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(produto);
		tx.commit();
		em.close();

		setProdutos(null);
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select p from Produto p", Produto.class);
			produtos = q.getResultList();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
