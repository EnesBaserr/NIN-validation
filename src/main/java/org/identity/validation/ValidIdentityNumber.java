package org.identity.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.identity.validation.countries.Country;
import org.identity.validation.validators.IdentityNumberValidator;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdentityNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIdentityNumber {
    Country country() default Country.TURKEY;
    String message() default "{InvalidNationalIdentityNumber}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
