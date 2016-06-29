package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nz.co.simplypayroll.charts.TiChartsModule;
import nz.co.simplypayroll.charts.utils.TiConverter;

/**
 * Created by adrienblot on 29/06/16.
 */
@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class LineDataSetProxy extends LineRadarDataSetProxy {

    private static final String LCAT = "LineDataSetProxy";

    public LineDataSetProxy() {
        super();
    }

    @Override
    public void handleCreationDict(KrollDict options)
    {
        // This method is called from handleCreationArgs if there is at least one
        // argument specified for the proxy creation call and the first argument
        // is a KrollDict object.
        Log.d(LCAT, "PROXY LIFECYCLE EVENT] handleCreationDict " + options);

        ArrayList<Entry> yVals;
        String label = "";
        if(options.containsKey("yVals")) {
            yVals = TiConverter.toEntryList(options.get("yVals"));
        } else {
            yVals = new ArrayList<Entry>();
        }

        if(options.containsKey("label")) {
            label = TiConvert.toString(options.get("label"));
        }

        dataSet = new LineDataSet(yVals, label);
        super.handleCreationDict(options);
    }


    @Kroll.method
    public LineDataSetProxy copy() {
        DataSet<Entry> dataSetCopy = ((LineDataSet)dataSet).copy();
        LineDataSetProxy lineDataSetProxyCopy = new LineDataSetProxy();
        lineDataSetProxyCopy.dataSet = dataSetCopy;
        return lineDataSetProxyCopy;
    }

    /**
     * Returns the drawing mode for this line dataset
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getMode() {
        LineDataSet.Mode mode = ((LineDataSet)dataSet).getMode();
        String txtLabelPosition = "";
        switch(mode) {
            case LINEAR:
                txtLabelPosition = "LINEAR";
                break;
            case STEPPED:
                txtLabelPosition = "STEPPED";
                break;
            case CUBIC_BEZIER:
                txtLabelPosition = "CUBIC_BEZIER";
                break;
            case HORIZONTAL_BEZIER:
                txtLabelPosition = "HORIZONTAL_BEZIER";
                break;
            default:
                break;
        }
        return txtLabelPosition;
    }

    /**
     * Returns the drawing mode for this line dataset
     *
     * @return
     */
    @Kroll.setProperty @Kroll.method
    public void setMode(String mod) {
        LineDataSet.Mode mode = null;
        switch(mod) {
            case "LINEAR":
                mode = LineDataSet.Mode.LINEAR;
                break;
            case "STEPPED":
                mode = LineDataSet.Mode.STEPPED;
                break;
            case "CUBIC_BEZIER":
                mode = LineDataSet.Mode.CUBIC_BEZIER;
                break;
            case "HORIZONTAL_BEZIER":
                mode = LineDataSet.Mode.HORIZONTAL_BEZIER;
                break;
            default:
                break;
        }
        if(mode != null)
            ((LineDataSet)dataSet).setMode(mode);
    }

    /**
     * Sets the intensity for cubic lines (if enabled). Max = 1f = very cubic,
     * Min = 0.05f = low cubic effect, Default: 0.2f
     *
     * @param intensity
     */
    @Kroll.setProperty @Kroll.method
    public void setCubicIntensity(float intensity) {
        ((LineDataSet)dataSet).setCubicIntensity(intensity);
    }

    @Kroll.getProperty @Kroll.method
    public float getCubicIntensity() {
        return  ((LineDataSet)dataSet).getCubicIntensity();
    }


    /**
     * sets the radius of the drawn circles.
     * Default radius = 4f
     *
     * @param radius
     */
    @Kroll.setProperty @Kroll.method
    public void setCircleRadius(float radius) {
        ((LineDataSet)dataSet).setCircleRadius(radius);
    }

    @Kroll.getProperty @Kroll.method
    public float getCircleRadius() {
        return ((LineDataSet)dataSet).getCircleRadius();
    }

    /**
     * sets the hole radius of the drawn circles.
     * Default radius = 2f
     *
     * @param holeRadius
     */
    @Kroll.setProperty @Kroll.method
    public void setCircleHoleRadius(float holeRadius) {
        ((LineDataSet)dataSet).setCircleHoleRadius(holeRadius);
    }

    @Kroll.getProperty @Kroll.method
    public float getCircleHoleRadius() {
        return ((LineDataSet)dataSet).getCircleHoleRadius();
    }


    /**
     * Enables the line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param lineLength the length of the line pieces
     * @param spaceLength the length of space in between the pieces
     * @param phase offset, in degrees (normally, use 0)
     */
    @Kroll.method
    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        ((LineDataSet)dataSet).enableDashedLine(lineLength, spaceLength, phase);
    }

    /**
     * Disables the line to be drawn in dashed mode.
     */
    @Kroll.method
    public void disableDashedLine() {
        ((LineDataSet)dataSet).disableDashedLine();
    }


    @Kroll.getProperty @Kroll.method
    public boolean getDashedLineEnabled() {
        return ((LineDataSet)dataSet).isDashedLineEnabled();
    }

    /*
    @Override
    public DashPathEffect getDashPathEffect() {
        return mDashPathEffect;
    }*/

    /**
     * set this to true to enable the drawing of circle indicators for this
     * DataSet, default true
     *
     * @param enabled
     */
    @Kroll.getProperty @Kroll.method
    public void setDrawCirclesEnabled(Object enabled) {
        ((LineDataSet)dataSet).setDrawCircles(TiConvert.toBoolean(enabled));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getDrawCirclesEnabled() {
        return ((LineDataSet)dataSet).isDrawCirclesEnabled();
    }



    /** ALL CODE BELOW RELATED TO CIRCLE-COLORS */

    /**
     * returns all colors specified for the circles
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String[] getCircleColors() {
        if(hasProperty("circleColors")) {
            return (String[])getProperty("circleColors");
        } else {
            List<Integer> colors = ((LineDataSet)dataSet).getCircleColors();
            Iterator<Integer> iterator = colors.iterator();
            String[] colorsProperty = new String[colors.size()];
            int i = 0;
            while(iterator.hasNext()){
                Integer obj = iterator.next();
                colorsProperty[i] = Integer.toHexString(obj);
                i++;
            }
            setProperty("circleColors", colorsProperty);
            return colorsProperty;
        }
    }

    @Kroll.method
    public String getCircleColor(@Kroll.argument(optional=true) Object colorIndex) {
        int index = 0;
        if(colorIndex != null) {
            index = TiConvert.toInt(colorIndex, 0);
        }
        if(hasProperty("circleColors")) {
            String[] colors = (String[])getProperty("circleColors");
            if(colors.length == 0)
                return null;
            else
                return colors[index % colors.length];
        } else {
            return Integer.toHexString(((LineDataSet)dataSet).getCircleColor(index));
        }
    }

    /**
     * Sets the colors that should be used for the circles of this DataSet.
     * Colors are reused as soon as the number of Entries the DataSet represents
     * is higher than the size of the colors array. Make sure that the colors
     * are already prepared (by calling getResources().getColor(...)) before
     * adding them to the DataSet.
     *
     * @param colors
     */
    @Kroll.setProperty @Kroll.method
    public void setCircleColors(Object colors) {
        int [] colorsArray = null;
        String [] colorsProperty = null;
        if ((colors.getClass().isArray())) {
            colorsProperty = TiConvert.toStringArray((Object[])colors);

        } else {
            colorsProperty = new String[1];
            colorsProperty[0] = TiConvert.toString(colors);
        }

        if(colorsProperty != null) {
            colorsArray = TiConverter.toColorArray(colorsProperty);
            setProperty("circleColors", colorsProperty);
            ((LineDataSet)dataSet).setCircleColors(colorsArray);
        }
    }

    /**
     * Sets the color of the inner circle of the line-circles.
     *
     * @param color
     */
    @Kroll.setProperty @Kroll.method
    public void setCircleColorHole(String color) {
        setProperty("circleColorHole", color);
        ((LineDataSet)dataSet).setCircleColorHole(TiConvert.toColor(color));
    }

    @Kroll.getProperty @Kroll.method
    public String getCircleHoleColor() {
        if(hasProperty("circleColorHole")) {
            return (String)getProperty("circleColorHole");
        } else {
            int color = ((LineDataSet)dataSet).getCircleHoleColor();
            String colorProperty = Integer.toHexString(color);
            setProperty("circleColorHole", colorProperty);
            return colorProperty;
        }
    }

    /**
     * Set this to true to allow drawing a hole in each data circle.
     *
     * @param enabled
     */
    @Kroll.setProperty @Kroll.method
    public void setDrawCircleHoleEnabled(Object enabled) {
        ((LineDataSet)dataSet).setDrawCircleHole(TiConvert.toBoolean(enabled));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getDrawCircleHoleEnabled() {
        return ((LineDataSet)dataSet).isDrawCircleHoleEnabled();
    }

    /**
     * Sets a custom FillFormatter to the chart that handles the position of the
     * filled-line for each DataSet. Set this to null to use the default logic.
     *
     * @param formatter
     */
    /*public void setFillFormatter(FillFormatter formatter) {

        if (formatter == null)
            mFillFormatter = new DefaultFillFormatter();
        else
            mFillFormatter = formatter;
    }*/

    /*@Override
    public FillFormatter getFillFormatter() {
        return mFillFormatter;
    }*/



















}
