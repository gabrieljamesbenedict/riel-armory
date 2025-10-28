package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Manufacturer;
import com.gjbmloslos.rielarmoryspringboot.repository.ManufacturerRepository;
import com.gjbmloslos.rielarmoryspringboot.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id " + id));
    }

}
