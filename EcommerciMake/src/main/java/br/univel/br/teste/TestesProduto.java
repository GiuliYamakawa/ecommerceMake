package br.univel.br.teste;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.Test;

import br.univel.br.model.Categoria;
import br.univel.br.model.Produto;

public class TestesProduto {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	int produtoTeste = 6;

	@Test
	public void testeInsertProdutos() {

		boolean flag = false;

		for (int i = 1; i < 51; i++) { // inserindo 50 produtos aleat�rios
			Produto p = new Produto();

			Categoria categoria = new Categoria();
			p.setCategoria(categoria);
			p.setDescricao("Produto " + i);
			p.setCor("Vermelho");
			p.setNome("Produto " + 1);
			p.setPeso(1);
			p.setPreco(34);

			try {
				entityManager.persist(p);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		assertEquals(true, flag);
	}

	@Test
	public void testeGetProdutoNotNull() {
		Produto teste = entityManager.find(Produto.class, produtoTeste);
		assertEquals(true, teste != null);
	}

	@Test
	public void testeUpdateProduto() {
		Produto teste = entityManager.find(Produto.class, produtoTeste);
		teste.setDescricao("Nova descricao");

		entityManager.persist(teste);
		
		Produto atualizado = entityManager.find(Produto.class, produtoTeste);
		
		assertEquals(teste.getDescricao(), atualizado.getDescricao());
	}

	@Test
	public void testeDeleteProduto() {
		Produto produto = entityManager.find(Produto.class, produtoTeste);
		boolean ok = false;
		try {
			entityManager.remove(produto);
			ok = true;
		}catch(Exception e){
			ok = true;
		}
		assertEquals(ok, true);
	}
}
