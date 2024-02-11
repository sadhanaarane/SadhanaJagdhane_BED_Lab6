package com.gl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
