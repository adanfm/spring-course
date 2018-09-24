package com.adanfm.cursomc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentCreditCard")
public class PaymentCreditCard extends Payment{
    private static final long serialVersionUID = 1L;
    
    @Column(name="qtd_plots")
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
