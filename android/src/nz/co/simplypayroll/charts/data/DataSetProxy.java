package nz.co.simplypayroll.charts.data;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.TiConvert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nz.co.simplypayroll.charts.utils.TiConverter;

/**
 * Created by adrienblot on 29/06/16.
 */
@Kroll.proxy
public class DataSetProxy extends BaseDataSetProxy {

    public DataSetProxy() {
        super();
    }

    @Kroll.method
    public void calcMinMax(int start, int end) {
        ((DataSet)dataSet).calcMinMax(start, end);
    }


    @Kroll.method
    public int getEntryCount() {
        return ((DataSet)dataSet).getEntryCount();
    }

    @Kroll.method
    public String toString() {
        return ((DataSet)dataSet).toString();
    }

    /**
     * Returns a simple string representation of the DataSet with the type and
     * the number of Entries.
     *
     * @return
     */
    @Kroll.method
    public String toSimpleString() {
        return ((DataSet)dataSet).toSimpleString();
    }

    @Kroll.getProperty @Kroll.method
    public float getYMin() {
        return ((DataSet)dataSet).getYMin();
    }

    @Kroll.getProperty @Kroll.method
    public float getYMax() {
        return ((DataSet)dataSet).getYMax();
    }

    @Kroll.method
    public void clear() {
        ((DataSet)dataSet).clear();
    }

    @Kroll.method
    public void removeEntry(Object e) {
        Entry entry = TiConverter.toEntry(e);
        List<Entry> yVals = ((DataSet)dataSet).getYVals();
        if(yVals != null && entry != null) {
            Iterator<Entry> iterator = yVals.iterator();
            Entry entryToRemove = null;
            while(iterator.hasNext()){
                Entry obj = iterator.next();
                if(obj.equalTo(entry)) {
                    entryToRemove = obj;
                    break;
                }
            }
            if(entryToRemove != null) {
                ((DataSet)dataSet).removeEntry(entryToRemove);
            }
        }
    }

    @Kroll.method
    public int getEntryIndex(Object e) {
        Entry entry = TiConverter.toEntry(e);
        List<Entry> yVals = ((DataSet)dataSet).getYVals();
        if(yVals != null && entry != null) {
            Iterator<Entry> iterator = yVals.iterator();
            int i=0;
            boolean found = false;
            while(iterator.hasNext()){
                Entry obj = iterator.next();
                if(obj.equalTo(entry)) {
                    found = true;
                    break;
                }
                i++;
            }

            if(found) {
                return i;
            }
        }
        return -1;
    }

    @Kroll.method
    public Object getEntryForXIndex(int xIndex, @Kroll.argument(optional=true) Object rounding) {
        DataSet.Rounding position = DataSet.Rounding.CLOSEST;
        if(rounding != null) {
            position = TiConverter.StringtoRounding(TiConvert.toString(rounding));
        }

        return TiConverter.EntryToHashMap(((DataSet)dataSet).getEntryForXIndex(xIndex, position));
    }

    @Kroll.method
    public Object getEntryForIndex(int Index) {
        return TiConverter.EntryToHashMap(((DataSet)dataSet).getEntryForIndex(Index));
    }

    @Kroll.method
    public int getEntryIndexForXIndex(int xIndex, @Kroll.argument(optional=true) Object roundingText) {
        LineDataSet.Rounding rounding = null;
        if(roundingText != null) {
            rounding = TiConverter.StringtoRounding(TiConvert.toString(roundingText));
        }

        return ((DataSet)dataSet).getEntryIndex(xIndex, rounding);
    }

    @Kroll.method
    public float getYValForXIndex(int xIndex) {
        return ((DataSet)dataSet).getYValForXIndex(xIndex);
    }

    @Kroll.method
    public float[] getYValsForXIndex(int xIndex) {
        return ((DataSet)dataSet).getYValsForXIndex(xIndex);
    }

    /**
     * Returns all Entry objects at the given xIndex. INFORMATION: This method
     * does calculations at runtime. Do not over-use in performance critical
     * situations.
     *
     * @param xIndex
     * @return
     */
    @Kroll.method
    public Object[] getEntriesForXIndex(int xIndex) {

        List<Entry> entries = ((DataSet)dataSet).getEntriesForXIndex(xIndex);
        Object[] result = new Object[entries.size()];

        Iterator<Entry> iterator = entries.iterator();
        int i = 0;
        while(iterator.hasNext()){
            result[i] = TiConverter.EntryToHashMap(iterator.next());
        }
        return result;
    }

