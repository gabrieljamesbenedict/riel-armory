package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Caliber;

import java.util.List;

public interface CaliberService {
    Caliber getCaliberById(Long id);
    List<Caliber> getAllCalibers();
}
