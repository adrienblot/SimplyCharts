package nz.co.simplypayroll.charts.components;

import nz.co.simplypayroll.charts.TiChartsModule;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import com.github.mikephil.charting.components.AxisBase;

@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class AxisBaseProxy extends KrollProxy
{  
	public AxisBase axis;

	public AxisBaseProxy() {
		super();
	}
	
	public void setAxis(AxisBase axis) {
		this.axis = axis;		
	}

	@Kroll.method
	public void setEnabled(Boolean enabled) {	    
		this.axis.setEnabled(enabled);
	}

	@Kroll.method
	public void setDrawLabels(Boolean enabled) {	    
		this.axis.setDrawLabels(enabled);
	}

	@Kroll.method
	public void setDrawAxisLine(Boolean enabled) {	    
		this.axis.setDrawAxisLine(enabled);
	}

	@Kroll.method
	public void setDrawGridLines(Boolean enabled) {	    
		this.axis.setDrawGridLines(enabled);
	}

	@Kroll.method
	public void setTextColor(String color) {	    
		this.axis.setTextColor(TiConvert.toColor(color));
	}

	@Kroll.method
	public void setTextSize(float size) {	    
		this.axis.setTextSize(size);
	}

	@Kroll.method
	public void setTypeface(String tf) {	    
		this.axis.setTypeface(TiUIHelper.toTypeface(TiApplication.getInstance().getApplicationContext(), tf));
	}

	@Kroll.method
	public void setGridColor(String color) {	    
		this.axis.setGridColor(TiConvert.toColor(color));
	}

	@Kroll.method
	public void setGridLineWidth(float width) {	    
		this.axis.setGridLineWidth(width);
	}

	@Kroll.method
	public void setAxisLineColor(String color) {	    
		this.axis.setAxisLineColor(TiConvert.toColor(color));
	}

	@Kroll.method
	public void setAxisLineWidth(float width) {	    
		this.axis.setAxisLineWidth(width);
	}

	@Kroll.method
	public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {	    
		this.axis.enableGridDashedLine(lineLength, spaceLength, phase);
	}

}