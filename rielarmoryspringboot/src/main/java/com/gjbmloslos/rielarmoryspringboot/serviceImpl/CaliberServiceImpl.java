package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Caliber;
import com.gjbmloslos.rielarmoryspringboot.repository.CaliberRepository;
import com.gjbmloslos.rielarmoryspringboot.service.CaliberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaliberServiceImpl implements CaliberService {

    private final CaliberRepository caliberRepository;

    @Override
    public List<Caliber> getAllCalibers() {
        return caliberRepository.findAll();
    }

    @Override
    public Caliber getCaliberById(Long id) {
        return caliberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Caliber not found with id " + id));
    }


}
