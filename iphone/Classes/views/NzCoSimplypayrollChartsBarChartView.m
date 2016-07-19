//
//  NzCoSimplypayrollChartsBarChartView.m
//  SimplyCharts
//
//  Created by Adrien Blot on 24/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsBarChartView.h"

@implementation NzCoSimplypayrollChartsBarChartView

-(void)dealloc
{
    NSLog(@"[VIEW LIFECYCLE EVENT] dealloc");
    
    // Release objects and memory allocated by the view
    RELEASE_TO_NIL(chart);
    [super dealloc];
}

-(void)willMoveToSuperview:(UIView *)newSuperview
{
    NSLog(@"[VIEW LIFECYCLE EVENT] willMoveToSuperview");
}

-(void)initializeState
{
    // This method is called right after allocating the view and
    // is useful for initializing anything specific to the view
    
    [super initializeState];
    
    NSLog(@"[VIEW LIFECYCLE EVENT] initializeState");
}

-(void)configurationSet
{
    // This method is called right after all view properties have
    // been initialized from the view proxy. If the view is dependent
    // upon any properties being initialized then this is the method
    // to implement the dependent functionality.
    
    [super configurationSet];
    
    NSLog(@"[VIEW LIFECYCLE EVENT] configurationSet");
}

-(BarChartView*)chart
{
    // Return the square view. If this is the first time then allocate and
    // initialize it.
    if (chart == nil) {
        NSLog(@"[VIEW LIFECYCLE EVENT] chart");
        
        chart = [[BarChartView alloc] initWithFrame:[self frame]];
        [self addSubview:chart];
    }
    
    return chart;
}


-(void)frameSizeChanged:(CGRect)frame bounds:(CGRect)bounds
{
    // You must implement this method for your view to be sized correctly.
    // This method is called each time the frame / bounds / center changes
    // within Titanium.
    
    NSLog(@"[VIEW LIFECYCLE EVENT] frameSizeChanged");
		  
    if (chart != nil) {
        
        // You must call the special method 'setView:positionRect' against
        // the TiUtils helper class. This method will correctly layout your
        // child view within the correct layout boundaries of the new bounds
        // of your view.
        
        [TiUtils setView:chart positionRect:bounds];
    }
}


-(void)setBackgroundColor_:(id)color
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setBackgroundColor_");
    UIColor *newColor = [[TiUtils colorValue:color] _color];
    BarChartView *sq = [self chart];
    sq.backgroundColor = newColor;
}

-(void)setDescription_:(id)description
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescription_");
    NSString *newDescription = [TiUtils stringValue:description];
    [ [self chart] setDescriptionText: newDescription];
}

-(void)setDescriptionColor_:(id)descriptionColor
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescriptionColor_");
    UIColor *newColor = [[TiUtils colorValue:descriptionColor] _color];
    [[self chart] setDescriptionTextColor: newColor];
}

-(void)setDescriptionPosition_:(id)descriptionPosition
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescriptionPosition_");
    
}

-(void)setDescriptionFont_:(id)descriptionFont
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescriptionFont_");
    ENSURE_DICT(descriptionFont);
    NSDictionary *newdescriptionFont = (NSDictionary*)descriptionFont;
    id fontFamily = [newdescriptionFont valueForKey: @"fontFamily"];
    UIFont *newFont = nil;
    CGFloat pointSize;
    if(fontFamily != nil) {
        newFont = [[TiUtils fontValue:fontFamily] font];
        [[self chart] setDescriptionFont: newFont];
    } else {
        newFont =  [[self chart] descriptionFont];
    }
    
    if(newFont != nil) {
        id fontSize = [newdescriptionFont valueForKey: @"fontSize"];
        if(fontSize != nil) {
            pointSize = [TiUtils floatValue:fontSize];
            newFont = [newFont fontWithSize: pointSize];
        }
        [[self chart] setDescriptionFont: newFont];
    }
    
}


-(void)setNoDataTextDescription_:(id)noDataTextDescription
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setNoDataTextDescription_");
    NSString *newNoDataTextDescription = [TiUtils stringValue:noDataTextDescription];
    [ [self chart] setNoDataText: newNoDataTextDescription];
}

-(void)setDrawGridBackground_:(id)drawGridBackground
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDrawGridBackground_");
    BOOL newDrawGridBackground = [TiUtils boolValue:drawGridBackground];
    [ [self chart] setDrawGridBackgroundEnabled: newDrawGridBackground];
}

-(void)setGridBackgroundColor_:(id)gridBackgroundColor
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setGridBackgroundColor_");
    UIColor *newColor = [[TiUtils colorValue:gridBackgroundColor] _color];
    [ [self chart] setGridBackgroundColor: newColor];
    
}

-(void)setDrawBorders_:(id)drawBorders
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDrawBorders_");
    BOOL newDrawBorders = [TiUtils boolValue:drawBorders];
    [ [self chart] setDrawBordersEnabled: drawBorders];
}

-(void)setBorderColor_:(id)borderColor
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setBorderColor_");
    UIColor *newColor = [[TiUtils colorValue:borderColor] _color];
    [ [self chart] setBorderColor: newColor];
}

-(void)setBorderWidth_:(id)borderWidth
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setBorderWidth_");
    CGFloat newWidth = [TiUtils floatValue:borderWidth];
    [[self chart] setBorderLineWidth: newWidth];
}

-(void)setMaxVisibleValueCount_:(id)maxVisibleValueCount
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setMaxVisibleValueCount_");
    NSInteger newMaxVisibleValueCount = [TiUtils intValue:maxVisibleValueCount];
    [[self chart] setMaxVisibleValueCount: newMaxVisibleValueCount];
}

-(void)setData_:(id)data
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setData_");
    ENSURE_TYPE(data, NzCoSimplypayrollChartsBarDataProxy);
    NSLog(@"[VIEW LIFECYCLE EVENT] setData_ data %@", data);
    NzCoSimplypayrollChartsBarDataProxy *newData = data;
    NSLog(@"[VIEW LIFECYCLE EVENT] setData_ newData %@", newData);
    BarChartData* newBarChartData = (BarChartData*) [newData getData];
    NSLog(@"[VIEW LIFECYCLE EVENT] setData_ newBarChartData %@", newBarChartData);
    if(newBarChartData != nil) {
        BarChartView * barChart = [self chart];
        [ barChart setData: newBarChartData];
        NSLog(@"[VIEW LIFECYCLE EVENT] setData_ data added");
    }
}



typedef void(^RefreshCallback)(ChartDataEntry*,  ChartHighlight*);


-(void)setMarkerView_:(id)viewProxy
{
    ENSURE_TYPE(viewProxy, TiUIViewProxy);
    TiUIView *view = ((TiUIViewProxy*)viewProxy).view;
    BarChartView* barChartView = chart;
    RefreshCallback refreshCallback = ^(ChartDataEntry* entry,  ChartHighlight* highlight) {
        
        if ([view.proxy _hasListeners:@"refresh"]) {
            NSDictionary *value = [NSDictionary dictionaryWithObjectsAndKeys: NUMLONG([entry xIndex]),@"xIndex",NUMDOUBLE([entry value]),@"val",[entry data],@"data",nil];
            NSDictionary *event = [NSDictionary dictionaryWithObjectsAndKeys:value,@"value",nil];
        
            [view.proxy fireEvent:@"refresh" withObject:event];
        }
    };
    ChartMarker *markerView = [[ChartMarker alloc] initWithView: view refreshCallback:refreshCallback ];
    [barChartView setMarker: markerView];
}

/*-(void)setMarkerView_:(id)markerView
{
    
}*/

@end