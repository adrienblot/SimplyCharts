package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.LineScatterCandleRadarDataSet;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiDimension;
import org.appcelerator.titanium.util.TiConvert;

/**
 * Created by adrienblot on 29/06/16.
 */
@Kroll.proxy
public class LineScatterCandleRadarDataSetProxy extends BarLineScatterCandleBubbleDataSetProxy {

    public LineScatterCandleRadarDataSetProxy() {
        super();
    }

    /**
     * Enables / disables the horizontal highlight-indicator. If disabled, the indicator is not drawn.
     * @param enabled
     */
    @Kroll.setProperty @Kroll.method
    public void setHorizontalHighlightIndicatorEnabled(Object enabled) {
        ((LineScatterCandleRadarDataSet)dataSet).setDrawHorizontalHighlightIndicator(TiConvert.toBoolean(enabled));
    }

    /**
     * Enables / disables the vertical highlight-indicator. If disabled, the indicator is not drawn.
     * @param enabled
     */
    @Kroll.setProperty @Kroll.method
    public void setVerticalHighlightIndicatorEnabled(Object enabled) {
        ((LineScatterCandleRadarDataSet)dataSet).setDrawVerticalHighlightIndicator(TiConvert.toBoolean(enabled));
    }

    /**
     * Enables / disables both vertical and horizontal highlight-indicators.
     * @param enabled
     */
    @Kroll.method
    public void setDrawHighlightIndicators(Object enabled) {
        ((LineScatterCandleRadarDataSet)dataSet).setDrawHighlightIndicators(TiConvert.toBoolean(enabled));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getVerticalHighlightIndicatorEnabled() {
        return ((LineScatterCandleRadarDataSet)dataSet).isVerticalHighlightIndicatorEnabled();
    }

    @Kroll.getProperty @Kroll.method
    public boolean getHorizontalHighlightIndicatorEnabled() {
        return ((LineScatterCandleRadarDataSet)dataSet).isHorizontalHighlightIndicatorEnabled();
    }

    /**
     * Sets the width of the highlight line in dp.
     * @param width
     */
    @Kroll.setProperty @Kroll.method
    public void setHighlightLineWidth(float value) {
        ((LineScatterCandleRadarDataSet)dataSet).setHighlightLineWidth(value);
    }

    @Kroll.getProperty @Kroll.method
    public float getHighlightLineWidth() {
        return ((LineScatterCandleRadarDataSet)dataSet).getHighlightLineWidth();
    }

    /**
     * Enables the highlight-line to be drawn in dashed mode, e.g. like this "- - - - - -"
     *
     * @param lineLength the length of the line pieces
     * @param spaceLength the length of space inbetween the line-pieces
     * @param phase offset, in degrees (normally, use 0)
     */
    @Kroll.method
    public void enableDashedHighlightLine(float lineLength, float spaceLength, float phase) {
        ((LineScatterCandleRadarDataSet)dataSet).enableDashedHighlightLine(lineLength, spaceLength, phase);
    }

    /**
     * Disables the highlight-line to be drawn in dashed mode.
     */
    @Kroll.method
    public void disableDashedHighlightLine() {
        ((LineScatterCandleRadarDataSet)dataSet).disableDashedHighlightLine();
    }

    /**
     * Returns true if the dashed-line effect is enabled for highlight lines, false if not.
     * Default: disabled
     *
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public boolean getDashedHighlightLineEnabled() {
        return ((LineScatterCandleRadarDataSet)dataSet).isDashedHighlightLineEnabled();
    }

}
