package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.TiConvert;

/**
 * Created by adrienblot on 29/06/16.
 */
@Kroll.proxy
public class BarLineScatterCandleBubbleDataSetProxy extends DataSetProxy {

    public BarLineScatterCandleBubbleDataSetProxy() {
        super();
    }

    @Kroll.setProperty @Kroll.method
    public void setHighLightColor(String color) {
        setProperty("highLightColor", color);
        ((BarLineScatterCandleBubbleDataSet)dataSet).setHighLightColor(TiConvert.toColor(color));
    }

    @Kroll.getProperty @Kroll.method
    public String getHighLightColor() {
        if(hasProperty("highLightColor")) {
            return (String)getProperty("highLightColor");
        } else {
            return Integer.toHexString(((BarLineScatterCandleBubbleDataSet)dataSet).getHighLightColor());
        }
    }
}
