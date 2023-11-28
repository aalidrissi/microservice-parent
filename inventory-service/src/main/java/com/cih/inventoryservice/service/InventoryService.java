package com.cih.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cih.inventoryservice.dto.InventoryResponse;
import com.cih.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
    @Transactional(readOnly = true)
	public boolean isInStock(String skuCode) {	
		return inventoryRepository.findBySkuCode(skuCode).isPresent();
	}
    
    //Début phase 2
    @Transactional(readOnly = true)
//    @SneakyThrows
    public List <InventoryResponse> findBySkuCodeIn(List <String> skuCode) {
//    	log.info("start sleep");
//        Thread.sleep(10000);
//    	log.info("end sleep");
//        
    	 return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                 .map(inventory ->
                         InventoryResponse.builder()
                                 .skuCode(inventory.getSkuCode())
                                 .isInStock(inventory.getQuantity() > 0)
                                 .build()
                 ).toList();
    }
    //Fin phase 2

}
