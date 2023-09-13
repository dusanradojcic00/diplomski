package com.dipl.order.service;

public interface PaymentService {

  void createPayment(Long orderId, Long userId, Double price);
}
