package com.github.marschall.emptystreams;

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
  void count(IntStream stream) {
    assertEquals(0L, stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(IntStream stream) {
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

  static Stream<IntStream> emptyStreams() {
    return Stream.of(IntStream.empty(), new EmptyIntStream());
  }

}
