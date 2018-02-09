package com.jwt.service.impl;

import com.jwt.models.City;
import com.jwt.models.User;
import com.jwt.repository.RandomCityRepository;
import com.jwt.repository.UserRepository;
import com.jwt.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nydiarra on 07/05/17.
 */
@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomCityRepository randomCityRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<City> findAllRandomCities() {
        return (List<City>)randomCityRepository.findAll();
    }
}