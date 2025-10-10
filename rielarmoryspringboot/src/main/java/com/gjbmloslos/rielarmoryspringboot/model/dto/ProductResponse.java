package com.gjbmloslos.rielarmoryspringboot.model.dto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock
) {}
