package com.adanfm.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adanfm.cursomc.domain.Address;
import com.adanfm.cursomc.domain.Category;
import com.adanfm.cursomc.domain.City;
import com.adanfm.cursomc.domain.Client;
import com.adanfm.cursomc.domain.Order;
import com.adanfm.cursomc.domain.OrderItem;
import com.adanfm.cursomc.domain.Payment;
import com.adanfm.cursomc.domain.PaymentCreditCard;
import com.adanfm.cursomc.domain.PaymentSlip;
import com.adanfm.cursomc.domain.Product;
import com.adanfm.cursomc.domain.State;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.adanfm.cursomc.domain.enums.TypeClient;
import com.adanfm.cursomc.repositories.AddressRepository;
import com.adanfm.cursomc.repositories.CategoryRepository;
import com.adanfm.cursomc.repositories.CityRepository;
import com.adanfm.cursomc.repositories.ClientRepository;
import com.adanfm.cursomc.repositories.OrderItemsRepository;
import com.adanfm.cursomc.repositories.OrderRepository;
import com.adanfm.cursomc.repositories.PaymentRepository;
import com.adanfm.cursomc.repositories.ProductRepository;
import com.adanfm.cursomc.repositories.StateRepository;

@Service
public class DBService {
	

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	public void instantiateDatabase() throws ParseException {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV True color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2,p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1,cat4));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategories().addAll(Arrays.asList(cat1,cat4));
		p4.getCategories().addAll(Arrays.asList(cat4));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

		State state1 = new State(null, "Minas Gerais", "MG");
		State state2 = new State(null, "Sao Paulo", "SP");

		City c1 = new City(null, "Uberlandia", state1);
		City c2 = new City(null, "Sao Paulo", state2);
		City c3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2,c3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));

		Client client = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TypeClient.PESSOA_FISICA);
		client.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address address1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client, c1);
		Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", client, c2);

		client.getAddresses().addAll(Arrays.asList(address1, address2));

		clientRepository.save(client);
		addressRepository.saveAll(Arrays.asList(address1, address2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), client, address1);
		Order order2 = new Order(null, sdf.parse("10/110/2017 10:35"), client, address2);
		
		Payment payment1 = new PaymentCreditCard(null, StatePayment.SETTLED, order1, 6);
		order1.setPayment(payment1);
		Payment payment2 = new PaymentSlip(null, StatePayment.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
		order2.setPayment(payment2);
		
		client.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		
		OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, p3, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);
		
		order1.getOrderItems().addAll(Arrays.asList(orderItem1,orderItem2));

		order2.getOrderItems().addAll(Arrays.asList(orderItem3));
		
		p1.getOrderItems().addAll(Arrays.asList(orderItem1));
		p2.getOrderItems().addAll(Arrays.asList(orderItem3));
		p2.getOrderItems().addAll(Arrays.asList(orderItem1));
		
		orderItemsRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3));
	}
}
