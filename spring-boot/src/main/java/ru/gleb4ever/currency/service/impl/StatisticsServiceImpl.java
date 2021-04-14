package ru.gleb4ever.currency.service.impl;

import ru.gleb4ever.currency.controllers.enums.AmountSelectMethod;
import ru.gleb4ever.currency.model.ExchangeRequest;
import ru.gleb4ever.currency.repositories.ExchangeRequestRepository;
import ru.gleb4ever.currency.service.StatisticsService;
import ru.gleb4ever.currency.service.exception.EmptyResultException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticsServiceImpl implements StatisticsService {

  ExchangeRequestRepository exchangeRequestRepository;

  @Autowired
  public StatisticsServiceImpl(ExchangeRequestRepository exchangeRequestRepository) {
    this.exchangeRequestRepository = exchangeRequestRepository;
  }

  @Override
  public ResponseEntity<?> getUsersInfoByAmount(AmountSelectMethod amountSelectMethod,
      double amount)
      throws EmptyResultException {
    switch (amountSelectMethod) {
      case MORE_THAN:
        return ResponseEntity
            .ok(mapResult(exchangeRequestRepository.findAllByAmountGreaterThan(amount)));
      case LESS_THAN:
        return ResponseEntity
            .ok(mapResult(exchangeRequestRepository.findAllByAmountLessThan(amount)));
      default:
        throw new EmptyResultException();
    }
  }

  @Override
  public ResponseEntity<?> getUsersByTotalAmount(double amount) throws EmptyResultException {
    if (amount <= 0) {
      log.warn("requested amount is negative or zero, throwing EmptyResultException");
      throw new EmptyResultException();
    }
    List<String> resultList = exchangeRequestRepository.findByTotalAmountGreaterThen(amount)
        .orElseThrow(EmptyResultException::new);
    return ResponseEntity.ok(resultList);
  }

  @Override
  public ResponseEntity<?> getExchangeRating() {
    Map<String, Long> result = exchangeRequestRepository.findAll().stream()
        .collect(
            Collectors.groupingBy(er -> er.getReferenceCurrency().concat(er.getTargetCurrency()),
                Collectors.counting()));
    return ResponseEntity.ok(result);
  }

  private Set<String> mapResult(Optional<List<ExchangeRequest>> queryListResult)
      throws EmptyResultException {
    return queryListResult.map(list -> list.stream()
        .map(ExchangeRequest::getUserEmail).collect(Collectors.toSet()))
        .orElseThrow(EmptyResultException::new);
  }


}