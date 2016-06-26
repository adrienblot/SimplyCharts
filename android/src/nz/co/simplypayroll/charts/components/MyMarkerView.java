package nz.co.simplypayroll.charts.components;

import java.util.HashMap;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.TreeSet;


import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutParams;
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

public class MyMarkerView extends MarkerView
{

		private static final String LCAT = "CustomMarkerView";

		public TiViewProxy viewProxy;

		public MyMarkerView (Context context, TiViewProxy proxy) {
			
			super(context, proxy);			
	        Log.d(LCAT, "[CustomMarkerView] MyMarkerView proxy set");
	        this.viewProxy = proxy;
	    }

	    // callbacks everytime the MarkerView is redrawn, can be used to update the
	    // content (user-interface)
	    @Override
	    public void refreshContent(Entry e, Highlight highlight) {
	        if (this.viewProxy.hasListeners("refresh")) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				HashMap<String, Object> value = new HashMap<String, Object>();
				value.put("xIndex", e.getXIndex());
				value.put("val", e.getVal());
				value.put("data", e.getData());
				hm.put("value", value);
				this.viewProxy.fireEvent("refresh", hm);
			}
	    }

	    @Override
	    public int getXOffset(float xpos) {
	        // this will center the marker-view horizontally
	        return -(getWidth() / 2);
	    }

	    @Override
	    public int getYOffset(float ypos) {
	        // this will cause the marker-view to be above the selected value
	        return -getHeight();
	    }

	    
}