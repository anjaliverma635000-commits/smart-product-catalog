package com.anjali.smartproductcatalog.service;

import com.anjali.smartproductcatalog.dto.UserDto;
import com.anjali.smartproductcatalog.entity.User;
import com.anjali.smartproductcatalog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAll() {

        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Anjali");
        user1.setEmail("anjali@gmail.com");
        user1.setAge(22);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Rahul");
        user2.setEmail("rahul@gmail.com");
        user2.setAge(25);

        List<User> users = List.of(user1, user2);

        when(repository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUsers() {

        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Anjali");
        user.setEmail("anjali@gmail.com");
        user.setAge(22);

        Pageable pageable = PageRequest.of(0, 5);

        Page<User> page = new PageImpl<>(List.of(user));

        when(repository.findAll(org.mockito.ArgumentMatchers.any(Pageable.class)))
                .thenReturn(page);

        // Act
        Page<User> result = userService.getUsers(0, 5, "id", "asc");

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("Anjali", result.getContent().get(0).getName());

        verify(repository, times(1))
                .findAll(org.mockito.ArgumentMatchers.any(Pageable.class));
    }

    @Test

    void testAddUser() {

        // Arrange
        UserDto dto = new UserDto();
        dto.setName("Anjali");
        dto.setEmail("anjali@gmail.com");
        dto.setAge(22);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Anjali");
        savedUser.setEmail("anjali@gmail.com");
        savedUser.setAge(22);

        when(repository.save(org.mockito.ArgumentMatchers.any(User.class)))
                .thenReturn(savedUser);

        // Act
        User result = userService.addUser(dto);

        // Assert
        assertEquals("Anjali", result.getName());
        assertEquals("anjali@gmail.com", result.getEmail());
        assertEquals(22, result.getAge());
    }
}