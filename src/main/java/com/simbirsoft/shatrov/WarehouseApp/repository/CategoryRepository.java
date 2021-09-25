package com.simbirsoft.shatrov.WarehouseApp.repository;

import com.simbirsoft.shatrov.WarehouseApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
