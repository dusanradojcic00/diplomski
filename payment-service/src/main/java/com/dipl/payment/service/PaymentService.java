package com.dipl.payment.service;

import com.dipl.payment.api.dto.CreatePaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

  public void create(CreatePaymentRequest request) {
    try {
      //Simulate call to payment provider eg. Stripe
      Thread.sleep(250);
      log.info("'{}'$ captured from user with id '{}'", request.price(), request.userId());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
