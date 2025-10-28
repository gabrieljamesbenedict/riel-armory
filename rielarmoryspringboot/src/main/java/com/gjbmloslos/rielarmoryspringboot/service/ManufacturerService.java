package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer getManufacturerById(Long id);
    List<Manufacturer> getAllManufacturers();
}
