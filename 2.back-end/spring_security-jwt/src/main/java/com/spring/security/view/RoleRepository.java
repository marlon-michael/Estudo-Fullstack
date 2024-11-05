package com.spring.security.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.RoleEntity;
import com.spring.security.model.RoleNameEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
    public RoleEntity findByName(RoleNameEnum name);
}
