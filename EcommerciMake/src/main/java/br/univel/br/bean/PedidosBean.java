package br.univel.br.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.univel.br.model.Pedido;
import br.univel.br.util.JPAUtil;

@ManagedBean
public class PedidosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Pedido> pedidos;

	@PostConstruct
	public void init() {

	}

	public List<Pedido> getPedidos() {
		if (pedidos == null) {
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("select p from Pedido p", Pedido.class);
			pedidos = q.getResultList();
		}
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
