package com.dipl.payment.api;

import com.dipl.payment.api.dto.CreatePaymentRequest;
import com.dipl.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService service;

  @PostMapping
  public void createPayment(@RequestBody CreatePaymentRequest request){
    service.create(request);
  }
}
