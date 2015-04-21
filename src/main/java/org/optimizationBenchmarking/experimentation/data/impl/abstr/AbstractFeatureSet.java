package org.optimizationBenchmarking.experimentation.data.impl.abstr;

import org.optimizationBenchmarking.experimentation.data.spec.IExperimentSet;
import org.optimizationBenchmarking.experimentation.data.spec.IFeature;
import org.optimizationBenchmarking.experimentation.data.spec.IFeatureSet;
import org.optimizationBenchmarking.experimentation.data.spec.IFeatureSetting;
import org.optimizationBenchmarking.utils.collections.lists.ArraySetView;

/**
 * An abstract implementation of the
 * {@link org.optimizationBenchmarking.experimentation.data.spec.IFeatureSet}
 * interface.
 */
public class AbstractFeatureSet extends
    _AbstractPropertySet<IFeatureSetting> implements IFeatureSet {

  /**
   * create the abstract feature set
   * 
   * @param owner
   *          the owner
   */
  protected AbstractFeatureSet(final IExperimentSet owner) {
    super(owner);
  }

  /**
   * Create an abstract feature set without an owner. You must later set
   * the owner via
   * {@link org.optimizationBenchmarking.experimentation.data.impl.abstr.AbstractExperimentSet#own(AbstractFeatureSet)}
   * .
   */
  protected AbstractFeatureSet() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  final IFeatureSetting _createEmpty() {
    return new AbstractFeatureSetting();
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public ArraySetView<? extends IFeature> getData() {
    return ((ArraySetView) (ArraySetView.EMPTY_SET_VIEW));
  }

  /** {@inheritDoc} */
  @Override
  public IFeature find(final String name) {
    return ((IFeature) (super.find(name)));
  }
}
