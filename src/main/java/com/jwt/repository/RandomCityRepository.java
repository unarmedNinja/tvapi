package com.jwt.repository;

import com.jwt.models.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nydiarra on 10/05/17.
 */
public interface RandomCityRepository extends CrudRepository<City, Long> {
}