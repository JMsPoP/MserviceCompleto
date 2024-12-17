package com.jmtsu.ms.core.repository;


import com.jmtsu.ms.core.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserModelRepository extends JpaRepository<UserModel, Long> {


   Optional<UserModel> findByEmail(String Email);

   UserModel findByVerificationCode(UUID VerificationCode);
}
