package ru.gleb4ever.currency.service.impl;

import ru.gleb4ever.currency.model.ExchangeRequest;
import ru.gleb4ever.currency.repositories.ExchangeRequestRepository;
import ru.gleb4ever.currency.request.ConverRequest;
import ru.gleb4ever.currency.response.ExchangeResultResponse;
import ru.gleb4ever.currency.service.ExchangeService;
import ru.gleb4ever.currency.service.RateHelper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.text.NumberFormat;
import java.util.Currency;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class ExchangeServiceImpl implements ExchangeService {

  RateHelper rateHelper;
  NumberFormat numberFormat;
  ExchangeRequestRepository exchangeRequestRepository;

  @Autowired
  public ExchangeServiceImpl(RateHelper rateHelper, NumberFormat numberFormat,
      ExchangeRequestRepository exchangeRequestRepository) {
    this.rateHelper = rateHelper;
    this.numberFormat = numberFormat;
    this.exchangeRequestRepository = exchangeRequestRepository;
  }

  @Override
  public ResponseEntity<?> convert(ConverRequest convertRequest) {
    double exchangeRate = rateHelper.getCurrencyExchangeRate(convertRequest.getReferenceCurrency(),
        convertRequest.getTargetCurrency());
    double result = convertRequest.getAmount() * exchangeRate;
    if (result > 0) {
      saveRequest(convertRequest, exchangeRate, result);
    }
    return ResponseEntity.ok(buildResultString(result, convertRequest.getTargetCurrency()));
  }


  private ExchangeResultResponse buildResultString(double result, String targetCurrencyCode) {
    numberFormat.setCurrency(Currency.getInstance(targetCurrencyCode));
    return ExchangeResultResponse.builder().result(numberFormat.format(result)).build();
  }


  @Async
  private void saveRequest(ConverRequest convertRequest, double exchangeRate, double result) {
    ExchangeRequest exchangeRequest = new ExchangeRequest();
    exchangeRequest.setUserEmail(convertRequest.getUserEmail());
    exchangeRequest.setReferenceCurrency(convertRequest.getReferenceCurrency());
    exchangeRequest.setTargetCurrency(convertRequest.getTargetCurrency());
    exchangeRequest.setAmount(convertRequest.getAmount());
    exchangeRequest.setRate(exchangeRate);
    exchangeRequest.setResult(result);
    exchangeRequestRepository.save(exchangeRequest);
  }

}