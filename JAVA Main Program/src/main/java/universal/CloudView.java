/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
package universal;


import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.ChartLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.chart.factories.IChartComponentFactory;
import org.jzy3d.colors.CompositeColorMapperUpdatePolicy;
import org.jzy3d.colors.OrderingStrategyScoreColorMapper;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.builder.concrete.OrthonormalTessellator;
import org.jzy3d.plot3d.primitives.Polygon;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.primitives.axes.layout.IAxeLayout;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.legends.colorbars.AWTColorbarLegend;
import org.jzy3d.plot3d.rendering.scene.Graph;
import org.jzy3d.plot3d.rendering.view.View;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.IColorMappable;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.colors.colormaps.IColorMap;
import org.jzy3d.plot3d.primitives.LineStrip;

/**
 *
 * @author Doreen
 */
class CloudView extends JFrame {
    // constants 
    UModel model;
    static double MAPPER_ZSCALE_FACTOR = 100;
    protected Shape surface;
    protected IColorMap colormap;
    protected ColorMapper colormapper;
    protected AWTColorbarLegend colorbar;
    float[] pmclouds0x, pmclouds0y, pmclouds1x, pmclouds1y, pmclouds2x, pmclouds2y;
    float[] paclouds0x, paclouds0y, paclouds1x, paclouds1y, paclouds2x, paclouds2y;
    int size = 100;
    float x, xm;
    float y, ym;
    float z, zm;
    float a = 0.9f;
    Coord3d[] points = new Coord3d[size];
    Color[] colors = new Color[size];
    IChartComponentFactory factory = new AWTChartComponentFactory();
    final Chart chartpa = new Chart(factory, Quality.Advanced, "awt");
    final Chart chartpm = new Chart(factory, Quality.Advanced, "awt");
    float pa1ymax, pa1ymin, pm1ymin, pm1ymax, avgpa, avgpm;
    float incrementpa, incrementpm;


    CloudView(UModel m) {
        model = m;
        size = model.iterations;
//        points = new Coord3d[size];
//        colors = new Color[size];
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pmclouds0x = model.getClouds(1, 0, 0); // (which map, which dim)
        pmclouds0y = model.getClouds(1, 1, 0); // (which map, which dim)
        pmclouds1x = model.getClouds(1, 0, 1); // (which map, which dim)        
        pmclouds1y = model.getClouds(1, 1, 1); // (which map, which dim)
        pmclouds2x = model.getClouds(1, 0, 2); // (which map, which dim)
        pmclouds2y = model.getClouds(1, 1, 2); // (which map, which dim)        
        paclouds0x = model.getClouds(2, 0, 0); // (which map, which dim)
        paclouds0y = model.getClouds(2, 1, 0); // (which map, which dim)
        paclouds1x = model.getClouds(2, 0, 1); // (which map, which dim)        
        paclouds1y = model.getClouds(2, 1, 1); // (which map, which dim)
        paclouds2x = model.getClouds(2, 0, 2); // (which map, which dim)
        paclouds2y = model.getClouds(2, 1, 2); // (which map, which dim)
        setminmax();
        incrementpa = (Math.abs(pa1ymax)+Math.abs(pa1ymin))/10;
        incrementpm = (Math.abs(pm1ymax)-Math.abs(pm1ymin))/10;

        // getScatter(10); // 10 gets lines
          // displayPanel(); // 1 plots PA for a fixed iy; also panel
        // getScatter(2); // 2 plots PM for a fixed iy
//        getScatter(3); // 3 plots PA without cross section
//        getScatter(4); // 4 plots PM without cross section 
        
        // displayPoly();
    }

    public void setD(int newD) {
//        System.out.println("d:" + newD);
        boolean[] fixed1y = new boolean[0];
        boolean[] fixed1ym = new boolean[0];
        if (newD == 0) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, pa1ymin); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, pm1ymin);}
        if (newD == 1) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm)));}
        if (newD == 2) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*2))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*2)));}
        if (newD == 3) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*3))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*3)));}
        if (newD == 4) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*4))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*4)));}
        if (newD == 5) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*5)));    fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*5)));}
        if (newD == 6) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*6))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*6)));}
        if (newD == 7) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*7))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*7)));}
        if (newD == 8) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*8))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*8)));}
        if (newD == 9) {fixed1y = CrossSectionRange(paclouds1y, incrementpa, (pa1ymin+(incrementpa*9))); fixed1ym = CrossSectionRange(pmclouds1y, incrementpm, (pm1ymin+(incrementpm*9)));}
