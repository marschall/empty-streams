package com.github.marschall.emptystreams;

import java.util.Objects;
import java.util.Spliterator.OfPrimitive;
import java.util.function.Consumer;

abstract class EmptyOfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> implements OfPrimitive<T, T_CONS, T_SPLITR> {

  EmptyOfPrimitive() {
    super();
  }

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    return false;
  }

  @Override
  public boolean tryAdvance(T_CONS action) {
    Objects.requireNonNull(action);
    return false;
  }

  @Override
  public T_SPLITR trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return 0L;
  }

  @Override
  public long getExactSizeIfKnown() {
    return 0L;
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

  @Override
  public void forEachRemaining(T_CONS action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

}
