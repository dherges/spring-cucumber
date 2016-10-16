/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.twitter.trends;

import org.springframework.data.repository.Repository;

/**
 * LocationsRepository is an indirection for storage of Locations :-)
 */
public interface LocationsRepository extends Repository<Location, Long> {

  Location findOne(Long id);

  boolean exists(Long id);

  Iterable<Location> findAll();

  Iterable<Location> findAll(Iterable<Long> ids);

  long count();

}
