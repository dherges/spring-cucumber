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

public class ClientSteps implements En {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientSteps.class);

  @LocalServerPort
  private int serverPort;

  private Response response;

  public ClientSteps() {

    When("client calls ([A-Z]{0,4}) (.*)", (String verb, String path) -> {
      try {
        response =  new OkHttpClient.Builder().build()
          .newCall(
            new Request.Builder()
              .method(verb, null)
              .url("http://localhost:" + serverPort + path)
              .build()
          )
          .execute();
      } catch (IOException e) {
        fail("Cannot execute HTTP request", e);
      }
    });

    Then("response code is ([0-9]+)", (Integer code) -> {
      assertThat(response.code()).isEqualTo(code);
    });

    Then("response body is \"([^\\\"]*)\"", (String text) -> {
      try {
        assertThat(response.body().string()).isEqualToIgnoringCase(text);
      } catch (IOException e) {
        fail("Cannot read response body", e);
      }
    });
  }
}
