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


@interface TiConverter : NSObject {
    
}

+(NSArray<NSString *> *) toStringArray:(NSArray*) inArray;
+(NSArray<UIColor *> *) toUIColorArray:(NSArray*) inArray;
+(NSArray<NSNumber *> *) toFloatArray:(NSArray*) inArray;
+(NSArray<TiColor *> *) toTiColorArray:(NSArray*) inArray;

@end
#endif /* TiConverter_h */
