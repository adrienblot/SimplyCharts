package nz.co.simplypayroll.charts.components;

import java.util.List;

import nz.co.simplypayroll.charts.TiChartsModule;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.formatter.DefaultXAxisValueFormatter;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

@Kroll.proxy(creatableInModule = TiChartsModule.class)
public class XAxisProxy extends AxisBaseProxy
{  
	
	
	public XAxisProxy() {
		super();
	}
	
	public void setAxis(XAxis axis) {
		this.axis = axis;	
	}

	@Kroll.getProperty @Kroll.method
	public String getPosition() {
		XAxisPosition position = ((XAxis) this.axis).getPosition();
		String txtPosition = "";
		switch(position) {
			case BOTH_SIDED:
				txtPosition = "BOTH_SIDED";
				break;
			case BOTTOM:
				txtPosition = "BOTTOM";
				break;
			case BOTTOM_INSIDE:
				txtPosition = "BOTTOM_INSIDE";
				break;
			case TOP:
                txtPosition = "TOP";
				break;
			case TOP_INSIDE:
				txtPosition = "TOP_INSIDE";
				break;
			default:
				break;
        }
        return txtPosition;
    }

    /**
     * sets the position of the x-labels
     * 
     * @param pos
     */
    @Kroll.setProperty @Kroll.method
    public void setPosition(String pos) {
    	XAxisPosition position = null;
    	switch(pos) {
			case "BOTH_SIDED":
				position = XAxisPosition.BOTH_SIDED;
				break;
			case "BOTTOM":
				position = XAxisPosition.BOTTOM;
				break;
			case "BOTTOM_INSIDE":
				position = XAxisPosition.BOTTOM_INSIDE;
				break;
			case "TOP":
                position = XAxisPosition.TOP;
				break;
			case "TOP_INSIDE":
				position = XAxisPosition.TOP_INSIDE;
				break;
			default:
				break;
	    }
    	if(position != null)
    		((XAxis) this.axis).setPosition(position);
    }

    /**
     * returns the angle for drawing the X axis labels (in degrees)
     */
    @Kroll.getProperty @Kroll.method
    public float getLabelRotationAngle() {
        return ((XAxis) this.axis).getLabelRotationAngle();
    }

    /**
     * sets the angle for drawing the X axis labels (in degrees)
     *
     * @param angle the angle in degrees
     */
    @Kroll.setProperty @Kroll.method
    public void setLabelRotationAngle(float angle) {
        ((XAxis) this.axis).setLabelRotationAngle(angle);
    }

    /**
     * Sets the space (in characters) that should be left out between the x-axis
     * labels, default 4. This only applies if the number of labels that will be
     * skipped in between drawn axis labels is not custom set.
     * 
     * @param spaceCharacters
     */
    @Kroll.setProperty @Kroll.method
    public void setSpaceBetweenLabels(int spaceCharacters) {
        ((XAxis) this.axis).setLabelRotationAngle(spaceCharacters);
    }

    /**
     * Sets the number of labels that should be skipped on the axis before the
     * next label is drawn. This will disable the feature that automatically
     * calculates an adequate space between the axis labels and set the number
     * of labels to be skipped to the fixed number provided by this method. Call
     * resetLabelsToSkip(...) to re-enable automatic calculation.
     * 
     * @param count
     */
    @Kroll.setProperty @Kroll.method
    public void setLabelsToSkip(int count) {
		((XAxis) this.axis).setLabelRotationAngle(count);
    }

    /**
     * Calling this will disable a custom number of labels to be skipped (set by
     * setLabelsToSkip(...)) while drawing the x-axis. Instead, the number of
     * values to skip will again be calculated automatically.
     */
    @Kroll.method
    public void resetLabelsToSkip() {
        ((XAxis) this.axis).resetLabelsToSkip();
    }

    /**
     * Returns true if a custom axis-modulus has been set that determines the
     * number of labels to skip when drawing.
     * 
     * @return
     */
    @Kroll.method
    public boolean isAxisModulusCustom() {
        return ((XAxis) this.axis).isAxisModulusCustom();
    }

    /**
     * Returns the space (in characters) that should be left out between the
     * x-axis labels
     */
    @Kroll.getProperty @Kroll.method
    public int getSpaceBetweenLabels() {
        return ((XAxis) this.axis).getSpaceBetweenLabels();
    }

    /**
     * if set to true, the chart will avoid that the first and last label entry
     * in the chart "clip" off the edge of the chart or the screen
     * 
     * @param enabled
     */
    @Kroll.setProperty @Kroll.method
    public void setAvoidFirstLastClipping(boolean enabled) {
    	((XAxis) this.axis).setAvoidFirstLastClipping(enabled);
    }

    /**
     * returns true if avoid-first-lastclipping is enabled, false if not
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public boolean getAvoidFirstLastClippingEnabled() {
        return ((XAxis) this.axis).isAvoidFirstLastClippingEnabled();
    }

    /**
     * Sets the labels for this axis.
     * 
     * @param values
     */
    @Kroll.setProperty @Kroll.method
    public void setValues(List<String> values) {
        ((XAxis) this.axis).setValues(values);
    }

    /**
     * Returns the labels for this axis.
     * 
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public List<String> getValues() {
        return ((XAxis) this.axis).getValues();
    }


    /**
     * Sets a custom XAxisValueFormatter for the data object that allows custom-formatting
     * of all x-values before rendering them. Provide null to reset back to the
     * default formatting.
     *
     * @param formatter
     */
    @Kroll.setProperty @Kroll.method
    public void setValueFormatter(String formatter) {
    	XAxisValueFormatter formatterObject = new DefaultXAxisValueFormatter();
    	switch(formatter) {
	    	case "date":
	    		formatterObject = new DateXAxisValueFormatter();
	    		break;
	    	default:
	    		break;  	
    	}
        ((XAxis) this.axis).setValueFormatter(formatterObject);
    }

    /**
     * Returns the custom XAxisValueFormatter that is set for this data object.
     * @return
     */
    @Kroll.getProperty @Kroll.method
    public String getValueFormatter() {
    	XAxisValueFormatter formatter = ((XAxis) this.axis).getValueFormatter();
    	if(formatter instanceof DateXAxisValueFormatter)
    		return "date";
    	else
    		return "default";
    }

    @Kroll.getProperty @Kroll.method
    public String getLongestLabel() {
        return ((XAxis) this.axis).getLongestLabel();
    }

}
