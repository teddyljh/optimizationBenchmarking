package org.optimizationBenchmarking.utils.graphics.style.color;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.optimizationBenchmarking.utils.config.Configuration;
import org.optimizationBenchmarking.utils.error.ErrorUtils;
import org.optimizationBenchmarking.utils.error.RethrowMode;
import org.optimizationBenchmarking.utils.graphics.style.Palette;

/**
 * a palette based on the <a
 * href="http://www.w3.org/TR/REC-html40/types.html#h-6.5">HTML 4.01</a>
 * color names
 */
public final class HTML401Palette extends ColorPalette {

  /** the serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * Get an instance of the html 401 palette
   * 
   * @return the html 401 palette
   */
  public static final HTML401Palette getInstance() {
    return __DefaultHTML401PaletteLoader.INSTANCE;
  }

  /**
   * instantiate
   * 
   * @param data
   *          the data
   */
  HTML401Palette(final ColorStyle[] data) {
    super(data);
  }

  /**
   * read resolve
   * 
   * @return {@link #getInstance()}
   */
  private final Object readResolve() {
    return HTML401Palette.getInstance();
  }

  /**
   * write replace
   * 
   * @return {@link #getInstance()}
   */
  private final Object writeReplace() {
    return HTML401Palette.getInstance();
  }

  /** the default palette builder */
  private static final class __DefaultHTML401PaletteBuilder extends
      ColorPaletteBuilder {
    /** the default palette builder */
    __DefaultHTML401PaletteBuilder() {
      super();
    }

    /** {@inheritDoc} */
    @Override
    protected final HTML401Palette createPalette(
        final ArrayList<ColorStyle> data) {
      return new HTML401Palette(data.toArray(new ColorStyle[data.size()]));
    }
  }

  /** the default html 401 palette loader */
  private static final class __DefaultHTML401PaletteLoader {
    /**
     * the globally shared instance of ta palette based on the <a
     * href="http://www.w3.org/TR/REC-html40/types.html#h-6.5">HTML
     * 4.01</a> color names
     */
    static final HTML401Palette INSTANCE;

    static {
      final Logger logger;
      Palette<ColorStyle> pal;

      pal = null;
      logger = Configuration.getGlobalLogger();
      try (final __DefaultHTML401PaletteBuilder cspb = new __DefaultHTML401PaletteBuilder()) {
        ColorPaletteXMLInput
            .getInstance()
            .use()
            .setLogger(logger)
            .setDestination(cspb)
            .addResource(HTML401Palette.class, "html401.colorPalette").create().call(); //$NON-NLS-1$
        pal = cspb.getResult();
      } catch (final Throwable t) {
        ErrorUtils
            .logError(
                logger,
                "Error while loading the HTML 4.01 color palette. This palette will not be available.",//$NON-NLS-1$
                t, true, RethrowMode.AS_RUNTIME_EXCEPTION);
      }

      INSTANCE = ((HTML401Palette) pal);
    }
  }
}
