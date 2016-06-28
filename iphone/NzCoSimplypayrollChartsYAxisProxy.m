//
//  NzCoSimplypayrollChartsYAxisProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsYAxisProxy.h"

#import "TiUtils.h"

@implementation NzCoSimplypayrollChartsYAxisProxy

-(void) setAxis:(ChartYAxis*) axis
{
    chartAxisBase = axis;
}

-(NSString*)dependency
{
    AxisDependency position = [(ChartYAxis*)chartAxisBase axisDependency];
    NSString *txtPosition = @"";
    switch(position) {
        case AxisDependencyLeft:
            txtPosition = @"LEFT";
            break;
        case AxisDependencyRight:
            txtPosition = @"RIGHT";
            break;
        default:
            break;
    }
    return txtPosition;
}

-(void)setMinWidth: (id)value
{
    [(ChartYAxis*)chartAxisBase setMinWidth: [TiUtils sizeValue: value]];
}

-(NSNumber*)minWidth
{
    return NUMDOUBLE([(ChartYAxis*)chartAxisBase minWidth]);
}


-(void)setMaxWidth: (id)value
{
    [(ChartYAxis*)chartAxisBase setMaxWidth: [TiUtils sizeValue: value]];
}

-(NSNumber*)maxWidth
{
    return NUMDOUBLE([(ChartYAxis*)chartAxisBase maxWidth]);
}


-(void)setGranularityEnabled: (id)value
{
    [(ChartYAxis*)chartAxisBase setGranularityEnabled: [TiUtils boolValue: value]];
}

-(NSNumber*)granularityEnabled
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase granularityEnabled]);
}


-(void)setGranularity: (id)value
{
    [(ChartYAxis*)chartAxisBase setGranularity: [TiUtils floatValue: value]];
}

-(NSNumber*)granularity
{
    return NUMFLOAT([(ChartYAxis*)chartAxisBase granularityEnabled]);
}

-(NSString*)position
{
    YAxisLabelPosition position = [(ChartYAxis*)chartAxisBase labelPosition];
    NSString *txtPosition = @"";
    switch(position) {
        case YAxisLabelPositionInsideChart:
            txtPosition = @"INSIDE_CHART";
            break;
        case YAxisLabelPositionOutsideChart:
            txtPosition = @"OUTSIDE_CHART";
            break;
        default:
            break;
    }
    return txtPosition;
}


/**
 * sets the position of the y-labels
 *
 * @param pos
 */
-(void)setPosition: (id)value
{
    ENSURE_STRING_OR_NIL(value);
    XAxisLabelPosition position = YAxisLabelPositionOutsideChart;
    if ([value isEqualToString:@"OUTSIDE_CHART"])
    {
        position = YAxisLabelPositionOutsideChart;
    }
    if ([value isEqualToString:@"INSIDE_CHART"])
    {
        position = YAxisLabelPositionInsideChart;
    }
    [(ChartYAxis*)chartAxisBase setLabelPosition:position];
    
}

/**
 * returns true if drawing the top y-axis label entry is enabled
 *
 * @return
 */
-(NSNumber*)drawTopYLabelEntryEnabled
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase drawTopYLabelEntryEnabled]);
}


/**
 * set this to true to enable drawing the top y-label entry. Disabling this
 * can be helpful when the top y-label and left x-label interfere with each
 * other. default: true
 *
 * @param enabled
 */
-(void)setDrawTopYLabelEntryEnabled: (id)value
{
    [(ChartYAxis*)chartAxisBase setDrawTopYLabelEntryEnabled: [TiUtils boolValue: value]];
}

/**
 * sets the number of label entries for the y-axis max = 25, min = 2,
 * default: 6, be aware that this number is not fixed (if force == false)
 * and can only be approximated.
 *
 * @param count
 *            the number of y-axis labels that sould be displayed
 * @param force
 *            if enabled, the set label count will be forced, meaning that
 *            the exact specified count of labels will be drawn and evenly
 *            distributed alongside the axis - this might cause labels to
 *            have uneven values
 */
-(void)setLabelCount: (id)value
{
    [(ChartYAxis*)chartAxisBase setLabelCount: [TiUtils intValue: value]];
}

-(void)setForceLabels: (id)value
{
    [(ChartYAxis*)chartAxisBase setForceLabelsEnabled: [TiUtils boolValue: value]];
}

-(NSNumber*)labelCount
{
    return NUMLONG([(ChartYAxis*)chartAxisBase labelCount]);
}

-(NSNumber*)forceLabels
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase forceLabelsEnabled]);
}


/**
 * If enabled, the YLabels will only show the minimum and maximum value of
 * the chart. This will ignore/override the set label count.
 *
 * @param enabled
 */
-(void)setShowOnlyMinMax: (id)value
{
    [(ChartYAxis*)chartAxisBase setShowOnlyMinMaxEnabled: [TiUtils boolValue: value]];
}

-(NSNumber*)showOnlyMinMax
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase showOnlyMinMaxEnabled]);
}


/**
 * If this is set to true, the y-axis is inverted which means that low
 * values are on top of the chart, high values on bottom.
 *
 * @param enabled
 */
-(void)setInverted: (id)value
{
    [(ChartYAxis*)chartAxisBase setInverted: [TiUtils boolValue: value]];
}

