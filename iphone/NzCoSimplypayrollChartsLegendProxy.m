//
//  NzCoSimplypayrollChartsLegendProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsLegendProxy.h"

#import "TiUtils.h"

@implementation NzCoSimplypayrollChartsLegendProxy


-(void)_destroy
{
    // This method is called from the dealloc method and is good place to
    // release any objects and memory that have been allocated for the proxy.
    RELEASE_TO_NIL(chartLegend);
    [super _destroy];
}

-(void)setLegend:(ChartLegend*)legend
{
    chartLegend = legend;
}

-(ChartLegend*)getLegend
{
    return chartLegend;
}

-(NSArray<TiColor*> *)colors
{
    NSArray<NSObject*> * colorArray = [chartLegend colorsObjc];
    NSLog(@"[PROXY LIFECYCLE EVENT] colorArray %@", colorArray);
    return [TiConverter toTiColorArray:colorArray];
}

/**
 * returns all the labels the legend uses
 *
 * @return
 */
-(NSArray<NSString*> *)labels
{
    return [TiConverter toStringArray:[chartLegend labelsObjc]];
}

/**
 * Returns the legend-label at the given index.
 *
 * @param index
 * @return
 */
-(NSString*)getLabel:(id)index
{
    return [chartLegend getLabel:[TiUtils intValue:index]];
}

/**
 * colors that will be appended to the end of the colors array after
 * calculating the legend.
 */
-(NSArray<TiColor*> *)extraColors
{
    NSArray<NSObject*> * colorArray = [chartLegend extraColorsObjc];
    NSLog(@"[PROXY LIFECYCLE EVENT] colorArray %@", colorArray);
    return [TiConverter toTiColorArray:colorArray];
}

/**
 * labels that will be appended to the end of the labels array after
 * calculating the legend. a null label will start a group.
 */
-(NSArray<NSString*> *)extraLabels
{
    return [TiConverter toStringArray:[chartLegend extraLabelsObjc]];
}


/**
 * Colors and labels that will be appended to the end of the auto calculated
 * colors and labels arrays after calculating the legend. (if the legend has
 * already been calculated, you will need to call notifyDataSetChanged() to
 * let the changes take effect)
 */

-(void)setExtra:(id)args
{
    enum Args {
        kArgColors = 0,
        kArgLabels,
        kArgCount
    };
    
    // Validate correct number of arguments
    ENSURE_ARG_COUNT(args, kArgCount);
    
    // Use the TiUtils methods to get the values from the arguments
    NSObject *colorArray = [args objectAtIndex:kArgColors];
    NSObject *labelArray = [args objectAtIndex:kArgLabels];
    ENSURE_ARRAY(colorArray);
    ENSURE_ARRAY(labelArray);
    NSArray<UIColor *> * colors = [TiConverter toUIColorArray: (NSArray *)colorArray];
    NSArray<NSString *> * labels = [TiConverter toStringArray:(NSArray *)labelArray];
    return [chartLegend setExtraWithColors:colors labels:labels];
}


/**
 * Sets a custom legend's labels and colors arrays. The colors count should
 * match the labels count. * Each color is for the form drawn at the same
 * index. * A null label will start a group. * A ColorTemplate.COLOR_SKIP
 * color will avoid drawing a form This will disable the feature that
 * automatically calculates the legend labels and colors from the datasets.
 * Call resetCustom() to re-enable automatic calculation (and then
 * notifyDataSetChanged() is needed to auto-calculate the legend again)
 */
-(void)setCustom:(id)args
{
    enum Args {
        kArgColors = 0,
        kArgLabels,
        kArgCount
    };
    
    // Validate correct number of arguments
    ENSURE_ARG_COUNT(args, kArgCount);
    
    // Use the TiUtils methods to get the values from the arguments
    NSObject *colorArray = [args objectAtIndex:kArgColors];
    NSObject *labelArray = [args objectAtIndex:kArgLabels];
    ENSURE_ARRAY(colorArray);
    ENSURE_ARRAY(labelArray);
    NSArray<UIColor *> * colors = [TiConverter toUIColorArray: (NSArray *)colorArray];
    NSArray<NSString *> * labels = [TiConverter toStringArray:(NSArray *)labelArray];
    return [chartLegend setCustomWithColors:colors labels:labels];
}


