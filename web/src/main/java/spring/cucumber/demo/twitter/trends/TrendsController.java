/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.twitter.trends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@ResponseBody
@RequestMapping("trends")
public class TrendsController {

  @Autowired
  LocationsRepository repository;

  @RequestMapping("available")
  public List<Location> available() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
      .collect(Collectors.toList());
  }

  @RequestMapping("place")
  public List<Topic> place(
    @RequestParam("id") String id,
    @RequestParam(value = "exclude", required = false, defaultValue = "") String exclude
  ) {
    Long longId = Long.valueOf(id);
    if (repository.exists(longId)) {
      Location loc = repository.findOne(Long.valueOf(id));

      Topic topic = new Topic();
      topic.locations.add(loc);
      topic.created_at = Date.from(Instant.now());
      topic.as_of = Date.from(Instant.now().minus(123, ChronoUnit.DAYS));

      return Collections.singletonList(topic);
    } else {
      return Collections.emptyList();
    }
  }
}
