package org.optimizationBenchmarking.utils.graphics.graphic.impl.freeHEP;

import java.awt.Color;
import java.awt.Dimension;
import java.io.OutputStream;
import java.nio.file.Path;

import org.freehep.graphicsio.FontConstants;
import org.freehep.graphicsio.PageConstants;
import org.freehep.graphicsio.pdf.PDFGraphics2D;
import org.freehep.util.UserProperties;
import org.optimizationBenchmarking.utils.document.object.IObjectListener;
import org.optimizationBenchmarking.utils.graphics.GraphicUtils;
import org.optimizationBenchmarking.utils.graphics.PhysicalDimension;
import org.optimizationBenchmarking.utils.graphics.graphic.EGraphicFormat;
import org.optimizationBenchmarking.utils.graphics.graphic.impl.abstr.AbstractGraphicDriver;
import org.optimizationBenchmarking.utils.graphics.graphic.spec.Graphic;
import org.optimizationBenchmarking.utils.math.units.ELength;

/**
 * A driver which creates <a
 * href="http://en.wikipedia.org/wiki/Portable_Document_Format">PDF</a>
 * graphics. This driver uses a very dirty hack to enforce custom page
 * sizes in the pdf output: It temporarily changes the size of the
 * {@link org.freehep.graphicsio.PageConstants#INTERNATIONAL} page&hellip;
 * All using FreeHEP should thus synchronize on
 * <code>{@link org.freehep.graphicsio.PageConstants}.class</code> during
 * all code accessing page sizes. This driver has some problems, see, e.g.,
 * {@link examples.org.optimizationBenchmarking.utils.graphics.GraphicsExample}
 * .
 */
public class FreeHEPPDFGraphicDriver extends AbstractGraphicDriver {
  /** the properties */
  private final org.freehep.util.UserProperties m_props;

  /** the correct dimension to use */
  final Dimension m_correctDim;

  /** the dimension to temporarily destroy */
  final Dimension m_messWith;

  /** the hidden constructor */
  FreeHEPPDFGraphicDriver() {
    super("pdf"); //$NON-NLS-1$

    this.m_props = new org.freehep.util.UserProperties();

    PDFGraphics2D.setClipEnabled(true);

    this.m_props.putAll(PDFGraphics2D.getDefaultProperties());
    this.m_props.setProperty(PDFGraphics2D.EMBED_FONTS, true);
    this.m_props.setProperty(PDFGraphics2D.EMBED_FONTS_AS,
        FontConstants.EMBED_FONTS_TYPE3);
    this.m_props.setProperty(PDFGraphics2D.BACKGROUND_COLOR, Color.WHITE);
    this.m_props.setProperty(PDFGraphics2D.COMPRESS, true);
    this.m_props.setProperty(PDFGraphics2D.FIT_TO_PAGE, false);
    this.m_props.setProperty(PDFGraphics2D.VERSION,//
        PDFGraphics2D.VERSION6);
    this.m_props.setProperty(PDFGraphics2D.ORIENTATION,
        PageConstants.PORTRAIT);
    this.m_props.setProperty(PDFGraphics2D.PAGE_MARGINS, "0, 0, 0, 0"); //$NON-NLS-1$
    this.m_props.setProperty(PDFGraphics2D.PAGE_SIZE,
        PageConstants.INTERNATIONAL);

    synchronized (PageConstants.class) {
      this.m_messWith = PageConstants.getSize(PageConstants.INTERNATIONAL);
      this.m_correctDim = new Dimension(this.m_messWith.width,
          this.m_messWith.height);
    }
  }

  /**
   * get the instance of the FreeHEP PDF driver
   * 
   * @return the instance of the FreeHEP PDF driver
   */
  public static final FreeHEPPDFGraphicDriver getInstance() {
    return __FreeHEPPDFGraphicDriverLoader.INSTANCE;
  }

  /** {@inheritDoc} */
  @Override
  public final String toString() {
    return "FreeHEP-based PDF Driver"; //$NON-NLS-1$
  }

  /** {@inheritDoc} */
  @Override
  protected final Graphic doCreateGraphic(final Path path,
      final OutputStream os, final PhysicalDimension size,
      final IObjectListener listener) {
    final UserProperties up;
    final PDFGraphics2D g;
    final double wd, hd;
    final Dimension dim;
    final ELength sizeUnit;

    up = new UserProperties();
    up.putAll(this.m_props);

    sizeUnit = size.getUnit();
    wd = sizeUnit.convertTo(size.getWidth(), ELength.POINT);
    hd = sizeUnit.convertTo(size.getHeight(), ELength.POINT);
    dim = new Dimension();
    if ((wd <= 0d) || (wd >= Integer.MAX_VALUE) || (hd <= 0d)
        || (hd >= Integer.MAX_VALUE)
        || ((dim.width = ((int) (0.5d + wd))) <= 0)
        || ((dim.height = ((int) (0.5d + hd))) <= 0)) {
      throw new IllegalArgumentException("Invalid size " + size + //$NON-NLS-1$
          " translated to " + dim);//$NON-NLS-1$
    }

    synchronized (PageConstants.class) {
      this.m_messWith.setSize(dim);
      try {
        synchronized (PDFGraphics2D.class) {
          PDFGraphics2D.setClipEnabled(true);

          g = new PDFGraphics2D(os, dim);
          g.setProperties(up);
          g.setMultiPage(false);
          GraphicUtils.setDefaultRenderingHints(g);
          g.startExport();
          g.setClip(0, 0, dim.width, dim.height);
        }
        GraphicUtils.setDefaultRenderingHints(g);
      } finally {
        this.m_messWith.setSize(this.m_correctDim);
      }
    }

    return new _FreeHEPPDFGraphic(g, path, listener, dim.width, dim.height);
  }

  /** {@inheritDoc} */
  @Override
  public final EGraphicFormat getGraphicFormat() {
    return EGraphicFormat.PDF;
  }

  /** the loader */
  static final class __FreeHEPPDFGraphicDriverLoader {
    /**
     * the globally shared instance of the <a
     * href="http://en.wikipedia.org/wiki/Portable_Document_Format">PDF</a>
     * graphic driver
     */
    static final FreeHEPPDFGraphicDriver INSTANCE = new FreeHEPPDFGraphicDriver();

  }
}
