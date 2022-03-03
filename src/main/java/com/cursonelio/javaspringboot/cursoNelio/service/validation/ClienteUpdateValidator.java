package com.cursonelio.javaspringboot.cursoNelio.service.validation;

import com.cursonelio.javaspringboot.cursoNelio.controller.exception.FieldMessage;
import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequest;
import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequestNew;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.service.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteRequest> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteRequest objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email ja existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}