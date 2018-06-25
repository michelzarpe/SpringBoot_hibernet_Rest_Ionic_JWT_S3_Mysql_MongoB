package com.michelzarpelon.cursomcmz.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.michelzarpelon.cursomcmz.domain.dto.NovoClienteDTO;
import com.michelzarpelon.cursomcmz.domain.enums.TipoCliente;
import com.michelzarpelon.cursomcmz.resources.exceptions.FieldMessage;
import com.michelzarpelon.cursomcmz.services.validation.util.Br;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NovoClienteDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(NovoClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !Br.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CPF Inválido"));

		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !Br.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ Inválido"));

		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}