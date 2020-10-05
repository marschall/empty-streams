package com.github.marschall.emptystreams;

import java.util.Objects;
import java.util.Spliterator.OfPrimitive;

abstract class EmptyOfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends EmptySpliterator<T> implements OfPrimitive<T, T_CONS, T_SPLITR> {

  EmptyOfPrimitive() {
    super();
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
  public void forEachRemaining(T_CONS action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

}
