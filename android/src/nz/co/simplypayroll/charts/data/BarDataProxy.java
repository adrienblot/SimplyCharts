package nz.co.simplypayroll.charts.data;

import nz.co.simplypayroll.charts.TiChartsModule;
import nz.co.simplypayroll.charts.data.ChartDataProxy;
import nz.co.simplypayroll.charts.utils.TiConverter;

import java.util.*;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.github.mikephil.charting.utils.Utils;


@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class BarDataProxy extends ChartDataProxy
{  
	private static final String LCAT = "BarDataProxy";

	public BarDataProxy() {
		super();
	}

	@Override
	public void handleCreationDict(KrollDict options) 
	{
		// This method is called from handleCreationArgs if there is at least one
		// argument specified for the proxy creation call and the first argument
		// is a KrollDict object.
		
		Log.d(LCAT, "PROXY LIFECYCLE EVENT] handleCreationDict " + options);

		// Calling the superclass method ensures that the properties specified
		// in the dictionary are properly set on the proxy object.
		String[] xVals = null;
		ArrayList<IBarDataSet> data = null;
		if(options.containsKey("xVals")) {
			Object d = options.get("xVals");
			if (!(d.getClass().isArray())) {
				throw new IllegalArgumentException("xVals must be an arrayof string");
			}
			xVals = TiConvert.toStringArray((Object[])d);
		}
		
		if(options.containsKey("dataSets")) {
			Object d = options.get("dataSets");
			data = new ArrayList<IBarDataSet>();
			if (d instanceof HashMap) {
				HashMap<String, Object> sets = (HashMap<String, Object>) d;
				Set set = sets.entrySet();
			    Iterator i = set.iterator(); 
			    while(i.hasNext()) {
					Map.Entry me = (Map.Entry)i.next();
					Log.d(LCAT, "PROXY LIFECYCLE EVENT] " + me.getKey() + ": " + me.getValue());

					data.add(this.createDataSet(TiConvert.toString(me.getKey()), me.getValue()));

				}
			} else if(d.getClass().isArray()) {
				Object[] inArray = (Object[])d;
				for (int i = 0; i < inArray.length; i++) {
					Object dataSet = inArray[i];
					if(d instanceof BarDataSetProxy) {
						data.add((BarDataSet)((BarDataSetProxy)dataSet).dataSet);
					}
				}
			} else if(d instanceof BarDataSetProxy) {
				data.add((BarDataSet)((BarDataSetProxy)d).dataSet);
			}
		}
		
		if(xVals == null && data == null) {
			this.data = new BarData();			
		} else if(data == null) {
			this.data = new BarData(xVals);
		} else if(xVals == null){
			throw new IllegalArgumentException("No xVal argument.");
		} else {
			this.data = new BarData(xVals, data);
		}
		super.handleCreationDict(options);
	}

	public void handleCreationArgs(KrollModule createdInModule, Object[] args) 
	{
		// This method is one of the initializers for the proxy class. The arguments
		// for the create call are passed as an array of objects. If your proxy 
		// simply needs to handle a single KrollDict argument, use handleCreationDict.
		// The superclass method calls the handleCreationDict if the first argument
		// to the create method is a dictionary object.

		Log.d(LCAT, "PROXY LIFECYCLE EVENT] handleCreationArgs ");
	
		for (int i = 0; i < args.length; i++) {
			Log.d(LCAT, "PROXY LIFECYCLE EVENT] args[" + i + "] " + args[i]);
		}
		
		// Calling the superclass method is required
		super.handleCreationArgs(createdInModule, args);
	}

	private  BarDataSet createDataSet(String label, Object values) 
	{		
		return new BarDataSet(this.createEntryList(values), label);
	}

	private  BarDataSet createDataSet(String label, Object values, String color) 
	{		
		BarDataSet dataSet = new BarDataSet(this.createEntryList(values), label);
		dataSet.setColor(TiConvert.toColor(color));
		return dataSet;
	}


	private  BarDataSet createDataSet(String label, Object values, String[] colors) 
	{		
		BarDataSet dataSet = new BarDataSet(this.createEntryList(values), label);
		dataSet.setColors(TiConverter.toColorArray(colors));
		return dataSet;
	}

	private  ArrayList<BarEntry> createEntryList(Object values) 
	{
		ArrayList<BarEntry> entryList = new ArrayList<BarEntry>();
		if (values.getClass().isArray()) {
			Object[] valueArray = (Object[])values;
			
			for (int index=0; index < valueArray.length; index++) {
				BarEntry entry = this.createEntry(valueArray[index]);
				if(entry != null) {
					entryList.add(entry);
				}
			}
		}
		return entryList;
	}

	private  BarEntry createEntry(Object valueObject) 
	{
		BarEntry entry = null;
		if (valueObject instanceof HashMap) {
			HashMap<String, Object> value = (HashMap<String, Object>)valueObject;
			if(value.containsKey("xIndex")) {
				if(value.containsKey("val")) {
					if(value.containsKey("label"))
		    			entry = new BarEntry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"), TiConvert.toString(value, "label"));
		    		else
		    			entry = new BarEntry(TiConvert.toFloat(value, "val"), TiConvert.toInt(value, "xIndex"));
				} else if(value.containsKey("vals")) {
					Object d = value.get("vals");
					if (!(d.getClass().isArray())) {
						throw new IllegalArgumentException("xVals must be an array of string");
					}					
					if(value.containsKey("label"))
		    			entry = new BarEntry(TiConverter.toFloatArray((Object[])d), TiConvert.toInt(value, "xIndex"), TiConvert.toString(value, "label"));
		    		else
		    			entry = new BarEntry(TiConverter.toFloatArray((Object[])d), TiConvert.toInt(value, "xIndex"));
				}
			}
		}
		return entry;
	}



}