package com.dipl.order.service;

public interface PaymentService {

  void createPayment(Long userId, Double price);
}
