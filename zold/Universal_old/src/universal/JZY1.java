/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universal;

import org.jzy3d.plot3d.primitives.Polygon;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.chart.Chart;
import java.util.ArrayList;
import java.util.List;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.chart.ChartLauncher;
import org.jzy3d.colors.Color;
import org.jzy3d.plot3d.primitives.MultiColorScatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;


/**
 *
 * @author Doreen
 */
public class JZY1 {

    public JZY1 (){


// Define a function to plot
Mapper mapper = new Mapper() {
    public double f(double x, double y) {
        return 10 * Math.sin(x / 10) * Math.cos(y / 20) * x;
    }
};

// Define range and precision for the function to plot
Range range = new Range(-150, 150);
int steps = 50;

// Create a surface drawing that function
Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
surface.setFaceDisplayed(true);
surface.setWireframeDisplayed(false);
surface.setWireframeColor(Color.BLACK);

// Create a chart and add the surface
// Chart chart = new Chart();
//chart.getScene().getGraph().add(surface);
//ChartLauncher.openChart(chart);


//int size = 100000;
//float x;
//float y;
//float z;
//Coord3d[] points = new Coord3d[size];
//
//// Create scatter points
//for(int i=0; i<size; i++){
//    x = (float)Math.random() - 0.5f;
//    y = (float)Math.random() - 0.5f;
//    z = (float)Math.random() - 0.5f;
//    points[i] = new Coord3d(x, y, z);
//}       
//
//// Create a drawable scatter with a colormap
//MultiColorScatter scatter = new MultiColorScatter( points, new ColorMapper( new ColorMapRainbow(), -0.5f, 0.5f ) );
//
//// Create a chart and add scatter
Chart chart = new Chart();
//chart.getAxeLayout().setMainColor(Color.WHITE);
//chart.getView().setBackgroundColor(Color.BLACK);
//chart.getScene().add(scatter);
//ChartLauncher.openChart(chart);

    
    
}}
    
