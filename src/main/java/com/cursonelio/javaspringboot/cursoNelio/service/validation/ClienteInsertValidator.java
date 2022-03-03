package com.cursonelio.javaspringboot.cursoNelio.service.validation;

import com.cursonelio.javaspringboot.cursoNelio.controller.exception.FieldMessage;
import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequestNew;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.service.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteRequestNew> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteRequestNew objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CPF", "CPF invalido"));
        }

        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CNPJ", "CNPJ invalido"));
        }

        Cliente aux = repository.findByEmail(objDto.getEmail());
        if (aux != null){
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