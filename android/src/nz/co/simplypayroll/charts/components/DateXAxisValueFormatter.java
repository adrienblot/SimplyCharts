package nz.co.simplypayroll.charts.components;


import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;



public class DateXAxisValueFormatter implements XAxisValueFormatter {

    @Override
    public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
        // original is the original value to use, x-index is the index in your x-values array
        // implement your logic here ...
        return original;
    }
}