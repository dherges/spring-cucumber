/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package ext.assertj;

import okhttp3.MediaType;

/** Library class for <code>assertThat(..)</code> calls that create custom assert instances. */
public class CustomAssertions {

  public static MediaTypeAssert assertThat(MediaType mediaType) {
    return new MediaTypeAssert(mediaType);
  }
}
