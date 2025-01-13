package org.identity.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.identity.validation.ValidIdentityNumber;
import org.identity.validation.countries.Country;

import java.util.ResourceBundle;

public class IdentityNumberValidator implements ConstraintValidator<ValidIdentityNumber, String> {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages");
    private Country country;

    @Override
    public void initialize(ValidIdentityNumber annotation) {
        this.country = annotation.country();
    }

    @Override
    public boolean isValid(String identityNumber, ConstraintValidatorContext context) {
        if (identityNumber == null) {
            return buildErrorMessage(context, MESSAGES.getString("InvalidFormat"));
        }

        if (country == Country.TURKEY) {
            return validateTurkishIdentityNumber(identityNumber, context);
        } else {
            return buildErrorMessage(context, MESSAGES.getString("UnsupportedCountry"));
        }
    }

    private boolean validateTurkishIdentityNumber(String identityNumber, ConstraintValidatorContext context) {
        if (!identityNumber.matches("\\d{11}")) {
            return buildErrorMessage(context, MESSAGES.getString("InvalidFormat"));
        }

        int[] digits = identityNumber.chars().map(c -> c - '0').toArray();

        int sumOdd = 0, sumEven = 0;
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                sumOdd += digits[i];
            } else {
                sumEven += digits[i];
            }
        }

        int tenthDigit = ((sumOdd * 7) - sumEven) % 10;
        if (tenthDigit != digits[9]) {
            return buildErrorMessage(context, MESSAGES.getString("InvalidTurkishIdentityNumber"));
        }

        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            totalSum += digits[i];
        }

        if (totalSum % 10 != digits[10]) {
            return buildErrorMessage(context, MESSAGES.getString("InvalidTurkishIdentityNumber"));
        }

        return true;
    }

    private boolean buildErrorMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
