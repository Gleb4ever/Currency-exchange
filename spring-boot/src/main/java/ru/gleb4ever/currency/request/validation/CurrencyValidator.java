package ru.gleb4ever.currency.request.validation;

import org.apache.logging.log4j.util.Strings;

import javax.validation.*;
import java.util.Currency;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

  @Override
  public void initialize(ValidCurrency constraintAnnotation) {

  }

  @Override
  public boolean isValid(String currencyCode,
      ConstraintValidatorContext constraintValidatorContext) {
    return Strings.isNotEmpty(currencyCode) && isCurrencyCodeExist(currencyCode);
  }

  private boolean isCurrencyCodeExist(String currencyCode) {
    return Currency.getAvailableCurrencies().stream()
        .anyMatch(currency -> currency.getCurrencyCode().equals(currencyCode));
  }
}