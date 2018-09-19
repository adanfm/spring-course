package com.adanfm.cursomc.dto;

import java.io.Serializable;

import com.adanfm.cursomc.domain.Product;

public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Double preco;
	
	public ProductDTO() {}

	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		preco = product.getPreco();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
