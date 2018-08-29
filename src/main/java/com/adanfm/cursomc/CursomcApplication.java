package com.adanfm.cursomc;

import java.util.Arrays;

import com.adanfm.cursomc.domain.*;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
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

	}
}

