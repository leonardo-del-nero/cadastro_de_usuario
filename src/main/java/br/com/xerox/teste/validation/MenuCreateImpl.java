package br.com.xerox.teste.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.xerox.teste.common.FieldMessage;
import br.com.xerox.teste.dto.MenuDTO;
import br.com.xerox.teste.repository.MenuRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuCreateImpl implements ConstraintValidator<MenuCreate,MenuDTO> {

    private final MenuRepository repository;

    @Override
    public boolean isValid(MenuDTO value, ConstraintValidatorContext context) {
		
        List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		//Check Code
		var result = repository.findByLabelIgnoreCase(value.getLabel());
		if(result.isPresent()) list.add(new FieldMessage("label", "Label already exists"));

		list.stream().forEach(e -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName()).addConstraintViolation();
		});
		
		return list.isEmpty();
    }
}
