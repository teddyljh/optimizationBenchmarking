package test.junit.org.optimizationBenchmarking.experimentation.dataAndIO;

import test.junit.TestBase;
import examples.org.optimizationBenchmarking.experimentation.dataAndIO.Example1;

/** Test the first example. */
public class Example1Test extends ExperimentSetTest {

  /** create */
  public Example1Test() {
    super(new Example1(TestBase.getNullLogger()));
  }
}
