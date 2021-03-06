package com.adanfm.cursomc.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	
	private Double discount;
	private Integer qtd;
	private Double price;
	
	public OrderItem() {}

	public OrderItem(Order order, Product product , Double discount, Integer qtd, Double price) {
		super();
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.discount = discount;
		this.qtd = qtd;
		this.price = price;
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public double getSubTotal() {
		return (price - discount) * qtd;
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return this.id.getOrder();
	}

	public Product getProduct() {
		return this.id.getProduct();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
