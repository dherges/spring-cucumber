/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.twitter.trends;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
@Primary
public class FakeLocationsRepository extends LiveLocationsRepository {

  public void loadFakeLocations() {
    locations.addAll(
      IntStream.range(100, 999)
        .mapToObj((int id) -> {
          final Location t = new Location();
          t.woeid = id;
          t.country = "Germany";
          t.countryCode = "GER";
          t.name = "Frankfurt";
          t.url = "http://where.yahooapis.com/v1/place/" + id;
          t.placeType = new Place();
          t.placeType.code = 7;
          t.placeType.name = "town";

          return t;
        })
        .collect(Collectors.toList())
    );
  }

}
