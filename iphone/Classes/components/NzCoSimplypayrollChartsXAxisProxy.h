//
//  NzCoSimplypayrollChartsXAxisProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#ifndef NzCoSimplypayrollChartsXAxisProxy_h
#define NzCoSimplypayrollChartsXAxisProxy_h

#import "TiProxy.h"
#import <UIKit/UIKit.h>
#import "Charts-Swift.h"
#import "TiConverter.h"
#import "NzCoSimplypayrollChartsAxisBaseProxy.h"

@interface NzCoSimplypayrollChartsXAxisProxy : NzCoSimplypayrollChartsAxisBaseProxy {

}
-(void) setAxis:(ChartXAxis*) axis;

@end

#endif /* NzCoSimplypayrollChartsXAxisProxy_h */
