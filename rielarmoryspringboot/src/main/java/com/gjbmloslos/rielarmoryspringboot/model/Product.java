package com.gjbmloslos.rielarmoryspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tagId", nullable = false)
    private Set<Tag> tags;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturerId", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column
    private Long caliber;

    @Column
    private Long magazineCapacity;

}
