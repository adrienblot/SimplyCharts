//
//  BarDataSetProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsBarDataSetProxy.h"

@implementation NzCoSimplypayrollChartsBarDataSetProxy

-(void)_initWithProperties:(NSDictionary *)properties
{
    // This method is called from _initWithPageContext if arguments have been
    // used to create the proxy. It is called after the initializers have completed
    // and is a good point to process arguments that have been passed to the
    // proxy create method since most of the initialization has been completed
    // at this point.
    
    NSLog(@"[PROXY LIFECYCLE EVENT] _initWithProperties %@", properties);
    NSArray<BarChartDataEntry *> * yValsArray = nil;
    NSString* label;
    id yVals = [properties valueForKey: @"yVals"];
    if(yVals != nil) {
        ENSURE_ARRAY(yVals);
        yValsArray = [TiConverter toBarEntryList: (NSArray *)yVals];
    }
    
    id labelObj = [properties valueForKey: @"label"];
    if(labelObj != nil) {
        ENSURE_STRING(yVals);
        label = [TiUtils stringValue: labelObj];
    }
    
    if(yValsArray != nil && label!= nil) {
        dataSet = [[BarChartDataSet alloc] initWithYVals: yVals label: label];
    } else if(yValsArray != nil) {
        dataSet = [[BarChartDataSet alloc] initWithYVals: yVals];
    } else if(label!= nil) {
        dataSet = [[BarChartDataSet alloc] initWithLabel: label];
    } else {
        dataSet = [[BarChartDataSet alloc] init];
    }
    [super _initWithProperties:properties];
}


-(void)calcMinMax:(id)args
{
    enum Args {
        kArgStart = 0,
        kArgEnd,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int start = [TiUtils intValue:[args objectAtIndex:kArgStart]];
    int end = [TiUtils intValue:[args objectAtIndex:kArgEnd]];
    [(BarChartDataSet*)dataSet calcMinMaxWithStart:start end:end];
}

-(NSNumber*)getStackSize:(id)args
{
    return NUMLONG([(BarChartDataSet*)dataSet stackSize]);
}

-(NSNumber*)isStacked:(id)args
{
    return NUMBOOL([(BarChartDataSet*)dataSet isStacked]);
}

-(NSNumber*)getEntryCountStacks:(id)args
{
    return NUMLONG([(BarChartDataSet*)dataSet entryCountStacks]);
}

-(NSNumber*)barSpacePercent
{
    return NUMFLOAT([(BarChartDataSet*)dataSet barSpace]);
}

-(void)setBarSpace:(id)space
{
    [(BarChartDataSet*)dataSet setBarSpace:[TiUtils floatValue: space]];
}

-(TiColor *)barShadowColor
{
    if(barShadowColor == nil) {
        barShadowColor = [TiConverter toTiColor: [(BarChartDataSet*)dataSet barShadowColor]];
    }
    return barShadowColor;
}

-(void)setBarShadowColor:(id)newColor
{
    ENSURE_STRING(newColor);
    barShadowColor = [TiUtils colorValue:newColor];
    [(BarChartDataSet*)dataSet setBarShadowColor:[barShadowColor _color]];
}


-(NSNumber*)barBorderWidth
{
    return NUMFLOAT([(BarChartDataSet*)dataSet barSpace]);
}

-(void)setBarBorderWidth:(id)width
{
    [(BarChartDataSet*)dataSet setBarBorderWidth:[TiUtils floatValue: width]];
}

-(TiColor *)barBorderColor
{
    if(barBorderColor == nil) {
        barBorderColor = [TiConverter toTiColor: [(BarChartDataSet*)dataSet barBorderColor]];
    }
    return barBorderColor;
}

-(void)setBarBorderColor:(id)newColor
{
    ENSURE_STRING(newColor);
    barBorderColor = [TiUtils colorValue:newColor];
    [(BarChartDataSet*)dataSet setBarBorderColor:[barBorderColor _color]];
}

-(NSNumber*)highLightAlpha
{
    return NUMFLOAT([(BarChartDataSet*)dataSet highlightAlpha]);
}

-(void)setHighLightAlpha:(id)alpha
{
    [(BarChartDataSet*)dataSet setHighlightAlpha:[TiUtils intValue: alpha]];
}

-(NSArray*)stackLabels
{
    return [(BarChartDataSet*)dataSet stackLabels];
}

-(void)setStackLabels:(id)labels
{
    ENSURE_ARRAY(labels)
    [(BarChartDataSet*)dataSet setStackLabels:[TiConverter toStringArray:(NSArray*)labels]];
}


@end