/**
 * Calling this will disable the custom legend labels (set by
 * setCustom(...)). Instead, the labels will again be calculated
 * automatically (after notifyDataSetChanged() is called).
 */
-(void)setCuresetCustomstom
{
    [chartLegend resetCustom];
}

/**
 * @return true if a custom legend labels and colors has been set default
 *         false (automatic legend)
 */
-(void)isLegendCustom
{
    [chartLegend isLegendCustom];
}

/**
 * returns the position of the legend relative to the chart
 *
 * @return
 */
-(NSString*)position
{
    ChartLegendPosition position = [chartLegend position];
    NSString *txtPosition = @"";
    switch(position) {
        case ChartLegendPositionRightOfChart:
            txtPosition = @"RIGHT_OF_CHART";
            break;
        case ChartLegendPositionRightOfChartCenter:
            txtPosition = @"RIGHT_OF_CHART_CENTER";
            break;
        case ChartLegendPositionRightOfChartInside:
            txtPosition = @"RIGHT_OF_CHART_INSIDE";
            break;
        case ChartLegendPositionLeftOfChart:
            txtPosition = @"LEFT_OF_CHART";
            break;
        case ChartLegendPositionLeftOfChartCenter:
            txtPosition = @"LEFT_OF_CHART_CENTER";
            break;
        case ChartLegendPositionLeftOfChartInside:
            txtPosition = @"LEFT_OF_CHART_INSIDE";
            break;
        case ChartLegendPositionBelowChartLeft:
            txtPosition = @"BELOW_CHART_LEFT";
            break;
        case ChartLegendPositionBelowChartRight:
            txtPosition = @"BELOW_CHART_RIGHT";
            break;
        case ChartLegendPositionBelowChartCenter:
            txtPosition = @"BELOW_CHART_CENTER";
            break;
        case ChartLegendPositionAboveChartLeft:
            txtPosition = @"ABOVE_CHART_LEFT";
            break;
        case ChartLegendPositionAboveChartRight:
            txtPosition = @"ABOVE_CHART_RIGHT";
            break;
        case ChartLegendPositionAboveChartCenter:
            txtPosition = @"ABOVE_CHART_CENTER";
            break;
        case ChartLegendPositionPiechartCenter:
            txtPosition = @"PIECHART_CENTER";
            break;
        default:
            break;
    }
    return txtPosition;
}

/**
 * sets the position of the legend relative to the whole chart
 *
 * @param newValue
 */

-(void)setPosition: (id)value
{
    ENSURE_STRING_OR_NIL(value);
    ChartLegendPosition position = ChartLegendPositionAboveChartLeft;
    if ([value isEqualToString:@"RIGHT_OF_CHART"])
    {
        position = ChartLegendPositionRightOfChart;
    }
    if ([value isEqualToString:@"RIGHT_OF_CHART_CENTER"])
    {
        position = ChartLegendPositionRightOfChartCenter;
    }
    if ([value isEqualToString:@"RIGHT_OF_CHART_INSIDE"])
    {
        position = ChartLegendPositionRightOfChartInside;
    }
    if ([value isEqualToString:@"LEFT_OF_CHART"])
    {
        position = ChartLegendPositionLeftOfChart;
    }
    if ([value isEqualToString:@"LEFT_OF_CHART_CENTER"])
    {
        position = ChartLegendPositionLeftOfChartCenter;
    }
    if ([value isEqualToString:@"LEFT_OF_CHART_INSIDE"])
    {
        position = ChartLegendPositionLeftOfChartInside;
    }
    if ([value isEqualToString:@"BELOW_CHART_LEFT"])
    {
        position = ChartLegendPositionBelowChartLeft;
    }
    if ([value isEqualToString:@"BELOW_CHART_RIGHT"])
    {
        position = ChartLegendPositionBelowChartRight;
    }
    if ([value isEqualToString:@"BELOW_CHART_CENTER"])
    {
        position = ChartLegendPositionBelowChartCenter;
    }
    if ([value isEqualToString:@"ABOVE_CHART_LEFT"])
    {
        position = ChartLegendPositionAboveChartLeft;
    }
    if ([value isEqualToString:@"ABOVE_CHART_RIGHT"])
    {
        position = ChartLegendPositionAboveChartRight;
    }
    if ([value isEqualToString:@"ABOVE_CHART_CENTER"])
    {
        position = ChartLegendPositionAboveChartCenter;
    }
    if ([value isEqualToString:@"PIECHART_CENTER"])
    {
        position = ChartLegendPositionPiechartCenter;
    }

    [chartLegend setPosition:position];
    
}

