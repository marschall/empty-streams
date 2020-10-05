package com.github.marschall.emptystreams;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SIZED;
import static java.util.Spliterator.SUBSIZED;

import java.util.LongSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator.OfLong;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

final class EmptyLongStream extends EmptyBaseStream<Long, LongStream> implements LongStream {

  private static final Spliterator.OfLong EMPTY_SPLITERATOR_ORDERD = new EmptySpliteratorOfLong(SIZED | NONNULL | IMMUTABLE | ORDERED | SUBSIZED);
  private static final Spliterator.OfLong EMPTY_SPLITERATOR_UNORDERD = new EmptySpliteratorOfLong(SIZED | NONNULL | IMMUTABLE | SUBSIZED);
  private static final Spliterator.OfLong EMPTY_SPLITERATOR_SORTED = new EmptySortedSpliteratorOfLong();
  private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

  private static final long[] EMPTY = new long[0];

  EmptyLongStream() {
    super();
  }

  EmptyLongStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    super(ordered, parallel, sorted, closeHandler);
  }

  EmptyLongStream(boolean ordered, boolean parallel, boolean sorted) {
    super(ordered, parallel, sorted);
  }

  @Override
  public LongStream unordered() {
    this.closedCheck();
    if (this.ordered) {
      return new EmptyLongStream(false, this.parallel, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public LongStream onClose(Runnable closeHandler) {
    Objects.requireNonNull(closeHandler);
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this.composeCloseHandler(closeHandler));
  }

  @Override
  public LongStream filter(LongPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closedCheck();
    return this;
  }

  @Override
  public LongStream map(LongUnaryOperator mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return this;
  }

  @Override
  public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public IntStream mapToInt(LongToIntFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public LongStream distinct() {
    this.closedCheck();
    return this;
  }

  @Override
  public LongStream sorted() {
    this.closedCheck();
    if (this.sorted) {
      return this;
    } else {
      return new EmptyLongStream(this.ordered, this.parallel, true, this::close);
    }
  }

  @Override
  public LongStream peek(LongConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public LongStream limit(long maxSize) {
    if (maxSize < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public LongStream skip(long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public void forEach(LongConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public void forEachOrdered(LongConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public long[] toArray() {
    this.closeAndCheck();
    return EMPTY;
  }

  @Override
  public long reduce(long identity, LongBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return identity;
  }

  @Override
  public OptionalLong reduce(LongBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return OptionalLong.empty();
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner) {
    Objects.requireNonNull(supplier);
    Objects.requireNonNull(accumulator);
    Objects.requireNonNull(combiner);
    this.closeAndCheck();
    return supplier.get();
  }

  @Override
  public long sum() {
    this.closeAndCheck();
    return 0L;
  }

  @Override
  public OptionalLong min() {
    this.closeAndCheck();
    return OptionalLong.empty();
  }

  @Override
  public OptionalLong max() {
    this.closeAndCheck();
    return OptionalLong.empty();
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
  public LongSummaryStatistics summaryStatistics() {
    this.closeAndCheck();
    return new LongSummaryStatistics();
  }

  @Override
  public boolean anyMatch(LongPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return false;
  }

  @Override
  public boolean allMatch(LongPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public boolean noneMatch(LongPredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public OptionalLong findFirst() {
    this.closeAndCheck();
    return OptionalLong.empty();
  }

  @Override
  public OptionalLong findAny() {
    this.closeAndCheck();
    return OptionalLong.empty();
  }

  @Override
  public DoubleStream asDoubleStream() {
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public Stream<Long> boxed() {
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream sequential() {
    this.closedCheck();
    if (this.parallel) {
      return new EmptyLongStream(this.ordered, false, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public LongStream parallel() {
    this.closedCheck();
    if (this.parallel) {
      return this;
    } else {
      return new EmptyLongStream(this.ordered, true, this.sorted, this::close);
    }
  }

  @Override
  public OfLong iterator() {
    return EMPTY_ITERATOR;
  }

  @Override
  public Spliterator.OfLong spliterator() {
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
    return "long[0]";
  }

  static final class EmptyIterator extends EmptyPrimitiveIterator<Long, LongConsumer> implements OfLong {

    @Override
    public long nextLong() {
      throw new NoSuchElementException();
    }

  }

  static final class EmptySpliteratorOfLong extends EmptyWithCharacteristicsOfPrimitive<Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {

    EmptySpliteratorOfLong(int characteristics) {
      super(characteristics);
    }

  }

  static final class EmptySortedSpliteratorOfLong extends EmptySortedOfPrimitive<Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {

    @Override
    public int characteristics() {
      return SIZED | NONNULL | IMMUTABLE | ORDERED | SORTED | SUBSIZED;
    }

  }

}
