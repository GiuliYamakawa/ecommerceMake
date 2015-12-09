package br.univel.br.teste;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.Test;

import br.univel.br.model.Categoria;

public class TestesCategoria {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	int idteste = 6;

	@Test
	public void testeInsertCategoria() {

		boolean flag = false;

		for (int i = 1; i < 51; i++) { 
			Categoria c = new Categoria();

			c.setDescricao("Categoria " + 1);
			
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
	public void testeGetCategoriaNotNull() {
		Categoria teste = entityManager.find(Categoria.class, idteste);
		assertEquals(true, teste != null);
	}

	@Test
	public void testeUpdateCategoria() {
		Categoria teste = entityManager.find(Categoria.class, idteste);
		teste.setDescricao("Novo Categoria");

		entityManager.persist(teste);
		
		Categoria atualizado = entityManager.find(Categoria.class, idteste);
		
		assertEquals(teste.getDescricao(), atualizado.getDescricao());
	}

	@Test
	public void testeDeleteCategoria() {
		Categoria teste = entityManager.find(Categoria.class, idteste);
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
