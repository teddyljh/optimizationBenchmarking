package org.optimizationBenchmarking.utils.document.impl.latex;

import java.nio.file.Path;

import org.optimizationBenchmarking.utils.ErrorUtils;
import org.optimizationBenchmarking.utils.document.IObjectListener;
import org.optimizationBenchmarking.utils.document.impl.abstr.BasicMath;
import org.optimizationBenchmarking.utils.document.impl.abstr.Code;
import org.optimizationBenchmarking.utils.document.impl.abstr.CodeBody;
import org.optimizationBenchmarking.utils.document.impl.abstr.ComplexText;
import org.optimizationBenchmarking.utils.document.impl.abstr.Document;
import org.optimizationBenchmarking.utils.document.impl.abstr.DocumentDriver;
import org.optimizationBenchmarking.utils.document.impl.abstr.DocumentHeader;
import org.optimizationBenchmarking.utils.document.impl.abstr.DocumentPart;
import org.optimizationBenchmarking.utils.document.impl.abstr.EMathOperators;
import org.optimizationBenchmarking.utils.document.impl.abstr.Enumeration;
import org.optimizationBenchmarking.utils.document.impl.abstr.Figure;
import org.optimizationBenchmarking.utils.document.impl.abstr.FigureSeries;
import org.optimizationBenchmarking.utils.document.impl.abstr.Itemization;
import org.optimizationBenchmarking.utils.document.impl.abstr.MathFunction;
import org.optimizationBenchmarking.utils.document.impl.abstr.Section;
import org.optimizationBenchmarking.utils.document.impl.abstr.SectionBody;
import org.optimizationBenchmarking.utils.document.impl.abstr.StructuredText;
import org.optimizationBenchmarking.utils.document.impl.abstr.Table;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableBody;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableBodyRow;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableFooter;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableFooterRow;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableHeader;
import org.optimizationBenchmarking.utils.document.impl.abstr.TableHeaderRow;
import org.optimizationBenchmarking.utils.document.impl.abstr.Text;
import org.optimizationBenchmarking.utils.document.spec.EFigureSize;
import org.optimizationBenchmarking.utils.document.spec.ILabel;
import org.optimizationBenchmarking.utils.document.spec.PageDimension;
import org.optimizationBenchmarking.utils.document.spec.TableCellDef;
import org.optimizationBenchmarking.utils.graphics.EScreenSize;
import org.optimizationBenchmarking.utils.graphics.PhysicalDimension;
import org.optimizationBenchmarking.utils.graphics.graphic.EGraphicFormat;
import org.optimizationBenchmarking.utils.graphics.graphic.IGraphicDriver;
import org.optimizationBenchmarking.utils.graphics.style.IStyle;
import org.optimizationBenchmarking.utils.graphics.style.PaletteIODriver;
import org.optimizationBenchmarking.utils.graphics.style.StyleSet;
import org.optimizationBenchmarking.utils.graphics.style.font.FontPalette;
import org.optimizationBenchmarking.utils.graphics.style.font.FontPaletteBuilder;

/**
 * The driver for LaTeX output
 */
public final class LaTeXDriver extends DocumentDriver {

  /** the synchronizer */
  private static final Object SYNCH = new Object();

  /** the internal font palette */
  private static FontPalette s_fonts;
  /** The default LaTeX driver */
  private static LaTeXDriver s_default;

  /** the graphic driver */
  private final IGraphicDriver m_graphicDriver;

  /** the screen size */
  private final PageDimension m_size;

  /** the font palette */
  private final FontPalette m_fonts;

  /**
   * Get the default LaTeX driver
   * 
   * @return the default LaTeX driver
   */
  public static final LaTeXDriver getDefaultDriver() {
    synchronized (LaTeXDriver.SYNCH) {
      if (LaTeXDriver.s_default == null) {
        LaTeXDriver.s_default = new LaTeXDriver(null, null, null);
      }
      return LaTeXDriver.s_default;
    }
  }

