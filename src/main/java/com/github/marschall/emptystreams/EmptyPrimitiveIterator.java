package com.github.marschall.emptystreams;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;

abstract class EmptyPrimitiveIterator<T, T_CONS> implements PrimitiveIterator<T, T_CONS> {

  EmptyPrimitiveIterator() {
    super();
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public T next() {
    throw new NoSuchElementException();
  }

  @Override
  public void forEachRemaining(T_CONS action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

}
