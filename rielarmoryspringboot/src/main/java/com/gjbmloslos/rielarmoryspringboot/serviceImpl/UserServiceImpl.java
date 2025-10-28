package com.gjbmloslos.rielarmoryspringboot.serviceImpl;

import com.gjbmloslos.rielarmoryspringboot.model.User;
import com.gjbmloslos.rielarmoryspringboot.repository.UserRepository;
import com.gjbmloslos.rielarmoryspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User request) {
        User user = User.builder()
                .name(request.getName())
                .description(request.getDescription())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public User updateUser(Long id, User request) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setName(request.getName());
        user.setDescription(request.getDescription());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}
