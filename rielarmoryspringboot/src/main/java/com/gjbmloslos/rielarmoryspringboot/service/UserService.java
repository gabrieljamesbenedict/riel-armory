package com.gjbmloslos.rielarmoryspringboot.service;

import com.gjbmloslos.rielarmoryspringboot.model.User;

import java.util.List;

public interface UserService {
    User createUser(User request);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User request);
    void deleteUser(Long id);
}
