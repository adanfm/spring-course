package com.adanfm.cursomc.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("paymentSlip")
public class PaymentSlip extends Payment{
    private static final long serialVersionUID = 1L;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_due")
	private Date dateDue;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_payment")
	private Date datePayment;
	
	public PaymentSlip() {}

	public PaymentSlip(Integer id, StatePayment statePayment, Order order, Date dateDue, Date datePayment) {
		super(id, statePayment, order);
		this.dateDue = dateDue;
		this.datePayment = datePayment;
	}

	public Date getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
}
