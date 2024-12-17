package com.jmtsu.ms.core.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductModel implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;
    @NotNull(message = "The field 'description' is mandatory")
    @Column(nullable = false)
    private String description;
    @NotNull(message = "The field 'qtd' is mandatory")
    @Column(nullable = false)
    private int qtd;
    @NotNull(message = "The field 'price' is mandatory")
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserModel user;

    private LocalDateTime timeProductSend;

    public ProductModel(ProductModel product) {
    }
}
