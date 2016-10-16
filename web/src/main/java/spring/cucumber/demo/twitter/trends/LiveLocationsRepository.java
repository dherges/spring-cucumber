/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.twitter.trends;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Repository
public class LiveLocationsRepository implements LocationsRepository {

  /* XX: for demonstration purposes only ... in real world, we'd probabyl connect against sql or
   * mongo or some other database
   */
  List<Location> locations = IntStream.range(1, 3)
    .mapToObj((int id) -> {
      final Location t = new Location();
      t.woeid = id;
      if (id == 1) {
        t.name = "Worldwide";

        return t;
      }

      t.country = "Germany";
      t.countryCode = "GER";
      t.name = "Mainz";
      t.url = "http://where.yahooapis.com/v1/place/" + id;
      t.placeType = new Place();
      t.placeType.code = 7;
      t.placeType.name = "town";

      return t;
    })
    .collect(Collectors.toList());

  public Location findOne(Long id) {
    return locations.stream()
      .filter((Location t) -> t.woeid == id)
      .findFirst()
      .orElseGet(null);
  }

  public boolean exists(Long id) {
    return locations.stream()
      .filter((Location t) -> t.woeid == id)
      .findFirst()
      .isPresent();
  }

  public Iterable<Location> findAll() {
    return new ArrayList<>(locations);
  }

  public Iterable<Location> findAll(Iterable<Long> ids) {
    List<Long> idsList = StreamSupport
      .stream(ids.spliterator(), false)
      .collect(Collectors.toList());

    return locations.stream()
      .filter((Location t) -> idsList.contains(t.woeid))
      .collect(Collectors.toList());
  }

  public long count() {
    return locations.size();
  }

}
