package com.dipl.payment.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class EventExecutorConfig {

  @Bean("internalTaskExecutor")
  public Executor getInternalTaskAsyncExecutor() {
    return getThreadPoolTaskExecutor();
  }

  private Executor getThreadPoolTaskExecutor() {

    var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(50);
    threadPoolTaskExecutor.setCorePoolSize(25);
    threadPoolTaskExecutor.setQueueCapacity(10000);
    threadPoolTaskExecutor.setRejectedExecutionHandler(
        new CustomRetryExecutionHandler(getFallbackTaskExecutor()));
    threadPoolTaskExecutor.setThreadNamePrefix("internal-");
//    threadPoolTaskExecutor.setTaskDecorator(new MdcTaskDecorator());
    threadPoolTaskExecutor.initialize();

    return threadPoolTaskExecutor;
  }

  private Executor getFallbackTaskExecutor() {

    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(20);
    threadPoolTaskExecutor.setCorePoolSize(10);
    threadPoolTaskExecutor.setQueueCapacity(15000);
    threadPoolTaskExecutor.setThreadNamePrefix("fallback");
    threadPoolTaskExecutor.initialize();

    return threadPoolTaskExecutor;
  }
}
