//
//  NzCoSimplypayrollChartsAxisBaseProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsAxisBaseProxy.h"

#import "TiUtils.h"

@implementation NzCoSimplypayrollChartsAxisBaseProxy
-(void)_destroy
{
    // This method is called from the dealloc method and is good place to
    // release any objects and memory that have been allocated for the proxy.
    
    RELEASE_TO_NIL(chartAxisBase);
    [super _destroy];
}

-(void)setAxisBase:(ChartAxisBase*)axisBase
{
    chartAxisBase = axisBase;
}

-(ChartAxisBase*)getAxisBase
{
    return chartAxisBase;
}


-(void)setEnabled: (id)value
{
    BOOL enabled = [TiUtils boolValue: value];
    [chartAxisBase setEnabled: enabled];
}

-(NSNumber*)enabled
{
    return NUMBOOL([chartAxisBase enabled]);
}

-(void)setDrawLabelsEnabled: (id)value
{
    BOOL enabled = [TiUtils boolValue: value];
    [chartAxisBase setDrawLabelsEnabled: enabled];
}

-(NSNumber*)drawLabelsEnabled
{
    return NUMBOOL([chartAxisBase drawLabelsEnabled]);
}

-(void)setDrawAxisLineEnabled: (id)value
{
    BOOL enabled = [TiUtils boolValue: value];
    [chartAxisBase setDrawAxisLineEnabled: enabled];
}

-(NSNumber*)drawAxisLineEnabled
{
    return NUMBOOL([chartAxisBase drawAxisLineEnabled]);
}

-(void)setDrawGridLinesEnabled: (id)value
{
    BOOL enabled = [TiUtils boolValue: value];
    [chartAxisBase setDrawGridLinesEnabled: enabled];
}

-(NSNumber*)drawGridLinesEnabled
{
    return NUMBOOL([chartAxisBase drawGridLinesEnabled]);
}

-(void)setTextColor: (id)value
{
    UIColor* color = [[TiUtils colorValue: value] _color];
    [chartAxisBase setLabelTextColor: color];
}

-(TiColor*)textColor
{
    return [[[TiColor alloc] initWithColor:[chartAxisBase labelTextColor] name:@"#fff"] autorelease];
}

-(void)setLabelFont: (id)value
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescriptionFont_");
    ENSURE_DICT(value);
    NSDictionary *newdescriptionFont = (NSDictionary*)value;
    id fontFamily = [newdescriptionFont valueForKey: @"fontFamily"];
    UIFont *newFont = nil;
    CGFloat pointSize;
    if(fontFamily != nil) {
        newFont = [[TiUtils fontValue:fontFamily] font];
        [chartAxisBase setLabelFont: newFont];
    } else {
        newFont =  [chartAxisBase labelFont];
    }
    
    if(newFont != nil) {
        id fontSize = [newdescriptionFont valueForKey: @"fontSize"];
        if(fontSize != nil) {
            pointSize = [TiUtils sizeValue:fontSize];
            newFont = [newFont fontWithSize: pointSize];
        }
        [chartAxisBase setLabelFont: newFont];
    }
}

-(NSDictionary*)labelFont
{
    UIFont* font = [chartAxisBase labelFont];
    NSDictionary* fontDict = [[NSDictionary alloc] initWithObjectsAndKeys: [font fontName], @"fontFamily", NUMDOUBLE([font pointSize]), @"fontSize", nil];
    return fontDict;
}

-(void)setGridColor: (id)value
{
    
    UIColor* color = [[TiUtils colorValue: value] _color];
    [chartAxisBase setGridColor: color];
}

-(TiColor*)gridColor
{
    return [[[TiColor alloc] initWithColor:[chartAxisBase gridColor] name:@"#fff"] autorelease];
}


-(void)setGridLineWidth: (id)value
{
    [chartAxisBase setGridLineWidth: [TiUtils sizeValue: value]];
}

-(NSNumber*)gridLineWidth
{
    return NUMDOUBLE([chartAxisBase gridLineWidth]);
}

-(void)setAxisLineColor: (id)color
{
    UIColor* newcolor = [[TiUtils colorValue: color] _color];
    [chartAxisBase setLabelTextColor: newcolor];
}

-(TiColor*)axisLineColor
{
    return [[[TiColor alloc] initWithColor:[chartAxisBase axisLineColor] name:@"#fff"] autorelease];
}

-(void)setAxisLineWidth: (id)value
{
    [chartAxisBase setAxisLineWidth: [TiUtils sizeValue: value]];
}

-(NSNumber*)axisLineWidth
{
    return NUMDOUBLE([chartAxisBase axisLineWidth]);
}

@end