package nz.co.simplypayroll.charts.components;

import nz.co.simplypayroll.charts.TiChartsModule;
import nz.co.simplypayroll.charts.components.AxisBaseProxy;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import android.graphics.Paint;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.formatter.DefaultXAxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultYAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class YAxisProxy extends AxisBaseProxy {

	public YAxisProxy() {
		super();
	}
	
	public void setAxis(YAxis axis) {
		this.axis = axis;	
	}

	@Kroll.getProperty
	@Kroll.method
	public String getAxisDependency() {
		AxisDependency axisDependency = ((YAxis) this.axis).getAxisDependency();
		String txtAxisDependency = "";
		switch(axisDependency) {
			case LEFT:
				txtAxisDependency = "LEFT";
				break;
			case RIGHT:
				txtAxisDependency = "RIGHT";
				break;
			default:
				break;
        }
		return txtAxisDependency;
	}

	/**
	 * @return the minimum width that the axis should take (in dp).
	 */
	@Kroll.getProperty
	@Kroll.method
	public float getMinWidth() {
		return ((YAxis) this.axis).getMinWidth();
	}

	/**
	 * Sets the minimum width that the axis should take (in dp).
	 * 
	 * @param minWidth
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setMinWidth(float minWidth) {
		((YAxis) this.axis).setMinWidth(minWidth);
	}

	/**
	 * @return the maximum width that the axis can take (in dp).
	 */
	@Kroll.getProperty
	@Kroll.method
	public float getMaxWidth() {
		return ((YAxis) this.axis).getMaxWidth();
	}

	/**
	 * Sets the maximum width that the axis can take (in dp).
	 * 
	 * @param maxWidth
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setMaxWidth(float maxWidth) {
		((YAxis) this.axis).setMaxWidth(maxWidth);
	}

	/**
	 * @return true if granularity is enabled
	 */
	@Kroll.getProperty
	@Kroll.method
	public boolean getGranularityEnabled() {
		return ((YAxis) this.axis).isGranularityEnabled();
	}

	/**
	 * Enabled/disable granularity control on axis value intervals. If enabled,
	 * the axis interval is not allowed to go below a certain granularity.
	 * Default: false
	 * 
	 * @param enabled
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setGranularityEnabled(boolean enabled) {
		((YAxis) this.axis).setGranularityEnabled(enabled);
	}

	/**
	 * @return the minimum interval between axis values
	 */
	@Kroll.getProperty
	@Kroll.method
	public float getGranularity() {
		return ((YAxis) this.axis).getGranularity();
	}

	/**
	 * Set a minimum interval for the axis when zooming in. The axis is not
	 * allowed to go below that limit. This can be used to avoid label
	 * duplicating when zooming in.
	 * 
	 * @param granularity
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setGranularity(float granularity) {
		((YAxis) this.axis).setGranularity(granularity);
	}

	/**
	 * returns the position of the y-labels
	 */
	@Kroll.getProperty
	@Kroll.method
	public String getLabelPosition() {
		YAxisLabelPosition labelPosition = ((YAxis) this.axis).getLabelPosition();
		String txtLabelPosition = "";
		switch(labelPosition) {
			case OUTSIDE_CHART:
				txtLabelPosition = "OUTSIDE_CHART";
				break;
			case INSIDE_CHART:
				txtLabelPosition = "INSIDE_CHART";
				break;
			default:
				break;
        }
		return txtLabelPosition;
	}

	/**
	 * sets the position of the y-labels
	 * 
	 * @param pos
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setPosition(String pos) {
		YAxisLabelPosition position = null;
    	switch(pos) {
			case "OUTSIDE_CHART":
				position = YAxisLabelPosition.OUTSIDE_CHART;
				break;
			case "INSIDE_CHART":
				position = YAxisLabelPosition.INSIDE_CHART;
				break;
			default:
				break;
	    }
    	if(position != null)
    		((YAxis) this.axis).setPosition(position);
	}

	/**
	 * returns true if drawing the top y-axis label entry is enabled
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public boolean getDrawTopYLabelEntryEnabled() {
		return ((YAxis) this.axis).isDrawTopYLabelEntryEnabled();
	}

	/**
	 * set this to true to enable drawing the top y-label entry. Disabling this
	 * can be helpful when the top y-label and left x-label interfere with each
	 * other. default: true
	 * 
	 * @param enabled
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setDrawTopYLabelEntry(boolean enabled) {
		((YAxis) this.axis).setDrawTopYLabelEntry(enabled);
	}

	/**
	 * sets the number of label entries for the y-axis max = 25, min = 2,
	 * default: 6, be aware that this number is not fixed (if force == false)
	 * and can only be approximated.
	 * 
	 * @param count
	 *            the number of y-axis labels that sould be displayed
	 * @param force
	 *            if enabled, the set label count will be forced, meaning that
	 *            the exact specified count of labels will be drawn and evenly
	 *            distributed alongside the axis - this might cause labels to
	 *            have uneven values
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setLabelCount(int count) {
		boolean force = ((YAxis) this.axis).isForceLabelsEnabled();
		((YAxis) this.axis).setLabelCount(count, force);
	}

	@Kroll.setProperty
	@Kroll.method
	public void setForceLabels(boolean force) {
		int count = ((YAxis) this.axis).getLabelCount();
		((YAxis) this.axis).setLabelCount(count, force);
	}

	/**
	 * Returns the number of label entries the y-axis should have
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public int getLabelCount() {
		return ((YAxis) this.axis).getLabelCount();
	}

	/**
	 * Returns true if focing the y-label count is enabled. Default: false
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public boolean getForceLabels() {
		return ((YAxis) this.axis).isForceLabelsEnabled();
	}

	/**
	 * If enabled, the YLabels will only show the minimum and maximum value of
	 * the chart. This will ignore/override the set label count.
	 * 
	 * @param enabled
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setShowOnlyMinMax(boolean enabled) {
		((YAxis) this.axis).setShowOnlyMinMax(enabled);
	}

	/**
	 * Returns true if showing only min and max value is enabled.
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public boolean getShowOnlyMinMax() {
		return ((YAxis) this.axis).isShowOnlyMinMaxEnabled();
	}

	/**
	 * If this is set to true, the y-axis is inverted which means that low
	 * values are on top of the chart, high values on bottom.
	 * 
	 * @param enabled
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setInverted(boolean enabled) {
		((YAxis) this.axis).setInverted(enabled);
	}

	/**
	 * If this returns true, the y-axis is inverted.
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public boolean getInverted() {
		return ((YAxis) this.axis).isInverted();
	}

	/**
	 * Sets the top axis space in percent of the full range. Default 10f
	 * 
	 * @param percent
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setSpaceTop(float percent) {
		((YAxis) this.axis).setSpaceTop(percent);
	}

	/**
	 * Returns the top axis space in percent of the full range. Default 10f
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public float getSpaceTop() {
		return ((YAxis) this.axis).getSpaceTop();
	}

	/**
	 * Sets the bottom axis space in percent of the full range. Default 10f
	 * 
	 * @param percent
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setSpaceBottom(float percent) {
		((YAxis) this.axis).setSpaceBottom(percent);
	}

	/**
	 * Returns the bottom axis space in percent of the full range. Default 10f
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public float getSpaceBottom() {
		return ((YAxis) this.axis).getSpaceBottom();
	}

	@Kroll.getProperty
	@Kroll.method
	public boolean getDrawZeroLine() {
		return ((YAxis) this.axis).isDrawZeroLineEnabled();
	}

	/**
	 * Set this to true to draw the zero-line regardless of weather other
	 * grid-lines are enabled or not. Default: false
	 * 
	 * @param mDrawZeroLine
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setDrawZeroLine(boolean mDrawZeroLine) {
		((YAxis) this.axis).setDrawZeroLine(mDrawZeroLine);
	}

	@Kroll.getProperty
	@Kroll.method
	public int getZeroLineColor() {
		return ((YAxis) this.axis).getZeroLineColor();
	}

	/**
	 * Sets the color of the zero line
	 * 
	 * @param color
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setZeroLineColor(int color) {
		((YAxis) this.axis).setZeroLineColor(color);
	}

	@Kroll.getProperty
	@Kroll.method
	public float getZeroLineWidth() {
		return ((YAxis) this.axis).getZeroLineWidth();
	}

	/**
	 * Sets the width of the zero line in dp
	 * 
	 * @param width
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setZeroLineWidth(float width) {
		((YAxis) this.axis).setZeroLineWidth(width);
	}

	/**
	 * This is for normal (not horizontal) charts horizontal spacing.
	 * 
	 * @param p
	 * @return
	 */
	@Kroll.method
	public float getRequiredWidthSpace(Paint p) {
		return ((YAxis) this.axis).getRequiredWidthSpace(p);
	}

	/**
	 * This is for HorizontalBarChart vertical spacing.
	 * 
	 * @param p
	 * @return
	 */
	@Kroll.method
	public float getRequiredHeightSpace(Paint p) {
		return ((YAxis) this.axis).getRequiredHeightSpace(p);
	}

	@Kroll.getProperty
	@Kroll.method
	public String getLongestLabel() {
		return ((YAxis) this.axis).getLongestLabel();
	}

	/**
	 * Returns the formatted y-label at the specified index. This will either
	 * use the auto-formatter or the custom formatter (if one is set).
	 * 
	 * @param index
	 * @return
	 */
	@Kroll.method
	public String getFormattedLabel(int index) {
		return ((YAxis) this.axis).getFormattedLabel(index);
	}

	/**
	 * Sets the formatter to be used for formatting the axis labels. If no
	 * formatter is set, the chart will automatically determine a reasonable
	 * formatting (concerning decimals) for all the values that are drawn inside
	 * the chart. Use chart.getDefaultValueFormatter() to use the formatter
	 * calculated by the chart.
	 * 
	 * @param f
	 */
	@Kroll.setProperty
	@Kroll.method
	public void setValueFormatter(String formatter) {
		YAxisValueFormatter formatterObject = null;
    	switch(formatter) {
	    	case "largeValue":
	    		formatterObject = new LargeValueFormatter();
	    		break;
	    	case "percent":
	    		formatterObject = new PercentFormatter();
	    		break;
	    	default:
	    		break;  	
    	}
    	if(formatterObject != null)
    		((YAxis) this.axis).setValueFormatter(formatterObject);
	}

	/**
	 * Returns the formatter used for formatting the axis labels.
	 * 
	 * @return
	 */
	@Kroll.getProperty
	@Kroll.method
	public String getValueFormatter() {
		YAxisValueFormatter formatter = ((YAxis) this.axis).getValueFormatter();
    	if(formatter instanceof LargeValueFormatter)
    		return "largeValue";
    	else if(formatter instanceof PercentFormatter)
    		return "percent";
    	else
    		return "default";
	}


	/**
	 * If this component has no YAxisValueFormatter or is only equipped with the
	 * default one (no custom set), return true.
	 * 
	 * @return
	 */
	@Kroll.method
	public boolean needsDefaultFormatter() {
		return ((YAxis) this.axis).needsDefaultFormatter();
	}

	/**
	 * Returns true if this axis needs horizontal offset, false if no offset is
	 * needed.
	 * 
	 * @return
	 */
	@Kroll.method
	public boolean needsOffset() {
		return ((YAxis) this.axis).needsOffset();
	}

	/**
	 * Calculates the minimum, maximum, granularity and range values of the
	 * YAxis with the given minimum and maximum values from the chart data.
	 * 
	 * @param dataMin
	 *            the y-min value according to chart data
	 * @param dataMax
	 *            the y-max value according to chart data
	 */
	/*@Kroll.method
	public void calculate(float dataMin, float dataMax) {
		return ((YAxis) this.axis).calculate(dataMin, dataMax);
	}*/
}