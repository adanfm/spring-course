package com.adanfm.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adanfm.cursomc.domain.Order;
import com.adanfm.cursomc.repositories.OrderRepository;
import com.adanfm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Order find(Integer id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo:" + Order.class.getName()));
	}
	
}
