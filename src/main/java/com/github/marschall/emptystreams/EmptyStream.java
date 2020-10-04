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

  EmptyStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    super(ordered, parallel, sorted, closeHandler);
  }

  EmptyStream(boolean ordered, boolean parallel, boolean sorted) {
    super(ordered, parallel, sorted);
  }

  @Override
  public Stream<T> unordered() {
    this.closedCheck();
    if (this.ordered) {
      return new EmptyStream<>(false, this.parallel, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public Stream<T> onClose(Runnable closeHandler) {
    Objects.requireNonNull(closeHandler);
    this.closedCheck();
    return new EmptyStream<>(this.ordered, this.parallel, this.sorted, this.composeCloseHandler(closeHandler));
  }

  @Override
  public Stream<T> filter(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    this.closedCheck();
    return this;
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
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream mapToLong(ToLongFunction<? super T> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
    Objects.requireNonNull(mapper);
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return (Stream<R>) this;
  }

  @Override
  public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
    // ignore because empty
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
    // ignore because empty
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return new EmptyDoubleStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public Stream<T> distinct() {
    this.closedCheck();
    return this;
  }

  @Override
  public Stream<T> sorted() {
    this.closedCheck();
    if (this.sorted) {
      return this;
    } else {
      return new EmptyStream<>(this.ordered, this.parallel, true, this::close);
    }
  }

  @Override
  public Stream<T> sorted(Comparator<? super T> comparator) {
    // TODO pass to spliterator
    Objects.requireNonNull(comparator);
    this.closedCheck();
    if (this.sorted) {
      return this;
    } else {
      return new EmptyStream<>(this.ordered, this.parallel, true, this::close);
    }
  }

  @Override
  public Stream<T> peek(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closedCheck();
    return this;
  }

  @Override
  public Stream<T> limit(long maxSize) {
    if (maxSize < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public Stream<T> skip(long n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    this.closedCheck();
    return this;
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
  }

  @Override
  public void forEachOrdered(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    // ignore because empty
    this.closeAndCheck();
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
    Objects.requireNonNull(accumulator);
    this.closeAndCheck();
    return identity;
  }

  @Override
  public Optional<T> reduce(BinaryOperator<T> accumulator) {
    Objects.requireNonNull(accumulator);
    this.closeAndCheck();
    return Optional.empty();
  }

  @Override
  public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
    Objects.requireNonNull(accumulator);
    Objects.requireNonNull(combiner);
    this.closeAndCheck();
    return identity;
  }

  @Override
  public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
    Objects.requireNonNull(supplier);
    Objects.requireNonNull(accumulator);
    Objects.requireNonNull(combiner);
    this.closeAndCheck();
    return supplier.get();
  }

  @Override
  public <R, A> R collect(Collector<? super T, A, R> collector) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<T> min(Comparator<? super T> comparator) {
    Objects.requireNonNull(comparator);
    this.closeAndCheck();
    return Optional.empty();
  }

  @Override
  public Optional<T> max(Comparator<? super T> comparator) {
    Objects.requireNonNull(comparator);
    this.closeAndCheck();
    return Optional.empty();
  }

  @Override
  public long count() {
    this.closeAndCheck();
    return 0L;
  }

  @Override
  public boolean anyMatch(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return false;
  }

  @Override
  public boolean allMatch(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public boolean noneMatch(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    this.closeAndCheck();
    return true;
  }

  @Override
  public Optional<T> findFirst() {
    this.closeAndCheck();
    return Optional.empty();
  }

  @Override
  public Optional<T> findAny() {
    this.closeAndCheck();
    return Optional.empty();
  }

  @Override
  public Stream<T> sequential() {
    this.closedCheck();
    if (this.parallel) {
      return new EmptyStream<>(this.ordered, false, this.sorted, this::close);
    } else {
      return this;
    }
  }

  @Override
  public Stream<T> parallel() {
    this.closedCheck();
    if (this.parallel) {
      return this;
    } else {
      return new EmptyStream<>(this.ordered, true, this.sorted, this::close);
    }
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
  public String toString() {
    return "Object[0]";
  }

}
