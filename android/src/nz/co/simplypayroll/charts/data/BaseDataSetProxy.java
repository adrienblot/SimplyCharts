package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.utils.Utils;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.util.TiColorHelper;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nz.co.simplypayroll.charts.utils.TiConverter;

/**
 * Created by adrienblot on 28/06/16.
 */
@Kroll.proxy
public class BaseDataSetProxy extends KrollProxy {

    private static final String LCAT = "BaseDataSetProxy";

    public BaseDataSet dataSet;

    public BaseDataSetProxy() {
        super();
        Utils.init(TiApplication.getInstance().getApplicationContext().getResources());
    }

    /**
     * Use this method to tell the data set that the underlying data has changed.
     */
    @Kroll.method
    public void notifyDataSetChanged() {
        dataSet.notifyDataSetChanged();
    }


    /**
     * ###### ###### COLOR GETTING RELATED METHODS ##### ######
     */
    @Kroll.getProperty @Kroll.method
    public String[] getColors() {
        if(hasProperty("colors")) {
            return (String[])getProperty("colors");
        } else {
            List<Integer> colors = dataSet.getColors();
            Iterator<Integer> iterator = colors.iterator();
            String[] colorsProperty = new String[colors.size()];
            int i = 0;
            while(iterator.hasNext()){
                Integer obj = iterator.next();
                colorsProperty[i] = Integer.toHexString(obj);
                i++;
            }
            setProperty("colors", colorsProperty);
            return colorsProperty;
        }
    }

    @Kroll.getProperty @Kroll.method
    public String[] getValueTextColors() {
        if(hasProperty("valueTextColors")) {
            return (String[])getProperty("valueTextColors");
        } else {
            List<Integer> colors = dataSet.getValueColors();
            Iterator<Integer> iterator = colors.iterator();
            String[] colorsProperty = new String[colors.size()];
            int i = 0;
            while(iterator.hasNext()){
                Integer obj = iterator.next();
                colorsProperty[i] = Integer.toHexString(obj);
                i++;
            }
            setProperty("valueTextColors", colorsProperty);
            return colorsProperty;
        }
    }

    @Kroll.method
    public String getColor(@Kroll.argument(optional=true) Object colorIndex) {
        int index = 0;
        if(colorIndex != null) {
            index = TiConvert.toInt(colorIndex, 0);
        }
        if(hasProperty("colors")) {
            String[] colors = (String[])getProperty("colors");
            if(colors.length == 0)
                return null;
            else
                return colors[index % colors.length];
        } else {
            return Integer.toHexString(dataSet.getColor(index));
        }
    }


    /**
     * ###### ###### COLOR SETTING RELATED METHODS ##### ######
     */

    /**
     * Sets the colors that should be used fore this DataSet. Colors are reused
     * as soon as the number of Entries the DataSet represents is higher than
     * the size of the colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    @Kroll.setProperty @Kroll.method
    public void setColors(Object colors) {
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
            setProperty("colors", colorsProperty);
            dataSet.setColors(colorsArray);
        }
    }


    /**
     * Adds a new color to the colors array of the DataSet.
     *
     * @param color
     */
    @Kroll.method
    public void addColor(String color) {
        String [] newColorsProperty = null;
        if(hasProperty("colors")) {
            String [] colorsProperty = (String[])getProperty("colors");
            newColorsProperty = new String [colorsProperty.length + 1];
            for (int i = 0; i < colorsProperty.length; i++) {
                newColorsProperty[i] = colorsProperty[i];
            }
            newColorsProperty[newColorsProperty.length-1] = color;
        } else {
            List<Integer> colors = dataSet.getColors();
            Iterator<Integer> iterator = colors.iterator();
            newColorsProperty = new String[colors.size() + 1];
            int i = 0;
            while(iterator.hasNext()){
                Integer obj = iterator.next();
                newColorsProperty[i] = Integer.toHexString(obj);
                i++;
            }
            newColorsProperty[newColorsProperty.length-1] = color;

        }
        if(newColorsProperty != null)
            setProperty("colors", newColorsProperty);

        dataSet.addColor(TiConvert.toColor(color));
    }

    /** ###### ###### OTHER STYLING RELATED METHODS ##### ###### */

    @Kroll.setProperty @Kroll.method
    public void setLabel(String label) {
        dataSet.setLabel(label);
    }

    @Kroll.getProperty @Kroll.method
    public String getLabel() {
        return dataSet.getLabel();
    }

