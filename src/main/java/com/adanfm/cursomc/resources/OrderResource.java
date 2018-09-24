package com.adanfm.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adanfm.cursomc.domain.Order;
import com.adanfm.cursomc.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Order order) {
		order = service.insert(order);
		System.out.println(order.getId());
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(order.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
}
