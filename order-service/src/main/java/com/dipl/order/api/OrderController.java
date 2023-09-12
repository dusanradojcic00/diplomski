package com.dipl.order.api;

import com.dipl.order.api.dto.CreateOrderRequest;
import com.dipl.order.api.dto.CreateOrderResponse;
import com.dipl.order.api.dto.OrderDtoMapper;
import com.dipl.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderDtoMapper mapper;
  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.OK)
  public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {

    var order = orderService.createOrder(request);

    return mapper.toResponse(order);

  }

  @PostMapping("/async")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public CreateOrderResponse createOrderAsync(@RequestBody CreateOrderRequest request) {

    var order = orderService.createOrderAsync(request);

    return mapper.toResponse(order);
  }
}
