package com.adanfm.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.adanfm.cursomc.domain.Client;
import com.adanfm.cursomc.domain.enums.TypeClient;
import com.adanfm.cursomc.dto.ClientNewDTO;
import com.adanfm.cursomc.repositories.ClientRepository;
import com.adanfm.cursomc.resources.exception.FieldMessage;
import com.adanfm.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientInsert ann) {}
	
	@Override
	public boolean isValid(ClientNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDTO.getTypeClient().equals(TypeClient.PESSOA_FISICA.getId()) && !BR.isValidCPF(objDTO.getDocument())) {
			list.add(new FieldMessage("typeClient", "The field document is not valid!"));
		}
		
		if (objDTO.getTypeClient().equals(TypeClient.PESSOA_JURIDICA.getId()) && !BR.isValidCNPJ(objDTO.getDocument())) {
			list.add(new FieldMessage("typeClient", "The field document is not valid!"));
		}
		
		Client aux = clientRepository.findByEmail(objDTO.getEmail());
		
		if (aux != null) {
			list.add(new FieldMessage("email", "This email '" + objDTO.getEmail() + "' already exists"));
		}
		
		for (FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate(
					e.getMessage()
				)
				.addPropertyNode(
					e.getFieldName()
				)
				.addConstraintViolation()
			;
		}
		
		return list.isEmpty();
	}
	
}
