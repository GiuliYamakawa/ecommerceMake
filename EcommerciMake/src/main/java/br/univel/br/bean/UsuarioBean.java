package br.univel.br.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import util.JPAUtil;
import br.univel.br.model.Cliente;
import br.univel.br.model.Usuario;

@ManagedBean
public class UsuarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {

	}

	public void gravar() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(usuario);
		tx.commit();
		em.close();

		setUsuarios(null);
	}

	public void remove(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Usuario usuario = em.getReference(Usuario.class, id);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(usuario);
		tx.commit();
		em.close();

		setUsuarios(null);
	}

	public Usuario getCliente() {
		return usuario;
	}

	public void setCliente(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select u from Usuario u", Usuario.class);
			usuarios = q.getResultList();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
