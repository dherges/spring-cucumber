/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package spring.cucumber.demo.steps;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.springframework.boot.context.embedded.LocalServerPort;

import java.io.IOException;

import cucumber.api.java8.En;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static ext.assertj.CustomAssertions.assertThat;

public class ClientSteps implements En {

  @LocalServerPort // <-- injects the tcp port of the embedded web server
  private int serverPort;

  private Response response;

  private String responseBody;
  private DocumentContext documentContext;

  private String responseBody() {
    if (responseBody == null) {
      try {
        responseBody = response.body().string();
      } catch (IOException e) {
        fail("Cannot read response body", e);
      }
    }

    return responseBody;
  }

  private DocumentContext jsonPath() {
    if (documentContext == null) {
      documentContext = JsonPath.parse(responseBody());
    }

    return documentContext;
  }

  private String baseUrl() {
    return "http://localhost:" + serverPort;
  }

  public ClientSteps() {

    When("client calls ([A-Z]{0,4}) (.*)", (String verb, String path) -> {
      try {
        response =  new OkHttpClient.Builder().build()
          .newCall(
            new Request.Builder()
              .method(verb, null)
              .url(baseUrl() + path)
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
      assertThat(responseBody()).isEqualToIgnoringCase(text);
    });

    Then("response body is", (String docString) -> {
      assertThat(responseBody()).isEqualToIgnoringCase(docString);
    });

    Then("response content type equals \"([^\\\"]*)\"", (String contentType) -> {
      assertThat(response.body().contentType()).isEqualTo(contentType);
    });

    Then("response content type matches \"([^\\\"]*)/([^\\\"]*)\"", (String type, String subtype) -> {
      assertThat(response.body().contentType()).typeIs(type);
      assertThat(response.body().contentType()).subtypeIs(subtype);
    });

    Then("response content type is \"([^\\\"]*)\"", (String contentType) -> {
      assertThat(response.body().contentType()).typeIs(contentType);
    });

    Then("response content sub-type is \"([^\\\"]*)\"", (String contentType) -> {
      assertThat(response.body().contentType()).subtypeIs(contentType);
    });

    Then("response json path '([^']+)' equals ([0-9]+)", (String expression, Integer value) -> {
      assertThat(jsonPath().read(expression, Integer.class)).isEqualTo(value);
    });

    Then("response json path '([^']+)' equals \"([^\\\"]*)\"", (String expression, String value) -> {
      assertThat(jsonPath().read(expression, String.class)).isEqualTo(value);
    });

  }


}
