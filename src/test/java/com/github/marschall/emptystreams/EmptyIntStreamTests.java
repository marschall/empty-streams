package com.github.marschall.emptystreams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.Spliterator;
import java.util.Spliterator.OfInt;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyIntStreamTests {

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEach(IntStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEach(i -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEach(i -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEachOrdered(IntStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEachOrdered(i -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEachOrdered(i -> accumulator.incrementAndGet()));
  }

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
    assertArrayEquals(new int[0], stream.skip(1L).skip(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void limit(IntStream stream) {
    assertArrayEquals(new int[0], stream.limit(1L).limit(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void peek(IntStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    assertArrayEquals(new int[0], stream.peek(i -> accumulator.incrementAndGet()).peek(i -> accumulator.incrementAndGet()).toArray());
    assertEquals(0, accumulator.get());
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
    assertEquals(Collections.emptyList(), stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));

    assertThrows(IllegalStateException.class, () -> stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToObj(IntStream stream) {
    assertArrayEquals(new Integer[0], stream.mapToObj(Integer::valueOf).toArray(Integer[]::new));

    assertThrows(IllegalStateException.class, () -> stream.mapToObj(Integer::valueOf));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToLong(IntStream stream) {
    assertArrayEquals(new long[0], stream.mapToLong(i -> i).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToLong(i -> i));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void asLongStream(IntStream stream) {
    assertArrayEquals(new long[0], stream.asLongStream().toArray());

    assertThrows(IllegalStateException.class, () -> stream.asLongStream());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToDouble(IntStream stream) {
    assertArrayEquals(new double[0], stream.mapToDouble(i -> i).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToDouble(i -> i));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void asDoubleStream(IntStream stream) {
    assertArrayEquals(new double[0], stream.asDoubleStream().toArray());

    assertThrows(IllegalStateException.class, () -> stream.asDoubleStream());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void distinct(IntStream stream) {
    assertNotNull(stream.distinct());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sorted(IntStream stream) {
    IntStream sorted = stream.sorted();
    OfInt spliterator = sorted.spliterator();
    assertTrue(spliterator.hasCharacteristics(Spliterator.SORTED));
    assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
    sorted.close();

    assertThrows(IllegalStateException.class, () -> stream.sorted());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void unordered(IntStream stream) {
    IntStream unordered = stream.unordered();
    assertFalse(unordered.spliterator().hasCharacteristics(Spliterator.ORDERED));
    unordered.close();

//    assertThrows(IllegalStateException.class, () -> stream.unordered());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void parallel(IntStream stream) {
    assertFalse(stream.isParallel());
    assertTrue(stream.parallel().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sequential(IntStream stream) {
    assertFalse(stream.isParallel());
    assertFalse(stream.parallel().sequential().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void onClose(IntStream stream) {
    AtomicBoolean flag1 = new AtomicBoolean(false);
    AtomicBoolean flag2 = new AtomicBoolean(false);

    IntStream toClose = stream.onClose(() -> flag1.set(true)).onClose(() -> flag2.set(true));

    assertFalse(flag1.get());
    assertFalse(flag2.get());

    toClose.close();

    assertTrue(flag1.get());
    assertTrue(flag2.get());

    AtomicBoolean flag3 = new AtomicBoolean(false);
    assertThrows(IllegalStateException.class, () -> stream.onClose(() -> flag3.set(true)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void flatMap(IntStream stream) {
    assertArrayEquals(new int[0], stream.flatMap(i -> IntStream.of(i)).toArray());

    assertThrows(IllegalStateException.class, () -> stream.flatMap(i -> IntStream.of(i)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void summaryStatistics(IntStream stream) {
    IntSummaryStatistics summaryStatistics = stream.summaryStatistics();
    assertEquals(0.0d, summaryStatistics.getAverage(), 0.00000001d);
    assertEquals(0L, summaryStatistics.getCount());
    assertEquals(Integer.MAX_VALUE, summaryStatistics.getMin());
    assertEquals(Integer.MIN_VALUE, summaryStatistics.getMax());

    assertThrows(IllegalStateException.class, () -> stream.summaryStatistics());
  }

  static Stream<IntStream> emptyStreams() {
    return Stream.of(IntStream.empty(), new EmptyIntStream());
  }

}
