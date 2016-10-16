/*
 * spring-cucumber
 * https://github.com/dherges/spring-cucumber
 *
 * Copyright (c) 2016 David Herges
 * Licensed under the MIT license.
 */
package ext.assertj;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;
import org.assertj.core.internal.Strings;

import okhttp3.MediaType;

public class MediaTypeAssert extends AbstractAssert<MediaTypeAssert, MediaType> {

  Objects objects = Objects.instance();
  Strings strings = Strings.instance();

  public MediaTypeAssert(MediaType actual) {
    super(actual, MediaTypeAssert.class);
  }

  public void typeIs(String type) {
    objects.assertNotNull(info, actual);
    strings.assertEqualsIgnoringCase(info, actual.type(), type);
  }

  public void subtypeIs(String subtype) {
    objects.assertNotNull(info, actual);
    strings.assertEqualsIgnoringCase(info, actual.subtype(), subtype);
  }

  public void isEqualTo(MediaType mediaType) {
    objects.assertEqual(info, actual, mediaType);
  }

  public void isEqualTo(String contentType) {
    objects.assertNotNull(info, actual);
    strings.assertEqualsIgnoringCase(info, actual.toString(), contentType);
  }

}
