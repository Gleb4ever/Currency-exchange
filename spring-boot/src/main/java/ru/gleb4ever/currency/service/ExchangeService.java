package ru.gleb4ever.currency.service;

import ru.gleb4ever.currency.request.ConverRequest;
import org.springframework.http.ResponseEntity;

public interface ExchangeService {

  ResponseEntity<?> convert(ConverRequest convertRequest);

}
