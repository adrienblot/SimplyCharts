//
//  NzCoSimplypayrollChartsBarChartView.h
//  SimplyCharts
//
//  Created by Adrien Blot on 24/06/16.
//
//

#ifndef NzCoSimplypayrollChartsBarChartView_h
#define NzCoSimplypayrollChartsBarChartView_h

#import "Charts-Swift.h"
#import "NzCoSimplypayrollChartsBarDataProxy.h"
#import "TiUIView.h"
#import "TiUIViewProxy.h"

@interface NzCoSimplypayrollChartsBarChartView: TiUIView
{
    BarChartView *chart;
}
-(BarChartView*)chart;
@end 
#endif /* NzCoSimplypayrollChartsBarChartView_h */
