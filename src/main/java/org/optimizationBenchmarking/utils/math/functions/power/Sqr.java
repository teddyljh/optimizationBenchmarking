package org.optimizationBenchmarking.utils.math.functions.power;

import org.optimizationBenchmarking.utils.math.functions.UnaryFunction;

/**
 * The sqr function
 */
public final class Sqr extends UnaryFunction {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /** the globally shared instance */
  public static final Sqr INSTANCE = new Sqr();

  /** instantiate */
  private Sqr() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public final byte computeAsByte(final byte x1) {
    return ((byte) (x1 * x1));
  }

  /** {@inheritDoc} */
  @Override
  public final short computeAsShort(final short x1) {
    return ((short) (x1 * x1));
  }

  /** {@inheritDoc} */
  @Override
  public final int computeAsInt(final int x1) {
    return (x1 * x1);
  }

  /** {@inheritDoc} */
  @Override
  public final long computeAsLong(final long x1) {
    return (x1 * x1);
  }

  /** {@inheritDoc} */
  @Override
  public final float computeAsFloat(final float x1) {
    return (x1 * x1);
  }

  /** {@inheritDoc} */
  @Override
  public final double computeAsDouble(final double x1) {
    return (x1 * x1);
  }

  /** {@inheritDoc} */
  @Override
  public final Sqrt invertFor(final int index) {
    return Sqrt.INSTANCE;
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
    return Sqr.INSTANCE;
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
    return Sqr.INSTANCE;
  }
}