    @Kroll.setProperty @Kroll.method
    public void setYVals(Object yVals) {
        if(dataSet instanceof LineDataSet) {
            ((DataSet) dataSet).setYVals(TiConverter.toEntryList(yVals));
        } else if(dataSet instanceof BarDataSet) {
            ((DataSet) dataSet).setYVals(TiConverter.toBarEntryList(yVals));
        }
    }

    @Kroll.getProperty @Kroll.method
    public Object[] getYVals() {
        List<Entry> entries = ((DataSet)dataSet).getYVals();
        Object[] result = new Object[entries.size()];

        Iterator<Entry> iterator = entries.iterator();
        int i = 0;
        while(iterator.hasNext()){
            result[i] = TiConverter.EntryToHashMap(iterator.next());
        }
        return result;
    }

    @Kroll.method
    public void addEntryOrdered(Object e) {
        Entry entry = null;
        if(dataSet instanceof LineDataSet) {
            entry = TiConverter.toEntry(e);

        } else if(dataSet instanceof BarDataSet) {
            entry = TiConverter.toBarEntry(e);
        }
        if(entry != null) {
            ((DataSet)dataSet).addEntryOrdered(entry);
        }
    }

    @Kroll.method
    public void addEntry(Object e) {
        Entry entry = null;
        if(dataSet instanceof LineDataSet) {
            entry = TiConverter.toEntry(e);

        } else if(dataSet instanceof BarDataSet) {
            entry = TiConverter.toBarEntry(e);
        }
        if(entry != null) {
            ((DataSet)dataSet).addEntry(entry);
        }
    }


    /*@Override
    public int getEntryIndex(Entry e) {
        return mYVals.indexOf(e);
    }*/

    /*@Override
    public T getEntryForXIndex(int xIndex, Rounding rounding) {

        int index = getEntryIndex(xIndex, rounding);
        if (index > -1)
            return mYVals.get(index);
        return null;
    }

    @Override
    public T getEntryForXIndex(int xIndex) {
        return getEntryForXIndex(xIndex, Rounding.CLOSEST);
    }

    @Override
    public T getEntryForIndex(int index) {
        return mYVals.get(index);
    }

    @Override
    public int getEntryIndex(int xIndex, Rounding rounding) {

        int low = 0;
        int high = mYVals.size() - 1;
        int closest = -1;

        while (low <= high) {
            int m = (high + low) / 2;

            if (xIndex == mYVals.get(m).getXIndex()) {
                while (m > 0 && mYVals.get(m - 1).getXIndex() == xIndex)
                    m--;

                return m;
            }

            if (xIndex > mYVals.get(m).getXIndex())
                low = m + 1;
            else
                high = m - 1;

            closest = m;
        }

        if (closest != -1) {
            int closestXIndex = mYVals.get(closest).getXIndex();
            if (rounding == Rounding.UP) {
                if (closestXIndex < xIndex && closest < mYVals.size() - 1) {
                    ++closest;
                }
            } else if (rounding == Rounding.DOWN) {
                if (closestXIndex > xIndex && closest > 0) {
                    --closest;
                }
            }
        }

        return closest;
    }

    @Override
    public float getYValForXIndex(int xIndex) {

        Entry e = getEntryForXIndex(xIndex);

        if (e != null && e.getXIndex() == xIndex)
            return e.getVal();
        else
            return Float.NaN;
    }

    @Override
    public float[] getYValsForXIndex(int xIndex) {

        List<T> entries = getEntriesForXIndex(xIndex);

        float[] yVals = new float[entries.size()];
        int i = 0;

        for (T e : entries)
            yVals[i++] = e.getVal();

        return yVals;
    }

    /**
     * Returns all Entry objects at the given xIndex. INFORMATION: This method
     * does calculations at runtime. Do not over-use in performance critical
     * situations.
     *
     * @param xIndex
     * @return
     */
   /* @Override
    public List<T> getEntriesForXIndex(int xIndex) {

        List<T> entries = new ArrayList<T>();

        int low = 0;
        int high = mYVals.size() - 1;

        while (low <= high) {
            int m = (high + low) / 2;
            T entry = mYVals.get(m);

            if (xIndex == entry.getXIndex()) {
                while (m > 0 && mYVals.get(m - 1).getXIndex() == xIndex)
                    m--;

                high = mYVals.size();
                for (; m < high; m++) {
                    entry = mYVals.get(m);
                    if (entry.getXIndex() == xIndex) {
                        entries.add(entry);
                    } else {
                        break;
                    }
                }

                break;
            } else {
                if (xIndex > entry.getXIndex())
                    low = m + 1;
                else
                    high = m - 1;
            }
        }

        return entries;
    }*/
}
