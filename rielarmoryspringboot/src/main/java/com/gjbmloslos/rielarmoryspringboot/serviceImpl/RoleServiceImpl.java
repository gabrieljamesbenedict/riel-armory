package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.Role;
import com.gjbmloslos.rielarmoryspringboot.repository.RoleRepository;
import com.gjbmloslos.rielarmoryspringboot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(Long id) {
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

}
