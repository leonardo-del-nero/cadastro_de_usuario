package br.com.xerox.teste.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MenuCreateImpl.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuCreate {
    String message() default "Error Inserting";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
