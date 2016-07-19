//
//  NzCoSimplypayrollChartsLegendProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#ifndef NzCoSimplypayrollChartsLegendProxy_h
#define NzCoSimplypayrollChartsLegendProxy_h

#import "TiProxy.h"
#import <UIKit/UIKit.h>
#import "Charts-Swift.h"
#import "TiConverter.h"

@interface NzCoSimplypayrollChartsLegendProxy : TiProxy {
    ChartLegend *chartLegend;
}
-(ChartLegend*)getLegend;
-(void)setLegend:(ChartLegend*)legend;

@end

#endif /* NzCoSimplypayrollChartsLegendProxy_h */
