package org.optimizationBenchmarking.experimentation.attributes.clusters.propertyValueGroups;

import java.util.Arrays;

import org.optimizationBenchmarking.experimentation.data.impl.shadow.DataSelection;
import org.optimizationBenchmarking.experimentation.data.spec.Attribute;
import org.optimizationBenchmarking.experimentation.data.spec.EAttributeType;
import org.optimizationBenchmarking.experimentation.data.spec.IExperimentSet;
import org.optimizationBenchmarking.experimentation.data.spec.IParameter;
import org.optimizationBenchmarking.experimentation.data.spec.IParameterValue;
import org.optimizationBenchmarking.experimentation.data.spec.IProperty;
import org.optimizationBenchmarking.experimentation.data.spec.IPropertyValue;
import org.optimizationBenchmarking.utils.collections.lists.ArrayListView;
import org.optimizationBenchmarking.utils.config.Configuration;
import org.optimizationBenchmarking.utils.hash.HashUtils;
import org.optimizationBenchmarking.utils.math.NumericalTypes;

/**
 * An attribute which can group property values.
 */
public final class PropertyValueGrouper extends
    Attribute<IProperty, PropertyValueGroups> {

  /** The suffix of the grouping parameter: {@value} */
  public static final String PARAM_GROUPING_SUFFIX = "Grouping"; //$NON-NLS-1$

  /** The default parameter for all grouping */
  public static final String PARAM_DEFAULT_GROUPING = //
  ("default" + PropertyValueGrouper.PARAM_GROUPING_SUFFIX);//$NON-NLS-1$

  /** the default minimum number of anticipated groups */
  private static final int DEFAULT_MIN_GROUPS = 2;
  /** the default maximum number of anticipated groups */
  private static final int DEFAULT_MAX_GROUPS = 10;
  /** the default grouping mode */
  private static final EGroupingMode DEFAULT_GROUPING_MODE = EGroupingMode.ANY;

  /** The default value grouper for experiment parameters */
  public static final PropertyValueGrouper DEFAULT_GROUPER//
  = new PropertyValueGrouper(PropertyValueGrouper.DEFAULT_GROUPING_MODE,
      null, PropertyValueGrouper.DEFAULT_MIN_GROUPS,
      PropertyValueGrouper.DEFAULT_MAX_GROUPS);

  /** the grouping mode to use */
  private final EGroupingMode m_groupingMode;

  /** the parameter to be passed to the grouping */
  private final Number m_groupingParameter;

  /**
   * the goal minimum of the number of groups &ndash; any number of groups
   * less than this would not be good
   */
  private final int m_minGroups;
  /**
   * the goal maximum of the number of groups &ndash; any number of groups
   * higher than this would not be good
   */
  private final int m_maxGroups;

  /**
   * create the property value grouper
   * 
   * @param groupingMode
   *          the grouping mode
   * @param groupingParameter
   *          the parameter to be passed to the grouping
   * @param minGroups
   *          the goal minimum of the number of groups &ndash; any number
   *          of groups less than this would not be good
   * @param maxGroups
   *          the goal maximum of the number of groups &ndash; any number
   *          of groups higher than this would not be good
   */
  public PropertyValueGrouper(final EGroupingMode groupingMode,
      final Number groupingParameter, final int minGroups,
      final int maxGroups) {
    super(EAttributeType.TEMPORARILY_STORED);

    if (groupingMode == null) {
      throw new IllegalArgumentException(//
          "Grouping mode must not be null."); //$NON-NLS-1$
    }
    if (minGroups < 0) {
      throw new IllegalArgumentException(//
          "The minimum number of groups must be greater or equal to 0, but is " //$NON-NLS-1$
              + minGroups);
    }
    if (maxGroups < minGroups) {//
      throw new IllegalArgumentException(//
          "The maximum number of groups must be greater or equal to the minimum number, but is " //$NON-NLS-1$
              + maxGroups + " while the minimum is " + minGroups);//$NON-NLS-1$
    }

    this.m_groupingMode = groupingMode;
    this.m_groupingParameter = groupingParameter;
    this.m_minGroups = minGroups;
    this.m_maxGroups = maxGroups;
  }

  /**
   * Get the goal minimum number of groups
   * 
   * @return the goal minimum number of groups
   */
  public final int getMinGroups() {
    return this.m_minGroups;
  }

  /**
   * Get the goal maximum number of groups
   * 
   * @return the goal maximum number of groups
   */
  public final int getMaxGroups() {
    return this.m_maxGroups;
  }

  /**
   * Get the grouping parameter, or {@code null} if none is specified
   * 
   * @return the grouping parameter, or {@code null} if none is specified
   */
  public final Number getGroupingParameter() {
    return this.m_groupingParameter;
  }

  /**
   * Get the grouping mode
   * 
   * @return the grouping mode
   */
  public final EGroupingMode getGroupingMode() {
    return this.m_groupingMode;
  }

  /** {@inheritDoc} */
  @Override
  protected final int calcHashCode() {
    return HashUtils.combineHashes(//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_groupingMode),//
            HashUtils.hashCode(this.m_groupingParameter)),//
        HashUtils.combineHashes(//
            HashUtils.hashCode(this.m_minGroups),//
            HashUtils.hashCode(this.m_maxGroups)));
  }

  /** {@inheritDoc} */
  @Override
  protected final PropertyValueGroups compute(final IProperty data) {
    final _Groups groups;
    final IExperimentSet set;
    ArrayListView<? extends IPropertyValue> valueSet;
    Object[] objData;
    Number[] numberData;
    _Group[] buffer;
    int index, type;
    Object value;
    DataSelection unspecified;
    IParameterValue pv;

    // extract all values
    valueSet = data.getData();
    objData = new Object[valueSet.size()];
    index = 0;
    type = (-1);
    for (final IPropertyValue propVal : valueSet) {
      objData[index++] = value = propVal.getValue();
      if (type != 0) {
        type &= NumericalTypes.getTypes(value);
      }
    }
    valueSet = null;

    try {
      Arrays.sort(objData);
    } catch (final Throwable ignoreable) {
      // ignore
    }

    // allocate the memory for the group
    buffer = new _Group[index];
    for (index = buffer.length; (--index) >= 0;) {
      buffer[index] = new _Group();
    }

    // get groupings

    if (type == 0) {
      groups = this.m_groupingMode._groupObjects(this.m_groupingParameter,
          objData, this.m_minGroups, this.m_maxGroups, buffer);
    } else {
      numberData = new Number[objData.length];
      System.arraycopy(objData, 0, numberData, 0, objData.length);
      if ((type & NumericalTypes.IS_LONG) != 0) {
        groups = this.m_groupingMode._groupLongs(this.m_groupingParameter,
            numberData, this.m_minGroups, this.m_maxGroups, buffer);
      } else {
        groups = this.m_groupingMode._groupDoubles(
            this.m_groupingParameter, numberData, this.m_minGroups,
            this.m_maxGroups, buffer);
      }
      numberData = null;
    }
    buffer = null;

    // now compile data
    set = data.getOwner().getOwner();

    for (final _Group group : groups.m_groups) {
      group.m_selection = new DataSelection(set);
      for (final Object object : objData) {
        if (group._contains(object)) {
          group.m_selection.addPropertyValue(data, object);
        }
      }
    }
    objData = null;

    unspecified = null;
    value = null;
    if (data instanceof IParameter) {
      pv = ((IParameter) data).getUnspecified();
      if (pv != null) {
        value = pv.getValue();
        if (value != null) {
          unspecified = new DataSelection(set);
          unspecified.addParameterValue(pv);
        }
      }
    }

    // return the object

    switch (groups.m_groupingMode) {
      case DISTINCT: {
        return new DistinctValueGroups(data, groups, unspecified, value);
      }

      case POWERS:
      case MULTIPLES: {
        return new ValueRangeGroups(data, groups, unspecified, value);
      }

      default: {
        throw new IllegalArgumentException(//
            "Unknown grouping mode: " + groups.m_groupingMode); //$NON-NLS-1$
      }
    }
  }

  /**
   * Load a property value grouper from a configuration
   * 
   * @param property
   *          the property
   * @param config
   *          the configuration
   * @return the grouper
   */
  public static final PropertyValueGrouper configure(
      final IProperty property, final Configuration config) {
    final PropertyValueGrouper all;
    all = config.get(PropertyValueGrouper.PARAM_DEFAULT_GROUPING,//
        PropertyValueGrouperParser.DEFAULT_GROUPER_PARSER,//
        PropertyValueGrouper.DEFAULT_GROUPER);
    if (property != null) {
      return config
          .get(//
              (property.getName() + PropertyValueGrouper.PARAM_GROUPING_SUFFIX),//
              PropertyValueGrouperParser.DEFAULT_GROUPER_PARSER,//
              all);
    }
    return all;
  }
}
