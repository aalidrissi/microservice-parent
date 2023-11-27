package com.cih.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cih.inventoryservice.dto.InventoryResponse;
import com.cih.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
		
	 private final InventoryService inventoryService;
	 
	 // Début Phase 1
//	 @GetMapping("/{sku-code}")
//     @ResponseStatus(HttpStatus.OK)
//	 public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//		 return inventoryService.isInStock(skuCode);
//	 }
	 // Fin Phase 1
	 
	 @GetMapping
   @ResponseStatus(HttpStatus.OK)
	 public List <InventoryResponse> isInStock(@RequestParam List <String> skuCode) {
      log.info(" inventory service a reçu pour check la demande du skuCode: {}", skuCode);
	  return inventoryService.findBySkuCodeIn(skuCode);
	 }
	 

}