//         if (newD == 10) {fixed1y = CrossSectionRange(paclouds1y, 0.3f, 1.5f);}
        ArrayList<Coord3d> toplot = new ArrayList<>();
        ArrayList<Coord3d> toplotm = new ArrayList<>();
        for(int i = 0; i < size; i++){
                if (fixed1y[i] == true) {
//                if (fixed1y[i] == true && fixed2y[i] == true) {
                    x = paclouds0x[i];
                    y = paclouds0y[i];
                    z = paclouds1x[i]; 
                toplot.add(new Coord3d(x, y, z));}}
        Coord3d[] toplt = new Coord3d[toplot.size()];        
        int i = 0; for (Coord3d f : toplot) {toplt[i++] = f;}       
        addScatterPA(toplt, 0.02f); 
        
        for(int j = 0; j < size; j++){        
                if (fixed1ym[j] == true) {
//                if (fixed1y[i] == true && fixed2y[i] == true) {
                    xm = pmclouds0x[j];
                    ym = pmclouds0y[j];
                    zm = pmclouds1x[j]; 
                toplotm.add(new Coord3d(xm, ym, zm));}}
        Coord3d[] topltm = new Coord3d[toplotm.size()];
        int k = 0; for (Coord3d g : toplotm) {topltm[k++] = g;}
        addScatterPM(topltm, 0.02f);    
    }
        
    public void addScatterPA(Coord3d[] pts, float f) {
        Scatter scatter1 = new Scatter(pts, Color.RED, 1f);
        chartpa.getScene().add(scatter1);
    }    
    public void addScatterPM(Coord3d[] pts, float f) {
        Scatter scatter1 = new Scatter(pts, Color.RED, 1f);
        chartpm.getScene().add(scatter1);
    }
    
    private boolean within(float val, float min, float max) {
        return val >= min && val <= max;
    }
    
    private boolean[] CrossSectionRange(float[] values, float epsilon, float startpoint) {
        ArrayList<Boolean> fixed = new ArrayList<>();
        for (int i = 0; i < values.length; ++i){
            fixed.add(within(values[i], startpoint, (startpoint+epsilon)));}
        boolean[] returned = new boolean[fixed.size()];
        int i = 0;
        for (Boolean f : fixed) {
        returned[i++] = f;}    
        return returned;
    }
        
    private void getScatter(int choice){
        if (choice == 10) {
            ArrayList<Coord3d> toplot = new ArrayList<>();
            for(int i = 0; i < size; i++){
                    x = paclouds0x[i];
                    y = paclouds0y[i];
                    z = paclouds1x[i]; 
                    toplot.add(new Coord3d(x, y, z));}
            Coord3d[] toplt = new Coord3d[toplot.size()];
                int i = 0;
                for (Coord3d f : toplot) {
                toplt[i++] = f;}
            ArrayList<LineStrip> allStrips = new ArrayList<>();
            for (int j = 0; j < (toplt.length-10); j++){    
            LineStrip ls = new LineStrip();
            // add source point for the linestrip
            ls.add(new Point(toplt[j], Color.RED));
            // add target point for the linestrip
            ls.add(new Point(toplt[j+7], Color.BLUE));
            // display flag for linestrip
            ls.setDisplayed(true);
            // add linestrip ls to a list of linestrips
            allStrips.add(ls);}
            
            Iterator<LineStrip> lsIterator = allStrips.iterator();
            while (lsIterator.hasNext()) {
                chartpa.getScene().getGraph().add(lsIterator.next());
                }
            chartpa.setAxeDisplayed(false);
            ChartLauncher.openChart(chartpa);
            
//            Scatter scatter1 = new Scatter(toplt, new Color(0.0f, 0.0f, 0.5f, a), 1f);
//            chartpa.getScene().add(scatter1);
//            chartpa.setAxeDisplayed(false);
//            ChartLauncher.openChart(chartpa);
        }
        
        
        
        
        
        
                if (choice == 1) {
            // fix 0.3 range in paclouds1y starting at average 
            boolean[] fixed1y = CrossSectionRange(paclouds1y, 0.3f, 0.35f);
//            boolean[] fixed2x = CrossSectionRange(paclouds2x, 0.3f, 0.35f);
//            boolean[] fixed2y = CrossSectionRange(paclouds2y, 0.3f, 0.35f);
//            for (int i = 0; i < fixed1y.length; ++i) {
//                System.out.println("fixed:" + fixed1y[i]);
//            }
            ArrayList<Coord3d> toplot = new ArrayList<>();
            for(int i = 0; i < size; i++){
                if (fixed1y[i] == true) {
//                if (fixed1y[i] == true && fixed2y[i] == true) {
                    x = paclouds0x[i];
                    y = paclouds0y[i];
                    z = paclouds1x[i]; 
                    toplot.add(new Coord3d(x, y, z));}}
                Coord3d[] toplt = new Coord3d[toplot.size()];
                int i = 0;
                for (Coord3d f : toplot) {
                toplt[i++] = f;}
                Scatter scatter1 = new Scatter(toplt, new Color(0.0f, 0.0f, 0.5f, a), 1f);
                chartpa.getScene().add(scatter1);
                chartpa.setAxeDisplayed(false);
                ChartLauncher.openChart(chartpa);
        }
        
        if (choice == 2) {
            boolean[] fixed1y = CrossSectionRange(pmclouds1y, 0.3f, 0.38f);
//          boolean[] fixed2x = CrossSectionRange(pmclouds2x, 0.3f, 0.35f);
//          boolean[] fixed2y = CrossSectionRange(pmclouds2y, 0.3f, 0.35f);
//            for (int i = 0; i < fixed1y.length; ++i) {
//                System.out.println("fixed:" + fixed1y[i]);
//            }
            ArrayList<Coord3d> toplot = new ArrayList<>();
            for(int i = 0; i < size; i++){
                if (fixed1y[i] == true) {
                    // if (fixed1y[i] == true && fixed2y[i] == true) {
                    x = pmclouds0x[i];
                    y = pmclouds0y[i];
                    z = pmclouds1x[i]; 
                    toplot.add(new Coord3d(x, y, z));}}
                Coord3d[] toplt = new Coord3d[toplot.size()];
                int i = 0;
                for (Coord3d f : toplot) {
                toplt[i++] = f;}
                Scatter scatter1 = new Scatter(toplt, new Color(0.0f, 0.0f, 0.5f, a), 1f);
                chartpm.getScene().add(scatter1);
                chartpm.setAxeDisplayed(false);
                ChartLauncher.openChart(chartpm);
        }
        
        if (choice == 3) {
            ArrayList<Coord3d> toplot = new ArrayList<>();
            for(int i = 0; i < size; i++){
                    x = paclouds0x[i];
                    y = paclouds0y[i];
                    z = paclouds1x[i]; 
                    toplot.add(new Coord3d(x, y, z));}
                Coord3d[] toplt = new Coord3d[toplot.size()];
                int i = 0;
                for (Coord3d f : toplot) {
                toplt[i++] = f;}
                Scatter scatter1 = new Scatter(toplt, new Color(0.0f, 0.0f, 0.5f, a), 1f);
                chartpa.getScene().add(scatter1);
                chartpa.setAxeDisplayed(false);
                ChartLauncher.openChart(chartpa);
        }
        
        if (choice == 4) {
            ArrayList<Coord3d> toplot = new ArrayList<>();
            for(int i = 0; i < size; i++){
                    x = pmclouds0x[i];
                    y = pmclouds0y[i];
                    z = pmclouds1x[i]; 
                    toplot.add(new Coord3d(x, y, z));}
                Coord3d[] toplt = new Coord3d[toplot.size()];
                int i = 0;
                for (Coord3d f : toplot) {
                toplt[i++] = f;}
                Scatter scatter1 = new Scatter(toplt, new Color(0.0f, 0.0f, 0.5f, a), 1f);
                chartpm.getScene().add(scatter1);
                chartpm.setAxeDisplayed(false);
                ChartLauncher.openChart(chartpm);
        }
    }
    
    private void setminmax() {
        ArrayList<Float> palist1y = new ArrayList<>();
        ArrayList<Float> pmlist1y = new ArrayList<>();
        for (int i = 0; i < pmclouds0x.length; ++i){
            palist1y.add(paclouds1y[i]); 
            pmlist1y.add(pmclouds1y[i]);}
        pa1ymax = Collections.max(palist1y);
        pm1ymax = Collections.max(pmlist1y);
        pa1ymin = Collections.min(palist1y);
        pm1ymin = Collections.min(pmlist1y);
        avgpa = 0f; for(Float i:palist1y) {avgpa += i;} avgpa = (float)avgpa/(float)(palist1y.size());
        avgpm = 0f; for(Float i:pmlist1y) {avgpm += i;} avgpm = (float)avgpm/(float)(pmlist1y.size());
//        ArrayList<Float> list0x = new ArrayList<>(); ArrayList<Float> list0y = new ArrayList<>();
//        ArrayList<Float> list1x = new ArrayList<>(); ArrayList<Float> list1y = new ArrayList<>();        
//        ArrayList<Float> list2x = new ArrayList<>(); ArrayList<Float> list2y = new ArrayList<>();
//        for (int i = 0; i < paclouds0x.length; ++i){
//            list0x.add(paclouds0x[i]); list0y.add(paclouds0y[i]);
//            list1x.add(paclouds1x[i]); list1y.add(paclouds1y[i]);               
//            list2x.add(paclouds2x[i]); list2y.add(paclouds2y[i]);}
//        Float max0x = Collections.max(list0x); Float min0x = Collections.min(list0x); Float max0y = Collections.max(list0y); Float min0y = Collections.min(list0y);
//        Float max1x = Collections.max(list1x); Float min1x = Collections.min(list1x); Float max1y = Collections.max(list1y); Float min1y = Collections.min(list1y);        
//        Float max2x = Collections.max(list2x); Float min2x = Collections.min(list2x); Float max2y = Collections.max(list2y); Float min2y = Collections.min(list2y);
//        float avg = 0.0f; for(Float i:list0x) {avg += i;} System.out.println("Average: " + (float)avg/(float)(list0x.size())); 
//        System.out.println("0x max:" + max0x + ", min:" + min0x); System.out.println("0y max:" + max0y + ", min:" + min0y);
//        System.out.println("1x max:" + max1x + ", min:" + min1x); System.out.println("1y max:" + max1y + ", min:" + min1y);        
//        System.out.println("2x max:" + max2x + ", min:" + min2x); System.out.println("2y max:" + max2y + ", min:" + min2y);
//        for (int i = 0; i < paclouds0x.length; ++i) {System.out.println("0x:" + paclouds0x[i] + ", 0y:" + paclouds0y[i] + ", 1x:" + paclouds1x[i] + ", 1y:" + paclouds1y[i]+ ", 2x:" + paclouds2x[i] + ", 2y:" + paclouds2y[i]); }
//        ArrayList<Float> list0x = new ArrayList<>(); ArrayList<Float> list0y = new ArrayList<>();
//        ArrayList<Float> list1x = new ArrayList<>(); ArrayList<Float> list1y = new ArrayList<>();        
//        ArrayList<Float> list2x = new ArrayList<>(); ArrayList<Float> list2y = new ArrayList<>();
//        for (int i = 0; i < pmclouds0x.length; ++i){
//            list0x.add(pmclouds0x[i]); list0y.add(pmclouds0y[i]);
//            list1x.add(pmclouds1x[i]); list1y.add(pmclouds1y[i]);               
//            list2x.add(pmclouds2x[i]); list2y.add(pmclouds2y[i]);}
//        Float max0x = Collections.max(list0x); Float min0x = Collections.min(list0x); Float max0y = Collections.max(list0y); Float min0y = Collections.min(list0y);
//        Float max1x = Collections.max(list1x); Float min1x = Collections.min(list1x); Float max1y = Collections.max(list1y); Float min1y = Collections.min(list1y);        
//        Float max2x = Collections.max(list2x); Float min2x = Collections.min(list2x); Float max2y = Collections.max(list2y); Float min2y = Collections.min(list2y);
//        float avg = 0.0f; for(Float i:list0x) {avg += i;} System.out.println("Average: " + (float)avg/(float)(list0x.size())); 
//        System.out.println("0x max:" + max0x + ", min:" + min0x); System.out.println("0y max:" + max0y + ", min:" + min0y);
//        System.out.println("1x max:" + max1x + ", min:" + min1x); System.out.println("1y max:" + max1y + ", min:" + min1y);        
//        System.out.println("2x max:" + max2x + ", min:" + min2x); System.out.println("2y max:" + max2y + ", min:" + min2y);
//        for (int i = 0; i < paclouds0x.length; ++i) {System.out.println("0x:" + paclouds0x[i] + ", 0y:" + paclouds0y[i] + ", 1x:" + paclouds1x[i] + ", 1y:" + paclouds1y[i]+ ", 2x:" + paclouds2x[i] + ", 2y:" + paclouds2y[i]); }
    }
  
    private void displayPoly(){
    List<Polygon> polygons = new ArrayList<>();
    for(int i = 0; i < 200; i++){
        for(int j = 0; j < 200; j++){
            Polygon polygon = new Polygon();
            polygon.add(new org.jzy3d.plot3d.primitives.Point(new Coord3d(paclouds0x[i], paclouds0y[i], paclouds1x[i])));
            polygon.add(new org.jzy3d.plot3d.primitives.Point(new Coord3d(paclouds1x[i], paclouds1y[i], paclouds2x[i])));
            polygon.add(new org.jzy3d.plot3d.primitives.Point(new Coord3d(paclouds2x[i], paclouds2y[i], paclouds0x[i])));
            polygons.add(polygon);
        }
    }
    // Creates the 3d object
    surface = new Shape(polygons);
    surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new org.jzy3d.colors.Color(1,1,1,.8f)));
    surface.setWireframeDisplayed(false);
    //surface.setWireframeColor(org.jzy3d.colors.Color.BLACK);
    IChartComponentFactory factory2 = new AWTChartComponentFactory();
    final Chart chart2 = new Chart(factory2, Quality.Advanced, "awt");
    chart2.getScene().getGraph().add(surface);
    chart2.setAxeDisplayed(false);
    ChartLauncher.openChart(chart2);}
    
    
        private void displayPanel(){
        final JPanel panel = new JPanel();
        final JSlider slider = new JSlider(0,9);
        slider.setSize(400, 900);
        slider.setValue(0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        setSize(420, 100);
        panel.add(slider, BorderLayout.SOUTH);
        add(panel,BorderLayout.CENTER);
        setVisible(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            setD(slider.getValue()); //CALL SETTER METHOD
            }});}
    
    private void displayF(){
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                return x * Math.sin(x * y);}};
        // Define range and precision for the function to plot
        Range range = new Range(-300, 300);
        int steps = 80;
        surface = new Shape();
        // Create the object to represent the function over the given range.
        surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);
        // Create a chartpa
        IChartComponentFactory factory = getFactory();
        final Chart chart = new Chart(factory, Quality.Advanced, "awt");
        // genMapperSurface(chartpa.getView(), chartpa.getScene().getGraph(), chartpa.getAxeLayout());
        chart.getScene().getGraph().add(surface);
        ChartLauncher.openChart(chart); 
        
    }
        








    

