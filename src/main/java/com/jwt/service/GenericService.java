package com.jwt.service;

import com.jwt.models.City;
import com.jwt.models.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<City> findAllRandomCities();
}