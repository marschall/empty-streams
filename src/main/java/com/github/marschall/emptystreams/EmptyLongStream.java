package com.github.marschall.emptystreams;

import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.PrimitiveIterator.OfLong;
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

  EmptyLongStream() {
    super();
  }

  EmptyLongStream(boolean ordered, boolean parallel, Runnable closeHandler) {
    super(ordered, parallel, closeHandler);
  }

  EmptyLongStream(boolean ordered, boolean parallel) {
    super(ordered, parallel);
  }

  @Override
  public LongStream unordered() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream onClose(Runnable closeHandler) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream filter(LongPredicate predicate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream map(LongUnaryOperator mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream mapToInt(LongToIntFunction mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream distinct() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream sorted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream peek(LongConsumer action) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream limit(long maxSize) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream skip(long n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void forEach(LongConsumer action) {
    // TODO Auto-generated method stub

  }

  @Override
  public void forEachOrdered(LongConsumer action) {
    // TODO Auto-generated method stub

  }

  @Override
  public long[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long reduce(long identity, LongBinaryOperator op) {
    return identity;
  }

  @Override
  public OptionalLong reduce(LongBinaryOperator op) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator,
          BiConsumer<R, R> combiner) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long sum() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public OptionalLong min() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OptionalLong max() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public OptionalDouble average() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongSummaryStatistics summaryStatistics() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean anyMatch(LongPredicate predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean allMatch(LongPredicate predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean noneMatch(LongPredicate predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public OptionalLong findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OptionalLong findAny() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleStream asDoubleStream() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<Long> boxed() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream sequential() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream parallel() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public OfLong iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public java.util.Spliterator.OfLong spliterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return "long[0]";
  }

}
