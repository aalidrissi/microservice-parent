package com.cih.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cih.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
    //Début Phase 2
    Optional<Inventory> findBySkuCode(String skuCode);
    //Fin Phase 2
    List <Inventory> findBySkuCodeIn (List <String> skuCode);
}
