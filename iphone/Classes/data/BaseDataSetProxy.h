//
//  BaseDataSetProxy.h
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#ifndef BaseDataSetProxy_h
#define BaseDataSetProxy_h

#import "Charts-Swift.h"
#import "TiProxy.h"
#import "TiConverter.h"

@interface BaseDataSetProxy : TiProxy {
    ChartBaseDataSet *dataSet;
    int proxyId;
    NSMutableArray<TiColor *> *colors;
    NSMutableArray<TiColor *> *valueColors;
}
-(ChartBaseDataSet*)getDataSet;

@end

#endif /* BaseDataSetProxy_h */
