package com.williamdsw.cursomodelagemconceitual.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;
import com.williamdsw.cursomodelagemconceitual.dto.ClienteDTO;
import com.williamdsw.cursomodelagemconceitual.repositories.ClienteRepository;
import com.williamdsw.cursomodelagemconceitual.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private HttpServletRequest request;

    // ------------------------------------------------------------------------------------//
    // IMPLEMENTADOS
    @Override
    public void initialize (ClienteUpdate ann)
    {}

    @Override
    public boolean isValid (ClienteDTO dto, ConstraintValidatorContext context)
    {
        // Buscando atributos da URI
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute (HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt (map.get ("id"));

        List<FieldMessage> errors = new ArrayList<> ();

        // Verifica EMAIL
        Cliente cliente = repository.findByEmail (dto.getEmail ());
        if (cliente != null && !cliente.getId ().equals (id))
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