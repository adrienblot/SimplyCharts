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

+(NSString*)axisDependencytoString: (AxisDependency)position
{
    NSString *txtPosition = @"";
    switch(position) {
        case AxisDependencyLeft:
            txtPosition = @"left";
            break;
        case AxisDependencyRight:
            txtPosition = @"right";
            break;
        default:
            break;
    }
    return txtPosition;
}

+(AxisDependency)stringtoAxisDependency: (NSString *)value
{
    AxisDependency position = AxisDependencyLeft;
    value = [value lowercaseString];
    if ([value isEqualToString:@"left"])
    {
        position = AxisDependencyLeft;
    }
    if ([value isEqualToString:@"right"])
    {
        position = AxisDependencyRight;
    }    
    return position;
}

+(ChartDataSetRounding)stringToRounding: (NSString *)value
{
    ChartDataSetRounding position = ChartDataSetRoundingClosest;
    value = [value lowercaseString];
    if ([value isEqualToString:@"closest"])
    {
        position = ChartDataSetRoundingClosest;
    }
    if ([value isEqualToString:@"up"])
    {
        position = ChartDataSetRoundingUp;
    }
    if ([value isEqualToString:@"down"])
    {
        position = ChartDataSetRoundingDown;
    }
    return position;
}

+(NSMutableArray<NSString *> *) toStringArray:(NSArray*) inArray
{
    NSMutableArray<NSString *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[TiUtils stringValue:anObject]];
    }
    
    return outArray;
}

+(NSMutableArray<UIColor *> *) toUIColorArray:(NSArray*) inArray
{
    NSMutableArray<UIColor *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        if ([anObject isKindOfClass:[TiColor class]]) {
            [outArray addObject:[anObject _color]];
        } else {
            [outArray addObject:[[TiUtils colorValue:anObject] _color] ];
        }
        
    }
    
    return outArray;
}

+(NSMutableArray<NSString *> *) toHexStringArray:(NSArray<UIColor *>*) inArray
{
    NSMutableArray<NSString *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    UIColor * anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[self hexStringFromColor: anObject]];
    }
    return outArray;
}

+(NSMutableArray<NSNumber *> *) toFloatArray:(NSArray*) inArray
{
    NSMutableArray<NSNumber *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:NUMDOUBLE([TiUtils doubleValue:anObject def:0])];
    }
    
    return outArray;
}

+(NSMutableArray<TiColor *> *) toTiColorArray:(NSArray*) inArray
{
    NSMutableArray<TiColor *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        TiColor* color = [TiConverter toTiColor:anObject];
        if(color != nil){
            [outArray addObject:color];
        }
    }
    return outArray;
}

+(TiColor *) toTiColor:(id) inColor
{
    TiColor *outColor = nil;
    if ([inColor isKindOfClass:[UIColor class]]) {
        outColor = [[[TiColor alloc] initWithColor:inColor name:[self hexStringFromColor: inColor]] autorelease];
    } else {
        outColor = [TiUtils colorValue:inColor];
    }
    
    return outColor;
}

+ (NSString *)hexStringFromColor:(UIColor *)color {
    const CGFloat *components = CGColorGetComponents(color.CGColor);
    
    CGFloat r = components[0];
    CGFloat g = components[1];
    CGFloat b = components[2];
    
    return [NSString stringWithFormat:@"#%02lX%02lX%02lX",
            lroundf(r * 255),
            lroundf(g * 255),
            lroundf(b * 255)];
}

+(ChartDataEntry*)toEntry:(id)valueObject
{
    ChartDataEntry* entry = nil;
    NSDictionary *value = (NSDictionary*)valueObject;
    id xIndex = [value valueForKey: @"xIndex"];
    if(xIndex != nil) {
        id val = [value valueForKey: @"val"];
        if(val != nil) {
            id dataObject = [value valueForKey: @"data"];
            if(dataObject != nil) {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0] data: dataObject];
            } else {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0]];
            }
        }
    }
    return entry;
}

+(BarChartDataEntry*)toBarEntry:(id)valueObject
{
    BarChartDataEntry* entry = nil;
    NSDictionary *value = (NSDictionary*)valueObject;
    id xIndex = [value valueForKey: @"xIndex"];
    if(xIndex != nil) {
        id val = [value valueForKey: @"val"];
        if(val != nil) {
            id dataObject = [value valueForKey: @"data"];
            if(dataObject != nil) {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0] data: dataObject];
            } else {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0]];
            }
        } else {
            id vals = [value valueForKey: @"vals"];
            if(vals != nil) {
                NSArray<NSNumber *> * valArray = [TiConverter toFloatArray:vals];
                id label = [value valueForKey: @"label"];
                if(label != nil) {
                    entry = [[BarChartDataEntry alloc] initWithValues: valArray xIndex: [TiUtils intValue:xIndex def:0] label: [TiUtils stringValue:label]];
                } else {
                    entry = [[BarChartDataEntry alloc] initWithValues: valArray xIndex: [TiUtils intValue:xIndex def:0]];
                }
            }
        }

    }
    return entry;
}


+(NSDictionary *)entryToDict:(ChartDataEntry*)entry
{
    NSMutableDictionary *value = [NSMutableDictionary dictionaryWithObjectsAndKeys: NUMLONG([entry xIndex]),@"xIndex",NUMDOUBLE([entry value]),@"val",nil];
    id xIndex = [value valueForKey: @"xIndex"];
    if([entry isKindOfClass:[BarChartDataEntry class]]) {
        NSArray<NSNumber*> * vals = [(BarChartDataEntry*)entry values];
        if(vals == nil) {
            [value setObject: [entry data] forKey: @"data"];
        } else {
            [value setObject: vals forKey: @"vals"];
            [value setObject: [TiUtils stringValue: [entry data]] forKey: @"label"];
        }
    } else {
        [value setObject: [entry data] forKey: @"data"];
    }

    return value;
}

+(NSArray<BarChartDataEntry*>*)toBarEntryList:(id)values
{
    NSArray* valueArray = (NSArray*)values;
    NSMutableArray<BarChartDataEntry*>* YVals = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [valueArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        BarChartDataEntry* entry = [TiConverter toBarEntry:anObject];
        if(entry != nil) {
            [YVals addObject:entry];
        }
    }
    return YVals;
}

+(NSArray<ChartDataEntry*>*)toEntryList:(id)values
{
    NSArray* valueArray = (NSArray*)values;
    NSMutableArray<ChartDataEntry*>* YVals = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [valueArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        ChartDataEntry* entry = [TiConverter toEntry:anObject];
        if(entry != nil) {
            [YVals addObject:entry];
        }
    }
    return YVals;
}


@end

