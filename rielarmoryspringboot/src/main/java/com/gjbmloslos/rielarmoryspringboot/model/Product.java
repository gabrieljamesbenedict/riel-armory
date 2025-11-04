package com.gjbmloslos.rielarmoryspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturerId", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column
    private String caliber;

    @Column
    private Long magazineCapacity;

}
