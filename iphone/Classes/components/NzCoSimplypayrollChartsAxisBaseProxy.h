//
//  NzCoSimplypayrollChartsAxisBaseProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#ifndef NzCoSimplypayrollChartsAxisBaseProxy_h
#define NzCoSimplypayrollChartsAxisBaseProxy_h

#import "TiProxy.h"
#import <UIKit/UIKit.h>
#import "Charts-Swift.h"
#import "TiConverter.h"

@interface NzCoSimplypayrollChartsAxisBaseProxy : TiProxy {
    ChartAxisBase *chartAxisBase;
}
-(ChartAxisBase*)getAxisBase;

@end

#endif /* NzCoSimplypayrollChartsAxisBaseProxy_h */
