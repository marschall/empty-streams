package com.github.marschall.emptystreams;

import static com.github.marschall.emptystreams.JdkCompatibility.isBuggyJdkStream;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmptyStreamTests {

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEach(Stream<Object> stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEach(o -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEach(o -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void forEachOrdered(Stream<Object> stream) {
    AtomicInteger accumulator = new AtomicInteger();
    stream.forEachOrdered(o -> accumulator.incrementAndGet());
    assertEquals(0L, accumulator.get());

    assertThrows(IllegalStateException.class, () -> stream.forEachOrdered(o -> accumulator.incrementAndGet()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void estimateSize(Stream<Object> stream) {
    assertEquals(0L, stream.spliterator().estimateSize());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void getExactSizeIfKnown(Stream<Object> stream) {
    assertEquals(0L, stream.spliterator().getExactSizeIfKnown());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void count(Stream<Object> stream) {
    assertEquals(0L, stream.count());

    assertThrows(IllegalStateException.class, () -> stream.count());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void allMatch(Stream<Object> stream) {
    assertTrue(stream.allMatch(o -> false));

    assertThrows(IllegalStateException.class, () -> stream.allMatch(o -> false));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void anyMatch(Stream<Object> stream) {
    assertFalse(stream.anyMatch(o -> true));

    assertThrows(IllegalStateException.class, () -> stream.anyMatch(o -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void noneMatch(Stream<Object> stream) {
    assertTrue(stream.noneMatch(o -> true));

    assertThrows(IllegalStateException.class, () -> stream.noneMatch(o -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArray(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.toArray());

    assertThrows(IllegalStateException.class, () -> stream.toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void toArrayNull(Stream<Object> stream) {
    assertThrows(NullPointerException.class, () -> stream.toArray(i -> null));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void skip(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.skip(1L).skip(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void limit(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.limit(1L).limit(2L).toArray());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void peek(Stream<Object> stream) {
    AtomicInteger accumulator = new AtomicInteger();
    assertArrayEquals(new Object[0], stream.peek(o -> accumulator.incrementAndGet()).peek(o -> accumulator.incrementAndGet()).toArray());
    assertEquals(0, accumulator.get());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void map(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.map(Function.identity()).toArray());

    assertThrows(IllegalStateException.class, () -> stream.map(Function.identity()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void filter(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.filter(o -> true).toArray());

    assertThrows(IllegalStateException.class, () -> stream.filter(o -> true));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduce(Stream<Object> stream) {
    assertFalse(stream.reduce((a, b) -> a).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.reduce((a, b) -> a));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void reduceIdentity(Stream<Object> stream) {
    assertEquals(42, stream.reduce(42, (a, b) -> a));

    assertThrows(IllegalStateException.class, () -> stream.reduce(42, (a, b) -> a));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void min(Stream<Object> stream) {
    assertFalse(stream.min(Comparator.comparingInt(Object::hashCode)).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.min(Comparator.comparingInt(Object::hashCode)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void max(Stream<Object> stream) {
    assertFalse(stream.max(Comparator.comparingInt(Object::hashCode)).isPresent());

    assertThrows(IllegalStateException.class, () -> stream.max(Comparator.comparingInt(Object::hashCode)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findFirst(Stream<Object> stream) {
    assertFalse(stream.findFirst().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findFirst());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void findAny(Stream<Object> stream) {
    assertFalse(stream.findAny().isPresent());

    assertThrows(IllegalStateException.class, () -> stream.findAny());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void collect(Stream<Object> stream) {
    assertEquals(Collections.emptyList(), stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));

    assertThrows(IllegalStateException.class, () -> stream.collect(() -> new ArrayList<>(), (list, i) -> list.add(i), (a, b) -> a.addAll(b)));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void collectCollector(Stream<Object> stream) {
    assertEquals(Collections.emptyList(), stream.collect(toList()));

    assertThrows(IllegalStateException.class, () -> stream.collect(toList()));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToInt(Stream<Object> stream) {
    assertArrayEquals(new int[0], stream.mapToInt(o -> Integer.valueOf(o.hashCode())).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToInt(o -> Integer.valueOf(o.hashCode())));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToLong(Stream<Object> stream) {
    assertArrayEquals(new long[0], stream.mapToLong(o -> Long.valueOf(o.hashCode())).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToLong(o -> Long.valueOf(o.hashCode())));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void mapToDouble(Stream<Object> stream) {
    assertArrayEquals(new double[0], stream.mapToDouble(o -> Double.valueOf(o.hashCode())).toArray());

    assertThrows(IllegalStateException.class, () -> stream.mapToDouble(o -> Double.valueOf(o.hashCode())));
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void distinct(Stream<Object> stream) {
    assertNotNull(stream.distinct());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sorted(Stream<Object> stream) {
    Stream<Object> sorted = stream.sorted();
    Spliterator<Object> spliterator = sorted.spliterator();
    assertTrue(spliterator.hasCharacteristics(Spliterator.SORTED));
    assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
    assertNull(spliterator.getComparator());
    sorted.close();

    assertThrows(IllegalStateException.class, () -> stream.sorted());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sortedComparator(Stream<Object> stream) {
    Comparator<Object> comparator = Comparator.comparing(Object::hashCode);
    Stream<Object> sorted = stream.sorted(comparator);
    Spliterator<Object> spliterator = sorted.spliterator();

    assertTrue(spliterator.hasCharacteristics(Spliterator.ORDERED));
    if (spliterator.hasCharacteristics(Spliterator.SORTED)) {
      // false for JDK
      assertSame(comparator, spliterator.getComparator());
    }
    sorted.close();

    assertThrows(IllegalStateException.class, () -> stream.sorted(comparator));
  }

  public static void main(String[] args) {
    Stream<String> stream = Stream.of("a", "b");
    Comparator<? super String> comparator = String::compareTo;
    Stream<String> sorted = stream.sorted(comparator);
    Spliterator<String> spliterator = sorted.spliterator();
    System.out.println(spliterator.hasCharacteristics(Spliterator.SORTED));
    System.out.println(spliterator.getComparator());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void unordered(Stream<Object> stream) {
    Stream<Object> unordered = stream.unordered();
    assertFalse(unordered.spliterator().hasCharacteristics(Spliterator.ORDERED));
    unordered.close();

//    assertThrows(IllegalStateException.class, () -> stream.unordered());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void parallel(Stream<Object> stream) {
    assertFalse(stream.isParallel());
    assertTrue(stream.parallel().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void sequential(Stream<Object> stream) {
    assertFalse(stream.isParallel());
    assertFalse(stream.parallel().sequential().isParallel());
  }

  @ParameterizedTest
  @MethodSource("emptyStreams")
  void onClose(Stream<Object> stream) {
    assumeFalse(isBuggyJdkStream(stream));
    AtomicBoolean flag1 = new AtomicBoolean(false);
    AtomicBoolean flag2 = new AtomicBoolean(false);

    Stream<Object> toClose = stream.onClose(() -> flag1.set(true)).onClose(() -> flag2.set(true));

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
  void flatMap(Stream<Object> stream) {
    assertArrayEquals(new Object[0], stream.flatMap(o -> Stream.of(o)).toArray());

    assertThrows(IllegalStateException.class, () -> stream.flatMap(o -> Stream.of(o)));
  }

  static Stream<Stream<Object>> emptyStreams() {
    return Stream.of(Stream.empty(), new EmptyStream<>());
  }

}
