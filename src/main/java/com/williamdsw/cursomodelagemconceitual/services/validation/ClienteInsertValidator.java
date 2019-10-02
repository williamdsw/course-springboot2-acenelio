package com.williamdsw.cursomodelagemconceitual.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.williamdsw.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteNewDTO;
import com.williamdsw.cursomodelagemconceitual.resources.exceptions.FieldMessage;
import com.williamdsw.cursomodelagemconceitual.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>
{
	@Override
	public void initialize (ClienteInsert ann) { }

	@Override
	public boolean isValid (ClienteNewDTO dto, ConstraintValidatorContext context)
	{ 
		List<FieldMessage> errors = new ArrayList<>();
		
		if (dto.getTipoCliente ().equals (TipoCliente.PESSOA_FISICA.getCodigo ()) && !BR.isValidCPF (dto.getCpfOuCnpj ()))
		{
			errors.add (new FieldMessage ("cpfOuCnpj", "CPF inválido!"));
		}
		
		if (dto.getTipoCliente ().equals (TipoCliente.PESSOA_JURIDICA.getCodigo ()) && !BR.isValidCNPJ (dto.getCpfOuCnpj ())) 
		{
			errors.add (new FieldMessage ("cpfOuCnpj", "CNPJ inválido!"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		
		for (FieldMessage error : errors)
		{
			context.disableDefaultConstraintViolation ();
			context.buildConstraintViolationWithTemplate (error.getMessage ()).addPropertyNode(error.getFieldName ()).addConstraintViolation ();
		}
		
		return errors.isEmpty ();
	}
}