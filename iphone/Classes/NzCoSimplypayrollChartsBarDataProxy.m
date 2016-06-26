//
//  BarDataProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 20/06/16.
//
//

#import <Foundation/Foundation.h>
#import "NzCoSimplypayrollChartsBarDataProxy.h"


@implementation NzCoSimplypayrollChartsBarDataProxy



-(void)_initWithProperties:(NSDictionary *)properties
{
    // This method is called from _initWithPageContext if arguments have been
    // used to create the proxy. It is called after the initializers have completed
    // and is a good point to process arguments that have been passed to the
    // proxy create method since most of the initialization has been completed
    // at this point.
    
    NSLog(@"[PROXY LIFECYCLE EVENT] _initWithProperties %@", properties);
    NSArray<NSString *> * xValsArray = nil;
    NSMutableArray<BarChartDataSet *> * dataSets = nil;
    
    id xVals = [properties valueForKey: @"xVals"];
    if(xVals != nil) {
        ENSURE_ARRAY(xVals);
        xValsArray = [self toStringArray: (NSArray *)xVals];
    }
    
    id dataSet = [properties valueForKey: @"data"];
    if(dataSet != nil) {
        ENSURE_DICT(dataSet);
        NSDictionary *result = (NSDictionary*)dataSet;
        dataSets = [[NSMutableArray alloc] init];
        NSEnumerator *enumerator = [result keyEnumerator];
        id key;
        while ((key = [enumerator nextObject])) {
            id dataSetObject = [result objectForKey: key];
            ENSURE_DICT(dataSetObject);
            NSDictionary *dataSetDict = (NSDictionary*)dataSetObject;
            NSString* keyLabel = [TiUtils stringValue:key];
            id values = [dataSetObject valueForKey: @"values"];
            if(values != nil) {
                id color = [dataSetObject valueForKey: @"color"];
                if(color != nil) {
                    BarChartDataSet * dataSet = [self createDataSet:keyLabel values:values];
                    [dataSets addObject:[self createDataSet:keyLabel values:values color:color ]];
                    
                } else {
                    id colors = [dataSetObject valueForKey: @"colors"];
                    if(colors != nil) {
                        ENSURE_ARRAY(colors);
                        BarChartDataSet * dataSet = [self createDataSet:keyLabel values:values];
                        [dataSets addObject:[self createDataSet:keyLabel values:values colors:colors ]];
                    } else {
                        BarChartDataSet * dataSet = [self createDataSet:keyLabel values:values];
                        [dataSets addObject:[self createDataSet:keyLabel values:values]];
                    }
                }
            }
        }
    }
    NSLog(@"[PROXY LIFECYCLE EVENT] dataSets created %@", dataSets);
    if(xValsArray == nil && dataSets == nil) {
        NSLog(@"1");
        chartData = [[BarChartData alloc] init];
    } else if(dataSets == nil) {
        NSLog(@"2");
        chartData = [[BarChartData alloc] initWithXVals:xValsArray];
    } else if(xValsArray != nil){
        NSLog(@"3");
        chartData = [[BarChartData alloc] initWithXVals:xValsArray dataSets: dataSets];
    }
    NSLog(@"[PROXY LIFECYCLE EVENT] data created %@", chartData);
    [super _initWithProperties:properties];
}

-(NSArray<NSString *> *) toStringArray:(NSArray*) inArray
{
    NSMutableArray<NSString *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[TiUtils stringValue:anObject]];
    }
    
    return outArray;
}

/*-(void)setData:(id)value
{
    ENSURE_DICT(value);
    NSDictionary *result = (NSDictionary*)value;
    NSMutableArray<BarChartDataSet *> * dataArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [result keyEnumerator];
    id key;
    while ((key = [enumerator nextObject])) {
        id dataSetObject = [result objectForKey: key];
        ENSURE_DICT(dataSetObject);
        NSDictionary *dataSetDict = (NSDictionary*)dataSetObject;
        id values = [result valueForKey: @"values"];
        if(values != nil) {
            id color = [result valueForKey: @"color"];
            if(color != nil) {
                ENSURE_STRING(color);
                
            } else {
                id colors = [result valueForKey: @"colors"];
                if(colors != nil) {
                    ENSURE_STRING(colors);
                    
                } else {
                    NSString* keyLabel = [TiUtils stringValue:key];
                    BarChartDataSet * dataSet = [self createDataSet:keyLabel values:values];
                    [dataArray addObject:[self createDataSet:[TiUtils stringValue:key] values:values]];
                }
            }
        }
    }
}*/

