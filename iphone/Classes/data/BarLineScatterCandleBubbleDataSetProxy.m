//
//  BarLineScatterCandleBubbleDataSetProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#import <Foundation/Foundation.h>
#import "BarLineScatterCandleBubbleDataSetProxy.h"

@implementation BarLineScatterCandleBubbleDataSetProxy


-(TiColor *)highLightColor
{
    if(highlightColor == nil) {
        highlightColor = [TiConverter toTiColor: [(BarLineScatterCandleBubbleChartDataSet*)dataSet highlightColor]];
    }
    return highlightColor;
}

-(void)setHighLightColor:(id)newColor
{
    ENSURE_STRING(newColor);
    highlightColor = [TiUtils colorValue:newColor];
    [(BarLineScatterCandleBubbleChartDataSet*)dataSet setHighlightColor:[highlightColor _color]];
}

@end