/**
 * returns the horizontal alignment of the legend
 *
 * @return
 */
/*@Kroll.getProperty @Kroll.method
 public String getHorizontalAlignment() {
 
 LegendHorizontalAlignment position = this.legend.getHorizontalAlignment();
 String txtPosition = "";
 switch(position) {
 case LEFT:
 txtPosition = "LEFT";
 break;
 case CENTER:
 txtPosition = "CENTER";
 break;
 case RIGHT:
 txtPosition = "RIGHT";
 break;
 default:
 break;
 }
 return txtPosition;
 }*/

/**
 * sets the horizontal alignment of the legend
 *
 * @param value
 */
/*@Kroll.setProperty @Kroll.method
 public void setHorizontalAlignment(String value) {
 LegendHorizontalAlignment position = null;
 switch(value) {
 case "LEFT":
 position = LegendHorizontalAlignment.LEFT;
 break;
 case "CENTER":
 position = LegendHorizontalAlignment.CENTER;
 break;
 case "RIGHT":
 position = LegendHorizontalAlignment.RIGHT;
 break;
 default:
 break;
 
 }
 if(position != null)
 this.legend.setHorizontalAlignment(position);
 
 }*/

/**
 * returns the vertical alignment of the legend
 *
 * @return
 */
/* @Kroll.getProperty @Kroll.method
 public String getVerticalAlignment() {
 LegendVerticalAlignment position = this.legend.getVerticalAlignment();
 String txtPosition = "";
 switch(position) {
 case TOP:
 txtPosition = "TOP";
 break;
 case CENTER:
 txtPosition = "CENTER";
 break;
 case BOTTOM:
 txtPosition = "BOTTOM";
 break;
 default:
 break;
 }
 return txtPosition;
 }*/

/**
 * sets the vertical alignment of the legend
 *
 * @param value
 */
/* @Kroll.setProperty @Kroll.method
 public void setVerticalAlignment(String value) {
 LegendVerticalAlignment position = null;
 switch(value) {
 case "TOP":
 position = LegendVerticalAlignment.TOP;
 break;
 case "CENTER":
 position = LegendVerticalAlignment.CENTER;
 break;
 case "BOTTOM":
 position = LegendVerticalAlignment.BOTTOM;
 break;
 default:
 break;
 
 }
 if(position != null)
 this.legend.setVerticalAlignment(position);
 }*/

/**
 * returns the orientation of the legend
 *
 * @return
 */
/* @Kroll.getProperty @Kroll.method
 public String getOrientation() {
 LegendOrientation position = this.legend.getOrientation();
 String txtPosition = "";
 switch(position) {
 case HORIZONTAL:
 txtPosition = "HORIZONTAL";
 break;
 case VERTICAL:
 txtPosition = "VERTICAL";
 break;
 default:
 break;
 }
 return txtPosition;
 }*/

/**
 * sets the orientation of the legend
 *
 * @param value
 */
/*@Kroll.setProperty @Kroll.method
 public void setOrientation(String value) {
 LegendOrientation position = null;
 switch(value) {
 case "HORIZONTAL":
 position = LegendOrientation.HORIZONTAL;
 break;
 case "VERTICAL":
 position = LegendOrientation.VERTICAL;
 break;
 default:
 break;
 
 }
 if(position != null)
 this.legend.setOrientation(position);
 }*/

/**
 * returns whether the legend will draw inside the chart or outside
 *
 * @return
 */
/*@Kroll.method
 public boolean isDrawInsideEnabled() {
 return this.legend.isDrawInsideEnabled();
 }*/

/**
 * sets whether the legend will draw inside the chart or outside
 *
 * @param value
 */
/*@Kroll.setProperty @Kroll.method
 public void setDrawInside(boolean value) {
 this.legend.setDrawInside(value);
 }*/

/**
 * returns the text direction of the legend
 *
 * @return
 */
-(NSString*)direction
{
    ChartLegendDirection direction = [chartLegend direction];
    NSString *txtPosition = @"";
    switch(direction) {
        case ChartLegendDirectionLeftToRight:
            txtPosition = @"LEFT_TO_RIGHT";
            break;
        case ChartLegendDirectionRightToLeft:
            txtPosition = @"RIGHT_TO_LEFT";
            break;
        default:
            break;
    }
    return txtPosition;
}

