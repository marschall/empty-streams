package com.github.marschall.emptystreams;

import java.util.stream.IntStream;

/**
 * Class with factory methods for empty streams.
 */
public final class EmptyStreams {

  private EmptyStreams() {
    throw new AssertionError("not instantiable");
  }

  public static IntStream emptyIntStream() {
    return new EmptyIntStream();
  }

}
