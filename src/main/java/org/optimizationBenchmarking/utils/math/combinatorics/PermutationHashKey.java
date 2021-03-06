package org.optimizationBenchmarking.utils.math.combinatorics;

import java.util.Arrays;

/** A key that can be used to represent permutations in hashes. */
public final class PermutationHashKey implements Cloneable {

  /** the data, i.e., permutation */
  private int[] m_data;

  /** the hash */
  private int m_hash;

  /**
   * create the permutation hash key
   * 
   * @param data
   *          the data
   */
  public PermutationHashKey(final int[] data) {
    super();
    this.setData(data);
  }

  /**
   * Set the data array, i.e., the permutation
   * 
   * @param data
   *          the data
   */
  public final void setData(final int[] data) {
    this.m_data = data;
    this.updateHashCode();
  }

  /** Update the hash code after the data has been changed */
  public final void updateHashCode() {
    this.m_hash = Arrays.hashCode(this.m_data);
  }

  /**
   * Get the data, i.e., the permutation
   * 
   * @return the permutation
   */
  public final int[] getData() {
    return this.m_data;
  }

  /** {@inheritDoc} */
  @Override
  public final int hashCode() {
    return this.m_hash;
  }

  /** {@inheritDoc} */
  @Override
  public final boolean equals(final Object o) {
    return ((o == this) || //
    ((o instanceof PermutationHashKey) && //
    Arrays.equals(this.m_data, ((PermutationHashKey) o).m_data)));
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return (this.m_hash + ": " + //$NON-NLS-1$
    Arrays.toString(this.m_data));
  }

  /** {@inheritDoc} */
  @Override
  public final PermutationHashKey clone() {
    PermutationHashKey old;
    try {
      old = ((PermutationHashKey) (super.clone()));
    } catch (final Throwable tt) {
      return null;
    }
    old.m_data = old.m_data.clone();

    return old;
  }
}
