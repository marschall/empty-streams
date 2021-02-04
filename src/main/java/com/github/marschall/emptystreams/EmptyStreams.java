package com.github.marschall.emptystreams;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Class with factory methods for empty streams.
 */
public final class EmptyStreams {

  private EmptyStreams() {
    throw new AssertionError("not instantiable");
  }

  /**
   * Returns a new empty {@link IntStream}.
   *
   * @return a new empty int stream
   * @see IntStream#empty()
   */
  public static IntStream emptyIntStream() {
    return new EmptyIntStream();
  }

  /**
   * Returns a new empty {@link LongStream}.
   *
   * @return a new empty long stream
   * @see LongStream#empty()
   */
  public static LongStream emptyLongStream() {
    return new EmptyLongStream();
  }

  /**
   * Returns a new empty {@link DoubleStream}.
   *
   * @return a new empty double stream
   * @see DoubleStream#empty()
   */
  public static DoubleStream emptyDoubleStream() {
    return new EmptyDoubleStream();
  }

  /**
   * Returns a new empty {@link Stream}.
   *
   * @return a new empty stream
   * @param <T> the type of the stream elements
   * @see Stream#empty()
   */
  public static <T> Stream<T> emptyStream() {
    return new EmptyStream<>();
  }

}
