//
//  ChartDataProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 20/06/16.
//
//

#import <Foundation/Foundation.h>

#import "NzCoSimplypayrollChartsChartDataProxy.h"

#import "TiUtils.h"

@implementation NzCoSimplypayrollChartsChartDataProxy

/**
 * Call this method to let the ChartData know that the underlying data has
 * changed. Calling this performs all necessary recalculations needed when
 * the contained data has changed.
 */
-(void)notifyDataChanged:(id)args
{
    [chartData notifyDataChanged];
}

-(void)_destroy
{
    // This method is called from the dealloc method and is good place to
    // release any objects and memory that have been allocated for the proxy.
    
    [chartData dealloc];
    [super _destroy];
}

-(ChartData*)getData
{
    return chartData;
}

/**
 * Returns the minimum y-value for the specified axis.
 *
 * @param axis
 * @return
 */
/*-(NSNumber *)getYMin:(id)args
{
    id arg = [args objectAtIndex:0];
    if(arg == nil) {
        return NUMDOUBLE([data getYMin]);
    } else {
        ENSURE_STRING(arg);
        AxisDependency axisDependency = -1;
        int found = -1;
        if ([arg isEqualToString:@"LEFT"] || [arg isEqualToString:@"left"]) {
            axisDependency = AxisDependencyLeft;
            found = 1;
        } else if ([arg isEqualToString:@"RIGHT"] || [arg isEqualToString:@"right"]) {
            axisDependency = AxisDependencyRight;
            found = 1;
        }
        
        if(found) {
            return NUMDOUBLE([data getYMin: axisDependency]);
        } else {
            return nil;
        }
    }
}*/

/**
 * Returns the greatest y-value the data object contains.
 *
 * @return
 */
/*-(NSNumber *)getYMin:(id)args
{
    id arg = [args objectAtIndex:0];
    if(arg == nil) {
        return NUMDOUBLE([data getYMax]);
    } else {
        ENSURE_STRING(arg);
        AxisDependency axisDependency = -1;
        int found = -1;
        if ([arg isEqualToString:@"LEFT"] || [arg isEqualToString:@"left"]) {
            axisDependency = AxisDependencyLeft;
            found = 1;
        } else if ([arg isEqualToString:@"RIGHT"] || [arg isEqualToString:@"right"]) {
            axisDependency = AxisDependencyRight;
            found = 1;
        }
        
        if(found) {
            return NUMDOUBLE([data getYMax: axisDependency]);
        } else {
            return nil;
        }
    }
}*/



/**
 * returns the maximum length (in characters) across all values in the
 * x-vals array
 *
 * @return
 */
/*@Kroll.getProperty @Kroll.method
 public float getXValMaximumLength() {
 return mXValMaximumLength;
 }*/

/**
 * Returns the total number of y-values across all DataSet objects the this
 * object represents.
 *
 * @return
 */
/*@Kroll.getProperty @Kroll.method
 public int getYValCount() {
 return mYValCount;
 }*/

/**
 * returns the x-values the chart represents
 *
 * @return
 */
/*-(NSArray *)getXVals:(id)args {
    NSArray *result = [NSArray arrayWithArray:[data xValsObjc]];
    return result;
}*/

/**
 * sets the x-values the chart represents
 *
 */
/*@Kroll.setProperty @Kroll.method
 public void setXVals(Object xVals) {
 if (!(xVals.getClass().isArray())) {
 throw new IllegalArgumentException("xVals must be an arrayof string");
 }
 List<String> listxVals = Arrays.asList(TiConvert.toStringArray((Object[])xVals));
 this.data.setXVals(listxVals);
 }*/

/**
 * Adds a new x-value to the chart data.
 *
 * @param xVal
 */
/*-(void *)addXValue:(id)args
{
    ENSURE_ARG_COUNT(args, 1);
    ENSURE_STRING(args);
    [data addXValue: args];
}*/

/**
 * Removes the x-value at the specified index.
 *
 * @param index
 */
/*-(void *)removeXValue:(id)args
{
    NSInteger f = [TiUtils intValue:args];
    [data removeXValue: f];
}*/

/*public List<T> getDataSets() {
 return mDataSets;
 }*/

/**
 * returns the total number of x-values this ChartData object represents
 * (the size of the x-values array)
 *
 * @return
 */
