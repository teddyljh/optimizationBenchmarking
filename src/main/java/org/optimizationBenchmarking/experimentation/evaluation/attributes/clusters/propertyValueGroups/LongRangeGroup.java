package org.optimizationBenchmarking.experimentation.evaluation.attributes.clusters.propertyValueGroups;

import org.optimizationBenchmarking.experimentation.data.DataElement;

/**
 * A group of elements belonging to an integer range.
 * 
 * @param <DT>
 *          the data set type
 */
public final class LongRangeGroup<DT extends DataElement> extends
    ValueRangeGroup<Number, DT> {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * create the property value group
   * 
   * @param lowerBound
   *          the inclusive lower bound
   * @param upperBound
   *          the exclusive or inclusive upper bound
   * @param isUpperExclusive
   *          is the upper bound exclusive?
   * @param values
   *          the values
   * @param data
   *          the data
   */
  LongRangeGroup(final Long lowerBound, final Long upperBound,
      final boolean isUpperExclusive, final Number[] values,
      final DT[] data) {
    super(lowerBound, upperBound, isUpperExclusive, values, data);

    if (lowerBound.longValue() >= upperBound.longValue()) {
      throw new IllegalArgumentException(
          "Interval cannot be empty or composed of a single value, but lower bound " //$NON-NLS-1$
              + lowerBound + //
              " is specified and upper bound is "//$NON-NLS-1$ 
              + upperBound);
    }
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public final LongRangeGroups<DT> getOwner() {
    return ((LongRangeGroups) (this.m_owner));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean contains(final Object o) {
    final long num;
    if (o instanceof Number) {
      num = ((Number) o).longValue();
      if (num >= this.getLowerBound().longValue()) {
        if (this.isUpperBoundExclusive()) {
          return (num < this.getUpperBound().longValue());
        }
        return (num <= this.getUpperBound().longValue());
      }
    }
    return false;
  }
}