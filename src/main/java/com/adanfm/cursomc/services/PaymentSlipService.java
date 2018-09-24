package com.adanfm.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.adanfm.cursomc.domain.PaymentSlip;

@Service
public class PaymentSlipService {
	
	public void fillPaymentSlip(PaymentSlip payment, Date createdAt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdAt);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDateDue(cal.getTime());
	}
}
