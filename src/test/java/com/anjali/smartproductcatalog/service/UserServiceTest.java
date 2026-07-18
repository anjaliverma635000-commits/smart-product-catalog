package com.anjali.smartproductcatalog.service;

import com.anjali.smartproductcatalog.dto.UserDto;
import com.anjali.smartproductcatalog.entity.User;
import com.anjali.smartproductcatalog.repository.UserRepository;
import com.anjali.smartproductcatalog.security.JwtService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddUser() {

        // Arrange
        UserDto dto = new UserDto();

        dto.setName("Anjali");
        dto.setEmail("anjali@gmail.com");
        dto.setAge(22);
        dto.setUsername("anjali");
        dto.setPassword("password123");


        User savedUser = new User();

        savedUser.setId(1L);
        savedUser.setName("Anjali");
        savedUser.setEmail("anjali@gmail.com");
        savedUser.setAge(22);
        savedUser.setUsername("anjali");


        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");


        when(repository.save(any(User.class)))
                .thenReturn(savedUser);


        // Act
        User result = userService.addUser(dto);


        // Assert
        assertEquals("Anjali", result.getName());
        assertEquals("anjali@gmail.com", result.getEmail());
        assertEquals(22, result.getAge());
    }
}