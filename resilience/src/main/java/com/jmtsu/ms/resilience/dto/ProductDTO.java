package com.jmtsu.ms.resilience.dto;

public record ProductDTO(
        String name,
        String description,
        int qtd,
        double price
) {
}
