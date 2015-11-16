package br.univel.br.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class HomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String produto;

	@PostConstruct
	public void init() {
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	

}
