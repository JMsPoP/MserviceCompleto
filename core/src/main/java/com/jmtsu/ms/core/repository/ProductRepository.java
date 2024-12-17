package com.jmtsu.ms.core.repository;

import com.jmtsu.ms.core.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findProductById(Long id);

}
