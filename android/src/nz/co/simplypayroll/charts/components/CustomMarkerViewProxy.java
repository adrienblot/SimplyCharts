/**
  * Appcelerator Titanium Mobile
  * Copyright (c) 2009-2012 by Appcelerator, Inc. All Rights Reserved.
  * Licensed under the terms of the Apache Public License
  * Please see the LICENSE included with this distribution for details.
  *
  */

package nz.co.simplypayroll.charts.components;

import nz.co.simplypayroll.charts.TiChartsModule;
import nz.co.simplypayroll.charts.data.BarDataProxy;
import nz.co.simplypayroll.charts.components.AxisBaseProxy;
import nz.co.simplypayroll.charts.components.XAxisProxy;
import nz.co.simplypayroll.charts.components.YAxisProxy;
import nz.co.simplypayroll.charts.components.LegendProxy;
import nz.co.simplypayroll.charts.components.CustomMarkerView;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollRuntime;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.AsyncResult;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.proxy.TiViewProxy;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;

@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class CustomMarkerViewProxy extends TiViewProxy 
{
	// Standard Debugging variables
	private static final String LCAT = "TiChartsModule";

	public CustomMarkerViewProxy() {
		super();
		
		Log.d(LCAT, "[VIEWPROXY LIFECYCLE EVENT] init");
	}
	
	@Override
	public TiUIView createView(Activity activity) 
	{
		// This method is called when the view needs to be created. This is
		// a required method for a TiViewProxy subclass.
		
		CustomMarkerView view = new CustomMarkerView(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;
		
		return view;
	}

}