package br.com.xerox.teste.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.HandlerMapping;

import br.com.xerox.teste.common.FieldMessage;
import br.com.xerox.teste.dto.MenuDTO;
import br.com.xerox.teste.repository.MenuRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuUpdateImpl implements ConstraintValidator<MenuUpdate,MenuDTO> {

    private final MenuRepository repository;

	private final HttpServletRequest request;

    @SuppressWarnings("unchecked")
	@Override
    public boolean isValid(MenuDTO value, ConstraintValidatorContext context) {
		
        List<FieldMessage> list = new ArrayList<FieldMessage>();

		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long uriId = Long.parseLong(map.get("id"));
		
		//Check Code
		var result = repository.findByLabelIgnoreCase(value.getLabel());
		if(result.isPresent() && !result.get().getId().equals(uriId)) list.add(new FieldMessage("label", "Label already exists"));

		list.stream().forEach(e -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName()).addConstraintViolation();
		});
		
		return list.isEmpty();
    }
}
