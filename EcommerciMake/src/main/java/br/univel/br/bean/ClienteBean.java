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

@ManagedBean
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;

	@PostConstruct
	public void init() {

	}

	public void gravar() {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(cliente);
		tx.commit();
		em.close();

		setClientes(null);
	}

	public void remove(Long id) {
		EntityManager em = JPAUtil.getEntityManager();
		Cliente cliente = em.getReference(Cliente.class, id);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.remove(cliente);
		tx.commit();
		em.close();

		setClientes(null);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
//		if (clientes == null) {
//			EntityManager em = JPAUtil.getEntityManager();
//			Query q = em.createQuery("select c from Cliente c", Cliente.class);
//			clientes = q.getResultList();
//		}
//		return clientes;
		System.out.println("getCliente");
		new ArrayList<Cliente>();
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
