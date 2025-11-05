package com.gjbmloslos.rielarmoryspringboot.controller;

import com.gjbmloslos.rielarmoryspringboot.model.Caliber;
import com.gjbmloslos.rielarmoryspringboot.model.Category;
import com.gjbmloslos.rielarmoryspringboot.service.CaliberService;
import com.gjbmloslos.rielarmoryspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calibers")
@RequiredArgsConstructor
public class CaliberController {

    private final CaliberService caliberService;

    @GetMapping("all")
    public ResponseEntity<List<Caliber>> getAllCalibers() {
        return ResponseEntity.ok(caliberService.getAllCalibers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caliber> getCaliberById(@PathVariable Long id) {
        return ResponseEntity.ok(caliberService.getCaliberById(id));
    }

}
