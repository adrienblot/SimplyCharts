// This is a test harness for your module
// You should do something interesting in this harness
// to test out the module and to provide instructions
// to users on how to use it by example.


// open a single window
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});
win.open();

// TODO: write your module tests here
var ticharts = require('nz.co.simplypayroll.charts');


if (Ti.Platform.name == "android") {
	 //Create chart view
	var chart = module.createLineChartView({ 
	    backgroundColor: "#E5EFCE",  
	    drawGridBackground: true,
	    gridBackgroundColor: 'white',
	    drawBorders: true,
	    borderColor: "#74AD50",
	    borderWidth: 2,
	    maxVisibleValueCount: 2
	});

	win.add(chart);          

	//Change legend properties
	var legend = chart.getLegend();
	legend.setPosition('ABOVE_CHART_LEFT');

	//Create data
	var lineDataSet = module.createLineDataSet({
        label: 'line1',
        yVals: [{xIndex: 0, val: 10 }, {xIndex: 1, val: 20 }, {xIndex: 3, val: 30 }]
    });

    lineDataSet.setDrawFilledEnabled(true);
    lineDataSet.setFillColor('green');

	var lineData = module.createLineData({
	    xVals: ['x1', 'x2', 'x3'], 
	    dataSets: lineDataSet // or [lineDataSet]
	});
	chart.setData(lineData);

	//Change XAxis properties
	var xAxis = chart.getXAxis();
	xAxis.setPosition('BOTTOM');
	xAxis.setTextColor("#74AD50");
}

