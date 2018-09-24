package com.adanfm.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adanfm.cursomc.domain.Order;
import com.adanfm.cursomc.domain.OrderItem;
import com.adanfm.cursomc.domain.PaymentSlip;
import com.adanfm.cursomc.domain.Product;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.adanfm.cursomc.repositories.OrderItemsRepository;
import com.adanfm.cursomc.repositories.OrderRepository;
import com.adanfm.cursomc.repositories.PaymentRepository;
import com.adanfm.cursomc.repositories.ProductRepository;
import com.adanfm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentSlipService paymentSlipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemsRepository itemsRepository;
	
	public Order find(Integer id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo:" + Order.class.getName()));
	}
	
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setCreatedAt(new Date());
		obj.getPayment().setStatePayment(StatePayment.PENDING);
		obj.getPayment().setOrder(obj);
		
		if (obj.getPayment() instanceof PaymentSlip) {
			PaymentSlip pagto = (PaymentSlip) obj.getPayment();
			paymentSlipService.fillPaymentSlip(pagto, obj.getCreatedAt());
		}
		
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		
		for (OrderItem oi: obj.getOrderItems()) {
			oi.setDiscount(0.0);
			Optional<Product> objProduct = productRepository.findById(oi.getProduct().getId());
			oi.setPrice(objProduct.get().getPreco());
			oi.setOrder(obj);
		}
		
		itemsRepository.saveAll(obj.getOrderItems());
		
		return obj;
	}
}
