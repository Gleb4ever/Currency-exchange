package ru.gleb4ever.currency.configuration;

import java.text.NumberFormat;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberFormatConfig {

  @Bean
  public NumberFormat numberFormat() {
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
    numberFormat.setMaximumFractionDigits(2);
    return numberFormat;
  }
}