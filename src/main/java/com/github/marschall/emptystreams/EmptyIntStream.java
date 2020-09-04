package com.github.marschall.emptystreams;

import java.util.IntSummaryStatistics;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator.OfInt;
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

final class EmptyIntStream implements IntStream {

  private static final EmptySpliterator EMPTY_SPLITERATOR = new EmptySpliterator();
  private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  private static final int[] EMPTY = new int[0];

  EmptyIntStream() {
    super();
  }

  @Override
  public boolean isParallel() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public IntStream unordered() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream onClose(Runnable closeHandler) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void close() {
    // TODO Auto-generated method stub
  }

  @Override
  public IntStream filter(IntPredicate predicate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream map(IntUnaryOperator mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream mapToLong(IntToLongFunction mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream distinct() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream sorted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream peek(IntConsumer action) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream limit(long maxSize) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream skip(long n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void forEach(IntConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty

  }

  @Override
  public void forEachOrdered(IntConsumer action) {
    Objects.requireNonNull(action);
    // ignore because empty
  }

  @Override
  public int[] toArray() {
    return EMPTY;
  }

  @Override
  public int reduce(int identity, IntBinaryOperator op) {
    return identity;
  }

  @Override
  public OptionalInt reduce(IntBinaryOperator op) {
    return OptionalInt.empty();
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator,
          BiConsumer<R, R> combiner) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int sum() {
    return 0;
  }

  @Override
  public OptionalInt min() {
    return OptionalInt.empty();
  }

  @Override
  public OptionalInt max() {
    return OptionalInt.empty();
  }

  @Override
  public long count() {
    return 0L;
  }

  @Override
  public OptionalDouble average() {
    return OptionalDouble.empty();
  }

  @Override
  public IntSummaryStatistics summaryStatistics() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean anyMatch(IntPredicate predicate) {
    return false;
  }

  @Override
  public boolean allMatch(IntPredicate predicate) {
    return true;
  }

  @Override
  public boolean noneMatch(IntPredicate predicate) {
    return true;
  }

  @Override
  public OptionalInt findFirst() {
    return OptionalInt.empty();
  }

  @Override
  public OptionalInt findAny() {
    return OptionalInt.empty();
  }

  @Override
  public LongStream asLongStream() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleStream asDoubleStream() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<Integer> boxed() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream sequential() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream parallel() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OfInt iterator() {
    return EMPTY_ITERATOR;
  }

  @Override
  public java.util.Spliterator.OfInt spliterator() {
    return EMPTY_SPLITERATOR;
  }

  @Override
  public String toString() {
    return "int[0]";
  }

  static final class EmptyIterator extends EmptyPrimitiveIterator<Integer, IntConsumer> implements OfInt {

    @Override
    public int nextInt() {
      throw new NoSuchElementException();
    }

  }

  static final class EmptySpliterator extends EmptyOfPrimitive<Integer, IntConsumer, java.util.Spliterator.OfInt> implements java.util.Spliterator.OfInt {

    @Override
    public int characteristics() {
      return SIZED | NONNULL | IMMUTABLE;
    }

  }

}
