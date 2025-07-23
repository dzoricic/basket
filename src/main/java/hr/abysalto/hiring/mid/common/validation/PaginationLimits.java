package hr.abysalto.hiring.mid.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PaginationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PaginationLimits {

    String message() default "Invalid pagination value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
