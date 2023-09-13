package com.dipl.order.feign;

import com.dipl.order.feign.dto.CreatePaymentRequest;
import com.dipl.order.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentFeignClient client;


  @Override
  public void createPayment(Long orderId, Long userId, Double price) {
    client.createPayment(new CreatePaymentRequest(userId, price));
  }
}
