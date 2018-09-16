package com.adanfm.cursomc.services;

import com.adanfm.cursomc.domain.Address;
import com.adanfm.cursomc.domain.City;
import com.adanfm.cursomc.domain.Client;
import com.adanfm.cursomc.domain.enums.TypeClient;
import com.adanfm.cursomc.dto.ClientDTO;
import com.adanfm.cursomc.dto.ClientNewDTO;
import com.adanfm.cursomc.repositories.AddressRepository;
import com.adanfm.cursomc.repositories.CityRepository;
import com.adanfm.cursomc.repositories.ClientRepository;
import com.adanfm.cursomc.services.exceptions.DataIntegrityException;
import com.adanfm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Client find(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo:" + Client.class.getName()));
	}
	
	public List<Client> findAll() {
		return repository.findAll();
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj = repository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("This client can not be deleted because there are orders assigned");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO dto) {
		return new Client(dto.getId(), dto.getName(), dto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getDocument(), TypeClient.toEnum(objDTO.getTypeClient()));
		Optional<City> city = cityRepository.findById(objDTO.getCityId());
		
		Address address = new Address(
				null, 
				objDTO.getAddress(), 
				objDTO.getNumber(), 
				objDTO.getComplement(), 
				objDTO.getNeighborhood(), 
				objDTO.getZipCode(), 
				cli, 
				city.get()
		);
		
		cli.getAddresses().add(address);
		cli.getPhones().add(objDTO.getPhone1());
		
		if (objDTO.getPhone2() != null) {
			cli.getPhones().add(objDTO.getPhone2());
		}
		
		if (objDTO.getPhone3() != null) {
			cli.getPhones().add(objDTO.getPhone3());
		}
		
		return cli;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
}
