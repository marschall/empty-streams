package com.github.marschall.emptystreams;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SIZED;
import static java.util.Spliterator.SUBSIZED;

import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator.OfInt;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

final class EmptyIntStream extends EmptyBaseStream<Integer, IntStream> implements IntStream {

  private static final Spliterator.OfInt EMPTY_SPLITERATOR_ORDERD = new EmptySpliteratorOfInt(SIZED | NONNULL | IMMUTABLE | ORDERED | SUBSIZED);
  private static final Spliterator.OfInt EMPTY_SPLITERATOR_UNORDERD = new EmptySpliteratorOfInt(SIZED | NONNULL | IMMUTABLE | SUBSIZED);
  private static final Spliterator.OfInt EMPTY_SPLITERATOR_SORTED = new EmptySortedSpliteratorOfInt();
  private static final EmptyIteratorOfInt EMPTY_ITERATOR = new EmptyIteratorOfInt();

  private static final int[] EMPTY = new int[0];

  EmptyIntStream() {
    super();
  }

  EmptyIntStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    super(ordered, parallel, sorted, closeHandler);
  }

  @Override
  public IntStream filter(IntPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closedCheck();
    return this;
  }

  @Override
  public IntStream map(IntUnaryOperator mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return this;
  }

  @Override
  public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream mapToLong(IntToLongFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public IntStream distinct() {
    this.closedCheck();
    return this;
  }

  @Override
  public IntStream sorted() {
    this.closedCheck();
    this.sorted = true;
    return this;
  }

  @Override
  public IntStream peek(IntConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public IntStream limit(long maxSize) {
    if (maxSize < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public IntStream skip(long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public void forEach(IntConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();

  }

  @Override
  public void forEachOrdered(IntConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public int[] toArray() {
    this.closeAndCheck();
    return EMPTY;
  }

  @Override
  public int reduce(int identity, IntBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return identity;
  }

  @Override
  public OptionalInt reduce(IntBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return OptionalInt.empty();
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner) {
    Objects.requireNonNull(supplier);
    Objects.requireNonNull(accumulator);
    Objects.requireNonNull(combiner);
    this.closeAndCheck();
    return supplier.get();
  }

  @Override
  public int sum() {
    this.closeAndCheck();
    return 0;
  }

  @Override
  public OptionalInt min() {
    this.closeAndCheck();
    return OptionalInt.empty();
  }

  @Override
  public OptionalInt max() {
    this.closeAndCheck();
    return OptionalInt.empty();
  }

  @Override
  public long count() {
    this.closeAndCheck();
    return 0L;
  }

  @Override
  public OptionalDouble average() {
    this.closeAndCheck();
    return OptionalDouble.empty();
  }

  @Override
  public IntSummaryStatistics summaryStatistics() {
    this.closeAndCheck();
    return new IntSummaryStatistics();
  }

  @Override
  public boolean anyMatch(IntPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return false;
  }

  @Override
  public boolean allMatch(IntPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public boolean noneMatch(IntPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public OptionalInt findFirst() {
    this.closeAndCheck();
    return OptionalInt.empty();
  }

  @Override
  public OptionalInt findAny() {
    this.closeAndCheck();
    return OptionalInt.empty();
  }

  @Override
  public LongStream asLongStream() {
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream asDoubleStream() {
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public Stream<Integer> boxed() {
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public OfInt iterator() {
    return EMPTY_ITERATOR;
  }

  @Override
  public Spliterator.OfInt spliterator() {
    if (this.sorted) {
      return EMPTY_SPLITERATOR_SORTED;
    } else {
      if (this.ordered) {
        return EMPTY_SPLITERATOR_ORDERD;
      } else {
        return EMPTY_SPLITERATOR_UNORDERD;
      }
    }
  }

  @Override
  public String toString() {
    return "int[0]";
  }

  static final class EmptyIteratorOfInt extends EmptyPrimitiveIterator<Integer, IntConsumer> implements OfInt {

    @Override
    public int nextInt() {
      throw new NoSuchElementException();
    }

  }

  static final class EmptySpliteratorOfInt extends EmptyWithCharacteristicsOfPrimitive<Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {

    EmptySpliteratorOfInt(int characteristics) {
      super(characteristics);
    }

  }

  static final class EmptySortedSpliteratorOfInt extends EmptySortedOfPrimitive<Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {

    @Override
    public int characteristics() {
      return SIZED | NONNULL | IMMUTABLE | ORDERED | SORTED | SUBSIZED;
    }

  }

}
