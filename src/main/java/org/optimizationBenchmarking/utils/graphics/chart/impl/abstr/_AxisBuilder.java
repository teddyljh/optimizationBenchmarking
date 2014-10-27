package org.optimizationBenchmarking.utils.graphics.chart.impl.abstr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;

import org.optimizationBenchmarking.utils.graphics.chart.spec.IAxis;
import org.optimizationBenchmarking.utils.hierarchy.FSM;
import org.optimizationBenchmarking.utils.hierarchy.HierarchicalFSM;
import org.optimizationBenchmarking.utils.math.matrix.IMatrix;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.CompoundAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.IAggregate;
import org.optimizationBenchmarking.utils.math.statistics.aggregate.ScalarAggregate;
import org.optimizationBenchmarking.utils.text.textOutput.MemoryTextOutput;

/**
 * The base class for all axes
 */
final class _AxisBuilder extends _TitledElementBuilder implements IAxis {

  /** the tick font has been set */
  private static final int FLAG_HAS_TICK_FONT = (_TitledElementBuilder.FLAG_TITLED_ELEMENT_BUILDER_MAX << 1);
  /** the stroke has been set */
  private static final int FLAG_HAS_STROKE = (_AxisBuilder.FLAG_HAS_TICK_FONT << 1);
  /** the color has been set */
  private static final int FLAG_HAS_COLOR = (_AxisBuilder.FLAG_HAS_STROKE << 1);
  /** the grid line stroke has been set */
  private static final int FLAG_HAS_GRID_LINE_STROKE = (_AxisBuilder.FLAG_HAS_COLOR << 1);
  /** the grid line color has been set */
  private static final int FLAG_HAS_GRID_LINE_COLOR = (_AxisBuilder.FLAG_HAS_GRID_LINE_STROKE << 1);
  /** the minimum has been set */
  private static final int FLAG_HAS_MIN = (_AxisBuilder.FLAG_HAS_GRID_LINE_COLOR << 1);
  /** the maximum has been set */
  private static final int FLAG_HAS_MAX = (_AxisBuilder.FLAG_HAS_MIN << 1);

  /** the minimum aggregate */
  private ScalarAggregate m_minAg;

  /** the maximum aggregate */
  private ScalarAggregate m_maxAg;

  /** the minimum value */
  private double m_min;

  /** the maximum value */
  private double m_max;

  /** the axis stroke */
  private Stroke m_axisStroke;

  /** the axis color */
  private Color m_axisColor;

  /** the grid line stroke */
  private Stroke m_gridLineStroke;

  /** the grid line color */
  private Color m_gridLineColor;

  /** the aggregate */
  private IAggregate m_agg;

  /** the tick font */
  private Font m_tickFont;

  /** the column this axis is responsible for */
  private final int m_col;

  /**
   * create the chart item
   * 
   * @param owner
   *          the owner
   * @param col
   *          the column this axis is responsible for
   */
  _AxisBuilder(final _ChartElementBuilder owner, final int col) {
    super(owner);
    this.m_col = col;
    this.open();
  }