/**
 * sets the text direction of the legend
 *
 * @param pos
 */
-(void)setDirection: (id)value
{
    ENSURE_STRING_OR_NIL(value);
    ChartLegendDirection position = ChartLegendDirectionLeftToRight;
    if ([value isEqualToString:@"LEFT_TO_RIGHT"])
    {
        position = ChartLegendDirectionLeftToRight;
    }
    if ([value isEqualToString:@"RIGHT_TO_LEFT"])
    {
        position = ChartLegendDirectionRightToLeft;
    }
    [chartLegend setDirection:position];
    
}


/**
 * returns the current form/shape that is set for the legend
 *
 * @return
 */
-(NSString*)form
{
    ChartLegendForm form = [chartLegend form];
    NSString *txtPosition = @"";
    switch(form) {
        case ChartLegendFormSquare:
            txtPosition = @"SQUARE";
            break;
        case ChartLegendFormCircle:
            txtPosition = @"CIRCLE";
            break;
        case ChartLegendFormLine:
            txtPosition = @"LINE";
            break;
        default:
            break;
    }
    return txtPosition;
}

/**
 * sets the form/shape of the legend forms
 *
 * @param shape
 */
-(void)setForm: (id)value
{
    ENSURE_STRING_OR_NIL(value);
    ChartLegendForm position = ChartLegendFormSquare;
    if ([value isEqualToString:@"SQUARE"])
    {
        position = ChartLegendFormSquare;
    }
    if ([value isEqualToString:@"CIRCLE"])
    {
        position = ChartLegendFormCircle;
    }
    if ([value isEqualToString:@"LINE"])
    {
        position = ChartLegendFormLine;
    }
    [chartLegend setForm:position];
    
}

/**
 * sets the size in pixels of the legend forms, this is internally converted
 * in dp, default 8f
 *
 * @param size
 */
-(void)setFormSize: (id)value
{
    CGFloat size = [TiUtils sizeValue: value];
    [chartLegend setFormSize: size];
}


/**
 * returns the size in dp of the legend forms
 *
 * @return
 */
-(NSNumber*)formSize
{
    return [NSNumber numberWithDouble:[chartLegend formSize]];
}

/**
 * returns the space between the legend entries on a horizontal axis in
 * pixels
 *
 * @return
 */
-(NSNumber*)xEntrySpace
{
    return [NSNumber numberWithDouble:[chartLegend xEntrySpace]];
}

/**
 * sets the space between the legend entries on a horizontal axis in pixels,
 * converts to dp internally
 *
 * @param space
 */
-(void)setXEntrySpace: (id)value
{
    CGFloat size = [TiUtils sizeValue: value];
    [chartLegend setXEntrySpace: size];
}

/**
 * returns the space between the legend entries on a vertical axis in pixels
 *
 * @return
 */
-(NSNumber*)yEntrySpace
{
    return [NSNumber numberWithDouble:[chartLegend yEntrySpace]];
}


/**
 * sets the space between the legend entries on a vertical axis in pixels,
 * converts to dp internally
 *
 * @param space
 */
-(void)setYEntrySpace: (id)value
{
    CGFloat size = [TiUtils sizeValue: value];
    [chartLegend setYEntrySpace: size];
}

/**
 * returns the space between the form and the actual label/text
 *
 * @return
 */
-(NSNumber*)formToTextSpace
{
    return [NSNumber numberWithDouble:[chartLegend formToTextSpace]];
}


/**
 * sets the space between the form and the actual label/text, converts to dp
 * internally
 *
 * @param mFormToTextSpace
 */
-(void)setFormToTextSpace: (id)value
{
    CGFloat size = [TiUtils sizeValue: value];
    [chartLegend setFormToTextSpace: size];
}


/**
 * returns the space that is left out between stacked forms (with no label)
 *
 * @return
 */
-(NSNumber*)stackSpace
{
    return [NSNumber numberWithDouble:[chartLegend stackSpace]];
}

/**
 * sets the space that is left out between stacked forms (with no label)
 *
 * @param space
 */
-(void)setStackSpace: (id)value
{
    CGFloat size = [TiUtils sizeValue: value];
    [chartLegend setStackSpace: size];
}

/**
 * calculates the full width the fully drawn legend will use in pixels
 *
 * @return
 */
