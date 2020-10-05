package com.github.marschall.emptystreams;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SIZED;
import static java.util.Spliterator.SUBSIZED;

import java.util.DoubleSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator.OfDouble;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

final class EmptyDoubleStream extends EmptyBaseStream<Double, DoubleStream> implements DoubleStream {

  private static final Spliterator.OfDouble EMPTY_SPLITERATOR_ORDERD = new EmptySpliteratorOfDouble(SIZED | NONNULL | IMMUTABLE | ORDERED | SUBSIZED);
  private static final Spliterator.OfDouble EMPTY_SPLITERATOR_UNORDERD = new EmptySpliteratorOfDouble(SIZED | NONNULL | IMMUTABLE | SUBSIZED);
  private static final Spliterator.OfDouble EMPTY_SPLITERATOR_SORTED = new EmptySortedSpliteratorOfDouble();
  private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

  private static final double[] EMPTY = new double[0];

  EmptyDoubleStream() {
    super();
  }

  EmptyDoubleStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    super(ordered, parallel, sorted, closeHandler);
  }

  EmptyDoubleStream(boolean ordered, boolean parallel, boolean sorted) {
    super(ordered, parallel, sorted);
  }

  @Override
  public DoubleStream unordered() {
    this.closedCheck();
    if (this.ordered) {
      return new EmptyDoubleStream(false, this.parallel, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public DoubleStream onClose(Runnable closeHandler) {
    Objects.requireNonNull(closeHandler);
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this.composeCloseHandler(closeHandler));
  }

  @Override
  public DoubleStream filter(DoublePredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closedCheck();
    return this;
  }

  @Override
  public DoubleStream map(DoubleUnaryOperator mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return this;
  }

  @Override
  public <U> Stream<U> mapToObj(DoubleFunction<? extends U> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public IntStream mapToInt(DoubleToIntFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream mapToLong(DoubleToLongFunction mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream flatMap(DoubleFunction<? extends DoubleStream> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public DoubleStream distinct() {
    this.closedCheck();
    return this;
  }

  @Override
  public DoubleStream sorted() {
    this.closedCheck();
    if (this.sorted) {
      return this;
    } else {
      return new EmptyDoubleStream(this.ordered, this.parallel, true, this::close);
    }
  }

  @Override
  public DoubleStream peek(DoubleConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public DoubleStream limit(long maxSize) {
    if (maxSize < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public DoubleStream skip(long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public void forEach(DoubleConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public void forEachOrdered(DoubleConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public double[] toArray() {
    this.closeAndCheck();
    return EMPTY;
  }

  @Override
  public double reduce(double identity, DoubleBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return identity;
  }

  @Override
  public OptionalDouble reduce(DoubleBinaryOperator op) {
    Objects.requireNonNull(op);
    this.closeAndCheck();
    return OptionalDouble.empty();
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> accumulator, BiConsumer<R, R> combiner) {
    Objects.requireNonNull(supplier);
    Objects.requireNonNull(accumulator);
    Objects.requireNonNull(combiner);
    this.closeAndCheck();
    return supplier.get();
  }

  @Override
  public double sum() {
    this.closeAndCheck();
    return 0.0d;
  }

  @Override
  public OptionalDouble min() {
    this.closeAndCheck();
    return OptionalDouble.empty();
  }

  @Override
  public OptionalDouble max() {
    this.closeAndCheck();
    return OptionalDouble.empty();
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
  public DoubleSummaryStatistics summaryStatistics() {
    this.closeAndCheck();
    return new DoubleSummaryStatistics();
  }

  @Override
  public boolean anyMatch(DoublePredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return false;
  }

  @Override
  public boolean allMatch(DoublePredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public boolean noneMatch(DoublePredicate predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public OptionalDouble findFirst() {
    this.closeAndCheck();
    return OptionalDouble.empty();
  }

  @Override
  public OptionalDouble findAny() {
    this.closeAndCheck();
    return OptionalDouble.empty();
  }

  @Override
  public Stream<Double> boxed() {
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream sequential() {
    this.closedCheck();
    if (this.parallel) {
      return new EmptyDoubleStream(this.ordered, false, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public DoubleStream parallel() {
    this.closedCheck();
    if (this.parallel) {
      return this;
    } else {
      return new EmptyDoubleStream(this.ordered, true, this.sorted, this::close);
    }
  }

  @Override
  public OfDouble iterator() {
    return EMPTY_ITERATOR;
  }

  @Override
  public java.util.Spliterator.OfDouble spliterator() {
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
    return "double[0]";
  }

  static final class EmptyIterator extends EmptyPrimitiveIterator<Double, DoubleConsumer> implements OfDouble {

    @Override
    public double nextDouble() {
      throw new NoSuchElementException();
    }

  }

  static final class EmptySpliteratorOfDouble extends EmptyWithCharacteristicsOfPrimitive<Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {

    EmptySpliteratorOfDouble(int characteristics) {
      super(characteristics);
    }

  }

  static final class EmptySortedSpliteratorOfDouble extends EmptySortedOfPrimitive<Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {

    @Override
    public int characteristics() {
      return SIZED | NONNULL | IMMUTABLE | ORDERED | SORTED | SUBSIZED;
    }

  }

}
