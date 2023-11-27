	package com.cih.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.cih.orderservice.dto.InventoryResponse;
import com.cih.orderservice.dto.OrderLineItemsDto;
import com.cih.orderservice.dto.OrderRequest;
import com.cih.orderservice.model.Order;
import com.cih.orderservice.model.OrderLineItems;
import com.cih.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	//Nécessaire pour la Phase 2
	private final WebClient.Builder webClient;

	public void placeOrder(OrderRequest orderRequest) {
	    // début phase 1 
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);


        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

       // A garder en phase 1
        //orderRepository.save(order);
       // fin phase 1
//        
//       //Début phase 2 : Appel web service Inventory Service
////        phase 2:
//        InventoryResponse[] inventoryResponseArray =  webClient.build().get().uri("http://localhost:8686/api/inventory",  
//     		   uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//         .retrieve()
//         .bodyToMono(InventoryResponse[].class)
//         .block();
//        
//        // phase 3
        InventoryResponse[] inventoryResponseArray =  webClient.build().get().uri("http://inventory-service/api/inventory",  
    		   uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
        .retrieve()
        .bodyToMono(InventoryResponse[].class)
        .block();
       
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);        
       if(allProductsInStock) {
    	  orderRepository.save(order);
       } else {
    	   throw new IllegalArgumentException("Product is not in stock, please try again later");
       }
      
       //Fin phase 2  
	}
	
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