/*@Kroll.method
public int getXValCount() {
    return this.data.getXValCount();
}*/

/**
 * Get the Entry for a corresponding highlight object
 *
 * @param highlight
 * @return the entry that is highlighted
 */
/*public Entry getEntryForHighlight(Highlight highlight) {
 if (highlight.getDataSetIndex() >= mDataSets.size())
 return null;
 else {
 // The value of the highlighted entry could be NaN -
 //   if we are not interested in highlighting a specific value.
 
 List<?> entries = mDataSets.get(highlight.getDataSetIndex())
 .getEntriesForXIndex(highlight.getXIndex());
 for (Object entry : entries)
 if (((Entry)entry).getVal() == highlight.getValue() ||
 Float.isNaN(highlight.getValue()))
 return (Entry)entry;
 
 return null;
 }
 }*/

/**
 * Returns the DataSet object with the given label. Search can be case
 * sensitive or not. IMPORTANT: This method does calculations at runtime.
 * Use with care in performance critical situations.
 *
 * @param label
 * @param ignorecase
 * @return
 */
/*public T getDataSetByLabel(String label, boolean ignorecase) {
 
 int index = getDataSetIndexByLabel(mDataSets, label, ignorecase);
 
 if (index < 0 || index >= mDataSets.size())
 return null;
 else
 return mDataSets.get(index);
 }
 
 public T getDataSetByIndex(int index) {
 
 if (mDataSets == null || index < 0 || index >= mDataSets.size())
 return null;
 
 return mDataSets.get(index);
 }*/

/**
 * Adds a DataSet dynamically.
 *
 * @param d
 */
/*public void addDataSet(T d) {
 
 if (d == null)
 return;
 
 mYValCount += d.getEntryCount();
 
 if (mDataSets.size() <= 0) {
 
 mYMax = d.getYMax();
 mYMin = d.getYMin();
 
 if (d.getAxisDependency() == AxisDependency.LEFT) {
 
 mLeftAxisMax = d.getYMax();
 mLeftAxisMin = d.getYMin();
 } else {
 mRightAxisMax = d.getYMax();
 mRightAxisMin = d.getYMin();
 }
 } else {
 
 if (mYMax < d.getYMax())
 mYMax = d.getYMax();
 if (mYMin > d.getYMin())
 mYMin = d.getYMin();
 
 if (d.getAxisDependency() == AxisDependency.LEFT) {
 
 if (mLeftAxisMax < d.getYMax())
 mLeftAxisMax = d.getYMax();
 if (mLeftAxisMin > d.getYMin())
 mLeftAxisMin = d.getYMin();
 } else {
 if (mRightAxisMax < d.getYMax())
 mRightAxisMax = d.getYMax();
 if (mRightAxisMin > d.getYMin())
 mRightAxisMin = d.getYMin();
 }
 }
 
 mDataSets.add(d);
 
 handleEmptyAxis(getFirstLeft(), getFirstRight());
 }*/

/**
 * Removes the given DataSet from this data object. Also recalculates all
 * minimum and maximum values. Returns true if a DataSet was removed, false
 * if no DataSet could be removed.
 *
 * @param d
 */
/*public boolean removeDataSet(T d) {
 
 if (d == null)
 return false;
 
 boolean removed = mDataSets.remove(d);
 
 // if a DataSet was removed
 if (removed) {
 
 mYValCount -= d.getEntryCount();
 
 calcMinMax(0, mYValCount);
 }
 
 return removed;
 }*/

/**
 * Removes the DataSet at the given index in the DataSet array from the data
 * object. Also recalculates all minimum and maximum values. Returns true if
 * a DataSet was removed, false if no DataSet could be removed.
 *
 * @param index
 */
/*public boolean removeDataSet(int index) {
 
 if (index >= mDataSets.size() || index < 0)
 return false;
 
 T set = mDataSets.get(index);
 return removeDataSet(set);
 }*/

/**
 * Adds an Entry to the DataSet at the specified index.
 * Entries are added to the end of the list.
 *
 * @param e
 * @param dataSetIndex
 */
