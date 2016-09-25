/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java8.En;
import spring.cucumber.demo.TwitterApp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TwitterApp.class)
public class FooSteps implements En {
  private static final Logger LOGGER = LoggerFactory.getLogger(FooSteps.class);

  public FooSteps() {

    Given("step1", () -> {
      LOGGER.info("Step #1 ... some precondition");
    });

    Given("step2", () -> {
      LOGGER.info("Step #2 ... some other precondition");
    });
  }
}
