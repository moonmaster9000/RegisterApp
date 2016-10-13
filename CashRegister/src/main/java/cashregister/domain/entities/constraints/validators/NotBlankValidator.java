package cashregister.domain.entities.constraints.validators;

import cashregister.domain.entities.constraints.NotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

    @Override
    public void initialize(NotBlank notBlank) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.equals("") || s.trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