/*public void addEntry(Entry e, int dataSetIndex) {
 
 if (mDataSets.size() > dataSetIndex && dataSetIndex >= 0) {
 
 IDataSet set = mDataSets.get(dataSetIndex);
 // add the entry to the dataset
 if (!set.addEntry(e))
 return;
 
 float val = e.getVal();
 
 if (mYValCount == 0) {
 mYMin = val;
 mYMax = val;
 
 if (set.getAxisDependency() == AxisDependency.LEFT) {
 
 mLeftAxisMax = e.getVal();
 mLeftAxisMin = e.getVal();
 } else {
 mRightAxisMax = e.getVal();
 mRightAxisMin = e.getVal();
 }
 } else {
 
 if (mYMax < val)
 mYMax = val;
 if (mYMin > val)
 mYMin = val;
 
 if (set.getAxisDependency() == AxisDependency.LEFT) {
 
 if (mLeftAxisMax < e.getVal())
 mLeftAxisMax = e.getVal();
 if (mLeftAxisMin > e.getVal())
 mLeftAxisMin = e.getVal();
 } else {
 if (mRightAxisMax < e.getVal())
 mRightAxisMax = e.getVal();
 if (mRightAxisMin > e.getVal())
 mRightAxisMin = e.getVal();
 }
 }
 
 mYValCount += 1;
 
 handleEmptyAxis(getFirstLeft(), getFirstRight());
 
 } else {
 Log.e("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
 }
 }*/

/**
 * Removes the given Entry object from the DataSet at the specified index.
 *
 * @param e
 * @param dataSetIndex
 */
/*public boolean removeEntry(Entry e, int dataSetIndex) {
 
 // entry null, outofbounds
 if (e == null || dataSetIndex >= mDataSets.size())
 return false;
 
 IDataSet set = mDataSets.get(dataSetIndex);
 
 if (set != null) {
 // remove the entry from the dataset
 boolean removed = set.removeEntry(e);
 
 if (removed) {
 mYValCount -= 1;
 
 calcMinMax(0, mYValCount);
 }
 
 return removed;
 } else
 return false;
 }*/

/**
 * Removes the Entry object at the given xIndex from the DataSet at the
 * specified index. Returns true if an Entry was removed, false if no Entry
 * was found that meets the specified requirements.
 *
 * @param xIndex
 * @param dataSetIndex
 * @return
 */
/*public boolean removeEntry(int xIndex, int dataSetIndex) {
 
 if (dataSetIndex >= mDataSets.size())
 return false;
 
 IDataSet dataSet = mDataSets.get(dataSetIndex);
 Entry e = dataSet.getEntryForXIndex(xIndex);
 
 if (e == null || e.getXIndex() != xIndex)
 return false;
 
 return removeEntry(e, dataSetIndex);
 }/*
 
 /**
 * Returns the DataSet that contains the provided Entry, or null, if no
 * DataSet contains this Entry.
 *
 * @param e
 * @return
 */
/*public T getDataSetForEntry(Entry e) {
 
 if (e == null)
 return null;
 
 for (int i = 0; i < mDataSets.size(); i++) {
 
 T set = mDataSets.get(i);
 
 for (int j = 0; j < set.getEntryCount(); j++) {
 if (e.equalTo(set.getEntryForXIndex(e.getXIndex())))
 return set;
 }
 }
 
 return null;
 }*/

/**
 * Returns all colors used across all DataSet objects this object
 * represents.
 *
 * @return
 */
/*public int[] getColors() {
 
 if (mDataSets == null)
 return null;
 
 int clrcnt = 0;
 
 for (int i = 0; i < mDataSets.size(); i++) {
 clrcnt += mDataSets.get(i).getColors().size();
 }
 
 int[] colors = new int[clrcnt];
 int cnt = 0;
 
 for (int i = 0; i < mDataSets.size(); i++) {
 
 List<Integer> clrs = mDataSets.get(i).getColors();
 
 for (Integer clr : clrs) {
 colors[cnt] = clr;
 cnt++;
 }
 }
 
 return colors;
 }
 
 public int getIndexOfDataSet(T dataSet) {
 for (int i = 0; i < mDataSets.size(); i++) {
 if (mDataSets.get(i) == dataSet)
 return i;
 }
 
 return -1;
 }*/

/**
 * Returns the first DataSet from the datasets-array that has it's dependency on the left axis.
 * Returns null if no DataSet with left dependency could be found.
 *
 * @return
 */
