package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Tag;
import com.gjbmloslos.rielarmoryspringboot.repository.TagRepository;
import com.gjbmloslos.rielarmoryspringboot.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpli implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + id));
    }

}
