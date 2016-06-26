//
//  ChartDataProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 17/06/16.
//
//

#ifndef NzCoSimplypayrollChartsChartDataProxy_h
#define NzCoSimplypayrollChartsChartDataProxy_h

#import "TiProxy.h"
#import <UIKit/UIKit.h>
#import "Charts-Swift.h"

@interface NzCoSimplypayrollChartsChartDataProxy : TiProxy {
        ChartData *chartData;
}
-(ChartData*)getData;

@end

#endif /* ChartDataProxy_h */
