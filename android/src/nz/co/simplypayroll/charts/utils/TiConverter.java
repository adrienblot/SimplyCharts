package nz.co.simplypayroll.charts.utils;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import org.appcelerator.titanium.util.TiConvert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adrienblot on 29/06/16.
 */
public class TiConverter {

    public static int[] toColorArray(Object[] inArray) {
        int[] outArray = new int[inArray.length];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = TiConvert.toColor(TiConvert.toString(inArray[i]));
        }
        return outArray;
    }

    public static int[] toColorArray(String[] inArray) {
        int[] outArray = new int[inArray.length];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = TiConvert.toColor(inArray[i]);
        }
        return outArray;
    }

    public static List<Integer> toColorList(String[] inArray) {

        List<Integer> outArray = new ArrayList<>();
        for (int i = 0; i < inArray.length; i++) {
            outArray.add(TiConvert.toColor(inArray[i]));
        }
        return outArray;
    }

    public static float[] toFloatArray(Object[] inArray) {
        float[] outArray = new float[inArray.length];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = ((Number) inArray[i]).floatValue();
        }
        return outArray;
    }

    public static String AxisDependencytoString(YAxis.AxisDependency axisDependency) {
        String txtAxisDependency = "";
        switch(axisDependency) {
            case LEFT:
                txtAxisDependency = "left";
                break;
            case RIGHT:
                txtAxisDependency = "right";
                break;
            default:
                break;
        }
        return txtAxisDependency;
    }

    public static YAxis.AxisDependency StringtoAxisDependency(String txtAxisDependency) {
        YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
        txtAxisDependency = txtAxisDependency.toLowerCase();
        switch(txtAxisDependency) {
            case "left":
                axisDependency = YAxis.AxisDependency.LEFT;
                break;
            case "right":
                axisDependency = YAxis.AxisDependency.RIGHT;
                break;
            default:
                break;
        }
        return axisDependency;
    }

    public static LineDataSet.Rounding StringtoRounding(String txtRounding) {
        LineDataSet.Rounding rounding = LineDataSet.Rounding.CLOSEST;
        txtRounding = txtRounding.toLowerCase();
        switch(txtRounding) {
            case "up":
                rounding = LineDataSet.Rounding.UP;
                break;
            case "down":
                rounding = LineDataSet.Rounding.DOWN;
                break;
            default:
                break;
        }
        return rounding;
    }

    public static ArrayList<Entry> toEntryList(Object values)
    {
        ArrayList<Entry> entryList = new ArrayList<Entry>();
        if (values.getClass().isArray()) {
            Object[] valueArray = (Object[])values;

            for (int index=0; index < valueArray.length; index++) {
                Entry entry = toEntry(valueArray[index]);
                if(entry != null) {
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    public static ArrayList<BarEntry> toBarEntryList(Object values)
    {
        ArrayList<BarEntry> entryList = new ArrayList<BarEntry>();
        if (values.getClass().isArray()) {
            Object[] valueArray = (Object[])values;

            for (int index=0; index < valueArray.length; index++) {
                BarEntry entry = toBarEntry(valueArray[index]);
                if(entry != null) {
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    public static  Entry toEntry(Object valueObject)
    {
        Entry entry = null;
        if (valueObject instanceof HashMap) {
            HashMap<String, Object> value = (HashMap<String, Object>)valueObject;
            if(value.containsKey("xIndex")) {
                if(value.containsKey("val")) {
                    if(value.containsKey("data"))
                        entry = new Entry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"), value.get("data"));
                    else
                        entry = new Entry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"));
                }
            }
        }
        return entry;
    }

    public static BarEntry toBarEntry(Object valueObject)
    {
        BarEntry entry = null;
        if (valueObject instanceof HashMap) {
            HashMap<String, Object> value = (HashMap<String, Object>)valueObject;
            if(value.containsKey("xIndex")) {
                if(value.containsKey("vals")) {
                    if(value.containsKey("label"))
                        entry = new BarEntry(TiConverter.toFloatArray((Object[])value.get("vals")), TiConvert.toInt(value, "xIndex"), TiConvert.toString(value, "label"));
                    else
                        entry = new BarEntry(TiConverter.toFloatArray((Object[])value.get("vals")), TiConvert.toInt(value, "xIndex"));
                } else if(value.containsKey("val")) {
                    if(value.containsKey("data"))
                        entry = new BarEntry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"), value.get("data"));
                    else
                        entry = new BarEntry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"));
                }
            }
        }
        return entry;
    }

    public static  HashMap<String, Object> EntryToHashMap(Entry entry)
    {
        HashMap<String, Object> value = new HashMap<String, Object>();
        value.put("xIndex", entry.getXIndex());
        value.put("val", entry.getVal());
        if(entry instanceof BarEntry) {
            float[] vals = ((BarEntry)entry).getVals();
            if(vals == null) {
                value.put("data", entry.getData());
            } else {
                value.put("vals", vals);
                value.put("label", TiConvert.toString(entry.getData()));
            }
        } else {
            value.put("data", entry.getData());
        }

        return value;
    }

}


