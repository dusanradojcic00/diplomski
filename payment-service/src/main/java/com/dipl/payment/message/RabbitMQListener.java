package com.dipl.payment.message;

import com.dipl.payment.api.dto.CreatePaymentRequest;
import com.dipl.payment.message.dto.CreatePaymentEvent;
import com.dipl.payment.message.dto.PaymentCompletedEvent;
import com.dipl.payment.service.PaymentService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

  public static final String CREATE_PAYMENT_QUEUE = "create-payment-queue";
  public static final String PAYMENT_COMPLETED_QUEUE = "payment-completed-queue";

  private final RabbitTemplate rabbitTemplate;
  private final PaymentService paymentService;
  private final Executor internalExecutor;

  public RabbitMQListener(RabbitTemplate rabbitTemplate,
      PaymentService paymentService,
      @Qualifier("internalTaskExecutor") Executor internalExecutor) {
    this.rabbitTemplate = rabbitTemplate;
    this.paymentService = paymentService;
    this.internalExecutor = internalExecutor;
  }

  @RabbitListener(queues = CREATE_PAYMENT_QUEUE)
  public void receive(@Payload CreatePaymentEvent event) {

    CompletableFuture.runAsync(
            () -> paymentService.create(new CreatePaymentRequest(event.customerId(), event.total())),
            internalExecutor)
        .thenRun(() -> rabbitTemplate.convertAndSend(PAYMENT_COMPLETED_QUEUE,
            new PaymentCompletedEvent(event.orderId())));
  }
}
