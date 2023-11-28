 package com.cih.orderservice.controller;


import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cih.orderservice.dto.OrderRequest;
import com.cih.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	//début phase 1
	private final OrderService orderService;
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Lancement de la commande");
		orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
	}
//	//fin phase 1
//	
////	@ExceptionHandler(IllegalArgumentException.class)
////	public String errorIllegalArgumentException() {
////		return "Erreur de commande";
////	}
//	
    
	
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
//        log.info("Placing Order");
//        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
//    }
//    
//    
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
//    	 log.info("Erreur de commande lors de l'Execution Fallback logic");
//        return "Oops! Something went wrong, please order after some time!";
//    }
}
