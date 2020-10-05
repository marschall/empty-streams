package com.github.marschall.emptystreams;

import java.util.Spliterator.OfPrimitive;

abstract class EmptyWithCharacteristicsOfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends EmptyOfPrimitive<T, T_CONS, T_SPLITR> {

  private final int characteristics;

  EmptyWithCharacteristicsOfPrimitive(int characteristics) {
    this.characteristics = characteristics;
  }

  @Override
  public int characteristics() {
    return this.characteristics;
  }

}
