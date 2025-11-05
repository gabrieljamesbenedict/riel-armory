package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(Long id);
    List<Tag> getAllTags();
}
