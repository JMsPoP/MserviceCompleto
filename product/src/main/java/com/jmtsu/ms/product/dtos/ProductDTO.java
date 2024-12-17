package com.jmtsu.ms.product.dtos;

public record ProductDTO(
 String name,
 String description,
 int qtd,
 double price
) { }