// </editor-fold>    
private AWTChartComponentFactory getFactory() {
        return new AWTChartComponentFactory();
    } 

    public Shape genMapperSurface(final View view, final Graph graph, final IAxeLayout layout){
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                return MAPPER_ZSCALE_FACTOR * Math.sin(x / 10) * Math.cos(y / 20) * x;
            }
        };
        Range range = new Range(-150, 150);
        int steps = 50;
     // Create the object to represent the function over the given range
        OrthonormalGrid grid = new OrthonormalGrid(range, steps, range, steps);
        OrthonormalTessellator tesselator = new OrthonormalTessellator() {
            /*protected AbstractDrawable newQuad(Point p[]) {
                AbstractDrawable quad = new TesselatedPolygon(p);
                return quad;
            }*/
        };
        surface = (Shape) tesselator.build(grid.apply(mapper));
        return createSurface(surface, view, graph, layout);
    }
    public Shape createSurface(final Shape surface, final View view, final Graph graph, final IAxeLayout layout) {
        Color factor = new Color(1, 1, 1, 0.75f);
        colormap = new ColorMapRainbow();
        colormap.setDirection(false);
        colormapper = new OrderingStrategyScoreColorMapper(colormap, new CompositeColorMapperUpdatePolicy(), graph, factor);
        surface.setColorMapper(colormapper);
        surface.setWireframeDisplayed(true);
        colorbar = new AWTColorbarLegend(surface, layout);
        surface.setLegend(colorbar);
        graph.add(surface);
        return surface;
    }

