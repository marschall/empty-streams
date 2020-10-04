package com.github.marschall.emptystreams;

import java.util.Spliterator.OfPrimitive;

abstract class EmptyOfPrimitiveWithCharacteristics<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends EmptyOfPrimitive<T, T_CONS, T_SPLITR> {

  private final int characteristics;

  EmptyOfPrimitiveWithCharacteristics(int characteristics) {
    super();
    this.characteristics = characteristics;
  }

  @Override
  public int characteristics() {
    return this.characteristics;
  }

}
