package com.gjbmloslos.rielarmoryspringboot.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductRequest {
    private String name;                // required
    private String description;         // optional
    private Integer stock;              // required
    private Double price;               // required
    private Long manufacturerId;        // reference to existing Manufacturer
    private Long categoryId;            // reference to existing Category
    private Set<Long> tagIds;           // references to existing Tags
    private Long caliberId;             // optional
    private Long magazineCapacity;      // optional
}

