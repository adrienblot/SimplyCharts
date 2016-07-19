//
//  NzCoSimplypayrollChartsBarChartViewProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 24/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsBarChartViewProxy.h"


@implementation NzCoSimplypayrollChartsBarChartViewProxy

-(BarChartView*)getChartView
{
    NzCoSimplypayrollChartsBarChartView *chartView = (NzCoSimplypayrollChartsBarChartView *)[self view];
    BarChartView* barChartView = [chartView chart];
}

-(id)getAxisLeft:(id)args
{
    BarChartView* barChartView = [self getChartView];
    NzCoSimplypayrollChartsYAxisProxy * axis = nil;
    if(barChartView != nil) {
        NzCoSimplypayrollChartsYAxisProxy * axis = [[NzCoSimplypayrollChartsYAxisProxy alloc] init];
        [axis setAxis: [barChartView leftAxis]];
    }
    return axis;
}

-(id)getAxisRight:(id)args
{
    BarChartView* barChartView = [self getChartView];
    NzCoSimplypayrollChartsYAxisProxy * axis = nil;
    if(barChartView != nil) {
        axis = [[NzCoSimplypayrollChartsYAxisProxy alloc] init];
        [axis setAxis: [barChartView rightAxis]];
    }
    return axis;
}

-(id)getXAxis:(id)args
{
    BarChartView* barChartView = [self getChartView];
    NzCoSimplypayrollChartsXAxisProxy * axis = nil;
    if(barChartView != nil) {
        axis = [[NzCoSimplypayrollChartsXAxisProxy alloc] init];
        [axis setAxis: [barChartView xAxis]];
    }
    return axis;
}

-(id)getLegend:(id)args
{
    BarChartView* barChartView = [self getChartView];
    NzCoSimplypayrollChartsLegendProxy * axis = nil;
    if(barChartView != nil) {
        axis = [[NzCoSimplypayrollChartsLegendProxy alloc] init];
        [axis setLegend: [barChartView legend]];
    }
    return axis;
}



@end