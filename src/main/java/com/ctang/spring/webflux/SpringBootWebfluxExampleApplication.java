package com.ctang.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication(scanBasePackages = "com.ctang.spring.webflux")
@EnableFeignClients(basePackages = "com.ctang.spring.webflux.client") // Adjust if necessary
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class SpringBootWebfluxExampleApplication {


  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebfluxExampleApplication.class, args);
  }

}