-(NSNumber*)inverted
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase inverted]);
}


/**
 * Sets the top axis space in percent of the full range. Default 10f
 *
 * @param percent
 */
-(void)setSpaceTop: (id)value
{
    [(ChartYAxis*)chartAxisBase setSpaceTop: [TiUtils doubleValue: value]];
}

-(NSNumber*)spaceTop
{
    return NUMDOUBLE([(ChartYAxis*)chartAxisBase spaceTop]);
}


/**
 * Sets the bottom axis space in percent of the full range. Default 10f
 *
 * @param percent
 */
-(void)setSpaceBottom: (id)value
{
    [(ChartYAxis*)chartAxisBase setSpaceBottom: [TiUtils doubleValue: value]];
}

-(NSNumber*)spaceBottom
{
    return NUMDOUBLE([(ChartYAxis*)chartAxisBase spaceBottom]);
}


-(void)setDrawZeroLineEnabled: (id)value
{
    [(ChartYAxis*)chartAxisBase setDrawZeroLineEnabled: [TiUtils boolValue: value]];
}

-(NSNumber*)drawZeroLine
{
    return NUMBOOL([(ChartYAxis*)chartAxisBase drawZeroLineEnabled]);
}

-(void)setZeroLineColor: (id)value
{
    
    UIColor* color = [[TiUtils colorValue: value] _color];
    [(ChartYAxis*)chartAxisBase setZeroLineColor: color];
}

-(TiColor*)zeroLineColor
{
    return [[[TiColor alloc] initWithColor:[(ChartYAxis*)chartAxisBase zeroLineColor] name:@"#fff"] autorelease];
}

-(void)setZeroLineWidth: (id)value
{
    [(ChartYAxis*)chartAxisBase setZeroLineWidth: [TiUtils sizeValue: value]];
}

-(NSNumber*)zeroLineWidth
{
    return NUMDOUBLE([(ChartYAxis*)chartAxisBase zeroLineWidth]);
}



/**
 * This is for normal (not horizontal) charts horizontal spacing.
 *
 * @param p
 * @return
 */
/*@Kroll.method
public float getRequiredWidthSpace(Paint p) {
    return ((YAxis) this.axis).getRequiredWidthSpace(p);
}*/

/**
 * This is for HorizontalBarChart vertical spacing.
 *
 * @param p
 * @return
 */
/*@Kroll.method
public float getRequiredHeightSpace(Paint p) {
    return ((YAxis) this.axis).getRequiredHeightSpace(p);
}*/

-(NSString*)getLongestLabel
{
    return [(ChartYAxis*)chartAxisBase getLongestLabel];
}

/**
 * Returns the formatted y-label at the specified index. This will either
 * use the auto-formatter or the custom formatter (if one is set).
 *
 * @param index
 * @return
 */
-(NSString*)getFormattedLabel: (id)args
{

    ENSURE_TYPE(args,NSArray);
    ENSURE_ARG_COUNT(args, 1);
    NSInteger index = [TiUtils intValue:[args objectAtIndex:0]];
    return [(ChartYAxis*)chartAxisBase getFormattedLabel: index];
}

/**
 * Sets the formatter to be used for formatting the axis labels. If no
 * formatter is set, the chart will automatically determine a reasonable
 * formatting (concerning decimals) for all the values that are drawn inside
 * the chart. Use chart.getDefaultValueFormatter() to use the formatter
 * calculated by the chart.
 *
 * @param f
 */
/*@Kroll.setProperty
@Kroll.method
public void setValueFormatter(String formatter) {
    YAxisValueFormatter formatterObject = null;
    switch(formatter) {
        case "largeValue":
            formatterObject = new LargeValueFormatter();
            break;
        case "percent":
            formatterObject = new PercentFormatter();
            break;
        default:
            break;
    }
    if(formatterObject != null)
        ((YAxis) this.axis).setValueFormatter(formatterObject);
}*/

/**
 * Returns the formatter used for formatting the axis labels.
 *
 * @return
 */
/*@Kroll.getProperty
@Kroll.method
public String getValueFormatter() {
    YAxisValueFormatter formatter = ((YAxis) this.axis).getValueFormatter();
    if(formatter instanceof LargeValueFormatter)
        return "largeValue";
    else if(formatter instanceof PercentFormatter)
        return "percent";
    else
        return "default";
}*/


/**
 * If this component has no YAxisValueFormatter or is only equipped with the
 * default one (no custom set), return true.
 *
 * @return
 */
/*@Kroll.method
public boolean needsDefaultFormatter() {
    return ((YAxis) this.axis).needsDefaultFormatter();
}*/

/**
 * Returns true if this axis needs horizontal offset, false if no offset is
 * needed.
 *
 * @return
 */
/*@Kroll.method
public boolean needsOffset() {
    return ((YAxis) this.axis).needsOffset();
}*/

/**
 * Calculates the minimum, maximum, granularity and range values of the
 * YAxis with the given minimum and maximum values from the chart data.
 *
 * @param dataMin
 *            the y-min value according to chart data
 * @param dataMax
 *            the y-max value according to chart data
 */
/*@Kroll.method
	public void calculate(float dataMin, float dataMax) {
 return ((YAxis) this.axis).calculate(dataMin, dataMax);
	}*/

@end