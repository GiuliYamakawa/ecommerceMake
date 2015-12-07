package br.univel.br.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import br.univel.br.model.Produto;

@Named
@Stateful
@ConversationScoped
public class ProdutosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	   @PersistenceContext(type = PersistenceContextType.EXTENDED)
	   private EntityManager entityManager;
	@PostConstruct
	public void init() {

	}
	
	public void addProduto(Produto produto) {
		System.out.println("ProdutosBean.addProduto()");
	}

	public void gravar() {
//		EntityManager em = JPAUtil.getEntityManager();
//		produto.setCategoria(em.merge(produto.getCategoria()));
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		em.persist(produto);
//		tx.commit();
//		em.close();

		setProdutos(null);
	}

	public void remove(Long id) {
		try {
//			EntityManager em = JPAUtil.getEntityManager();
//			Produto produto = em.getReference(Produto.class, id);
//			
//			EntityTransaction tx = em.getTransaction();
//			tx.begin();
//			em.remove(produto);
//			tx.commit();
//			em.close();
			
			setProdutos(null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			Query q = entityManager.createQuery("select p from Produto p", Produto.class);
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
