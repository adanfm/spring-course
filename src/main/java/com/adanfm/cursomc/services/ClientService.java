package com.adanfm.cursomc.services;

import com.adanfm.cursomc.domain.Client;
import com.adanfm.cursomc.repositories.ClientRepository;
import com.adanfm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public Client find(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo:" + Client.class.getName()));
	}
	
}
