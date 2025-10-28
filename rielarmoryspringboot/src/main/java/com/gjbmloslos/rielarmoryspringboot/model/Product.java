package com.gjbmloslos.rielarmoryspringboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "manufacturerId", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column
    private Long caliber;

    @Column
    private Long magazineCapacity;

}
