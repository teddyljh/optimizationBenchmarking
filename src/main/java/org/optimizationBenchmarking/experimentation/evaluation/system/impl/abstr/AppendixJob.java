package org.optimizationBenchmarking.experimentation.evaluation.system.impl.abstr;

import java.util.logging.Logger;

import org.optimizationBenchmarking.experimentation.data.spec.IExperimentSet;

/**
 * The kind of job which can carry out the work of an
 * {@link org.optimizationBenchmarking.experimentation.evaluation.system.spec.IAppendixModule}
 * .
 */
public abstract class AppendixJob extends _EvaluationJob<IExperimentSet> {
  /**
   * Create the appendix evaluation job
   * 
   * @param data
   *          the data
   * @param logger
   *          the logger
   */
  protected AppendixJob(final IExperimentSet data, final Logger logger) {
    super(data, logger);
  }
}