    @Kroll.setProperty @Kroll.method
    public void setHighlightEnabled(Object enabled) {
        dataSet.setHighlightEnabled(TiConvert.toBoolean(enabled, false));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getHighlightEnabled() {
        return dataSet.isHighlightEnabled();
    }

    /*@Override
    public void setValueFormatter(ValueFormatter f) {

        if (f == null)
            return;
        else
            mValueFormatter = f;
    }*/

    /*@Override
    public ValueFormatter getValueFormatter() {
        if (mValueFormatter == null)
            return new DefaultValueFormatter(1);
        return mValueFormatter;
    }*/

    @Kroll.method
    public String getValueTextColor(@Kroll.argument(optional=true) Object colorIndex) {
        int index = 0;
        if(colorIndex != null) {
            index = TiConvert.toInt(colorIndex, 0);
        }
        if(hasProperty("valueTextColors")) {
            String[] colors = (String[])getProperty("valueTextColors");
            if(colors.length == 0)
                return null;
            else
                return colors[index % colors.length];
        } else {
            return Integer.toHexString(dataSet.getValueTextColor(index));
        }
    }


    /**
     * ###### ###### COLOR SETTING RELATED METHODS ##### ######
     */

    /**
     * Sets the colors that should be used fore this DataSet. Colors are reused
     * as soon as the number of Entries the DataSet represents is higher than
     * the size of the colors array. If you are using colors from the resources,
     * make sure that the colors are already prepared (by calling
     * getResources().getColor(...)) before adding them to the DataSet.
     *
     * @param colors
     */
    @Kroll.setProperty @Kroll.method
    public void setValueTextColors(Object colors) {
        List<Integer> colorsArray = null;
        String [] colorsProperty = null;
        if ((colors.getClass().isArray())) {
            colorsProperty = TiConvert.toStringArray((Object[])colors);

        } else {
            colorsProperty = new String[1];
            colorsProperty[0] = TiConvert.toString(colors);
        }

        if(colorsProperty != null) {
            colorsArray = TiConverter.toColorList(colorsProperty);
            setProperty("valueTextColors", colorsProperty);
            dataSet.setValueTextColors(colorsArray);
        }
    }

    @Kroll.setProperty @Kroll.method
    public void setValueTextFont(Object value) {
        if (value instanceof HashMap) {
            HashMap<String, Object> font = (HashMap<String, Object>) value;
            setProperty(TiC.PROPERTY_FONT, font);
            if (font.containsKey(TiC.PROPERTY_FONTFAMILY)) {
                dataSet.setValueTypeface(TiUIHelper.toTypeface(TiApplication.getInstance().getApplicationContext(), TiConvert.toString(font, TiC.PROPERTY_FONTFAMILY)));
            } else if (font.containsKey(TiC.PROPERTY_FONT_FAMILY)) {
                dataSet.setValueTypeface(TiUIHelper.toTypeface(TiApplication.getInstance().getApplicationContext(), TiConvert.toString(font, TiC.PROPERTY_FONT_FAMILY)));
            }
            if (font.containsKey(TiC.PROPERTY_FONTSIZE)) {
                dataSet.setValueTextSize(TiUIHelper.getSize(TiConvert.toString(font, TiC.PROPERTY_FONTSIZE)));
            } else if(font.containsKey(TiC.PROPERTY_FONT_SIZE)) {
                dataSet.setValueTextSize(TiUIHelper.getSize(TiConvert.toString(font, TiC.PROPERTY_FONT_SIZE)));
            }
        }
    }


    @Kroll.setProperty @Kroll.method
    public void setDrawValuesEnabled(Object enabled) {
        dataSet.setDrawValues(TiConvert.toBoolean(enabled, true));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getDrawValuesEnabled() {
        return dataSet.isDrawValuesEnabled();
    }

    @Kroll.setProperty @Kroll.method
    public void setVisible(Object enabled) {
        dataSet.setVisible(TiConvert.toBoolean(enabled, true));
    }

    @Kroll.getProperty @Kroll.method
    public boolean getVisible() {
        return dataSet.isVisible();
    }

    @Kroll.getProperty @Kroll.method
    public String getAxisDependency() {
        return TiConverter.AxisDependencytoString(dataSet.getAxisDependency());
    }

    @Kroll.setProperty @Kroll.method
    public void setAxisDependency(String dependency) {
        dataSet.setAxisDependency(TiConverter.StringtoAxisDependency(dependency));
    }


    /** ###### ###### DATA RELATED METHODS ###### ###### */

    @Kroll.method
    public int getIndexInEntries(int xIndex) {
        return dataSet.getIndexInEntries(xIndex);
    }

    @Kroll.method
    public void removeFirst() {
        dataSet.removeFirst();
    }

    @Kroll.method
    public void removeLast() {
        dataSet.removeLast();
    }

    @Kroll.method
    public void removeEntry(int xIndex) {
        dataSet.removeEntry(xIndex);
    }

    /*
    @Override
    public boolean contains(T e) {

        for(int i = 0; i < getEntryCount(); i++) {
            if(getEntryForIndex(i).equals(e))
                return true;
        }

        return false;
    }*/
}
