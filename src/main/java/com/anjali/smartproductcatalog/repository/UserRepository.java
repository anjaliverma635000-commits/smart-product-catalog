package com.anjali.smartproductcatalog.repository;

import com.anjali.smartproductcatalog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}