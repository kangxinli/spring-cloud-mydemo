package com.sample.demo.service.provider.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.service.provider.entity.User;
import com.sample.demo.service.provider.repository.UserRepository;

@RequestMapping("/users")
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{id}")
	public Optional<User> findById(@PathVariable Long id) {
		return this.userRepository.findById(id);
	}
}
