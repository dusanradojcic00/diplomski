package com.dipl.order.feign;

import com.dipl.order.feign.dto.CreatePaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "payment",
    value = "payment",
    url = "http://localhost:8083/api/v1/payments")
public interface PaymentFeignClient {

  @PostMapping
  void createPayment(@RequestBody CreatePaymentRequest request);
}
