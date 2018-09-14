package com.adanfm.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.adanfm.cursomc.domain.*;
import com.adanfm.cursomc.domain.enums.StatePayment;
import com.adanfm.cursomc.domain.enums.TypeClient;
import com.adanfm.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));

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

