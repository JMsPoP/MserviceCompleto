package com.jmtsu.ms.core.repository;


import com.jmtsu.ms.core.model.UserModel;
<<<<<<< HEAD
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
=======
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf


public interface UserModelRepository extends JpaRepository<UserModel, Long> {


<<<<<<< HEAD
   Optional<UserModel> findByEmail(String Email);

   UserModel findByVerificationCode(UUID VerificationCode);
=======
   Optional<UserModel> findByUsername(String Username);
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
}
