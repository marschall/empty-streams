package com.github.marschall.emptystreams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyIntStreamTests {

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void estimateSize(IntStream stream) {
    assertEquals(0L, stream.spliterator().estimateSize());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void getExactSizeIfKnown(IntStream stream) {
    assertEquals(0L, stream.spliterator().getExactSizeIfKnown());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void count(IntStream stream) {
    assertEquals(0L, stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(IntStream stream) {
    assertTrue(stream.allMatch(i -> false));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void anyMatch(IntStream stream) {
    assertFalse(stream.anyMatch(i -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void noneMatch(IntStream stream) {
    assertTrue(stream.noneMatch(i -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArray(IntStream stream) {
    assertArrayEquals(new int[0], stream.toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void skip(IntStream stream) {
    assertArrayEquals(new int[0], stream.skip(1L).toArray());
  }

  static Stream<IntStream> emptyStreams() {
    return Stream.of(IntStream.empty(), new EmptyIntStream());
  }

}
