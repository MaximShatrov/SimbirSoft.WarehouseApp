package com.simbirsoft.shatrov.WarehouseApp.repository;

import com.simbirsoft.shatrov.WarehouseApp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
