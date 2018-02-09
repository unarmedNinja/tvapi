package com.jwt.controllers;

import com.jwt.models.City;
import com.jwt.models.User;
import com.jwt.service.GenericService;
import com.tv.controller.tv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/springjwt")
public class ResourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private GenericService userService;

    @RequestMapping(value ="/cities")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<City> getUser(){
        LOGGER.debug("getuser");
        return userService.findAllRandomCities();
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping(value ="/health", method = RequestMethod.GET)
    public List<City> healthCheck(){
        ArrayList<City> cities = new ArrayList<City>();
        City aCity = new City();
        Integer i = 99;
        Long l = new Long(i);
        aCity.setId(l);
        aCity.setName("Health Check");
        cities.add(aCity);

        LOGGER.debug("health check - {}", cities);
        return cities;
    }
}