/*
 @Kroll.getProperty @Kroll.method
 public float getFullWidth(Paint labelpaint) {
 
 float width = 0f;
 
 for (int i = 0; i < mLabels.length; i++) {
 
 // grouped forms have null labels
 if (mLabels[i] != null) {
 
 // make a step to the left
 if (mColors[i] != ColorTemplate.COLOR_SKIP)
 width += mFormSize + mFormToTextSpace;
 
 width += Utils.calcTextWidth(labelpaint, mLabels[i]);
 
 if (i < mLabels.length - 1)
 width += mXEntrySpace;
 } else {
 width += mFormSize;
 if (i < mLabels.length - 1)
 width += mStackSpace;
 }
 }
 
 return width;
 }*/

/**
 * Calculates the full height of the drawn legend.
 *
 * @param mLegendLabelPaint
 * @return
 */
/*@Kroll.getProperty @Kroll.method
 public float getFullHeight(Paint labelpaint) {
 
 float height = 0f;
 
 for (int i = 0; i < mLabels.length; i++) {
 
 // grouped forms have null labels
 if (mLabels[i] != null) {
 
 height += Utils.calcTextHeight(labelpaint, mLabels[i]);
 
 if (i < mLabels.length - 1)
 height += mYEntrySpace;
 }
 }
 
 return height;
 }*/

/**
 * Should the legend word wrap? / this is currently supported only for:
 * BelowChartLeft, BelowChartRight, BelowChartCenter. / note that word
 * wrapping a legend takes a toll on performance. / you may want to set
 * maxSizePercent when word wrapping, to set the point where the text wraps.
 * / default: false
 *
 * @param enabled
 */
-(void)setWordWrapEnabled: (id)value
{
    BOOL size = [TiUtils boolValue: value];
    [chartLegend setWordWrapEnabled: size];
}

/**
 * If this is set, then word wrapping the legend is enabled. This means the
 * legend will not be cut off if too long.
 *
 * @return
 */
-(NSNumber*)wordWrapEnabled
{
    return NUMBOOL([chartLegend isWordWrapEnabled]);
}

/**
 * The maximum relative size out of the whole chart view. / If the legend is
 * to the right/left of the chart, then this affects the width of the
 * legend. / If the legend is to the top/bottom of the chart, then this
 * affects the height of the legend. / If the legend is the center of the
 * piechart, then this defines the size of the rectangular bounds out of the
 * size of the "hole". / default: 0.95f (95%)
 *
 * @return
 */
-(NSNumber*)maxSizePercent
{
    return NUMDOUBLE([chartLegend maxSizePercent]);
}


/**
 * The maximum relative size out of the whole chart view. / If
 * the legend is to the right/left of the chart, then this affects the width
 * of the legend. / If the legend is to the top/bottom of the chart, then
 * this affects the height of the legend. / default: 0.95f (95%)
 *
 * @param maxSize
 */
-(void)setMaxSizePercent: (id)value
{
    CGFloat size = [TiUtils floatValue: value];
    [chartLegend setMaxSizePercent: size];
}

/* @Kroll.getProperty @Kroll.method
 public HashMap[] getCalculatedLabelSizes() {
 return FSizeArraytoHashmapArray(this.legend.getCalculatedLabelSizes());
 }
 
 public static HashMap[] FSizeArraytoHashmapArray(FSize[] inArray) {
 HashMap[] outArray = new HashMap[inArray.length];
 for (int i = 0; i < inArray.length; i++) {
 FSize point = inArray[i];
 HashMap value = new HashMap();
 value.put("width", point.width);
 value.put("height", point.height);
 outArray[i] = value;
 }
 return outArray;
	}*/

/* @Kroll.getProperty @Kroll.method
 public Boolean[] getCalculatedLabelBreakPoints() {
 return this.legend.getCalculatedLabelBreakPoints();
 }*/

/* @Kroll.getProperty @Kroll.method
 public HashMap[] getCalculatedLineSizes() {
 return FSizeArraytoHashmapArray(this.legend.getCalculatedLineSizes());
 }*/

/**
 * Calculates the dimensions of the Legend. This includes the maximum width
 * and height of a single entry, as well as the total width and height of
 * the Legend.
 *
 * @param labelpaint
 */
/*
 public void calculateDimensions(Paint labelpaint, ViewPortHandler viewPortHandler) {
 
 
 }*/

@end