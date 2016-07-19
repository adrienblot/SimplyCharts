//
//  TiConverter.h
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#ifndef TiConverter_h
#define TiConverter_h

#import "TiUtils.h"
#import "Charts-Swift.h"


@interface TiConverter : NSObject {
    
}

+(NSMutableArray<NSString *> *) toStringArray:(NSArray*) inArray;
+(NSMutableArray<UIColor *> *) toUIColorArray:(NSArray*) inArray;
+(NSMutableArray<NSNumber *> *) toFloatArray:(NSArray*) inArray;
+(NSMutableArray<TiColor *> *) toTiColorArray:(NSArray*) inArray;
+(NSString *)hexStringFromColor:(UIColor *)color;
+(TiColor *) toTiColor:(id) inColor;
+(NSMutableArray<NSString *> *) toHexStringArray:(NSArray<UIColor *>*) inArray;

+(NSString*)axisDependencytoString: (AxisDependency)position;
+(AxisDependency)stringtoAxisDependency: (NSString *)value;
+(ChartDataSetRounding)stringToRounding: (NSString *)value;

+(ChartDataEntry*)toEntry:(id)valueObject;
+(BarChartDataEntry*)toBarEntry:(id)valueObject;
+(NSDictionary *)entryToDict:(ChartDataEntry*)entry;
+(NSArray<BarChartDataEntry*>*)toBarEntryList:(id)values;
+(NSArray<ChartDataEntry*>*)toEntryList:(id)values;

@end
#endif /* TiConverter_h */
