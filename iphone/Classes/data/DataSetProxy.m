//
//  DataSetProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#import <Foundation/Foundation.h>
#import "DataSetProxy.h"

@implementation DataSetProxy

-(void)calcMinMax:(id)args
{
    enum Args {
        kArgStart = 0,
        kArgEnd,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int start = [TiUtils intValue:[args objectAtIndex:kArgStart]];
    int end = [TiUtils intValue:[args objectAtIndex:kArgEnd]];
    [(ChartDataSet*)dataSet calcMinMaxWithStart:start end:end];
}

-(NSNumber *)getEntryCount:(id)args
{
    return NUMLONG([(ChartDataSet*)dataSet entryCount]);
}

-(NSNumber *)getYMin:(id)args
{
    return NUMDOUBLE([(ChartDataSet*)dataSet yMin]);
}

-(NSNumber *)getYMax:(id)args
{
    return NUMDOUBLE([(ChartDataSet*)dataSet yMax]);
}

-(void)clear:(id)args
{
    [(ChartDataSet*)dataSet clear];
}

-(void)removeEntry:(id)args
{
    enum Args {
        kArgEntry = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    id entry = [TiUtils intValue:[args objectAtIndex:kArgEntry]];
    ENSURE_DICT(entry);
    ChartDataEntry* dataEntry = [TiConverter toEntry:entry];
    NSArray<ChartDataEntry *> * yVals = [(ChartDataSet*)dataSet yVals];
    if(yVals != nil && dataEntry != nil) {
        NSEnumerator *enumerator = [yVals objectEnumerator];
        ChartDataEntry* entryToRemove = nil;
        ChartDataEntry* anObject;
        while (anObject = [enumerator nextObject]) {
            if([anObject isEqual:dataEntry]) {
                entryToRemove = anObject;
                break;
            }
        }
        if(entryToRemove != nil) {
            [(ChartDataSet*)dataSet removeEntry: entryToRemove];
        }
    }
}

-(int)getEntryIndex:(id)args
{
    enum Args {
        kArgEntry = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    id entry = [args objectAtIndex:kArgEntry];
    ENSURE_DICT(entry);
    ChartDataEntry* dataEntry = [TiConverter toEntry:entry];
    NSArray<ChartDataEntry *> * yVals = [(ChartDataSet*)dataSet yVals];
    if(yVals != nil && dataEntry != nil) {
        NSEnumerator *enumerator = [yVals objectEnumerator];
        ChartDataEntry* anObject;
        int index = 0;
        BOOL found = false;
        while (anObject = [enumerator nextObject]) {
            if([anObject isEqual:dataEntry]) {
                found = true;
                break;
            }
            index++;
        }
        if(found) {
            return NUMINT(index);
        }
    }
    return NUMINT(-1);
}

-(NSDictionary*)getEntryForXIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount,
        kArgRounding = kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int xIndex = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    NSString *txtRounding = ([args count] > kArgRounding) ? [TiUtils stringValue:[args objectAtIndex:kArgRounding]] : nil;
    ChartDataSetRounding rounding = ChartDataSetRoundingClosest;
    if(txtRounding != nil) {
        rounding = [TiConverter stringToRounding: txtRounding];
    }
    return [TiConverter entryToDict:[(ChartDataSet*)dataSet entryForXIndex:xIndex rounding:rounding]];
}

-(NSDictionary*)getEntryForIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int index = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    return [TiConverter entryToDict:[(ChartDataSet*)dataSet entryForIndex:index]];
}

-(NSDictionary*)getEntryIndexForXIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount,
        kArgRounding = kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int xIndex = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    NSString *txtRounding = ([args count] > kArgRounding) ? [TiUtils stringValue:[args objectAtIndex:kArgRounding]] : nil;
    ChartDataSetRounding rounding = ChartDataSetRoundingClosest;
    if(txtRounding != nil) {
        rounding = [TiConverter stringToRounding: txtRounding];
    }
    return [TiConverter entryToDict:[(ChartDataSet*)dataSet entryIndexWithXIndex:xIndex rounding:rounding]];
}

-(NSNumber*)getYValForXIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int index = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    return NUMDOUBLE([(ChartDataSet*)dataSet yValForXIndex:index]);
}

-(NSArray<NSNumber*> *)getYValsForXIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int index = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    return [(ChartDataSet*)dataSet yValsForXIndex:index];
}


/**
 * Returns all Entry objects at the given xIndex. INFORMATION: This method
 * does calculations at runtime. Do not over-use in performance critical
 * situations.
 *
 * @param xIndex
 * @return
 */

-(NSArray*)getEntriesForXIndex:(id)args
{
    enum Args {
        kArgIndex = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int index = [TiUtils intValue:[args objectAtIndex:kArgIndex]];
    NSArray<ChartDataEntry*>* entries = [(ChartDataSet*)dataSet entriesForXIndex:index];
    NSMutableArray<NSDictionary *> * result = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [entries objectEnumerator];
    ChartDataEntry* anObject;
    while (anObject = [enumerator nextObject]) {
        [result addObject:[TiConverter entryToDict:anObject]];
    }
    return result;
}

-(void)setYVals:(id)yVals
{
    ENSURE_ARRAY(yVals);
    if ([dataSet isKindOfClass:[LineChartDataSet class]]) {
        [(ChartDataSet*)dataSet setYVals:[TiConverter toEntryList: yVals]];
    } else if([dataSet isKindOfClass:[BarChartDataSet class]]) {
        [(ChartDataSet*)dataSet setYVals:[TiConverter toBarEntryList: yVals]];
    }
}

-(NSArray<NSDictionary *> *)yVals
{
    NSArray<ChartDataEntry*>* entries = [(ChartDataSet*)dataSet yVals];
    NSMutableArray<NSDictionary *> * result = [[NSMutableArray alloc] init];
    NSEnumerator *enumerator = [entries objectEnumerator];
    ChartDataEntry* anObject;
    while (anObject = [enumerator nextObject]) {
        [result addObject:[TiConverter entryToDict:anObject]];
    }

    return result;
}


-(void)addEntryOrdered:(id)args
{
    enum Args {
        kArgEntry = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    ChartDataEntry* entry = nil;
    id entryArg = [args objectAtIndex:kArgEntry];
    
    if ([dataSet isKindOfClass:[LineChartDataSet class]]) {
        entry = [TiConverter toEntry: entryArg];
    } else if([dataSet isKindOfClass:[BarChartDataSet class]]) {
        entry = [TiConverter toBarEntry: entryArg];
    }
    [(ChartDataSet*)dataSet addEntryOrdered:entry];
}

-(void)addEntry:(id)args
{
    enum Args {
        kArgEntry = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    ChartDataEntry* entry = nil;
    id entryArg = [args objectAtIndex:kArgEntry];
    
    if ([dataSet isKindOfClass:[LineChartDataSet class]]) {
        entry = [TiConverter toEntry: entryArg];
    } else if([dataSet isKindOfClass:[BarChartDataSet class]]) {
        entry = [TiConverter toBarEntry: entryArg];
    }
    [(ChartDataSet*)dataSet addEntry:entry];
}

@end
