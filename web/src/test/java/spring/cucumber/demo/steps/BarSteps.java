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
import org.springframework.boot.context.embedded.LocalServerPort;

import java.io.IOException;

import cucumber.api.java8.En;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BarSteps implements En {
  private static final Logger LOGGER = LoggerFactory.getLogger(BarSteps.class);

  @LocalServerPort
  private int serverPort;

  private Response response;

  public BarSteps() {
    When("step3", () -> {
      LOGGER.info("Step #3 ... some event is happening");

      try {
        response =  new OkHttpClient.Builder().build()
          .newCall(
            new Request.Builder()
              .get()
              .url("http://localhost:" + serverPort + "/hello")
              .build()
          )
          .execute();
      } catch (IOException e) {
        fail("Cannot execute HTTP request", e);
      }
    });

    Then("step4", () -> {
      LOGGER.info("Step #4 ... some assertion");

      assertThat(response.code()).isEqualTo(200);
      try {
        assertThat(response.body().string()).isEqualToIgnoringCase("Hello again!");
      } catch (IOException e) {
        fail("Cannot read response body", e);
      }
    });
  }
}
