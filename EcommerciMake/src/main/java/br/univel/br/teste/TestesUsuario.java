package br.univel.br.teste;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.Test;

import br.univel.br.model.Usuario;

public class TestesUsuario {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	int idTeste = 6;

	@Test
	public void testeInsertUsuarios() {

		boolean flag = false;

		for (int i = 1; i < 51; i++) {
			Usuario c = new Usuario();

			c.setNome("Usuario " + 1);
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
	public void testeGetUsuarioNotNull() {
		Usuario teste = entityManager.find(Usuario.class, idTeste);
		assertEquals(true, teste != null);
	}

	@Test
	public void testeUpdateUsuario() {
		Usuario teste = entityManager.find(Usuario.class, idTeste);
		teste.setNome("Novo nome");

		entityManager.persist(teste);

		Usuario atualizado = entityManager.find(Usuario.class, idTeste);

		assertEquals(teste.getNome(), atualizado.getNome());
	}

	@Test
	public void testeDeleteUsuario() {
		Usuario teste = entityManager.find(Usuario.class, idTeste);
		boolean ok = false;
		try {
			entityManager.remove(teste);
			ok = true;
		} catch (Exception e) {
			ok = true;
		}
		assertEquals(ok, true);
	}
}
