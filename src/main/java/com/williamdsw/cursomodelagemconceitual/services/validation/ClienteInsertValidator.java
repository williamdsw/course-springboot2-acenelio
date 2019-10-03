package com.williamdsw.cursomodelagemconceitual.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.domain.enums.TipoCliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteNewDTO;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.resources.exceptions.FieldMessage;
import com.williamdsw.cursomodelagemconceitual.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private ClienteRepository repository;

    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    
    @Override
    public void initialize (ClienteInsert ann) {}

    @Override
    public boolean isValid (ClienteNewDTO dto, ConstraintValidatorContext context)
    {
        List<FieldMessage> errors = new ArrayList<> ();

        // Verifica CPF
        if (dto.getTipoCliente ().equals (TipoCliente.PESSOA_FISICA.getCodigo ()) && !BR.isValidCPF (dto.getCpfOuCnpj ()))
        {
            errors.add (new FieldMessage ("cpfOuCnpj", "CPF inválido!"));
        }

        // Verifica CNPJ
        if (dto.getTipoCliente ().equals (TipoCliente.PESSOA_JURIDICA.getCodigo ()) && !BR.isValidCNPJ (dto.getCpfOuCnpj ()))
        {
            errors.add (new FieldMessage ("cpfOuCnpj", "CNPJ inválido!"));
        }

        // Verifica EMAIL
        Cliente cliente = repository.findByEmail (dto.getEmail ());
        if (cliente != null)
        {
            errors.add (new FieldMessage ("email", "Email já existente"));
        }

        for (FieldMessage error : errors)
        {
            context.disableDefaultConstraintViolation ();
            context.buildConstraintViolationWithTemplate (error.getMessage ()).addPropertyNode (error.getFieldName ()).addConstraintViolation ();
        }

        return errors.isEmpty ();
    }
}