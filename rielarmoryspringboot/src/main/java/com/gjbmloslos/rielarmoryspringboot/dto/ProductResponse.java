package com.gjbmloslos.rielarmoryspringboot.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private Integer stock;
    private Double price;

    private String manufacturerName;
    private String categoryName;
    private Set<String> tagNames;
    private String caliberName;

    private Long magazineCapacity;
    private String imageName;
}

