package br.com.vroom.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfConstraintValidator implements ConstraintValidator<CpfValido, String> {

    @Override
    public void initialize(CpfValido constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return true;
        }
        String cleanedCpf = cpf.replaceAll("[^\\d]", "");
        if (cleanedCpf.length() != 11 || cleanedCpf.chars().distinct().count() == 1) {
            return false;
        }

        try {
            int[] digits = cleanedCpf.chars().map(Character::getNumericValue).toArray();

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += digits[i] * (10 - i);
            }
            int remainder = sum % 11;
            int firstVerifierDigit = (remainder < 2) ? 0 : 11 - remainder;

            if (digits[9] != firstVerifierDigit) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += digits[i] * (11 - i);
            }
            remainder = sum % 11;
            int secondVerifierDigit = (remainder < 2) ? 0 : 11 - remainder;

            if (digits[10] != secondVerifierDigit) {
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
