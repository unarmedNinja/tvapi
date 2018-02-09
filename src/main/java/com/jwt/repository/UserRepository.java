package com.jwt.repository;

import com.jwt.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
