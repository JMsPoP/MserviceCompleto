package com.jmtsu.ms.core.repository;


import com.jmtsu.ms.core.model.UserModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserModelRepository extends PagingAndSortingRepository<UserModel, Long> {

   UserModel findByUsername(String Username);
}
