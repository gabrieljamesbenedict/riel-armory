package com.gjbmloslos.rielarmoryspringboot.repository;

import com.gjbmloslos.rielarmoryspringboot.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
