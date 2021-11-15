package com.github.marschall.emptystreams;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SIZED;
import static java.util.Spliterator.SUBSIZED;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
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

  @SuppressWarnings("rawtypes")
  private static final Spliterator EMPTY_SPLITERATOR_ORDERD = new EmptySpliteratorWithCharacteristics(SIZED | NONNULL | IMMUTABLE | ORDERED | SUBSIZED);
  @SuppressWarnings("rawtypes")
  private static final Spliterator EMPTY_SPLITERATOR_UNORDERD = new EmptySpliteratorWithCharacteristics(SIZED | NONNULL | IMMUTABLE | SUBSIZED);
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private static final Spliterator EMPTY_SPLITERATOR_SORTED = new EmptySortedSpliterator(null);
  private static final Object[] EMTPY = new Object[0];

  private Comparator<? super T> comparator;

  EmptyStream() {
    super();
  }

  EmptyStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    super(ordered, parallel, sorted, closeHandler);
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
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  @Override
  public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
    Objects.requireNonNull(mapper);
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

  public <R> Stream<R> mapMulti(BiConsumer<? super T, ? super Consumer<R>> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return (Stream<R>) this;
  }

  public IntStream mapMultiToInt(BiConsumer<? super T, ? super IntConsumer> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return new EmptyIntStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  public LongStream mapMultiToLong(BiConsumer<? super T, ? super LongConsumer> mapper) {
    Objects.requireNonNull(mapper);
    // ignore because empty
    this.closedCheck();
    return new EmptyLongStream(this.ordered, this.parallel, this.sorted, this::close);
  }

  public DoubleStream mapMultiToDouble(BiConsumer<? super T, ? super DoubleConsumer> mapper) {
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
    this.sorted = true;
    return this;
  }

  @Override
  public Stream<T> sorted(Comparator<? super T> comparator) {
    Objects.requireNonNull(comparator);
    this.closedCheck();
    this.sorted = true;
    this.comparator = comparator;
    return this;
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
    return Objects.requireNonNull(generator.apply(0));
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
    Objects.requireNonNull(collector);
    this.closeAndCheck();
    Supplier<A> supplier = collector.supplier();
    Function<A, R> finisher = collector.finisher();
    return finisher.apply(supplier.get());
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

  public List<T> toList() {
    this.closeAndCheck();
    // List.of() would require JDK 11+
    return Collections.emptyList();
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

  public Stream<T> dropWhile​(Predicate<? super T> predicate) {
    this.closedCheck();
    Objects.requireNonNull(predicate);
    return this;
  }

  public Stream<T> takeWhile​(Predicate<? super T> predicate) {
    this.closedCheck();
    Objects.requireNonNull(predicate);
    return this;
  }

  @Override
  public Iterator<T> iterator() {
    return Collections.emptyIterator();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Spliterator<T> spliterator() {
    if (this.sorted) {
      if (this.comparator != null) {
        return new EmptySortedSpliterator<>(this.comparator);
      } else {
        return EMPTY_SPLITERATOR_SORTED;
      }
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
    return "Object[0]";
  }

  static final class EmptySpliteratorWithCharacteristics<T> extends EmptySpliterator<T> {

    private final int characteristics;

    EmptySpliteratorWithCharacteristics(int characteristics) {
      this.characteristics = characteristics;
    }

    @Override
    public int characteristics() {
      return this.characteristics;
    }

  }

  static final class EmptySortedSpliterator<T> extends EmptySpliterator<T> {

    private final Comparator<? super T> comparator;

    EmptySortedSpliterator(Comparator<? super T> comparator) {
      this.comparator = comparator;
    }

    @Override
    public Comparator<? super T> getComparator() {
      return this.comparator;
    }

    @Override
    public int characteristics() {
      return SIZED | NONNULL | IMMUTABLE | ORDERED | SORTED | SUBSIZED;
    }

  }

}
