package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    List<Role> getAllRoles();
}
