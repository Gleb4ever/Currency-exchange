package ru.gleb4ever.currency.request.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyValidator.class)
@Documented
public @interface ValidCurrency {

  String message() default "role not valid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}