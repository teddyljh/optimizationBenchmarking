package org.optimizationBenchmarking.utils.math.statistics;

import org.optimizationBenchmarking.utils.math.functions.arithmetic.Absolute;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.MaximumAggregate;

/** The parameter representing the maximum of the observed samples. */
public final class Maximum extends StatisticParameter {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Maximum INSTANCE = new Maximum();

  /** create */
  private Maximum() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final MaximumAggregate createAggregate() {
    return new MaximumAggregate();
  }

  // default, automatic serialization replacement and resolve routines for
  // singletons
  //
  /**
   * Write replace: the instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} for serialization, i.e.,
   * when the instance is written with
   * {@link java.io.ObjectOutputStream#writeObject(Object)}.
   * 
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object writeReplace() {
    return Absolute.INSTANCE;
  }

  /**
   * Read resolve: The instance this method is invoked on will be replaced
   * with the singleton instance {@link #INSTANCE} after serialization,
   * i.e., when the instance is read with
   * {@link java.io.ObjectInputStream#readObject()}.
   * 
   * @return the replacement instance (always {@link #INSTANCE})
   */
  private final Object readResolve() {
    return Absolute.INSTANCE;
  }
}
