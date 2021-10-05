package com.simbirsoft.shatrov.warehouseApp.repository;

import com.simbirsoft.shatrov.warehouseApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
