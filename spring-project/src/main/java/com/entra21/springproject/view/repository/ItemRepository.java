package com.entra21.springproject.view.repository;

import com.entra21.springproject.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    public List<ItemEntity> findAllByGeneros_Id(Long idGenero);
    public List<ItemEntity> findAllByGeneros_NomeLike(String nome);
}