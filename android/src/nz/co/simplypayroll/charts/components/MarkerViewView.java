/**
  * Appcelerator Titanium Mobile
  * Copyright (c) 2009-2012 by Appcelerator, Inc. All Rights Reserved.
  * Licensed under the terms of the Apache Public License
  * Please see the LICENSE included with this distribution for details.
  *
  */
package nz.co.simplypayroll.charts.views;

import java.util.HashMap;

import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;
import android.view.View;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;

import nz.co.simplypayroll.charts.data.BarDataProxy;

public class BarChartView extends TiUIView 
{
	// Standard Debugging variables
	private static final String LCAT = "TiChartsModule";
	
	private static final String PROPERTY_COLOR = "color";
	private static final String PROPERTY_BACKGROUND_COLOR = "backgroundColor";
	private static final String PROPERTY_DESCRIPTION = "description";
	private static final String PROPERTY_DESCRIPTION_COLOR = "descriptionColor";
	private static final String PROPERTY_DESCRIPTION_POSITION = "descriptionPosition";
	private static final String PROPERTY_DESCRIPTION_FONT = "descriptionFont";
	private static final String PROPERTY_FONT_FAMILY = "fontFamily";
	private static final String PROPERTY_FONT_SIZE = "fontSize";
	private static final String PROPERTY_DESCRIPTION_TEXT_SIZE = "descriptionFontSize";
	private static final String PROPERTY_NO_DATA_TEXT_DESCRIPTION = "noDataTextDescription";
	private static final String PROPERTY_DRAW_GRID_BACKGROUND = "drawGridBackground";
	private static final String PROPERTY_GRID_BACKGROUND_COLOR = "gridBackgroundColor";
	private static final String PROPERTY_DRAW_BORDERS = "drawBorders";
	private static final String PROPERTY_BORDER_COLOR = "borderColor";
	private static final String PROPERTY_BORDER_WIDTH = "borderWidth";
	private static final String PROPERTY_MAX_VISIBLE_VALUE_COUNT = "maxVisibleValueCount";
	private static final String PROPERTY_DATA = "data";

	public MarkerViewView(TiViewProxy proxy) 
	{
		super(proxy);
		
		Log.d(LCAT, "[VIEW LIFECYCLE EVENT] view");
		
		// Create the view as a TiCompositeLayout since our demo
		// will be adding children to the view.
		//TiCompositeLayout view = new TiCompositeLayout(proxy.getActivity());
		
		// Set the view as the native view. You must set the native view
		// for your view to be rendered correctly.
		setNativeView(new BarChart(TiApplication.getInstance().getApplicationContext()));
	}
		
	// The view is automatically registered as a model listener when the view
	// is realized by the view proxy. That means that the processProperties
	// method will be called during creation and that propertiesChanged and 
	// propertyChanged will be called when properties are changed on the proxy.

	@Override
	public void processProperties(KrollDict props) 
	{
		super.processProperties(props);
		
		Log.d(LCAT,"[VIEW LIFECYCLE EVENT] processProperties " + props);

		// Check if the color is specified when the view was created
		
	}
	
	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy)
	{
		// This method is called whenever a proxy property value is updated. Note that this 
		// method is only called if the new value is different than the current value.

		super.propertyChanged(key, oldValue, newValue, proxy);
		
		Log.d(LCAT,"[VIEW LIFECYCLE EVENT] propertyChanged: " + key + ' ' + oldValue + ' ' + newValue);
	}

	
}