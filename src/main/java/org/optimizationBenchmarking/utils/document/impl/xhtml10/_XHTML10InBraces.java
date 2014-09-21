package org.optimizationBenchmarking.utils.document.impl.xhtml10;

import org.optimizationBenchmarking.utils.document.impl.abstr.InBraces;
import org.optimizationBenchmarking.utils.document.impl.abstr.Text;

/** an in-braces element of a section in a XHTML document */
final class _XHTML10InBraces extends InBraces {
  /**
   * create the in-braces element
   * 
   * @param owner
   *          the owner
   */
  _XHTML10InBraces(final Text owner) {
    super(owner);
    this.open();
  }
}