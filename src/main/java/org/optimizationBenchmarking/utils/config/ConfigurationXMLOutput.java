package org.optimizationBenchmarking.utils.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map.Entry;

import org.optimizationBenchmarking.utils.io.encoding.StreamEncoding;
import org.optimizationBenchmarking.utils.io.structured.impl.abstr.IOJob;
import org.optimizationBenchmarking.utils.io.structured.impl.abstr.XMLOutputTool;
import org.optimizationBenchmarking.utils.io.xml.XMLBase;
import org.optimizationBenchmarking.utils.io.xml.XMLElement;

/** the configuration xml output */
public final class ConfigurationXMLOutput extends
    XMLOutputTool<Configuration> {

  /** create */
  ConfigurationXMLOutput() {
    super();
  }

  /**
   * get the instance of the {@link ConfigurationXMLOutput}
   * 
   * @return the instance of the {@link ConfigurationXMLOutput}
   */
  public static final ConfigurationXMLOutput getInstance() {
    return __ConfigurationXMLOutputLoader.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  protected void xml(final IOJob job, final Configuration data,
      final XMLBase xmlBase) throws Throwable {
    try (XMLElement root = xmlBase.element()) {
      root.namespaceSetPrefix(ConfigurationXML.NAMESPACE_URI, "cfg"); //$NON-NLS-1$
      root.name(ConfigurationXML.NAMESPACE_URI,
          ConfigurationXML.ELEMENT_CONFIGURATION_ROOT);

      synchronized (data.m_data) {

        root.attributeRaw(ConfigurationXML.NAMESPACE_URI,
            ConfigurationXML.ATTRIBUTE_CONFIGURATION_VERSION,
            ConfigurationXML.ATTRIBUTE_VALUE_CONFIGURATION_VERSION);

        for (final Entry<String, Object> e : data.m_data.entries()) {
          try (final XMLElement param = root.element()) {
            param.name(ConfigurationXML.NAMESPACE_URI,
                ConfigurationXML.ELEMENT_CONFIGURATION_PARAMETER);

            param.attributeEncoded(ConfigurationXML.NAMESPACE_URI,
                ConfigurationXML.ATTRIBUTE_CONFIGURATION_PARAMETER_NAME,
                e.getKey());
            param.attributeEncoded(ConfigurationXML.NAMESPACE_URI,
                ConfigurationXML.ATTRIBUTE_CONFIGURATION_PARAMETER_VALUE,
                String.valueOf(e.getValue()));
          }
        }
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "Configuration XMLFileType Output"; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final void file(final IOJob job, final Configuration data,
      final Path file, final StreamEncoding<?, ?> encoding)
      throws Throwable {
    super.file(job, data, file, encoding);
    if (Files.exists(file)) {
      this.addFile(job, file, ConfigurationXML.CONFIG_XML);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final String getDefaultPlainOutputFileName() {
    return ("config." + //$NON-NLS-1$
    ConfigurationXML.CONFIG_XML.getDefaultSuffix());
  }

  /** the loader */
  private static final class __ConfigurationXMLOutputLoader {
    /** the configuration xml */
    static final ConfigurationXMLOutput INSTANCE = new ConfigurationXMLOutput();
  }
}
