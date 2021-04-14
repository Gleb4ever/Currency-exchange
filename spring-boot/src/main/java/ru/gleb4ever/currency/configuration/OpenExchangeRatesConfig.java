package ru.gleb4ever.currency.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpenExchangeRatesConfig {

  String appId;
  String baseUrl;

  @Autowired
  public OpenExchangeRatesConfig(@Value("${openexchangerates.appId}") String appId,
      @Value("${openexchangerates.baseUrl}") String baseUrl) {
    this.appId = appId;
    this.baseUrl = baseUrl;
  }
}