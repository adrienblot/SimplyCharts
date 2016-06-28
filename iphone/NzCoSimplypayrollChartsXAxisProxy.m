//
//  NzCoSimplypayrollChartsXAxisProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsXAxisProxy.h"

#import "TiUtils.h"

@implementation NzCoSimplypayrollChartsXAxisProxy

-(void) setAxis:(ChartXAxis*) axis
{
    chartAxisBase = axis;
}

-(NSString*)position
{
    XAxisLabelPosition position = [(ChartXAxis*)chartAxisBase labelPosition];
    NSString *txtPosition = @"";
    switch(position) {
        case XAxisLabelPositionBothSided:
            txtPosition = @"BOTH_SIDED";
            break;
        case XAxisLabelPositionBottom:
            txtPosition = @"BOTTOM";
            break;
        case XAxisLabelPositionBottomInside:
            txtPosition = @"BOTTOM_INSIDE";
            break;
        case XAxisLabelPositionTop:
            txtPosition = @"TOP";
            break;
        case XAxisLabelPositionTopInside:
            txtPosition = @"TOP_INSIDE";
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
    XAxisLabelPosition position = XAxisLabelPositionTop;
    if ([value isEqualToString:@"BOTH_SIDED"])
    {
        position = XAxisLabelPositionBothSided;
    }
    if ([value isEqualToString:@"BOTTOM"])
    {
        position = XAxisLabelPositionBottom;
    }
    if ([value isEqualToString:@"BOTTOM_INSIDE"])
    {
        position = XAxisLabelPositionBottomInside;
    }
    if ([value isEqualToString:@"TOP"])
    {
        position = XAxisLabelPositionTop;
    }
    if ([value isEqualToString:@"TOP_INSIDE"])
    {
        position = XAxisLabelPositionTopInside;
    }
    
    [(ChartXAxis*)chartAxisBase setLabelPosition:position];
    
}

-(void)setLabelRotationAngle: (id)value
{
    [(ChartXAxis*)chartAxisBase setLabelRotationAngle: [TiUtils doubleValue: value]];
}

-(NSNumber*)labelRotationAngle
{
    return NUMDOUBLE([(ChartXAxis*)chartAxisBase labelRotationAngle]);
}

-(void)setSpaceBetweenLabels: (id)value
{
    [(ChartXAxis*)chartAxisBase setSpaceBetweenLabels: [TiUtils intValue: value]];
}

-(NSNumber*)spaceBetweenLabels
{
    return NUMLONG([(ChartXAxis*)chartAxisBase spaceBetweenLabels]);
}

-(void)setLabelsToSkip: (id)value
{
    [(ChartXAxis*)chartAxisBase setLabelsToSkip: [TiUtils intValue: value]];
}

-(void)resetLabelsToSkip
{
    [(ChartXAxis*)chartAxisBase resetLabelsToSkip];
}

-(NSNumber*)isAxisModulusCustom
{
    return NUMBOOL([(ChartXAxis*)chartAxisBase isAxisModulusCustom]);
}

-(void)setAvoidFirstLastClipping: (id)value
{
    BOOL enabled = [TiUtils boolValue: value];
    [(ChartXAxis*)chartAxisBase setAvoidFirstLastClippingEnabled: enabled];
}

-(NSNumber*)avoidFirstLastClipping
{
    return NUMBOOL([(ChartXAxis*)chartAxisBase avoidFirstLastClippingEnabled]);
}

-(void)setValues: (id)value
{
    ENSURE_ARRAY(value);
    NSArray<NSString*>* values = [TiConverter toStringArray:value];
    [(ChartXAxis*)chartAxisBase setValuesObjc: values];
}

-(NSArray*)values
{
    return [(ChartXAxis*)chartAxisBase valuesObjc];
}

/**
 * Sets a custom XAxisValueFormatter for the data object that allows custom-formatting
 * of all x-values before rendering them. Provide null to reset back to the
 * default formatting.
 *
 * @param formatter
 */
/*@Kroll.setProperty @Kroll.method
public void setValueFormatter(String formatter) {
    XAxisValueFormatter formatterObject = new DefaultXAxisValueFormatter();
    switch(formatter) {
        case "date":
            formatterObject = new DateXAxisValueFormatter();
            break;
        default:
            break;
    }
    ((XAxis) this.axis).setValueFormatter(formatterObject);
}*/

/**
 * Returns the custom XAxisValueFormatter that is set for this data object.
 * @return
 */
/*@Kroll.getProperty @Kroll.method
public String getValueFormatter() {
    XAxisValueFormatter formatter = ((XAxis) this.axis).getValueFormatter();
    if(formatter instanceof DateXAxisValueFormatter)
        return "date";
    else
        return "default";
}*/

-(NSString*)getLongestLabel
{
    return [(ChartXAxis*)chartAxisBase getLongestLabel];
}

@end