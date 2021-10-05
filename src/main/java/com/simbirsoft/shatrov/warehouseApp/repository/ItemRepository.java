package com.simbirsoft.shatrov.warehouseApp.repository;

import com.simbirsoft.shatrov.warehouseApp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
