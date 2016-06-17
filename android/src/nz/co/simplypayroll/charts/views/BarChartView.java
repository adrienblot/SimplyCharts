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

	public BarChartView(TiViewProxy proxy) 
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
		BarChart chart = (BarChart)getNativeView();
		Boolean changed = false;

		if (props.containsKey(PROPERTY_BACKGROUND_COLOR)) {
			changed = true;
			chart.setBackgroundColor(TiConvert.toColor(props, PROPERTY_BACKGROUND_COLOR));			
		}
		if (props.containsKey(PROPERTY_DESCRIPTION)) {
			changed = true;
			chart.setDescription(TiConvert.toString(props, PROPERTY_DESCRIPTION));			
		}
		if (props.containsKey(PROPERTY_DESCRIPTION_COLOR)) {
			changed = true;
			chart.setDescriptionColor(TiConvert.toColor(props, PROPERTY_DESCRIPTION_COLOR));
		}
		if (props.containsKey(PROPERTY_DESCRIPTION_POSITION)) {
			Object d = props.get(PROPERTY_DESCRIPTION_POSITION);
			if (d instanceof HashMap) {
				HashMap<String, Object> position = (HashMap<String, Object>) d;
				float x = 0;
				float y = 0;
				if(position.containsKey('x')) {
					x = TiConvert.toFloat(position, 'x');
				}
				if(position.containsKey('y')) {
					y = TiConvert.toFloat(position, 'y');
				}
				chart.setDescriptionPosition(x, y);	
				changed = true;
			}
		}
		if (props.containsKey(PROPERTY_DESCRIPTION_FONT)) {
			Object d = props.get(PROPERTY_DESCRIPTION_FONT);
			if (d instanceof HashMap) {
				HashMap<String, Object> font = (HashMap<String, Object>) d;
				if (font.containsKey(PROPERTY_FONT_FAMILY)) {
					changed = true;
					chart.setDescriptionTypeface(TiUIHelper.toTypeface(chart.getContext(), TiConvert.toString(font, PROPERTY_FONT_FAMILY)));	
				}
				if (font.containsKey(PROPERTY_FONT_SIZE)) {
					changed = true;
					chart.setDescriptionTextSize(TiConvert.toFloat(font, PROPERTY_FONT_SIZE));	
				}
			}
			
		}
		if (props.containsKey(PROPERTY_NO_DATA_TEXT_DESCRIPTION)) {
			changed = true;
			chart.setNoDataTextDescription(TiConvert.toString(props, PROPERTY_NO_DATA_TEXT_DESCRIPTION));			
		}
		if (props.containsKey(PROPERTY_DRAW_GRID_BACKGROUND)) {
			changed = true;
			chart.setDrawGridBackground(TiConvert.toBoolean(props, PROPERTY_DRAW_GRID_BACKGROUND));			
		}
		if (props.containsKey(PROPERTY_GRID_BACKGROUND_COLOR)) {
			changed = true;
			chart.setGridBackgroundColor(TiConvert.toColor(props, PROPERTY_GRID_BACKGROUND_COLOR));			
		}
		if (props.containsKey(PROPERTY_DRAW_BORDERS)) {
			changed = true;
			chart.setDrawBorders(TiConvert.toBoolean(props, PROPERTY_DRAW_BORDERS));			
		}
		if (props.containsKey(PROPERTY_BORDER_COLOR)) {
			changed = true;
			chart.setBorderColor(TiConvert.toColor(props, PROPERTY_BORDER_COLOR));			
		}
		if (props.containsKey(PROPERTY_BORDER_WIDTH)) {
			changed = true;
			chart.setBorderWidth(TiConvert.toFloat(props, PROPERTY_BORDER_WIDTH));			
		}
		if (props.containsKey(PROPERTY_MAX_VISIBLE_VALUE_COUNT)) {
			changed = true;
			chart.setMaxVisibleValueCount(TiConvert.toInt(props, PROPERTY_MAX_VISIBLE_VALUE_COUNT));			
		}

		if (props.containsKey(PROPERTY_DATA)) {
			changed = true;
			Log.d(LCAT,"[VIEW LIFECYCLE EVENT] prop " + props.get(PROPERTY_DATA));
			BarDataProxy dataProxy = (BarDataProxy)props.get(PROPERTY_DATA);
			Log.d(LCAT,"[VIEW LIFECYCLE EVENT] dataProxy " + dataProxy);
			Log.d(LCAT,"[VIEW LIFECYCLE EVENT] data " + dataProxy.data);
			chart.setData((BarData)dataProxy.data);			
		}

		if(changed) {
			chart.invalidate();
		}
	}
	
	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy)
	{
		// This method is called whenever a proxy property value is updated. Note that this 
		// method is only called if the new value is different than the current value.

		super.propertyChanged(key, oldValue, newValue, proxy);
		
		Log.d(LCAT,"[VIEW LIFECYCLE EVENT] propertyChanged: " + key + ' ' + oldValue + ' ' + newValue);
	}

	public AxisBase getAxisLeft() {	
		BarChart chart = (BarChart)getNativeView();    
		return chart.getAxisLeft();
	}

	public AxisBase getAxisRight() {	
		BarChart chart = (BarChart)getNativeView();  
		return chart.getAxisRight();
	}

	public AxisBase getXAxis() {	
		BarChart chart = (BarChart)getNativeView();    
		return chart.getXAxis();
	}

	public void setData(BarData data) {
		BarChart chart = (BarChart)getNativeView();    
		chart.setData(data);
		chart.invalidate();		
	}

	public Legend getLegend() {	
		BarChart chart = (BarChart)getNativeView();    
		return chart.getLegend();
	}
}
