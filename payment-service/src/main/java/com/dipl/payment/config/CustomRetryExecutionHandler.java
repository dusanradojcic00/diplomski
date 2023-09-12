package com.dipl.payment.config;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryExecutionHandler implements RejectedExecutionHandler {

  private final Executor fallbackExecutor;

  public CustomRetryExecutionHandler(Executor fallbackExecutor) {
    this.fallbackExecutor = fallbackExecutor;
  }

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    log.warn("Worker '{}' is rejected probably because the queue is full", r.toString());

    log.warn("Trying to execute worker with alternate executor");

    try {
      fallbackExecutor.execute(r);
    } catch (Exception e) {
      log.error("Failure to re-execute worker", e);
    }
  }
}