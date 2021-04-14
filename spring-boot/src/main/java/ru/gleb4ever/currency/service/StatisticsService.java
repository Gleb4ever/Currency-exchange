package ru.gleb4ever.currency.service;

import ru.gleb4ever.currency.controllers.enums.AmountSelectMethod;
import ru.gleb4ever.currency.service.exception.EmptyResultException;
import org.springframework.http.ResponseEntity;

public interface StatisticsService {

  ResponseEntity<?> getUsersInfoByAmount(AmountSelectMethod amountSelectMethod, double amount)
      throws EmptyResultException;

  ResponseEntity<?> getUsersByTotalAmount(double value) throws EmptyResultException;

  ResponseEntity<?> getExchangeRating();

}
