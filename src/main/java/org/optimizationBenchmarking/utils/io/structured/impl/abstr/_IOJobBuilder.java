package org.optimizationBenchmarking.utils.io.structured.impl.abstr;

import org.optimizationBenchmarking.utils.io.encoding.StreamEncoding;
import org.optimizationBenchmarking.utils.io.structured.spec.IIOJob;
import org.optimizationBenchmarking.utils.io.structured.spec.IIOJobBuilder;
import org.optimizationBenchmarking.utils.text.TextUtils;
import org.optimizationBenchmarking.utils.tools.impl.abstr.ConfigurableToolJobBuilder;

/**
 * The base class for building IO jobs
 * 
 * @param <JBT>
 *          the job builder type
 */
abstract class _IOJobBuilder<JBT extends _IOJobBuilder<JBT>> extends
    ConfigurableToolJobBuilder<IIOJob, JBT> implements IIOJobBuilder {

  /** the tool */
  final IOTool<?> m_tool;

  /**
   * create the IIOJobBuilder
   * 
   * @param tool
   *          the owning tool
   */
  _IOJobBuilder(final IOTool<?> tool) {
    super();
    _IOJobBuilder._validateTool(tool);
    this.m_tool = tool;
  }

  /**
   * load the give source/dest list string
   * 
   * @param location
   *          the location string
   */
  void _location(final String location) {
    int start, end;
    String function, parameter;

    if (location == null) {
      return;
    }
    start = location.indexOf('(');
    if (start <= 0) {
      return;
    }
    end = location.lastIndexOf(')');
    if (end <= start) {
      return;
    }

    function = TextUtils.prepare(location.substring(0, start));
    if (function == null) {
      return;
    }

    parameter = TextUtils.prepare(location.substring(start + 1, end));
    if (parameter == null) {
      return;
    }

    if (IOTool.PATH_ELEMENT.equalsIgnoreCase(function)) {
      this._setPath(parameter, null, false);
      return;
    }

    if (IOTool.ZIPPED_PATH_ELEMENT.equalsIgnoreCase(function)) {
      this._setPath(parameter, null, true);
      return;
    }

    if (IOTool.ZIPPED_URI_ELEMENT.equalsIgnoreCase(function)) {
      this._setURI(parameter, null, true);
      return;
    }

    if (IOTool.ZIPPED_URL_ELEMENT.equalsIgnoreCase(function)) {
      this._setURL(parameter, null, true);
      return;
    }

    throw new UnsupportedOperationException(//
        "Cannot process I/O function '" + function + //$NON-NLS-1$
            "' with parameter '" + parameter + '\'');//$NON-NLS-1$
  }

  /**
   * Set the path
   * 
   * @param path
   *          the path
   * @param encoding
   *          the encoding
   * @param zipCompress
   *          zip compress?
   */
  abstract void _setPath(final String path,
      final StreamEncoding<?, ?> encoding, final boolean zipCompress);

  /**
   * Set the uri
   * 
   * @param uri
   *          the uri
   * @param encoding
   *          the encoding
   * @param zipCompress
   *          zip compress?
   */
  abstract void _setURI(final String uri,
      final StreamEncoding<?, ?> encoding, final boolean zipCompress);

  /**
   * Set the url
   * 
   * @param url
   *          the url
   * @param encoding
   *          the encoding
   * @param zipCompress
   *          zip compress?
   */
  abstract void _setURL(final String url,
      final StreamEncoding<?, ?> encoding, final boolean zipCompress);

  /**
   * validate the tool
   * 
   * @param tool
   *          the tool
   */
  static final void _validateTool(final IOTool<?> tool) {
    if (tool == null) {
      throw new IllegalArgumentException("Tool must not be null."); //$NON-NLS-1$
    }
    if (!(tool.canUse())) {
      throw new IllegalArgumentException("Tool '" + //$NON-NLS-1$
          tool.toString() + "' cannot be used."); //$NON-NLS-1$
    }
  }

  /**
   * create the job
   * 
   * @return the job
   */
  abstract IIOJob _doCreate();

  /** {@inheritDoc} */
  @Override
  public final IIOJob create() {
    this.validate();
    return this._doCreate();
  }
}