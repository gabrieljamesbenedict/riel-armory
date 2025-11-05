package com.gjbmloslos.rielarmoryspringboot.controller;

import com.gjbmloslos.rielarmoryspringboot.model.Category;
import com.gjbmloslos.rielarmoryspringboot.model.Tag;
import com.gjbmloslos.rielarmoryspringboot.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("all")
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getTagById(id));
    }

}
