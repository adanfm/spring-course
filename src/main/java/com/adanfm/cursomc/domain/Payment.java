package com.adanfm.cursomc.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="payment")
abstract public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
	private Integer id;
    
    @Column(name="state_payment")
	private Integer statePayment;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	@MapsId
	private Order order;
	
	public Payment() {}

	public Payment(Integer id, StatePayment statePayment, Order order) {
		super();
		this.id = id;
		this.statePayment = statePayment.getId();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatePayment getStatePayment() {
		return StatePayment.toEnum(this.statePayment);
	}

	public void setStatePayment(StatePayment statePayment) {
		this.statePayment = statePayment.getId();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
