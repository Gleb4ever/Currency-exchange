package ru.gleb4ever.currency.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "exchange_requests")
@NoArgsConstructor
public class ExchangeRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  @Column(name = "user_email", nullable = false)
  String userEmail;
  @Column(name = "reference_currency", nullable = false)
  String referenceCurrency;
  @Column(name = "target_currency", nullable = false)
  String targetCurrency;
  @Column(nullable = false)
  double amount;
  @Column(nullable = false)
  double result;
  @Column(nullable = false)
  double rate;
}