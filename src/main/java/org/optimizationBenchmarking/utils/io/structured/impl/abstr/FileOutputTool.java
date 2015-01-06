package org.optimizationBenchmarking.utils.io.structured.impl.abstr;

import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.optimizationBenchmarking.utils.io.encoding.StreamEncoding;
import org.optimizationBenchmarking.utils.io.paths.PathUtils;
import org.optimizationBenchmarking.utils.io.paths.TempDir;
import org.optimizationBenchmarking.utils.io.structured.spec.IFileOutputJobBuilder;
import org.optimizationBenchmarking.utils.io.structured.spec.IFileOutputTool;

/**
 * A tool for generating file output.
 * 
 * @param <S>
 *          the source type
 */
public class FileOutputTool<S> extends IOTool<S> implements
    IFileOutputTool<S> {

  /** create */
  protected FileOutputTool() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  void _handle(final IOJob job, final S data, final _Location location)
      throws Throwable {

    if (location.m_location1 instanceof OutputStream) {
      if (!(location.m_zipped)) {
        this._checkRawStreams();
      }
      if (job.canLog()) {
        job.log("Beginning output to OutputStream."); //$NON-NLS-1$
      }
      this._stream(job, data, ((OutputStream) (location.m_location1)),
          location.m_encoding, location.m_zipped);
      if (job.canLog()) {
        job.log("Finished output to OutputStream."); //$NON-NLS-1$
      }
      return;
    }

    super._handle(job, data, location);
  }

  /**
   * Store the data element to a stream
   * 
   * @param job
   *          the job where logging info can be written
   * @param data
   *          the data to be written
   * @param stream
   *          the stream
   * @param encoding
   *          the encoding
   * @param zipCompress
   *          should we ZIP-compress?
   * @throws Throwable
   */
  void _stream(final IOJob job, final S data, final OutputStream stream,
      final StreamEncoding<?, ?> encoding, final boolean zipCompress)
      throws Throwable {
    final Path path;

    if (zipCompress) {
      try (final TempDir temp = new TempDir()) {
        path = temp.getPath();
        this._pathNormalized(job, data, temp.getPath(), encoding, false);
        PathUtils.zipToStream(path, stream);
      }
    } else {
      this._checkRawStreams();
    }
  }

  /**
   * Obtain the default name for a zipped file to write to if the
   * destination path specifies a directory
   * 
   * @return the default file name
   */
  protected String getDefaultZIPOutputFileName() {
    return "output.zip"; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  void _path(final IOJob job, final S data, final Path path,
      final BasicFileAttributes attributes,
      final StreamEncoding<?, ?> encoding, final boolean zipped)
      throws Throwable {
    Path file;

    if (zipped) {
      if ((attributes != null) && (attributes.isDirectory())) {
        file = PathUtils.createPathInside(path,
            this.getDefaultZIPOutputFileName());
        job.log("Path '" + path + //$NON-NLS-1$
            "' identifies a directory, creating file '" //$NON-NLS-1$
            + file + "' for output.");//$NON-NLS-1$
      } else {
        file = path;
      }

      try (final OutputStream output = PathUtils.openOutputStream(path)) {
        this._stream(job, data, output, encoding, true);
      }
    } else {
      this.path(job, data, path, attributes, encoding, false);
    }
  }

  /**
   * Handle a path
   * 
   * @param job
   *          the job where logging info can be written
   * @param data
   *          the data to be written or read
   * @param path
   *          the path
   * @param attributes
   *          the attributes
   * @param encoding
   *          the encoding
   * @throws Throwable
   */
  protected void path(final IOJob job, final S data, final Path path,
      final BasicFileAttributes attributes,
      final StreamEncoding<?, ?> encoding) throws Throwable {
    //
  }

  /** {@inheritDoc} */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public IFileOutputJobBuilder<S> use() {
    this.beforeUse();
    return new _FileOutputJobBuilder(this);
  }
}