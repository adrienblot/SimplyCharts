package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;

import java.util.ArrayList;

import nz.co.simplypayroll.charts.TiChartsModule;
import nz.co.simplypayroll.charts.utils.TiConverter;

/**
 * Created by adrienblot on 30/06/16.
 */
@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class BarDataSetProxy extends BarLineScatterCandleBubbleDataSetProxy{

    private static final String LCAT = "LineDataSetProxy";

    public BarDataSetProxy() {
        super();
    }

    @Override
    public void handleCreationDict(KrollDict options)
    {
        // This method is called from handleCreationArgs if there is at least one
        // argument specified for the proxy creation call and the first argument
        // is a KrollDict object.
        Log.d(LCAT, "PROXY LIFECYCLE EVENT] handleCreationDict " + options);

        ArrayList<BarEntry> yVals;
        String label = "";
        if(options.containsKey("yVals")) {
            yVals = TiConverter.toBarEntryList(options.get("yVals"));
        } else {
            yVals = new ArrayList<BarEntry>();
        }

        if(options.containsKey("label")) {
            label = TiConvert.toString(options.get("label"));
        }

        dataSet = new BarDataSet(yVals, label);
        super.handleCreationDict(options);
    }

    @Kroll.method
    public BarDataSetProxy copy() {
        DataSet<BarEntry> dataSetCopy = ((BarDataSet)dataSet).copy();
        BarDataSetProxy barDataSetProxyCopy = new BarDataSetProxy();
        barDataSetProxyCopy.dataSet = dataSetCopy;
        return barDataSetProxyCopy;
    }


    @Kroll.method
    public void calcMinMax(int start, int end) {
        ((BarDataSet)dataSet).calcMinMax(start, end);
    }

    @Kroll.method
    public int getStackSize() {
        return ((BarDataSet)dataSet).getStackSize();
    }

    @Kroll.method
    public boolean isStacked() {
        return ((BarDataSet)dataSet).isStacked();
    }

    /**
     * returns the overall entry count, including counting each stack-value
     * individually
     *
     * @return
     */
    @Kroll.method
    public int getEntryCountStacks() {
        return ((BarDataSet)dataSet).getEntryCountStacks();
    }

    /**
     * returns the space between bars in percent of the whole width of one value
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getBarSpacePercent() {
        return ((BarDataSet)dataSet).getBarSpacePercent();
    }

    @Kroll.getProperty @Kroll.method
    public float getBarSpace() {
        return ((BarDataSet)dataSet).getBarSpace();
    }

    /**
     * sets the space between the bars in percent (0-100) of the total bar width
     *
     * @param percent
     */
    @Kroll.setProperty @Kroll.method
    public void setBarSpacePercent(float percent) {
        ((BarDataSet)dataSet).setBarSpacePercent(percent);
    }

    /**
     * Sets the color used for drawing the bar-shadows. The bar shadows is a
     * surface behind the bar that indicates the maximum value. Don't for get to
     * use getResources().getColor(...) to set this. Or Color.rgb(...).
     *
     * @param color
     */
    @Kroll.setProperty @Kroll.method
    public void setBarShadowColor(String color) {
        setProperty("barShadowColor", color);
        ((BarDataSet)dataSet).setBarShadowColor(TiConvert.toColor(color));
    }

    @Kroll.getProperty @Kroll.method
    public String getBarShadowColor() {
        if(hasProperty("barShadowColor")) {
            return (String)getProperty("barShadowColor");
        } else {
            int color = ((BarDataSet)dataSet).getBarShadowColor();
            String colorProperty = Integer.toHexString(color);
            setProperty("barShadowColor", colorProperty);
            return colorProperty;
        }
    }

    /**
     * Sets the width used for drawing borders around the bars.
     * If borderWidth == 0, no border will be drawn.
     *
     * @return
     */
    @Kroll.setProperty @Kroll.method
    public void setBarBorderWidth(float width) {
        ((BarDataSet)dataSet).setBarBorderWidth(width);
    }

    /**
     * Returns the width used for drawing borders around the bars.
     * If borderWidth == 0, no border will be drawn.
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public float getBarBorderWidth() {
        return ((BarDataSet)dataSet).getBarBorderWidth();
    }

    /**
     * Sets the color drawing borders around the bars.
     *
     * @return
     */
    @Kroll.setProperty @Kroll.method
    public void setBarBorderColor(String color) {
        setProperty("barBorderColor", color);
        ((BarDataSet)dataSet).setBarBorderColor(TiConvert.toColor(color));
    }

    /**
     * Returns the color drawing borders around the bars.
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getBarBorderColor() {
        if(hasProperty("barBorderColor")) {
            return (String)getProperty("barBorderColor");
        } else {
            int color = ((BarDataSet)dataSet).getBarBorderColor();
            String colorProperty = Integer.toHexString(color);
            setProperty("barBorderColor", colorProperty);
            return colorProperty;
        }
    }

    /**
     * Set the alpha value (transparency) that is used for drawing the highlight
     * indicator bar. min = 0 (fully transparent), max = 255 (fully opaque)
     *
     * @param alpha
     */
    @Kroll.setProperty @Kroll.method
    public void setHighLightAlpha(int alpha) {
        ((BarDataSet)dataSet).setHighLightAlpha(alpha);
    }

    @Kroll.getProperty @Kroll.method
    public int getHighLightAlpha() {
        return ((BarDataSet)dataSet).getHighLightAlpha();
    }

    /**
     * Sets labels for different values of bar-stacks, in case there are one.
     *
     * @param labels
     */
    @Kroll.setProperty @Kroll.method
    public void setStackLabels(String[] labels) {
        ((BarDataSet)dataSet).setStackLabels(labels);
    }

    @Kroll.getProperty @Kroll.method
    public String[] getStackLabels() {
        return ((BarDataSet)dataSet).getStackLabels();
    }
}
