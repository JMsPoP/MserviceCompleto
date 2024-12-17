package com.jmtsu.ms.resilience.infra;

import com.jmtsu.ms.core.model.ProductModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.Optional;

@Component
public class ResilienceClientImpl implements ProductClient {
    private final Logger logger = LoggerFactory.getLogger(ResilienceClientImpl.class);
    private final RestTemplate restTemplate;

    // URL do produto com a chave como parâmetro
    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8080/product")
            .queryParam("key", "{key}")
            .encode()
            .toUriString();

    // Cache usando Caffeine
    private final Cache<String, List<ProductModel>> CACHE;

    public ResilienceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        // Inicializando o cache com expiração de 10 minutos e tamanho máximo de 100 itens
        this.CACHE = Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)  // Tempo de expiração dos itens
                .maximumSize(100)  // Limita o tamanho do cache a 100 itens
                .build();

        this.CACHE2 = Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    @Override
    @CircuitBreaker(name = "ProductCACHE", fallbackMethod = "productCACHE")
    public List<ProductModel> productSearch(String key) {
        // Verifica se o cache contém produtos para a chave
        if (CACHE.getIfPresent(key) != null) {
            logger.info("Buscando do cache");
            return CACHE.getIfPresent(key);  // Retorna os produtos do cache
        }

        // Caso não tenha no cache, realiza a requisição externa
        return productRequest(key);
    }

    private List<ProductModel> productRequest(String key) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("key", "{key}");

        logger.info("Looking for product");
        final ProductModel[] products;

        try {
            products = restTemplate.getForObject(API_URL, ProductModel[].class, parametros);
        } catch (Exception e) {
            logger.error("Error");
            throw e;
        }

        // Armazena os produtos no cache
        logger.info("Feeding cache");
        CACHE.put(key, Arrays.asList(products));

        return Arrays.asList(products);
    }

    // Método fallback que é chamado se o circuito for aberto ou ocorrer um erro
    private List<ProductModel> productCACHE(String key, Throwable e) {
        logger.info("Buscando no cache");
        // Retorna os produtos do cache ou uma lista vazia caso não tenha nada no cache
        return Optional.ofNullable(CACHE.getIfPresent(key))
                .orElse(new ArrayList<>());  // Evita retorno de null, retornando uma lista vazia
    }









    private final static String API_URL2 = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8080/product/findOne")
            .queryParam("id", "{id}")
            .encode()
            .toUriString();


    private final Cache<Long, List<ProductModel>> CACHE2;


    @Override
    @CircuitBreaker(name = "ProductCacheFindById", fallbackMethod = "productCacheFindById")
    public List<ProductModel> productSearchById(Long id) {
        if (CACHE2.getIfPresent(id) != null) {
            logger.info("Looking in cache");
            return CACHE2.getIfPresent(id);  // Retorna os produtos do cache
        }
        return productRequestById(id);
    }



    private List<ProductModel> productRequestById(Long id) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);

        logger.info("Looking for products by id");
        final ProductModel product;  // Mudança aqui, para um único objeto

        try {
            product = restTemplate.getForObject(API_URL2, ProductModel.class, parametros); // Alteração aqui
        } catch (Exception e) {
            logger.error("Error!");
            throw e;
        }

        logger.info("Feeding cache2");
        CACHE2.put(id, Arrays.asList(product));  // Converte para lista para armazenar no cache

        return Arrays.asList(product);  // Retorna como lista
    }


    private List<ProductModel> productCacheFindById(Long id, Throwable e) {
        logger.info("Looking on cache");
        // Retorna os produtos do cache ou uma lista vazia caso não tenha nada no cache
        return Optional.ofNullable(CACHE2.getIfPresent(id))
                .orElse(new ArrayList<>());  // Evita retorno de null, retornando uma lista vazia
    }

}
