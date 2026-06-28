package com.anjali.smartproductcatalog.service;

import com.anjali.smartproductcatalog.entity.User;
import com.anjali.smartproductcatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    @Cacheable("users")
    public List<User> getAll() {
        System.out.println("Fetching from DB...");
        return repository.findAll();
    }
}