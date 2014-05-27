/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package asgn2Simulators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
*
* @author Rebecca Zanchetta (n8300941)
* 		  and Brad Vose (n8280282)
*/
public class ChartPanel extends org.jfree.chart.ChartPanel {
    private final String TITLE = "Car Park Simulation Results";
    
    private TimeSeries vehTotal;
    private TimeSeries curTotal;
    private TimeSeries carTotal;
    private TimeSeries scarTotal;
    private TimeSeries mcTotal;
    private TimeSeries queueTotal;
    private TimeSeries archTotal;
    private TimeSeries dissTotal;
    
    private Calendar cal = GregorianCalendar.getInstance();
    
    public ChartPanel() {
        super(new JFreeChart(new XYPlot()));
    }

    // Resets the simulation data before starting a new one
    public final void resetSimulationData()
    {
        setUpSeries();
    }
    
    // Sets up the different series used to plot the graph
    private void setUpSeries()
    {
    	vehTotal = new TimeSeries("Total Vehicles to Date");
    	curTotal = new TimeSeries("Currently Parked Vehicles");
    	carTotal = new TimeSeries("Total Cars in Car Park");
    	scarTotal = new TimeSeries("Total Small Cars in Car Park");
    	mcTotal = new TimeSeries("Total MotorCycles in Car Park");
    	queueTotal = new TimeSeries("Number of Queued Vehicles");
    	archTotal = new TimeSeries("Number Archived");
    	dissTotal = new TimeSeries("Number Dissatisfied");
    }
    
    // Adds datapoints for a particular moment intime to the graph
    public void addDataForGivenTimePoint( int time, int dissatisfied, int archived, int queue, int nbCars, int nbSmallCars, int nbMotorCycles, int current, int total)
    {
        cal.set(2014,0,1,6, time);
        Date timePoint = cal.getTime();
        
        dissTotal.add(new Minute(timePoint), dissatisfied);
        archTotal.add(new Minute(timePoint), archived);
        queueTotal.add(new Minute(timePoint), queue);
        mcTotal.add(new Minute(timePoint), nbMotorCycles);
        scarTotal.add(new Minute(timePoint), nbSmallCars);
        carTotal.add(new Minute(timePoint),nbCars);
        curTotal.add(new Minute(timePoint), current);
        vehTotal.add(new Minute(timePoint), total);
    }
    
    // Generate the chart once the car park simulation is completed
    public void generateFinalChartFromData()
    {
        TimeSeriesCollection finalCollection = getFinalCollection();
        JFreeChart chart = createChart(finalCollection);
        setChart(chart);
    }
    
    // Get the collection containing the final relevant time series
    private TimeSeriesCollection getFinalCollection()
    {
        TimeSeriesCollection tsc = new TimeSeriesCollection();
        tsc.addSeries(vehTotal);
        tsc.addSeries(curTotal);
        tsc.addSeries(carTotal);
        tsc.addSeries(scarTotal);
        tsc.addSeries(mcTotal);
        tsc.addSeries(queueTotal);
        tsc.addSeries(archTotal);
        tsc.addSeries(dissTotal);
       
        return tsc;
    }
    
        /**
* Helper method to deliver the Chart - currently uses default colours and auto range
* @param dataset TimeSeriesCollection for plotting
* @returns chart to be added to panel
*/
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            TITLE, "hh:mm:ss", "Vehicles", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }
}