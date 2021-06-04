package com.adepo.adeline.repository;

import com.adepo.adeline.model.Role;
import com.adepo.adeline.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean   existsByUsername(String username);
}
