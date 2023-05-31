package com.entra21.springproject.view.repository;

import com.entra21.springproject.model.entity.FranquiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiaRepository extends JpaRepository<FranquiaEntity, Long> {
}
