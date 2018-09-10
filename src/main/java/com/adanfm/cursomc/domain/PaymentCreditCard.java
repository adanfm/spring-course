package com.adanfm.cursomc.domain;

import javax.persistence.Entity;

import com.adanfm.cursomc.domain.enums.StatePayment;

@Entity
public class PaymentCreditCard extends Payment{
    private static final long serialVersionUID = 1L;
    
	private Integer qtdPlots;
	
	public PaymentCreditCard() {}

	public PaymentCreditCard(Integer id, StatePayment statePayment, Order order, Integer qtdPlots) {
		super(id, statePayment, order);
		this.qtdPlots = qtdPlots;
	}

	public Integer getQtdPlots() {
		return qtdPlots;
	}

	public void setQtdPlots(Integer qtdPlots) {
		this.qtdPlots = qtdPlots;
	}
}
