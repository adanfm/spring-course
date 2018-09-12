package com.adanfm.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.adanfm.cursomc.domain.Category;
import com.adanfm.cursomc.repositories.CategoryRepository;
import com.adanfm.cursomc.services.exceptions.DataIntegrityException;
import com.adanfm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category find(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not Found! Id: " + id + ", Type:" + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("This category can not be deleted because there are products assigned");
		}
	}	
}
