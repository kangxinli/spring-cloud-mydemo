package com.sample.demo.service.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.demo.service.provider.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