/*public T getFirstLeft() {
 for (T dataSet : mDataSets) {
 if (dataSet.getAxisDependency() == AxisDependency.LEFT)
 return dataSet;
 }
 
 return null;
 }*/

/**
 * Returns the first DataSet from the datasets-array that has it's dependency on the right axis.
 * Returns null if no DataSet with right dependency could be found.
 *
 * @return
 */
/*public T getFirstRight() {
 for (T dataSet : mDataSets) {
 if (dataSet.getAxisDependency() == AxisDependency.RIGHT)
 return dataSet;
 }
 
 return null;
 }*/

/**
 * Generates an x-values array filled with numbers in range specified by the
 * parameters. Can be used for convenience.
 *
 * @return
 */
/*public static List<String> generateXVals(int from, int to) {
 
 List<String> xvals = new ArrayList<String>();
 
 for (int i = from; i < to; i++) {
 xvals.add("" + i);
 }
 
 return xvals;
 }*/

/**
 * Sets a custom ValueFormatter for all DataSets this data object contains.
 *
 * @param f
 */
/*public void setValueFormatter(ValueFormatter f) {
 if (f == null)
 return;
 else {
 for (IDataSet set : mDataSets) {
 set.setValueFormatter(f);
 }
 }
 }*/

/**
 * Sets the color of the value-text (color in which the value-labels are
 * drawn) for all DataSets this data object contains.
 *
 * @param color
 */
/*public void setValueTextColor(int color) {
 for (IDataSet set : mDataSets) {
 set.setValueTextColor(color);
 }
 }*/

/**
 * Sets the same list of value-colors for all DataSets this
 * data object contains.
 *
 * @param colors
 */
/*public void setValueTextColors(List<Integer> colors) {
 for (IDataSet set : mDataSets) {
 set.setValueTextColors(colors);
 }
 }*/

/**
 * Sets the Typeface for all value-labels for all DataSets this data object
 * contains.
 *
 * @param tf
 */
/*public void setValueTypeface(Typeface tf) {
 for (IDataSet set : mDataSets) {
 set.setValueTypeface(tf);
 }
 }*/

/**
 * Sets the size (in dp) of the value-text for all DataSets this data object
 * contains.
 *
 * @param size
 */
/*public void setValueTextSize(float size) {
 for (IDataSet set : mDataSets) {
 set.setValueTextSize(size);
 }
 }*/

/**
 * Enables / disables drawing values (value-text) for all DataSets this data
 * object contains.
 *
 * @param enabled
 */
/*public void setDrawValues(boolean enabled) {
 for (IDataSet set : mDataSets) {
 set.setDrawValues(enabled);
 }
 }*/

/**
 * Enables / disables highlighting values for all DataSets this data object
 * contains. If set to true, this means that values can
 * be highlighted programmatically or by touch gesture.
 */
/*public void setHighlightEnabled(boolean enabled) {
 for (IDataSet set : mDataSets) {
 set.setHighlightEnabled(enabled);
 }
 }*/

/**
 * Returns true if highlighting of all underlying values is enabled, false
 * if not.
 *
 * @return
 */
/*public boolean isHighlightEnabled() {
 for (IDataSet set : mDataSets) {
 if (!set.isHighlightEnabled())
 return false;
 }
 return true;
 }*/

/**
 * Clears this data object from all DataSets and removes all Entries. Don't
 * forget to invalidate the chart after this.
 */
/*public void clearValues() {
 mDataSets.clear();
 notifyDataChanged();
 }*/

//    /**
//     * Checks if this data object contains the specified Entry. Returns true if
//     * so, false if not. NOTE: Performance is pretty bad on this one, do not
//     * over-use in performance critical situations.
//     *
//     * @param e
//     * @return
//     */
//    public boolean contains(Entry e) {
//
//        for (T set : mDataSets) {
//            if (set.contains(e))
//                return true;
//        }
//
//        return false;
//    }

/**
 * Checks if this data object contains the specified DataSet. Returns true
 * if so, false if not.
 *
 * @param dataSet
 * @return
 */
/*public boolean contains(T dataSet) {
 
 for (T set : mDataSets) {
 if (set.equals(dataSet))
 return true;
 }
 
 return false;
 }*/

@end