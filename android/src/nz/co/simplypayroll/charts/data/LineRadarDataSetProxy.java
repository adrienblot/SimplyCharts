package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.LineRadarDataSet;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.TiConvert;

/**
 * Created by adrienblot on 29/06/16.
 */
@Kroll.proxy
public class LineRadarDataSetProxy extends LineScatterCandleRadarDataSetProxy {

    public LineRadarDataSetProxy() {
        super();
    }

    @Kroll.getProperty @Kroll.method
    public String getFillColor() {
        if(hasProperty("fillColor")) {
            return (String)getProperty("fillColor");
        } else {
            int color = ((LineRadarDataSet)dataSet).getFillColor();
            String colorProperty = Integer.toHexString(color);
            setProperty("fillColor", colorProperty);
            return colorProperty;
        }
    }

    /**
     * Sets the color that is used for filling the area below the line.
     * Resets an eventually set "fillDrawable".
     *
     * @param color
     */
    @Kroll.setProperty @Kroll.method
    public void setFillColor(String color) {
        setProperty("fillColor", color);
        ((LineRadarDataSet)dataSet).setFillColor(TiConvert.toColor(color));
    }

    /*@Override
    public Drawable getFillDrawable() {
        return mFillDrawable;
    }*/

    /**
     * Sets the drawable to be used to fill the area below the line.
     *
     * @param drawable
     */
    /*@TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }*/

    @Kroll.getProperty @Kroll.method
    public int getFillAlpha() {
        return ((LineRadarDataSet)dataSet).getFillAlpha();
    }

    /**
     * sets the alpha value (transparency) that is used for filling the line
     * surface (0-255), default: 85
     *
     * @param alpha
     */
    @Kroll.setProperty @Kroll.method
    public void setFillAlpha(int alpha) {
        ((LineRadarDataSet)dataSet).setFillAlpha(alpha);
    }

    /**
     * set the line width of the chart (min = 0.2f, max = 10f); default 1f NOTE:
     * thinner line == better performance, thicker line == worse performance
     *
     * @param width
     */
    @Kroll.setProperty @Kroll.method
    public void setLineWidth(float width) {
        ((LineRadarDataSet)dataSet).setLineWidth(width);
    }

    @Kroll.getProperty @Kroll.method
    public float getLineWidth() {
        return ((LineRadarDataSet)dataSet).getLineWidth();
    }

    @Kroll.setProperty @Kroll.method
    public void setDrawFilledEnabled(Object filled) {
        ((LineRadarDataSet)dataSet).setDrawFilled(TiConvert.toBoolean(filled));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getDrawFilledEnabled() {
        return ((LineRadarDataSet)dataSet).isDrawFilledEnabled();
    }
}
