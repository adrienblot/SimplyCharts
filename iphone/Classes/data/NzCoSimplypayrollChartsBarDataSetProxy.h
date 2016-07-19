//
//  BarDataSetProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#ifndef NzCoSimplypayrollChartsBarDataSetProxy_h
#define NzCoSimplypayrollChartsBarDataSetProxy_h

#import "BarLineScatterCandleBubbleDataSetProxy.h"

@interface NzCoSimplypayrollChartsBarDataSetProxy : BarLineScatterCandleBubbleDataSetProxy {
    TiColor *barShadowColor;
    TiColor *barBorderColor;
}

@end

#endif /* NzCoSimplypayrollChartsBarDataSetProxy_h */
