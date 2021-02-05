package com.github.marschall.emptystreams;

import static com.github.marschall.emptystreams.JdkCompatibility.isBuggyJdkStream;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LongSummaryStatistics;
import java.util.Spliterator;
import java.util.Spliterator.OfLong;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyLongStreamTests {

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEach(LongStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEach(l -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEach(l -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEachOrdered(LongStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEachOrdered(l -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEachOrdered(l -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void estimateSize(LongStream stream) {
    assertEquals(0L, stream.spliterator().estimateSize());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void getExactSizeIfKnown(LongStream stream) {
    assertEquals(0L, stream.spliterator().getExactSizeIfKnown());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void count(LongStream stream) {
    assertEquals(0L, stream.count());

    assertThrows(IllegalStateException.class, () -> stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(LongStream stream) {
    assertTrue(stream.allMatch(l -> false));

    assertThrows(IllegalStateException.class, () -> stream.allMatch(l -> false));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void anyMatch(LongStream stream) {
    assertFalse(stream.anyMatch(l -> true));

    assertThrows(IllegalStateException.class, () -> stream.anyMatch(l -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void noneMatch(LongStream stream) {
    assertTrue(stream.noneMatch(l -> true));

    assertThrows(IllegalStateException.class, () -> stream.noneMatch(l -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArray(LongStream stream) {
    assertArrayEquals(new long[0], stream.toArray());

    assertThrows(IllegalStateException.class, () -> stream.toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void skip(LongStream stream) {
    assertArrayEquals(new long[0], stream.skip(1L).skip(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void limit(LongStream stream) {
    assertArrayEquals(new long[0], stream.limit(1L).limit(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void peek(LongStream stream) {
    AtomicInteger accumulator = new AtomicInteger();
    assertArrayEquals(new long[0], stream.peek(l -> accumulator.incrementAndGet()).peek(l -> accumulator.incrementAndGet()).toArray());
    assertEquals(0, accumulator.get());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void map(LongStream stream) {
    assertArrayEquals(new long[0], stream.map(l -> l * l).toArray());

    assertThrows(IllegalStateException.class, () -> stream.map(l -> l * l));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void filter(LongStream stream) {
    assertArrayEquals(new long[0], stream.filter(l -> true).toArray());

    assertThrows(IllegalStateException.class, () -> stream.filter(l -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduce(LongStream stream) {
    assertFalse(stream.reduce((a, b) -> a + b).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.reduce((a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduceIdentity(LongStream stream) {
    assertEquals(42, stream.reduce(42, (a, b) -> a + b));

    assertThrows(IllegalStateException.class, () -> stream.reduce(42, (a, b) -> a + b));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sum(LongStream stream) {
    assertEquals(0, stream.sum());

    assertThrows(IllegalStateException.class, () -> stream.sum());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void min(LongStream stream) {
    assertFalse(stream.min().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.min());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void max(LongStream stream) {
    assertFalse(stream.max().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.max());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void average(LongStream stream) {
    assertFalse(stream.average().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.average());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findFirst(LongStream stream) {
    assertFalse(stream.findFirst().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findFirst());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findAny(LongStream stream) {
    assertFalse(stream.findAny().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findAny());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void boxed(LongStream stream) {
    assertArrayEquals(new Object[0], stream.boxed().toArray());

    assertThrows(IllegalStateException.class, () -> stream.boxed());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void collect(LongStream stream) {
    assertEquals(Collections.emptyList(), stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));

    assertThrows(IllegalStateException.class, () -> stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToObj(LongStream stream) {
    assertArrayEquals(new Long[0], stream.mapToObj(Long::valueOf).toArray(Long[]::new));

    assertThrows(IllegalStateException.class, () -> stream.mapToObj(Long::valueOf));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToInt(LongStream stream) {
    assertArrayEquals(new int[0], stream.mapToInt(l -> (int) l).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToInt(l -> (int) l));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToDouble(LongStream stream) {
    assertArrayEquals(new double[0], stream.mapToDouble(l -> Double.longBitsToDouble(l)).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToDouble(l -> Double.longBitsToDouble(l)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void asDoubleStream(LongStream stream) {
    assertArrayEquals(new double[0], stream.asDoubleStream().toArray());

    assertThrows(IllegalStateException.class, () -> stream.asDoubleStream());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void distinct(LongStream stream) {
    assertNotNull(stream.distinct());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sorted(LongStream stream) {
    LongStream sorted = stream.sorted();
    OfLong spliterator = sorted.spliterator();
    assertTrue(spliterator.hasCharacteristics(Spliterator.SORTED));
    assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
    sorted.close();

    assertThrows(IllegalStateException.class, () -> stream.sorted());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void unordered(LongStream stream) {
    LongStream unordered = stream.unordered();
    assertFalse(unordered.spliterator().hasCharacteristics(Spliterator.ORDERED));
    unordered.close();

//    assertThrows(IllegalStateException.class, () -> stream.unordered());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void parallel(LongStream stream) {
    assertFalse(stream.isParallel());
    assertTrue(stream.parallel().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sequential(LongStream stream) {
    assertFalse(stream.isParallel());
    assertFalse(stream.parallel().sequential().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void onClose(LongStream stream) {
    assumeFalse(isBuggyJdkStream(stream));
    AtomicBoolean flag1 = new AtomicBoolean(false);
    AtomicBoolean flag2 = new AtomicBoolean(false);

    LongStream toClose = stream.onClose(() -> flag1.set(true)).onClose(() -> flag2.set(true));

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
  void flatMap(LongStream stream) {
    assertArrayEquals(new long[0], stream.flatMap(l -> LongStream.of(l)).toArray());

    assertThrows(IllegalStateException.class, () -> stream.flatMap(l -> LongStream.of(l)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void summaryStatistics(LongStream stream) {
    LongSummaryStatistics summaryStatistics = stream.summaryStatistics();
    assertEquals(0.0d, summaryStatistics.getAverage(), 0.00000001d);
    assertEquals(0L, summaryStatistics.getCount());
    assertEquals(Long.MAX_VALUE, summaryStatistics.getMin());
    assertEquals(Long.MIN_VALUE, summaryStatistics.getMax());

    assertThrows(IllegalStateException.class, () -> stream.summaryStatistics());
  }

  static Stream<LongStream> emptyStreams() {
    return Stream.of(LongStream.empty(), new EmptyLongStream());
  }

}
