package com.anjali.smartproductcatalog.service;

import com.anjali.smartproductcatalog.dto.LoginRequest;
import com.anjali.smartproductcatalog.dto.UserDto;
import com.anjali.smartproductcatalog.entity.User;
import com.anjali.smartproductcatalog.repository.UserRepository;
import com.anjali.smartproductcatalog.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Add User
    public User addUser(UserDto userDto) {

        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setUsername(userDto.getUsername());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return repository.save(user);
    }

    // Get All Users
    public List<User> getAll() {
        return repository.findAll();
    }

    // Pagination & Sorting
    public Page<User> getUsers(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
    }

    // Login
    public String login(LoginRequest request) {

        Optional<User> optionalUser =
                repository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}