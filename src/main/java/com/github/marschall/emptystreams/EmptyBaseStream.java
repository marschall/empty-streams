package com.github.marschall.emptystreams;

import java.util.stream.BaseStream;

abstract class EmptyBaseStream<T, S extends BaseStream<T, S>> implements BaseStream<T, S> {

  private boolean closed;

  final boolean ordered;

  final boolean parallel;

  final boolean sorted;

  private final Runnable closeHandler;

  EmptyBaseStream() {
    this(true, false, false, null);
  }

  EmptyBaseStream(boolean ordered, boolean parallel, boolean sorted) {
    this(ordered, parallel, sorted, null);
  }

  EmptyBaseStream(boolean ordered, boolean parallel, boolean sorted, Runnable closeHandler) {
    this.ordered = ordered;
    this.parallel = parallel;
    this.sorted = sorted;
    this.closeHandler = closeHandler;
  }

  @Override
  public boolean isParallel() {
    return this.parallel;
  }

  Runnable composeCloseHandler(Runnable r) {
    if (this.closeHandler == null) {
      return compose(r, this::close);
    } else {
      return compose(this.closeHandler, r);
    }
  }

  static Runnable compose(Runnable first, Runnable second) {
    return () -> {
      try {
        first.run();
      } catch (Throwable t1) {
        try {
          second.run();
        } catch (Throwable t2) {
          t1.addSuppressed(t2);
        }
      }
      second.run();
    };
  }

  void closeAndCheck() {
    this.closedCheck();
    this.close();
  }

  void closedCheck() {
    if (this.closed) {
      throw new IllegalStateException("stream has already been operated upon or closed");
    }
  }

  @Override
  public void close() {
    if (!this.closed) {
      this.closed = true;
      if (this.closeHandler != null) {
        this.closeHandler.run();
      }
    }
  }

}
