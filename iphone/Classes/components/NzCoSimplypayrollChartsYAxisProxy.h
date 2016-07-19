//
//  NzCoSimplypayrollChartsYAxisProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#ifndef NzCoSimplypayrollChartsYAxisProxy_h
#define NzCoSimplypayrollChartsYAxisProxy_h

#import "TiProxy.h"
#import <UIKit/UIKit.h>
#import "Charts-Swift.h"
#import "TiConverter.h"
#import "NzCoSimplypayrollChartsAxisBaseProxy.h"

@interface NzCoSimplypayrollChartsYAxisProxy : NzCoSimplypayrollChartsAxisBaseProxy {

}
-(void) setAxis:(ChartYAxis*) axis;

@end

#endif /* NzCoSimplypayrollChartsYAxisProxy_h */
