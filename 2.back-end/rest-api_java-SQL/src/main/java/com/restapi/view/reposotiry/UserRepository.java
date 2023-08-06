package com.restapi.view.reposotiry;

import com.restapi.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    User findByName(String name);


}