  /**
   * Create a new LaTeX driver
   * 
   * @param gd
   *          the graphic driver to use
   * @param size
   *          the physical screen size to render for
   * @param fonts
   *          the font palette
   */
  public LaTeXDriver(final IGraphicDriver gd,
      final PhysicalDimension size, final FontPalette fonts) {
    super("LaTeX"); //$NON-NLS-1$
    final PhysicalDimension d;

    this.m_graphicDriver = ((gd != null) ? gd : EGraphicFormat.PNG
        .getDefaultDriver());

    d = ((size != null) ? size : EScreenSize.DEFAULT
        .getPhysicalSize(EScreenSize.DEFAULT_SCREEN_DPI));

    this.m_size = ((d instanceof PageDimension) ? ((PageDimension) d)
        : new PageDimension(d));

    this.m_fonts = ((fonts == null) ? LaTeXDriver.__defaultFonts() : fonts);
  }

  /**
   * obtain the default font palette
   * 
   * @return the default font palette
   */
  private static final FontPalette __defaultFonts() {
    synchronized (LaTeXDriver.SYNCH) {
      if (LaTeXDriver.s_fonts == null) {
        try (final FontPaletteBuilder tb = new FontPaletteBuilder()) {
          PaletteIODriver.INSTANCE.loadResource(tb, LaTeXDriver.class,
              "LaTeX10.font.palette"); //$NON-NLS-1$
          LaTeXDriver.s_fonts = tb.getResult();
        } catch (final Throwable tt) {
          ErrorUtils.throwAsRuntimeException(tt);
        }
      }
      return LaTeXDriver.s_fonts;
    }
  }

  /** {@inheritDoc} */
  @Override
  protected final IGraphicDriver getGraphicDriver() {
    return this.m_graphicDriver;
  }

  /** {@inheritDoc} */
  @Override
  protected final PhysicalDimension getSize(final EFigureSize size) {
    return size.approximateSize(this.m_size);
  }

  /** {@inheritDoc} */
  @Override
  protected final StyleSet createStyleSet() {
    final IGraphicDriver driver;
    driver = this.getGraphicDriver();
    return new StyleSet(this.m_fonts, driver.getColorPalette(),
        driver.getStrokePalette());
  }

