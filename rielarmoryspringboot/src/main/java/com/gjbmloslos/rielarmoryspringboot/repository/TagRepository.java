package com.gjbmloslos.rielarmoryspringboot.repository;

import com.gjbmloslos.rielarmoryspringboot.model.Manufacturer;
import com.gjbmloslos.rielarmoryspringboot.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