-(BarChartDataSet*)createDataSet:(NSString*)label values:(id)values
{
    NSLog(@"[PROXY LIFECYCLE EVENT] dataSet values %@ ", values);
    NSArray<BarChartDataEntry*>* YVals = [self createEntryList: values];
    BarChartDataSet* result = [[BarChartDataSet alloc] initWithYVals:YVals label:label];
    NSLog(@"[PROXY LIFECYCLE EVENT] dataSet created %@ label %@", result, label);
    return result;
}

-(BarChartDataSet*)createDataSet:(NSString*)label values:(id)values color:(id)color
{
    NSArray<BarChartDataEntry*>* YVals = [self createEntryList: values];
    BarChartDataSet* result = [[BarChartDataSet alloc] initWithYVals:YVals label: label];
    UIColor *newColor = [[TiUtils colorValue:color] _color];
    [result setColor: newColor];
    return result;
}

-(BarChartDataSet*)createDataSet:(NSString*)label values:(id)values colors:(id)colors
{
    NSArray<BarChartDataEntry*>* YVals = [self createEntryList: values];
    BarChartDataSet* result = [[BarChartDataSet alloc] initWithYVals:YVals label: label];
    [result setColors: [self toColorArray: colors]];
    return result;
}

-(NSArray<UIColor *> *) toColorArray:(NSArray*) inArray
{
    NSMutableArray<UIColor *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:[[TiUtils colorValue:anObject] _color] ];
    }
    
    return outArray;
}

-(NSArray<BarChartDataEntry*>*)createEntryList:(id)values
{
    ENSURE_ARRAY(values);
    NSArray* valueArray = (NSArray*)values;
    NSMutableArray<BarChartDataEntry*>* YVals = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [valueArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        BarChartDataEntry* entry = [self createEntry:anObject];
        if(entry != nil) {
            [YVals addObject:entry];
        }
    }
    NSLog(@"[PROXY LIFECYCLE EVENT] YVals created %@", YVals);
    return YVals;
}

-(BarChartDataEntry*)createEntry:(id)valueObject
{
    BarChartDataEntry* entry = nil;
    ENSURE_DICT(valueObject);
    NSDictionary *value = (NSDictionary*)valueObject;
    id xIndex = [value valueForKey: @"xIndex"];
    if(xIndex != nil) {
        NSLog(@"[PROXY LIFECYCLE EVENT] xIndex created %@", xIndex);
        id val = [value valueForKey: @"val"];
        if(val != nil) {
            NSLog(@"[PROXY LIFECYCLE EVENT] val created %@", val);
            id dataObject = [value valueForKey: @"data"];
            if(dataObject != nil) {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0] data: dataObject];
            } else {
                entry = [[BarChartDataEntry alloc] initWithValue: [TiUtils doubleValue:val def:0] xIndex: [TiUtils intValue:xIndex def:0]];
            }
        } else {
            id vals = [value valueForKey: @"vals"];
            if(vals != nil) {
                ENSURE_ARRAY(vals);
                NSArray<NSNumber *> * valArray = [self toFloatArray:vals];
                id label = [value valueForKey: @"label"];
                if(label != nil) {
                    ENSURE_STRING(label);
                    entry = [[BarChartDataEntry alloc] initWithValues: valArray xIndex: [TiUtils intValue:xIndex def:0] label: [TiUtils stringValue:label]];
                } else {
                    entry = [[BarChartDataEntry alloc] initWithValues: valArray xIndex: [TiUtils intValue:xIndex def:0]];
                }
            }
        }
    }
    NSLog(@"[PROXY LIFECYCLE EVENT] entry created %@", entry);
    return entry;
}

-(NSArray<NSNumber *> *) toFloatArray:(NSArray*) inArray
{
    NSMutableArray<NSNumber *> * outArray = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [inArray objectEnumerator];
    id anObject;
    while (anObject = [enumerator nextObject]) {
        [outArray addObject:NUMDOUBLE([TiUtils doubleValue:anObject def:0])];
    }

    return outArray;
}


@end