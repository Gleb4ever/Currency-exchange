package ru.gleb4ever.currency.controllers.enums;

import ru.gleb4ever.currency.service.exception.AmountSelectMethodNotFoundException;
import java.util.stream.Stream;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum AmountSelectMethod {

  MORE_THAN("more"), LESS_THAN("less");

  String value;

  public static AmountSelectMethod fromValue(String value)
      throws AmountSelectMethodNotFoundException {
    return Stream.of(values())
        .filter(selectMethod -> selectMethod.value.equals(value)).findAny()
        .orElseThrow(AmountSelectMethodNotFoundException::new);
  }
}