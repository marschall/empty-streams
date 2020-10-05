package com.github.marschall.emptystreams;

import java.util.Comparator;
import java.util.Spliterator.OfPrimitive;

abstract class EmptySortedOfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends EmptyOfPrimitive<T, T_CONS, T_SPLITR> {

  EmptySortedOfPrimitive() {
    super();
  }

  @Override
  public Comparator<? super T> getComparator() {
    return null;
  }

}
