package com.simbirsoft.shatrov.WarehouseApp.repository;

import com.simbirsoft.shatrov.WarehouseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
