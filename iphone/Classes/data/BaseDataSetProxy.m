//
//  BaseDataSetProxy.m
//  SimplyCharts
//
//  Created by Adrien Blot on 30/06/16.
//
//

#import <Foundation/Foundation.h>
#import "BaseDataSetProxy.h"

static int nextProxyId = 1;

@implementation BaseDataSetProxy

-(id)init
{
    // This is the designated initializer method and will always be called
    // when the proxy is created.
    
    // Generate a unique identifier so the user can see which proxy
    // instance is being created or destroyed (for demonstration purposes only).
    proxyId = nextProxyId++;
    
    NSLog(@"[PROXY LIFECYCLE EVENT] init with proxy id of %d", proxyId);
    
    return [super init];
}

-(id)initWithDataSet: (ChartBaseDataSet*)newdataSet
{
    dataSet = newdataSet;
    colors = [TiConverter toTiColorArray: [dataSet colors]];
    valueColors = [TiConverter toTiColorArray: [dataSet valueColors]];
    return [self init];
}

-(void)_destroy
{
    // This method is called from the dealloc method and is good place to
    // release any objects and memory that have been allocated for the proxy.
    RELEASE_TO_NIL(dataSet);
    [super _destroy];
}

-(ChartBaseDataSet*)getDataSet
{
    return dataSet;
}

-(id)notifyDataSetChanged
{
    [dataSet notifyDataSetChanged];
}


/**
 * ###### ###### COLOR GETTING RELATED METHODS ##### ######
 */

-(NSArray<TiColor *> *)Colors
{
    if(colors == nil) {
        colors = [TiConverter toTiColorArray: [dataSet colors]];
    }
    return colors;
}

-(NSArray<TiColor *> *) valueTextColors
{
    if(valueColors == nil) {
        valueColors = [TiConverter toTiColorArray: [dataSet valueColors]];
    }
    return valueColors;
}

-(TiColor*) getColor:(id)args
{
    ENSURE_ARRAY(args);
    int index = ([args count] > 0) ? [TiUtils intValue:[args objectAtIndex:0]] : 0;
    return colors[index % [colors count]];

}


/**
 * ###### ###### COLOR SETTING RELATED METHODS ##### ######
 */

/**
 * Sets the colors that should be used fore this DataSet. Colors are reused
 * as soon as the number of Entries the DataSet represents is higher than
 * the size of the colors array. If you are using colors from the resources,
 * make sure that the colors are already prepared (by calling
 * getResources().getColor(...)) before adding them to the DataSet.
 *
 * @param colors
 */
-(void)setColors:(id)newColors
{
    ENSURE_ARRAY(newColors);
    colors = [TiConverter toTiColorArray: (NSArray *)newColors];
    [dataSet setColors:[TiConverter toUIColorArray: colors]];
}


/**
 * Adds a new color to the colors array of the DataSet.
 *
 * @param color
 */
-(void)addColor:(id)args
{
    enum Args {
        kArgColor = 0,
        kArgCount
    };
    
    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    TiColor *color = [TiUtils colorValue:[args objectAtIndex:kArgColor]];
    [colors addObject: color];
    [dataSet addColor: [color _color]];
}

/** ###### ###### OTHER STYLING RELATED METHODS ##### ###### */

-(void)setLabel:(id)label
{
    ENSURE_STRING(label);
    [dataSet setLabel:[TiUtils stringValue:label]];
}

-(NSString *)label
{
    return [dataSet label];
}

-(void)setHighlightEnabled:(id)enabled
{
    [dataSet setHighlightEnabled:[TiUtils boolValue:enabled]];
}

-(NSNumber *)highlightEnabled
{
    return NUMBOOL([dataSet highlightEnabled]);
}


/*@Override
 public void setValueFormatter(ValueFormatter f) {
 
 if (f == null)
 return;
 else
 mValueFormatter = f;
 }*/

