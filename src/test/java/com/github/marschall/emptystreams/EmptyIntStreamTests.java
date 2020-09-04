package com.github.marschall.emptystreams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    assertThrows(IllegalStateException.class, () -> stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(IntStream stream) {
    assertTrue(stream.allMatch(i -> false));

    assertThrows(IllegalStateException.class, () -> stream.allMatch(i -> false));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void anyMatch(IntStream stream) {
    assertFalse(stream.anyMatch(i -> true));

    assertThrows(IllegalStateException.class, () -> stream.anyMatch(i -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void noneMatch(IntStream stream) {
    assertTrue(stream.noneMatch(i -> true));

    assertThrows(IllegalStateException.class, () -> stream.noneMatch(i -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArray(IntStream stream) {
    assertArrayEquals(new int[0], stream.toArray());

    assertThrows(IllegalStateException.class, () -> stream.toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void skip(IntStream stream) {
    assertArrayEquals(new int[0], stream.skip(1L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void map(IntStream stream) {
    assertArrayEquals(new int[0], stream.map(i -> i * i).toArray());

    assertThrows(IllegalStateException.class, () -> stream.map(i -> i * i));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void filter(IntStream stream) {
    assertArrayEquals(new int[0], stream.filter(i -> true).toArray());

    assertThrows(IllegalStateException.class, () -> stream.filter(i -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduce(IntStream stream) {
    assertFalse(stream.reduce((a, b) -> a + b).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.reduce((a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduceIdentity(IntStream stream) {
    assertEquals(42, stream.reduce(42, (a, b) -> a + b));

    assertThrows(IllegalStateException.class, () -> stream.reduce(42, (a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sum(IntStream stream) {
    assertEquals(0, stream.sum());

    assertThrows(IllegalStateException.class, () -> stream.sum());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void min(IntStream stream) {
    assertFalse(stream.min().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.min());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void max(IntStream stream) {
    assertFalse(stream.max().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.max());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void average(IntStream stream) {
    assertFalse(stream.average().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.average());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findFirst(IntStream stream) {
    assertFalse(stream.findFirst().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findFirst());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findAny(IntStream stream) {
    assertFalse(stream.findAny().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findAny());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void boxed(IntStream stream) {
    assertArrayEquals(new Object[0], stream.boxed().toArray());

    assertThrows(IllegalStateException.class, () -> stream.boxed());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void collect(IntStream stream) {

  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToObj(IntStream stream) {

  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToLong(IntStream stream) {

  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToDouble(IntStream stream) {

  }

  static Stream<IntStream> emptyStreams() {
    return Stream.of(IntStream.empty(), new EmptyIntStream());
  }

}