  /** {@inheritDoc} */
  @Override
  protected final Document doCreateDocument(final Path file,
      final IObjectListener listener) {
    return new _LaTeXDocument(this, file, listener);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXDocumentHeader createDocumentHeader(
      final Document owner) {
    return new _LaTeXDocumentHeader((_LaTeXDocument) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXDocumentBody createDocumentBody(
      final Document owner) {
    return new _LaTeXDocumentBody((_LaTeXDocument) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXDocumentFooter createDocumentFooter(
      final Document owner) {
    return new _LaTeXDocumentFooter((_LaTeXDocument) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXInBraces createInBraces(final Text owner) {
    return new _LaTeXInBraces(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXInQuotes createInQuotes(final Text owner) {
    return new _LaTeXInQuotes(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXStyledText createStyledText(
      final ComplexText owner, final IStyle style) {
    return new _LaTeXStyledText(owner, style);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXInlineCode createInlineCode(final ComplexText owner) {
    return new _LaTeXInlineCode(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSubscript createSubscript(final ComplexText owner) {
    return new _LaTeXSubscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSuperscript createSuperscript(
      final ComplexText owner) {
    return new _LaTeXSuperscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXInlineMath createInlineMath(final ComplexText owner) {
    return new _LaTeXInlineMath(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXDocumentTitle createDocumentTitle(
      final DocumentHeader owner) {
    return new _LaTeXDocumentTitle((_LaTeXDocumentHeader) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXDocumentSummary createDocumentSummary(
      final DocumentHeader owner) {
    return new _LaTeXDocumentSummary((_LaTeXDocumentHeader) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSection createSection(final DocumentPart owner,
      final ILabel useLabel, final int index) {
    return new _LaTeXSection(owner, useLabel, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSectionTitle createSectionTitle(final Section owner) {
    return new _LaTeXSectionTitle((_LaTeXSection) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSectionBody createSectionBody(final Section owner) {
    return new _LaTeXSectionBody((_LaTeXSection) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXEnumeration createEnumeration(
      final StructuredText owner) {
    return new _LaTeXEnumeration(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXItemization createItemization(
      final StructuredText owner) {
    return new _LaTeXItemization(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTable createTable(final SectionBody owner,
      final ILabel useLabel, final boolean spansAllColumns,
      final int index, final TableCellDef... cells) {
    return new _LaTeXTable(((_LaTeXSectionBody) owner), useLabel,
        spansAllColumns, index, cells);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXFigure createFigure(final SectionBody owner,
      final ILabel useLabel, final EFigureSize size, final String path,
      final int index) {
    return new _LaTeXFigure(((_LaTeXSectionBody) owner), useLabel, size,
        path, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXFigureSeries createFigureSeries(
      final SectionBody owner, final ILabel useLabel,
      final EFigureSize size, final String path, final int index) {
    return new _LaTeXFigureSeries(((_LaTeXSectionBody) owner), useLabel,
        size, path, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXCode createCode(final SectionBody owner,
      final ILabel useLabel, final boolean spansAllColumns, final int index) {
    return new _LaTeXCode(((_LaTeXSectionBody) owner), useLabel,
        spansAllColumns, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXEquation createEquation(final SectionBody owner,
      final ILabel useLabel, final int index) {
    return new _LaTeXEquation(((_LaTeXSectionBody) owner), useLabel, index);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXEnumerationItem createEnumerationItem(
      final Enumeration owner) {
    return new _LaTeXEnumerationItem((_LaTeXEnumeration) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXItemizationItem createItemizationItem(
      final Itemization owner) {
    return new _LaTeXItemizationItem((_LaTeXItemization) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXMathFunction createMathFunction(
      final BasicMath owner, final EMathOperators operator) {
    return new _LaTeXMathFunction(owner, operator);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXMathSubscript createMathSubscript(
      final BasicMath owner) {
    return new _LaTeXMathSubscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXMathSuperscript createMathSuperscript(
      final BasicMath owner) {
    return new _LaTeXMathSuperscript(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXMathInBraces createMathInBraces(
      final BasicMath owner) {
    return new _LaTeXMathInBraces(owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXFigureCaption createFigureCaption(
      final Figure owner) {
    return new _LaTeXFigureCaption((_LaTeXFigure) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXCodeCaption createCaption(final Code owner) {
    return new _LaTeXCodeCaption((_LaTeXCode) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXCodeBody createBody(final Code owner) {
    return new _LaTeXCodeBody((_LaTeXCode) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXCodeInBraces createCodeInBraces(
      final CodeBody owner) {
    return new _LaTeXCodeInBraces((_LaTeXCodeBody) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXFigureSeriesCaption createFigureSeriesCaption(
      final FigureSeries owner) {
    return new _LaTeXFigureSeriesCaption((_LaTeXFigureSeries) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXSubFigure createFigure(final FigureSeries owner,
      final ILabel useLabel, final String path) {
    return new _LaTeXSubFigure(((_LaTeXFigureSeries) owner), useLabel,
        path);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXMathArgument createMathArgument(
      final MathFunction owner) {
    return new _LaTeXMathArgument((_LaTeXMathFunction) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableCaption createTableCaption(final Table owner) {
    return new _LaTeXTableCaption((_LaTeXTable) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableHeader createTableHeader(final Table owner) {
    return new _LaTeXTableHeader((_LaTeXTable) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableBody createTableBody(final Table owner) {
    return new _LaTeXTableBody((_LaTeXTable) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableFooter createTableFooter(final Table owner) {
    return new _LaTeXTableFooter((_LaTeXTable) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableBodyRow createTableBodyRow(
      final TableBody owner) {
    return new _LaTeXTableBodyRow((_LaTeXTableBody) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableFooterRow createTableFooterRow(
      final TableFooter owner) {
    return new _LaTeXTableFooterRow((_LaTeXTableFooter) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableHeaderRow createTableHeaderRow(
      final TableHeader owner) {
    return new _LaTeXTableHeaderRow((_LaTeXTableHeader) owner);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableBodyCell createTableBodyCell(
      final TableBodyRow owner, final int rowSpan, final int colSpan,
      final TableCellDef[] def) {
    return new _LaTeXTableBodyCell(((_LaTeXTableBodyRow) owner), rowSpan,
        colSpan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableHeaderCell createTableHeaderCell(
      final TableHeaderRow owner, final int rowSpan, final int colSpan,
      final TableCellDef[] def) {
    return new _LaTeXTableHeaderCell(((_LaTeXTableHeaderRow) owner),
        rowSpan, colSpan, def);
  }

  /** {@inheritDoc} */
  @Override
  protected final _LaTeXTableFooterCell createTableFooterCell(
      final TableFooterRow owner, final int rowSpan, final int colSpan,
      final TableCellDef[] def) {
    return new _LaTeXTableFooterCell(((_LaTeXTableFooterRow) owner),
        rowSpan, colSpan, def);
  }

}