package com.spring.security.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByLogin(String login);
    public List<UserEntity> findAll();
}
