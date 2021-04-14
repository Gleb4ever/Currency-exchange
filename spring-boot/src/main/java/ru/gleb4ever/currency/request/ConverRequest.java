package ru.gleb4ever.currency.request;

import ru.gleb4ever.currency.request.validation.ValidCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConverRequest {

  final static String REF_CURRENCY_NOT_FOUND = "reference currency not found";
  final static String TARGET_CURRENCY_NOT_FOUND = "target currency not found";
  final static String INCORRECT_AMT = "incorrect amount";
  final static String NOT_NULL_AMT = "amount is null";
  final static String INCORRECT_USER_EMAIL = "incorrect email";

  @JsonProperty(value = "email", required = true)
  @NotEmpty(message = INCORRECT_USER_EMAIL)
  @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = INCORRECT_USER_EMAIL)
  String userEmail;

  @JsonProperty(value = "reference", required = true)
  @ValidCurrency(message = REF_CURRENCY_NOT_FOUND)
  String referenceCurrency;

  @JsonProperty(value = "target", required = true)
  @ValidCurrency(message = TARGET_CURRENCY_NOT_FOUND)
  String targetCurrency;

  @NotNull(message = NOT_NULL_AMT)
  @DecimalMin(value = "0.0", inclusive = false, message = INCORRECT_AMT)
  Double amount;
}