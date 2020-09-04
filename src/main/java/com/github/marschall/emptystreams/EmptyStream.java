package com.github.marschall.emptystreams;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

final class EmptyStream<T> extends EmptyBaseStream<T, Stream<T>> implements Stream<T> {

  private static final Object[] EMTPY = new Object[0];

  EmptyStream() {
    super();
  }

  EmptyStream(boolean ordered, boolean parallel, Runnable closeHandler) {
    super(ordered, parallel, closeHandler);
  }

  EmptyStream(boolean ordered, boolean parallel) {
    super(ordered, parallel);
  }

  @Override
  public Iterator<T> iterator() {
    return Collections.emptyIterator();
  }

  @Override
  public Spliterator<T> spliterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> sequential() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> parallel() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> unordered() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> onClose(Runnable closeHandler) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> filter(Predicate<? super T> predicate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return (Stream<R>) this;
  }

  @Override
  public IntStream mapToInt(ToIntFunction<? super T> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this::close);
  }

  @Override
  public LongStream mapToLong(ToLongFunction<? super T> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this::close);
  }

  @Override
  public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this::close);
  }

  @Override
  public <R> Stream<R> flatMap(
          Function<? super T, ? extends Stream<? extends R>> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntStream flatMapToInt(
          Function<? super T, ? extends IntStream> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LongStream flatMapToLong(
          Function<? super T, ? extends LongStream> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DoubleStream flatMapToDouble(
          Function<? super T, ? extends DoubleStream> mapper) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> distinct() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> sorted() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> sorted(Comparator<? super T> comparator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> peek(Consumer<? super T> action) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> limit(long maxSize) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Stream<T> skip(long n) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    // TODO Auto-generated method stub

  }

  @Override
  public void forEachOrdered(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    // TODO Auto-generated method stub

  }

  @Override
  public Object[] toArray() {
    this.closeAndCheck();
    return EMTPY;
  }

  @Override
  public <A> A[] toArray(IntFunction<A[]> generator) {
    Objects.requireNonNull(generator);
    this.closeAndCheck();
    return generator.apply(0);
  }

  @Override
  public T reduce(T identity, BinaryOperator<T> accumulator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> reduce(BinaryOperator<T> accumulator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator,
          BinaryOperator<U> combiner) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <R> R collect(Supplier<R> supplier,
          BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <R, A> R collect(Collector<? super T, A, R> collector) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> min(Comparator<? super T> comparator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> max(Comparator<? super T> comparator) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    return 0L;
  }

  @Override
  public boolean anyMatch(Predicate<? super T> predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean allMatch(Predicate<? super T> predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean noneMatch(Predicate<? super T> predicate) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Optional<T> findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> findAny() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return "Object[0]";
  }

}
