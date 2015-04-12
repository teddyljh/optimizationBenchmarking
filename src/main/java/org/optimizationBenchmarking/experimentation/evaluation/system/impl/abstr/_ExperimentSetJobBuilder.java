package org.optimizationBenchmarking.experimentation.evaluation.system.impl.abstr;

import org.optimizationBenchmarking.experimentation.data.ExperimentSet;
import org.optimizationBenchmarking.experimentation.evaluation.system.spec.IExperimentSetJobBuilder;

/** The experiment set job builder implementation */
final class _ExperimentSetJobBuilder
    extends
    _EvaluationJobBuilder<ExperimentSet, ExperimentSetModule, ExperimentSetJob, _ExperimentSetJobBuilder>
    implements IExperimentSetJobBuilder {

  /**
   * create the job builder
   * 
   * @param module
   *          the module
   */
  _ExperimentSetJobBuilder(final ExperimentSetModule module) {
    super(module);
  }
}
