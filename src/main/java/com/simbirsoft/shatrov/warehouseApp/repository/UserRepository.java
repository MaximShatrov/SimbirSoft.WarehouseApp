package com.simbirsoft.shatrov.warehouseApp.repository;

import com.simbirsoft.shatrov.warehouseApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
