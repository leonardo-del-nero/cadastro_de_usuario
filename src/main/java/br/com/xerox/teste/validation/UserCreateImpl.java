package br.com.xerox.teste.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.xerox.teste.common.FieldMessage;
import br.com.xerox.teste.dto.UserDTO;
import br.com.xerox.teste.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateImpl implements ConstraintValidator<UserCreate, UserDTO> {

    private final UserRepository repository;

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
		
        List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		//Check Code
		var result = repository.findByLabelIgnoreCase(value.getUsername());
		if(result.isPresent()) list.add(new FieldMessage("username", "username already exists"));

		list.stream().forEach(e -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName()).addConstraintViolation();
		});
		
		return list.isEmpty();
    }
}