/*@Override
 public ValueFormatter getValueFormatter() {
 if (mValueFormatter == null)
 return new DefaultValueFormatter(1);
 return mValueFormatter;
 }*/

-(TiColor*) getValueTextColor:(id)args
{
    ENSURE_ARRAY(args);
    int index = ([args count] > 0) ? [TiUtils intValue:[args objectAtIndex:0]] : 0;
    return valueColors[index % [valueColors count]];
    
}



/**
 * ###### ###### COLOR SETTING RELATED METHODS ##### ######
 */

/**
 * Sets the colors that should be used fore this DataSet. Colors are reused
 * as soon as the number of Entries the DataSet represents is higher than
 * the size of the colors array. If you are using colors from the resources,
 * make sure that the colors are already prepared (by calling
 * getResources().getColor(...)) before adding them to the DataSet.
 *
 * @param colors
 */
-(void)setValueTextColors:(id)newColors
{
    ENSURE_ARRAY(newColors);
    valueColors = [TiConverter toTiColorArray: (NSArray *)newColors];
    [dataSet setValueColors:[TiConverter toUIColorArray: colors]];
}

-(void)setValueTextFont: (id)value
{
    NSLog(@"[VIEW LIFECYCLE EVENT] setDescriptionFont_");
    ENSURE_DICT(value);
    NSDictionary *newdescriptionFont = (NSDictionary*)value;
    id fontFamily = [newdescriptionFont valueForKey: @"fontFamily"];
    UIFont *newFont = nil;
    CGFloat pointSize;
    if(fontFamily != nil) {
        newFont = [[TiUtils fontValue:fontFamily] font];
        [dataSet setValueFont: newFont];
    } else {
        newFont =  [dataSet valueFont];
    }
    
    if(newFont != nil) {
        id fontSize = [newdescriptionFont valueForKey: @"fontSize"];
        if(fontSize != nil) {
            pointSize = [TiUtils sizeValue:fontSize];
            newFont = [newFont fontWithSize: pointSize];
        }
        [dataSet setValueFont: newFont];
    }
}

-(NSDictionary*)valueTextFont
{
    UIFont* font = [dataSet valueFont];
    NSDictionary* fontDict = [[NSDictionary alloc] initWithObjectsAndKeys: [font fontName], @"fontFamily", NUMDOUBLE([font pointSize]), @"fontSize", nil];
    return fontDict;
}


-(void)setDrawValuesEnabled:(id)enabled
{
    [dataSet setDrawValuesEnabled:[TiUtils boolValue:enabled]];
}

-(NSNumber *)drawValuesEnabled
{
    return NUMBOOL([dataSet drawValuesEnabled]);
}

-(void)setVisible:(id)enabled
{
    [dataSet setVisible:[TiUtils boolValue:enabled]];
}

-(NSNumber *)visible
{
    return NUMBOOL([dataSet visible]);
}


-(NSString *)axisDependency
{
    return [TiConverter axisDependencytoString: [dataSet axisDependency]];
}

-(void)setAxisDependency:(id)dependency
{
    ENSURE_STRING(dependency);
    [dataSet setAxisDependency:[TiConverter stringtoAxisDependency:[TiUtils stringValue:dependency]]];
}


/** ###### ###### DATA RELATED METHODS ###### ###### */

-(void)removeFirst:(id)args
{
    [dataSet removeFirst];
}

-(void)removeLast:(id)args
{
    [dataSet removeLast];
}

-(void)removeEntry:(id)args
{
    enum Args {
        kArgXIndex = 0,
        kArgCount
    };

    ENSURE_ARRAY(args);
    ENSURE_ARG_COUNT(args, kArgCount);
    int xIndex = [TiUtils intValue:[args objectAtIndex:kArgXIndex]];
    [dataSet removeEntry: xIndex];
}

/*
 @Override
 public boolean contains(T e) {
 
 for(int i = 0; i < getEntryCount(); i++) {
 if(getEntryForIndex(i).equals(e))
 return true;
 }
 
 return false;
 }*/


@end