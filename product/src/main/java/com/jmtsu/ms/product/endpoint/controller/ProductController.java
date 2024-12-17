package com.jmtsu.ms.product.endpoint.controller;


import com.jmtsu.ms.core.model.ProductModel;

import com.jmtsu.ms.core.repository.ProductRepository;
import com.jmtsu.ms.product.endpoint.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
	private final ProductService productService;
	private final ProductRepository productRepository;

	@PostMapping("/envio")
	public String sendProduct(@RequestBody ProductModel productModel, @RequestParam String email) {
		return productService.sendProduct(productModel, email);
	}

	@GetMapping
	public List<ProductModel> getProducts(@RequestParam String key) {
		log.info("Received request for products with key: {}", key);

		// Aqui, você pode usar o parâmetro 'key' como desejar (por exemplo, para filtrar produtos ou acessar um cache)
		List<ProductModel> products = productRepository.findAll();

		// Se quiser usar a key para algum tipo de lógica adicional, pode fazer isso aqui
		// Exemplo: verificar se o cache contém dados para a key, etc.

		return products;


	}

	@GetMapping("/findOne")
	public ResponseEntity<ProductModel> findOne(@RequestParam Long id) {
		var product = productRepository.findProductById(id);
		return ResponseEntity.ok(product.get());
	}
}
@RestController
@RequestMapping("/course")
class HttpController {
	@GetMapping("/public")
	String privateRoute( ) {
		return String.format("""
				<h1>Private route, only authorized personal! 🔐  </h1>
				""");
	}
}