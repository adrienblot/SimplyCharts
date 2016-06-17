package nz.co.simplypayroll.charts.components;

import nz.co.simplypayroll.charts.TiChartsModule;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.components.Legend;
//import com.github.mikephil.charting.components.Legend.LegendOrientation;
//import com.github.mikephil.charting.components.Legend.LegendVerticalAlignment;
//import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendDirection;
import com.github.mikephil.charting.components.Legend.LegendPosition;

import java.util.HashMap;



@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class LegendProxy extends KrollProxy
{  
	public Legend legend;

	public LegendProxy() {
		super();
	}

	public void setLegend(Legend legend) {
		this.legend = legend;		
	}


    /**
     * returns all the colors the legend uses
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public int[] getColors() {
        return this.legend.getColors();
    }

    /**
     * returns all the labels the legend uses
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String[] getLabels() {
        return this.legend.getLabels();
    }

    /**
     * Returns the legend-label at the given index.
     * 
     * @param index
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getLabel(int index) {
        return this.legend.getLabel(index);
    }

    /**
     * colors that will be appended to the end of the colors array after
     * calculating the legend.
     */
    @Kroll.getProperty @Kroll.method
    public int[] getExtraColors() {
        return this.legend.getExtraColors();
    }

    /**
     * labels that will be appended to the end of the labels array after
     * calculating the legend. a null label will start a group.
     */
    @Kroll.getProperty @Kroll.method
    public String[] getExtraLabels() {
        return this.legend.getExtraLabels();
    }

    /**
     * Colors and labels that will be appended to the end of the auto calculated
     * colors and labels arrays after calculating the legend. (if the legend has
     * already been calculated, you will need to call notifyDataSetChanged() to
     * let the changes take effect)
     */
    @Kroll.method
    public void setExtra(Object colors, Object labels) {
    	if (!(colors.getClass().isArray())) {
			throw new IllegalArgumentException("colors must be an array of string");
		}
		if (!(labels.getClass().isArray())) {
			throw new IllegalArgumentException("labels must be an array of string");
		}
		String[] colorArray = TiConvert.toStringArray((Object[])colors);
		String[] labelArray = TiConvert.toStringArray((Object[])labels);
		int[] intColorArray = toColorArray(colorArray);
		this.legend.setExtra(intColorArray, labelArray);
    }

    public int[] toColorArray(String[] inArray) {
		int[] outArray = new int[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			outArray[i] = TiConvert.toColor(inArray[i]);
		}
		return outArray;
	}

    /**
     * Sets a custom legend's labels and colors arrays. The colors count should
     * match the labels count. * Each color is for the form drawn at the same
     * index. * A null label will start a group. * A ColorTemplate.COLOR_SKIP
     * color will avoid drawing a form This will disable the feature that
     * automatically calculates the legend labels and colors from the datasets.
     * Call resetCustom() to re-enable automatic calculation (and then
     * notifyDataSetChanged() is needed to auto-calculate the legend again)
     */
    @Kroll.method
    public void setCustom(Object colors, Object labels) {

        if (!(colors.getClass().isArray())) {
			throw new IllegalArgumentException("colors must be an array of string");
		}
		if (!(labels.getClass().isArray())) {
			throw new IllegalArgumentException("labels must be an array of string");
		}
		String[] colorArray = TiConvert.toStringArray((Object[])colors);
		String[] labelArray = TiConvert.toStringArray((Object[])labels);
		int[] intColorArray = toColorArray(colorArray);
		this.legend.setCustom(intColorArray, labelArray);
    }


    /**
     * Calling this will disable the custom legend labels (set by
     * setCustom(...)). Instead, the labels will again be calculated
     * automatically (after notifyDataSetChanged() is called).
     */
    @Kroll.method
    public void resetCustom() {
    	this.legend.resetCustom();
    }

    /**
     * @return true if a custom legend labels and colors has been set default
     *         false (automatic legend)
     */
    @Kroll.method
    public boolean isLegendCustom() {
        return this.legend.isLegendCustom();
    }

    /**
     * returns the position of the legend relative to the chart
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getPosition() {
    	LegendPosition position = this.legend.getPosition();
		String txtPosition = "";
		switch(position) {
			case RIGHT_OF_CHART:
				txtPosition = "RIGHT_OF_CHART";
				break;
			case RIGHT_OF_CHART_CENTER:
				txtPosition = "RIGHT_OF_CHART_CENTER";
				break;
			case RIGHT_OF_CHART_INSIDE:
				txtPosition = "RIGHT_OF_CHART_INSIDE";
				break;
			case LEFT_OF_CHART:
				txtPosition = "LEFT_OF_CHART";
				break;
			case LEFT_OF_CHART_CENTER:
				txtPosition = "LEFT_OF_CHART_CENTER";
				break;
			case LEFT_OF_CHART_INSIDE:
				txtPosition = "LEFT_OF_CHART_INSIDE";
				break;
			case BELOW_CHART_LEFT:
				txtPosition = "BELOW_CHART_LEFT";
				break;
			case BELOW_CHART_RIGHT:
				txtPosition = "BELOW_CHART_RIGHT";
				break;
			case BELOW_CHART_CENTER:
				txtPosition = "BELOW_CHART_CENTER";
				break;
			case ABOVE_CHART_LEFT:
				txtPosition = "ABOVE_CHART_LEFT";
				break;
			case ABOVE_CHART_RIGHT:
				txtPosition = "ABOVE_CHART_RIGHT";
				break;
			case ABOVE_CHART_CENTER:
				txtPosition = "ABOVE_CHART_CENTER";
				break;
			case PIECHART_CENTER:
				txtPosition = "PIECHART_CENTER";
				break;
			default:
				break;
        }
        return txtPosition;
    }

    /**
     * sets the position of the legend relative to the whole chart
     * 
     * @param newValue
     */
    @Kroll.setProperty @Kroll.method
    public void setPosition(String pos) {
    	LegendPosition position = null;
    	switch(pos) {
    		case "RIGHT_OF_CHART":
				position = LegendPosition.RIGHT_OF_CHART;
				break;
			case "RIGHT_OF_CHART_CENTER":
				position = LegendPosition.RIGHT_OF_CHART_CENTER;
				break;
			case "RIGHT_OF_CHART_INSIDE":
				position = LegendPosition.RIGHT_OF_CHART_INSIDE;
				break;
			case "LEFT_OF_CHART":
				position = LegendPosition.LEFT_OF_CHART;
				break;
			case "LEFT_OF_CHART_CENTER":
				position = LegendPosition.LEFT_OF_CHART_CENTER;
				break;
			case "LEFT_OF_CHART_INSIDE":
				position = LegendPosition.LEFT_OF_CHART_INSIDE;
				break;
			case "BELOW_CHART_LEFT":
				position = LegendPosition.BELOW_CHART_LEFT;
				break;
			case "BELOW_CHART_RIGHT":
				position = LegendPosition.BELOW_CHART_RIGHT;
				break;
			case "BELOW_CHART_CENTER":
				position = LegendPosition.BELOW_CHART_CENTER;
				break;
			case "ABOVE_CHART_LEFT":
				position = LegendPosition.ABOVE_CHART_LEFT;
				break;
			case "ABOVE_CHART_RIGHT":
				position = LegendPosition.ABOVE_CHART_RIGHT;
				break;
			case "ABOVE_CHART_CENTER":
				position = LegendPosition.ABOVE_CHART_CENTER;
				break;
			case "PIECHART_CENTER":
				position = LegendPosition.PIECHART_CENTER;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setPosition(position);
      
    }

    /**
     * returns the horizontal alignment of the legend
     *
     * @return
     */
    /*@Kroll.getProperty @Kroll.method
    public String getHorizontalAlignment() {

    	LegendHorizontalAlignment position = this.legend.getHorizontalAlignment();
		String txtPosition = "";
		switch(position) {
			case LEFT:
				txtPosition = "LEFT";
				break;
			case CENTER:
				txtPosition = "CENTER";
				break;
			case RIGHT:
				txtPosition = "RIGHT";
				break;
			default:
				break;
        }
        return txtPosition;
    }*/

    /**
     * sets the horizontal alignment of the legend
     *
     * @param value
     */
    /*@Kroll.setProperty @Kroll.method
    public void setHorizontalAlignment(String value) {
    	LegendHorizontalAlignment position = null;
    	switch(value) {
    		case "LEFT":
				position = LegendHorizontalAlignment.LEFT;
				break;
			case "CENTER":
				position = LegendHorizontalAlignment.CENTER;
				break;
			case "RIGHT":
				position = LegendHorizontalAlignment.RIGHT;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setHorizontalAlignment(position);

    }*/

    /**
     * returns the vertical alignment of the legend
     *
     * @return
     */
   /* @Kroll.getProperty @Kroll.method
    public String getVerticalAlignment() {
    	LegendVerticalAlignment position = this.legend.getVerticalAlignment();
		String txtPosition = "";
		switch(position) {
			case TOP:
				txtPosition = "TOP";
				break;
			case CENTER:
				txtPosition = "CENTER";
				break;
			case BOTTOM:
				txtPosition = "BOTTOM";
				break;
			default:
				break;
        }
        return txtPosition;
    }*/

    /**
     * sets the vertical alignment of the legend
     *
     * @param value
     */
   /* @Kroll.setProperty @Kroll.method
    public void setVerticalAlignment(String value) {
        LegendVerticalAlignment position = null;
    	switch(value) {
    		case "TOP":
				position = LegendVerticalAlignment.TOP;
				break;
			case "CENTER":
				position = LegendVerticalAlignment.CENTER;
				break;
			case "BOTTOM":
				position = LegendVerticalAlignment.BOTTOM;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setVerticalAlignment(position);
    }*/

    /**
     * returns the orientation of the legend
     *
     * @return
     */
   /* @Kroll.getProperty @Kroll.method
    public String getOrientation() {
        LegendOrientation position = this.legend.getOrientation();
		String txtPosition = "";
		switch(position) {
			case HORIZONTAL:
				txtPosition = "HORIZONTAL";
				break;
			case VERTICAL:
				txtPosition = "VERTICAL";
				break;
			default:
				break;
        }
        return txtPosition;
    }*/

    /**
     * sets the orientation of the legend
     *
     * @param value
     */
    /*@Kroll.setProperty @Kroll.method
    public void setOrientation(String value) {
        LegendOrientation position = null;
    	switch(value) {
    		case "HORIZONTAL":
				position = LegendOrientation.HORIZONTAL;
				break;
			case "VERTICAL":
				position = LegendOrientation.VERTICAL;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setOrientation(position);
    }*/

    /**
     * returns whether the legend will draw inside the chart or outside
     *
     * @return
     */
    /*@Kroll.method
    public boolean isDrawInsideEnabled() {
        return this.legend.isDrawInsideEnabled();
    }*/

    /**
     * sets whether the legend will draw inside the chart or outside
     *
     * @param value
     */
    /*@Kroll.setProperty @Kroll.method
    public void setDrawInside(boolean value) {
        this.legend.setDrawInside(value);
    }*/

    /**
     * returns the text direction of the legend
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getDirection() {
        LegendDirection position = this.legend.getDirection();
		String txtPosition = "";
		switch(position) {
			case LEFT_TO_RIGHT:
				txtPosition = "LEFT_TO_RIGHT";
				break;
			case RIGHT_TO_LEFT:
				txtPosition = "RIGHT_TO_LEFT";
				break;
			default:
				break;
        }
        return txtPosition;
    }

    /**
     * sets the text direction of the legend
     *
     * @param pos
     */
    @Kroll.setProperty @Kroll.method
    public void setDirection(String pos) {
        LegendDirection position = null;
    	switch(pos) {
    		case "LEFT_TO_RIGHT":
				position = LegendDirection.LEFT_TO_RIGHT;
				break;
			case "RIGHT_TO_LEFT":
				position = LegendDirection.RIGHT_TO_LEFT;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setDirection(position);
    }

    /**
     * returns the current form/shape that is set for the legend
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getForm() {
        LegendForm position = this.legend.getForm();
		String txtPosition = "";
		switch(position) {
			case SQUARE:
				txtPosition = "SQUARE";
				break;
			case CIRCLE:
				txtPosition = "CIRCLE";
				break;
			case LINE:
				txtPosition = "LINE";
				break;
			default:
				break;
        }
        return txtPosition;
    }

    /**
     * sets the form/shape of the legend forms
     * 
     * @param shape
     */
    @Kroll.setProperty @Kroll.method
    public void setForm(String shape) {
        LegendForm position = null;
    	switch(shape) {
    		case "SQUARE":
				position = LegendForm.SQUARE;
				break;
			case "CIRCLE":
				position = LegendForm.CIRCLE;
				break;
			case "LINE":
				position = LegendForm.LINE;
				break;
			default:
				break;
			
	    }
    	if(position != null)
    		this.legend.setForm(position);
    }

    /**
     * sets the size in pixels of the legend forms, this is internally converted
     * in dp, default 8f
     * 
     * @param size
     */
    @Kroll.setProperty @Kroll.method
    public void setFormSize(float size) {
        this.legend.setFormSize(size);
    }

    /**
     * returns the size in dp of the legend forms
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getFormSize() {
        return this.legend.getFormSize();
    }

    /**
     * returns the space between the legend entries on a horizontal axis in
     * pixels
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getXEntrySpace() {
        return this.legend.getXEntrySpace();
    }

    /**
     * sets the space between the legend entries on a horizontal axis in pixels,
     * converts to dp internally
     * 
     * @param space
     */
    @Kroll.setProperty @Kroll.method
    public void setXEntrySpace(float space) {
    	this.legend.setXEntrySpace(space);
    }

    /**
     * returns the space between the legend entries on a vertical axis in pixels
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getYEntrySpace() {
        return this.legend.getYEntrySpace();
    }

    /**
     * sets the space between the legend entries on a vertical axis in pixels,
     * converts to dp internally
     * 
     * @param space
     */
    @Kroll.setProperty @Kroll.method
    public void setYEntrySpace(float space) {
        this.legend.setYEntrySpace(space);
    }

    /**
     * returns the space between the form and the actual label/text
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getFormToTextSpace() {
        return this.legend.getFormToTextSpace();
    }

    /**
     * sets the space between the form and the actual label/text, converts to dp
     * internally
     * 
     * @param mFormToTextSpace
     */
    @Kroll.setProperty @Kroll.method
    public void setFormToTextSpace(float space) {
        this.legend.setFormToTextSpace(space);
    }

    /**
     * returns the space that is left out between stacked forms (with no label)
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getStackSpace() {
        return this.legend.getStackSpace();
    }

    /**
     * sets the space that is left out between stacked forms (with no label)
     * 
     * @param space
     */
    @Kroll.setProperty @Kroll.method
    public void setStackSpace(float space) {
        this.legend.setStackSpace(space);
    }

    /**
     * calculates the full width the fully drawn legend will use in pixels
     * 
     * @return
     */
    /*
    @Kroll.getProperty @Kroll.method
    public float getFullWidth(Paint labelpaint) {

        float width = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            // grouped forms have null labels
            if (mLabels[i] != null) {

                // make a step to the left
                if (mColors[i] != ColorTemplate.COLOR_SKIP)
                    width += mFormSize + mFormToTextSpace;

                width += Utils.calcTextWidth(labelpaint, mLabels[i]);

                if (i < mLabels.length - 1)
                    width += mXEntrySpace;
            } else {
                width += mFormSize;
                if (i < mLabels.length - 1)
                    width += mStackSpace;
            }
        }

        return width;
    }*/

    /**
     * Calculates the full height of the drawn legend.
     * 
     * @param mLegendLabelPaint
     * @return
     */
    /*@Kroll.getProperty @Kroll.method
    public float getFullHeight(Paint labelpaint) {

        float height = 0f;

        for (int i = 0; i < mLabels.length; i++) {

            // grouped forms have null labels
            if (mLabels[i] != null) {

                height += Utils.calcTextHeight(labelpaint, mLabels[i]);

                if (i < mLabels.length - 1)
                    height += mYEntrySpace;
            }
        }

        return height;
    }*/

    /**
     * Should the legend word wrap? / this is currently supported only for:
     * BelowChartLeft, BelowChartRight, BelowChartCenter. / note that word
     * wrapping a legend takes a toll on performance. / you may want to set
     * maxSizePercent when word wrapping, to set the point where the text wraps.
     * / default: false
     * 
     * @param enabled
     */
    @Kroll.setProperty @Kroll.method
    public void setWordWrapEnabled(boolean enabled) {
        this.legend.setWordWrapEnabled(enabled);
    }

    /**
     * If this is set, then word wrapping the legend is enabled. This means the
     * legend will not be cut off if too long.
     * 
     * @return
     */
    @Kroll.method
    public boolean isWordWrapEnabled() {
        return this.legend.isWordWrapEnabled();
    }

    /**
     * The maximum relative size out of the whole chart view. / If the legend is
     * to the right/left of the chart, then this affects the width of the
     * legend. / If the legend is to the top/bottom of the chart, then this
     * affects the height of the legend. / If the legend is the center of the
     * piechart, then this defines the size of the rectangular bounds out of the
     * size of the "hole". / default: 0.95f (95%)
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getMaxSizePercent() {
        return this.legend.getMaxSizePercent();
    }

    /**
     * The maximum relative size out of the whole chart view. / If
     * the legend is to the right/left of the chart, then this affects the width
     * of the legend. / If the legend is to the top/bottom of the chart, then
     * this affects the height of the legend. / default: 0.95f (95%)
     * 
     * @param maxSize
     */
    @Kroll.setProperty @Kroll.method
    public void setMaxSizePercent(float maxSize) {
        this.legend.setMaxSizePercent(maxSize);
    }

   /* @Kroll.getProperty @Kroll.method
    public HashMap[] getCalculatedLabelSizes() {
        return FSizeArraytoHashmapArray(this.legend.getCalculatedLabelSizes());
    }

    public static HashMap[] FSizeArraytoHashmapArray(FSize[] inArray) {
		HashMap[] outArray = new HashMap[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			FSize point = inArray[i];
			HashMap value = new HashMap();
			value.put("width", point.width);
			value.put("height", point.height);
			outArray[i] = value;
		}
		return outArray;
	}*/

   /* @Kroll.getProperty @Kroll.method
    public Boolean[] getCalculatedLabelBreakPoints() {
        return this.legend.getCalculatedLabelBreakPoints();
    }*/

   /* @Kroll.getProperty @Kroll.method
    public HashMap[] getCalculatedLineSizes() {
        return FSizeArraytoHashmapArray(this.legend.getCalculatedLineSizes());
    }*/

    /**
     * Calculates the dimensions of the Legend. This includes the maximum width
     * and height of a single entry, as well as the total width and height of
     * the Legend.
     * 
     * @param labelpaint
     */
    /*
    public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {

        
    }*/

}