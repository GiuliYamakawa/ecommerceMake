package br.univel.br.bean;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.univel.br.model.Produto;
import br.univel.br.model.ProdutoPedido;

@SessionScoped
@ManagedBean
public class CarrinhoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Long, ProdutoPedido> produtos;

	@PostConstruct
	public void init() {
		this.setProdutos(new HashMap<Long, ProdutoPedido>());
	}

	public void addProduto(String produto) {
		Long id = System.currentTimeMillis();
		Produto p = new Produto();
		p.setDescricao(produto);
		ProdutoPedido pp = new ProdutoPedido();
		pp.setProduto(p);
		pp.setQuantidade(1L);
		pp.setId(id);

		produtos.put(id, pp);
	}

	public void removeProduto(Long id) {
		System.out.println("remove produto: " + id);
		produtos.remove(id);
	}

	public Map<Long, ProdutoPedido> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Long, ProdutoPedido> produtos) {
		this.produtos = produtos;
	}
}
