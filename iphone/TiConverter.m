//
//  TiConverter.m
//  SimplyCharts
//
//  Created by Adrien Blot on 27/06/16.
//
//

#import <Foundation/Foundation.h>
#import "TiConverter.h"

@implementation TiConverter


+(NSArray<NSString *> *) toStringArray:(NSArray*) inArray
{
    NSMutableArray<NSString *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[TiUtils stringValue:anObject]];
    }
    
    return outArray;
}

+(NSArray<UIColor *> *) toUIColorArray:(NSArray*) inArray
{
    NSMutableArray<UIColor *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[[TiUtils colorValue:anObject] _color] ];
    }
    
    return outArray;
}

+(NSArray<NSNumber *> *) toFloatArray:(NSArray*) inArray
{
    NSMutableArray<NSNumber *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:NUMDOUBLE([TiUtils doubleValue:anObject def:0])];
    }
    
    return outArray;
}

+(NSArray<TiColor *> *) toTiColorArray:(NSArray*) inArray
{
    NSMutableArray<TiColor *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        
        [outArray addObject:[[[TiColor alloc] initWithColor:anObject name:@"#fff"] autorelease]];
    }
    
    return outArray;
}

@end

