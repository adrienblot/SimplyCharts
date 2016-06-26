/**
  * Appcelerator Titanium Mobile
  * Copyright (c) 2009-2012 by Appcelerator, Inc. All Rights Reserved.
  * Licensed under the terms of the Apache Public License
  * Please see the LICENSE included with this distribution for details.
  *
  */
package nz.co.simplypayroll.charts.components;

import java.util.HashMap;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.TreeSet;


import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.TiDimension;
import org.appcelerator.titanium.TiLaunchActivity;

import android.app.Activity;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;

import nz.co.simplypayroll.charts.data.BarDataProxy;
import nz.co.simplypayroll.charts.components.MyMarkerView;

public class CustomMarkerView extends TiUIView
{
	private static final String LCAT = "CustomMarkerView";

	/**
	 * A TiCompositeLayout specific version of {@link android.view.ViewGroup.LayoutParams}
	 */


	public CustomMarkerView(TiViewProxy proxy) 
	{
		super(proxy);

		Log.d(LCAT, "[CustomMarkerView] view");
		try {
			int layout = TiRHelper.getApplicationResource("layout.mymarkerviewlayout");
			Log.d(LCAT, "[CustomMarkerView] MyMarkerView layout " + layout);
			MyMarkerView nativeMarkerView = new MyMarkerView(TiApplication.getInstance().getApplicationContext(), proxy);
			Log.d(LCAT, "[CustomMarkerView] nativeView created");
			setNativeView(nativeMarkerView);
			Log.d(LCAT, "[CustomMarkerView] nativeView set");
		} catch(TiRHelper.ResourceNotFoundException e) {
			Log.d(LCAT, "[CustomMarkerView] ResourceNotFoundException");
		}
	}

	

	
}