package com.github.marschall.emptystreams;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

abstract class EmptySpliterator<T> implements Spliterator<T> {

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    return false;
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    // ignore because empty
    Objects.requireNonNull(action);
  }

  @Override
  public Spliterator<T> trySplit() {
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

}