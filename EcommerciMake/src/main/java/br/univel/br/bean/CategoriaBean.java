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

@ManagedBean
public class CategoriaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Categoria categoria = new Categoria();
	private List<Categoria> categorias;

	@PostConstruct
	public void init() {

	}

	public void gravar() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(categoria);
		tx.commit();
		em.close();

		setCategorias(null);
	}

	public void remove(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Categoria categoria = em.getReference(Categoria.class, id);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(categoria);
		tx.commit();
		em.close();

		setCategorias(null);
	}

	public List<Categoria> getCategorias() {
		if (categorias == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select a from Categoria a",
					Categoria.class);
			categorias = q.getResultList();
		}
		return categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
