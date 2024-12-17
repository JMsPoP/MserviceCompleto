package com.jmtsu.ms.resilience.controller;

import com.jmtsu.ms.core.model.ProductModel;
import com.jmtsu.ms.resilience.dto.ProductDTO;
import com.jmtsu.ms.resilience.infra.ProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/resilience")
public class ResilienceController {

    private final ProductClient productClient;


    public ResilienceController(ProductClient productClient) {

        this.productClient = productClient;
    }

    @GetMapping("/{key}")
    public List<ProductModel> productSearch(@PathVariable String key) {
        return productClient.productSearch(key); // Passa o valor da key para o ProductClient
    }

    @GetMapping("findOne/{id}")
    public List<ProductModel> productSearchById(@PathVariable Long id) {
        return productClient.productSearchById(id); // Passa o valor da key para o ProductClient
    }
}




/*

    private static final String BASEURL = "http://localhost:8080/product"; // URL do Product Service

    private final Map<Long, List<ProductDTO>> CACHE = new HashMap<>();

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    // Endpoint para buscar os produtos
    @GetMapping("/product")
    @CircuitBreaker(name = "ProductCache", fallbackMethod = "productCACHE")
    public List<ProductDTO> getProducts() {
        ResponseEntity<List<ProductDTO>> response = restTemplate.exchange(
                BASEURL,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDTO>>() {}
        );
        List<ProductDTO> products = response.getBody();

        // Usando um identificador único para o cache (como o timestamp ou qualquer outro critério)
        CACHE.put(System.currentTimeMillis(), products); // Adiciona os produtos ao cache com um identificador único
        System.out.println("Cache atualizado com sucesso");

        return products;
    }

    // Método de fallback que usa o cache
    private List<ProductDTO> productCACHE(Throwable e){
        if (CACHE != null && !CACHE.isEmpty()) {
            // Pode escolher a última entrada no cache, por exemplo
            return new ArrayList<>(CACHE.values()).get(CACHE.size() - 1);
        }
        // Caso o cache também esteja vazio, retorna uma lista vazia
        return Collections.emptyList();
    }

    // Bean do RestTemplate para facilitar as requisições HTTP
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
*/