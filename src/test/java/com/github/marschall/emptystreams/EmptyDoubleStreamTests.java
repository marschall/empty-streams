package com.github.marschall.emptystreams;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.Spliterator;
import java.util.Spliterator.OfDouble;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyDoubleStreamTests {

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEach(DoubleStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEach(d -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEach(d -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEachOrdered(DoubleStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEachOrdered(d -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEachOrdered(d -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void estimateSize(DoubleStream stream) {
    assertEquals(0L, stream.spliterator().estimateSize());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void getExactSizeIfKnown(DoubleStream stream) {
    assertEquals(0L, stream.spliterator().getExactSizeIfKnown());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void count(DoubleStream stream) {
    assertEquals(0L, stream.count());

    assertThrows(IllegalStateException.class, () -> stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(DoubleStream stream) {
    assertTrue(stream.allMatch(d -> false));

    assertThrows(IllegalStateException.class, () -> stream.allMatch(d -> false));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void anyMatch(DoubleStream stream) {
    assertFalse(stream.anyMatch(d -> true));

    assertThrows(IllegalStateException.class, () -> stream.anyMatch(d -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void noneMatch(DoubleStream stream) {
    assertTrue(stream.noneMatch(d -> true));

    assertThrows(IllegalStateException.class, () -> stream.noneMatch(d -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArray(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.toArray(), 0.000001d);

    assertThrows(IllegalStateException.class, () -> stream.toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void skip(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.skip(1L).skip(2L).toArray(), 0.000001d);
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void limit(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.limit(1L).limit(2L).toArray(), 0.000001d);
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void peek(DoubleStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    assertArrayEquals(new double[0], stream.peek(d -> accumulator.incrementAndGet()).peek(d -> accumulator.incrementAndGet()).toArray(), 0.000001d);
    assertEquals(0, accumulator.get());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void map(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.map(d -> d * d).toArray(), 0.000001d);

    assertThrows(IllegalStateException.class, () -> stream.map(d -> d * d));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void filter(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.filter(d -> true).toArray(), 0.000001d);

    assertThrows(IllegalStateException.class, () -> stream.filter(d -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduce(DoubleStream stream) {
    assertFalse(stream.reduce((a, b) -> a + b).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.reduce((a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduceIdentity(DoubleStream stream) {
    assertEquals(42, stream.reduce(42, (a, b) -> a + b));

    assertThrows(IllegalStateException.class, () -> stream.reduce(42, (a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sum(DoubleStream stream) {
    assertEquals(0, stream.sum());

    assertThrows(IllegalStateException.class, () -> stream.sum());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void min(DoubleStream stream) {
    assertFalse(stream.min().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.min());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void max(DoubleStream stream) {
    assertFalse(stream.max().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.max());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void average(DoubleStream stream) {
    assertFalse(stream.average().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.average());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findFirst(DoubleStream stream) {
    assertFalse(stream.findFirst().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findFirst());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findAny(DoubleStream stream) {
    assertFalse(stream.findAny().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findAny());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void boxed(DoubleStream stream) {
    assertArrayEquals(new Object[0], stream.boxed().toArray());

    assertThrows(IllegalStateException.class, () -> stream.boxed());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void collect(DoubleStream stream) {
    assertEquals(Collections.emptyList(), stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));

    assertThrows(IllegalStateException.class, () -> stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToObj(DoubleStream stream) {
    assertArrayEquals(new Double[0], stream.mapToObj(Double::valueOf).toArray(Double[]::new));

    assertThrows(IllegalStateException.class, () -> stream.mapToObj(Double::valueOf));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToInt(DoubleStream stream) {
    assertArrayEquals(new int[0], stream.mapToInt(d -> (int) d).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToInt(d -> (int) d));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToLong(DoubleStream stream) {
    assertArrayEquals(new long[0], stream.mapToLong(d -> Double.doubleToLongBits(d)).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToLong(d -> Double.doubleToLongBits(d)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void distinct(DoubleStream stream) {
    assertNotNull(stream.distinct());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sorted(DoubleStream stream) {
    DoubleStream sorted = stream.sorted();
    OfDouble spliterator = sorted.spliterator();
    assertTrue(spliterator.hasCharacteristics(Spliterator.SORTED));
    assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
    sorted.close();

    assertThrows(IllegalStateException.class, () -> stream.sorted());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void unordered(DoubleStream stream) {
    DoubleStream unordered = stream.unordered();
    assertFalse(unordered.spliterator().hasCharacteristics(Spliterator.ORDERED));
    unordered.close();

//    assertThrows(IllegalStateException.class, () -> stream.unordered());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void parallel(DoubleStream stream) {
    assertFalse(stream.isParallel());
    assertTrue(stream.parallel().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sequential(DoubleStream stream) {
    assertFalse(stream.isParallel());
    assertFalse(stream.parallel().sequential().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void onClose(DoubleStream stream) {
    AtomicBoolean flag1 = new AtomicBoolean(false);
    AtomicBoolean flag2 = new AtomicBoolean(false);

    DoubleStream toClose = stream.onClose(() -> flag1.set(true)).onClose(() -> flag2.set(true));

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
  void flatMap(DoubleStream stream) {
    assertArrayEquals(new double[0], stream.flatMap(d -> DoubleStream.of(d)).toArray(), 0.000001d);

    assertThrows(IllegalStateException.class, () -> stream.flatMap(d -> DoubleStream.of(d)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void summaryStatistics(DoubleStream stream) {
    DoubleSummaryStatistics summaryStatistics = stream.summaryStatistics();
    assertEquals(0.0d, summaryStatistics.getAverage(), 0.00000001d);
    assertEquals(0L, summaryStatistics.getCount());
    assertEquals(Double.POSITIVE_INFINITY, summaryStatistics.getMin());
    assertEquals(Double.NEGATIVE_INFINITY, summaryStatistics.getMax());

    assertThrows(IllegalStateException.class, () -> stream.summaryStatistics());
  }

  static Stream<DoubleStream> emptyStreams() {
    return Stream.of(DoubleStream.empty(), new EmptyDoubleStream());
  }

}
