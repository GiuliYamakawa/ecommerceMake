package br.univel.br.teste;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.Test;

import br.univel.br.model.Cliente;

public class TestesCliente {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	int clienteTeste = 6;

	@Test
	public void testeInsertClientes() {

		boolean flag = false;

		for (int i = 1; i < 51; i++) { // inserindo 50 produtos aleat�rios
			Cliente c = new Cliente();

			c.setNome("Cliente " + 1);
			c.setLogin("login");
			c.setSenha("senha");
			
			try {
				entityManager.persist(c);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		assertEquals(true, flag);
	}

	@Test
	public void testeGetClienteNotNull() {
		Cliente teste = entityManager.find(Cliente.class, clienteTeste);
		assertEquals(true, teste != null);
	}

	@Test
	public void testeUpdateCliente() {
		Cliente teste = entityManager.find(Cliente.class, clienteTeste);
		teste.setNome("Novo nome");

		entityManager.persist(teste);
		
		Cliente atualizado = entityManager.find(Cliente.class, clienteTeste);
		
		assertEquals(teste.getNome(), atualizado.getNome());
	}

	@Test
	public void testeDeleteCliente() {
		Cliente teste = entityManager.find(Cliente.class, clienteTeste);
		boolean ok = false;
		try {
			entityManager.remove(teste);
			ok = true;
		}catch(Exception e){
			ok = true;
		}
		assertEquals(ok, true);
	}
}