  /** {@inheritDoc} */
  @Override
  protected final void fsmFlagsAppendName(final int flagValue,
      final int flagIndex, final MemoryTextOutput append) {
    switch (flagValue) {
      case FLAG_HAS_TICK_FONT: {
        append.append("tickFontSet");break;} //$NON-NLS-1$
      case FLAG_HAS_STROKE: {
        append.append("axisStrokeSet");break;} //$NON-NLS-1$
      case FLAG_HAS_COLOR: {
        append.append("axisColorSet");break;} //$NON-NLS-1$
      case FLAG_HAS_GRID_LINE_COLOR: {
        append.append("gridLineColorSet");break;} //$NON-NLS-1$
      case FLAG_HAS_GRID_LINE_STROKE: {
        append.append("gridLineStrokeSet");break;} //$NON-NLS-1$
      case FLAG_HAS_MIN: {
        append.append("minimumSet");break;} //$NON-NLS-1$
      case FLAG_HAS_MAX: {
        append.append("maximumSet");break;} //$NON-NLS-1$
      default: {
        super.fsmFlagsAppendName(flagValue, flagIndex, append);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public final void setAxisColor(final Color axisColor) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_COLOR, _AxisBuilder.FLAG_HAS_COLOR,
        FSM.FLAG_NOTHING);
    if (axisColor == null) {
      throw new IllegalArgumentException(//
          "Axis color cannot be set to null. If you don't want to specify a title font, don't set it."); //$NON-NLS-1$
    }
    this.m_axisColor = axisColor;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setTickFont(final Font tickFont) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_TICK_FONT, _AxisBuilder.FLAG_HAS_TICK_FONT,
        FSM.FLAG_NOTHING);
    if (tickFont == null) {
      throw new IllegalArgumentException(//
          "Tick font cannot be set to null. If you don't want to specify a title font, don't set it."); //$NON-NLS-1$
    }
    this.m_tickFont = tickFont;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setAxisStroke(final Stroke axisStroke) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_STROKE, _AxisBuilder.FLAG_HAS_STROKE,
        FSM.FLAG_NOTHING);
    if (axisStroke == null) {
      throw new IllegalArgumentException(//
          "Axis stroke cannot be set to null. If you don't want to specify a title font, don't set it."); //$NON-NLS-1$
    }
    this.m_axisStroke = axisStroke;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setGridLineStroke(
      final Stroke gridLineStroke) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_GRID_LINE_STROKE,
        _AxisBuilder.FLAG_HAS_GRID_LINE_STROKE, FSM.FLAG_NOTHING);
    if (gridLineStroke == null) {
      throw new IllegalArgumentException(//
          "Grid line stroke cannot be set to null. If you don't want to specify a title font, don't set it."); //$NON-NLS-1$
    }
    this.m_gridLineStroke = gridLineStroke;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setGridLineColor(final Color gridLineColor) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_GRID_LINE_COLOR,
        _AxisBuilder.FLAG_HAS_GRID_LINE_COLOR, FSM.FLAG_NOTHING);
    if (gridLineColor == null) {
      throw new IllegalArgumentException(//
          "Grid line color cannot be set to null. If you don't want to specify a title font, don't set it."); //$NON-NLS-1$
    }
    this.m_gridLineColor = gridLineColor;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setMinimumAggregate(
      final ScalarAggregate min) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    if (min == null) {
      throw new IllegalArgumentException(
          "Minimum aggregate cannot be null.");//$NON-NLS-1$
    }
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_MIN, _AxisBuilder.FLAG_HAS_MIN,
        FSM.FLAG_NOTHING);
    this.m_minAg = min;
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setMaximumAggregate(
      final ScalarAggregate max) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    if (max == null) {
      throw new IllegalArgumentException(
          "Maximum aggregate cannot be null.");//$NON-NLS-1$
    }
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_MAX, _AxisBuilder.FLAG_HAS_MAX,
        FSM.FLAG_NOTHING);
    this.m_maxAg = max;
  }

  /**
   * check a minimum value
   * 
   * @param min
   *          the minimum value
   */
  static final void _assertMin(final double min) {
    if ((min != min) || (min >= Double.POSITIVE_INFINITY)) {
      throw new IllegalArgumentException(min
          + " is not a valid minimum value.");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setMinimum(final double min) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    _AxisBuilder._assertMin(min);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_MIN, _AxisBuilder.FLAG_HAS_MIN,
        FSM.FLAG_NOTHING);
    this.m_min = min;
  }

  /**
   * check a maximum value
   * 
   * @param max
   *          the maximum value
   */
  static final void _assertMax(final double max) {
    if ((max != max) || (max <= Double.NEGATIVE_INFINITY)) {
      throw new IllegalArgumentException(max
          + " is not a valid maximum value.");//$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public final synchronized void setMaximum(final double max) {
    this.fsmStateAssert(_ChartElementBuilder.STATE_ALIVE);
    _AxisBuilder._assertMax(max);
    this.fsmFlagsAssertAndUpdate(FSM.FLAG_NOTHING,
        _AxisBuilder.FLAG_HAS_MAX, _AxisBuilder.FLAG_HAS_MAX,
        FSM.FLAG_NOTHING);
    this.m_max = max;
  }

  /** {@inheritDoc} */
  @Override
  protected synchronized final void onClose() {
    this.fsmFlagsAssertTrue(_AxisBuilder.FLAG_HAS_MAX
        | _AxisBuilder.FLAG_HAS_MIN);

    if (this.m_maxAg != null) {
      if (this.m_minAg != null) {
        this.m_agg = CompoundAggregate.combine(this.m_minAg, this.m_maxAg);
      } else {
        this.m_agg = this.m_maxAg;
      }
    } else {
      this.m_agg = this.m_minAg;
    }

    super.onClose();
  }

  /**
   * Register some data
   * 
   * @param data
   *          the data set
   */
  final void _registerData(final IMatrix data) {
    if (this.m_agg != null) {
      data.aggregateColumn(this.m_col, this.m_agg);
    }
  }

  /**
   * get the axis
   * 
   * @return the axis
   */
  @SuppressWarnings("resource")
  final Axis _getAxis() {
    final HierarchicalFSM owner;
    final _ChartBuilder cb;
    Font titleFont, tickFont;
    Stroke axisStroke, gridLineStroke;
    Color axisColor, gridLineColor;

    titleFont = this.m_titleFont;
    tickFont = this.m_tickFont;
    axisStroke = this.m_axisStroke;
    axisColor = this.m_axisColor;
    gridLineStroke = this.m_gridLineStroke;
    gridLineColor = this.m_gridLineColor;

    owner = this.getOwner();
    if (owner instanceof _ChartBuilder) {
      cb = ((_ChartBuilder) owner);

      if ((titleFont == null) && (this.m_title != null)) {
        titleFont = cb._getAxisTitleFont();
      }
      if (tickFont == null) {
        tickFont = cb._getAxisTickFont();
      }
      if (axisStroke == null) {
        axisStroke = cb._getAxisStroke();
      }
      if (axisColor == null) {
        axisColor = cb._getAxisColor();
      }
      if (gridLineStroke == null) {
        gridLineStroke = cb._getGridLineStroke();
      }
      if (gridLineColor == null) {
        gridLineColor = cb._getGridLineColor();
      }
    }

    return new Axis(
        this.m_title,
        titleFont,
        tickFont,
        axisStroke,
        axisColor,
        gridLineStroke,
        gridLineColor,
        ((this.m_minAg != null) ? this.m_minAg.doubleValue() : this.m_min),
        ((this.m_maxAg != null) ? this.m_maxAg.doubleValue() : this.m_max));
  }
}