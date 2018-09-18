package com.adanfm.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.adanfm.cursomc.domain.Client;
import com.adanfm.cursomc.domain.enums.TypeClient;
import com.adanfm.cursomc.dto.ClientDTO;
import com.adanfm.cursomc.dto.ClientNewDTO;
import com.adanfm.cursomc.repositories.ClientRepository;
import com.adanfm.cursomc.resources.exception.FieldMessage;
import com.adanfm.cursomc.services.validation.utils.BR;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientUpdate ann) {}
	
	@Override
	public boolean isValid(ClientDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = clientRepository.findByEmail(objDTO.getEmail());
		
		if (aux != null && !aux.getId().equals(uriId)) {
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
