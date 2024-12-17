package com.jmtsu.ms.product.endpoint.service;

import com.jmtsu.ms.core.model.UserModel;
import com.jmtsu.ms.core.model.ProductModel;
import com.jmtsu.ms.core.repository.ProductRepository;
import com.jmtsu.ms.core.repository.UserModelRepository;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

	private final ProductRepository productRepository;
	private final UserModelRepository userRepository;

	@Bulkhead(name = "ProductSend")
	public String sendProduct(ProductModel productModel, String email) {
		try {
			Optional<UserModel> userOptional = userRepository.findByEmail(email);

			if (userOptional.isEmpty()) {
				throw new RuntimeException("Usuário não encontrado com o email: " + email);
			}

			UserModel user = userOptional.get();

			productModel.setUser(user);
			productModel.setTimeProductSend(LocalDateTime.now());

			productRepository.save(productModel);
			return "Produto adicionado ao sistema com sucesso!";
		} catch (Exception e) {
			log.error("Erro ao adicionar produto no sistema", e);
			return "Erro ao adicionar o produto.";
		}
	}
}