public static class MyColorFunction implements IColorMap { //Define the function to assign colors to each point
boolean colorMapDirection= true;
ColorMapper rainbowMap;
Color myColor;
float energy ;
public float zMin;
public float zMax;
double scale;
public MyColorFunction(){
this.zMin = -1.0f;
this.zMax = 1.0f;
this.rainbowMap = new ColorMapper(new ColorMapRainbow(), this.zMin, this.zMax);
this.scale= 2.0*Math.PI/100.0;
}
public void setDirection(boolean isStandard){
colorMapDirection= isStandard;
};
public boolean getDirection(){
return colorMapDirection;
}



        @Override
        public Color getColor(IColorMappable colorable, double x, double y, double z) {
energy= (float)(Math.sin(((double)x)*scale)
* Math.sin(((double)y)*scale)
* Math.sin(((double)z)*scale) );
myColor= rainbowMap.getColor(energy); // We get the color using the very useful RainbowMap
myColor.alphaSelf((float) Math.abs(energy/5.0));	// Giving some transparency to view the inside points
return myColor;        }

        @Override
        public Color getColor(IColorMappable colorable, double v) {
myColor= rainbowMap.getColor(v); // We get the color using the very useful RainbowMap
return myColor;        }

        @Override
        public double colorComponentRelative(double value, double center, double topwidth, double bottomwidth) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public double colorComponentAbsolute(double value, double bLeft, double bRight, double tLeft, double tRight) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
