package com.jmtsu.ms.core.repository;


import com.jmtsu.ms.core.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserModelRepository extends JpaRepository<UserModel, Long> {


   Optional<UserModel> findByUsername(String Username);
}
