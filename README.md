Empty Streams
=============

Empty implementations of the Java `java.util.stream.Stream`, `java.util.stream.IntStream`, `java.util.stream.LongStream` and `java.util.stream.DoubleStream`

Compared to `java.util.stream.Stream#empty()`, `java.util.stream.IntStream#empty()`, `java.util.stream.LongStream#empty()` and `java.util.stream.DoubleStream#empty()` these implementations can be more efficient.

Because streams are inherently stateful, they have a state which may be closed, a minimum of allocation can not be avoided.

The behavior of these streams might slightly differ to the JDK streams when it comes to spliterator characteristics but should still be compliant the with API contract requirements outlined in the Javadoc.

