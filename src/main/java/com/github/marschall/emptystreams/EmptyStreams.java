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
   * Returns and empty {@link IntStream}.
   * 
   * @return an empty stream
   * @see IntStream#empty()
   */
  public static IntStream emptyIntStream() {
    return new EmptyIntStream();
  }

  /**
   * Returns and empty {@link LongStream}.
   * 
   * @return an empty stream
   * @see LongStream#empty()
   */
  public static LongStream emptyLongStream() {
    return new EmptyLongStream();
  }

  /**
   * Returns and empty {@link DoubleStream}.
   * 
   * @return an empty stream
   * @see DoubleStream#empty()
   */
  public static DoubleStream emptyDoubleStream() {
    return new EmptyDoubleStream();
  }

  /**
   * Returns and empty {@link Stream}.
   * 
   * @return an empty stream
   * @param <T> the type of the stream elements
   * @see Stream#empty()
   */
  public static <T> Stream<T> emptyStream() {
    return new EmptyStream<>();
  }

}
