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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java8.En;
import spring.cucumber.demo.TwitterApp;
import spring.cucumber.demo.twitter.HelloController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TwitterApp.class)
public class AppSteps implements En {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppSteps.class);

  @Autowired
  HelloController helloController;

  public AppSteps() {

    Given("welcome message is \"([^\\\"]*)", (String msg) -> {
      helloController.welcomeMessage = msg;
    });

    Then("controller was not null", () -> {
      assertThat(helloController).isNotNull();
    });
  }
}
