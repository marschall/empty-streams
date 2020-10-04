package com.github.marschall.emptystreams;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class EmptyIntStreamBenchmarks {

  @Benchmark
  public long jdkCount() {
    return IntStream.empty().count();
  }

  @Benchmark
  public long libraryCount() {
    return EmptyStreams.emptyIntStream().count();
  }

  @Benchmark
  public void jdkForEach(Blackhole blackhole) {
    IntStream.empty().forEach((int i) -> blackhole.consume(i));
  }

  @Benchmark
  public void libraryForEach(Blackhole blackhole) {
    EmptyStreams.emptyIntStream().forEach((int i) -> blackhole.consume(i));
  }

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
            .include(".*EmptyIntStreamBenchmarks.*")
            .forks(3)
            .warmupIterations(3)
            .measurementIterations(5)
            .build();
    new Runner(options).run();
  }

}
