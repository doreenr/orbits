/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;



public class CanvasOrbPM1 extends Canvas implements MouseListener, MouseMotionListener {

    Graphics2D g3;
    private int h, w, factor;
    private double zoom, pointsize;
    private final UModel model;
    ListenSquare Plus, Minus, SizePlus, SizeMinus;
    Zeichnen zeichne;
    int referenceX, referenceY;
    double translateX, translateY;
    AffineTransform[] A = new AffineTransform[2];
    private Vector[] Ps;
    
    public CanvasOrbPM1(UModel m) {
        addMouseListener(this);
        addMouseMotionListener(this);
        Plus = new ListenSquare(30, 600, 16, 16); // 51
        Minus = new ListenSquare(7, 600, 16, 16);
        SizePlus = new ListenSquare(30, 576, 16, 16); // 51
        SizeMinus = new ListenSquare(7, 576, 16, 16);        
        zeichne = new Zeichnen();
        model = m; 
        factor = 10;
        zoom = 10.0;
        pointsize = 0.005;
        translateX = 0;
	translateY = 0;
        h = getSize().height;       
	w = getSize().width;
        A[0]=AffineTransform.getTranslateInstance(w/2-200, h/2);
        A[1]=AffineTransform.getScaleInstance(20, -20); 
        updateComputations();
	//scale = 1;
    }
    
    public void updateComputations() {
        // for when n changes}
    }
    
    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        h = getSize().height;       
	w = getSize().width; 
        zeichne.Bg(g, SizeMinus, "SizeMinus");
        zeichne.Bg(g, SizePlus, "SizePlus");        
        zeichne.Bg(g, Minus, "Minus");
        zeichne.Bg(g, Plus, "Plus");
        AffineTransform basic = new AffineTransform();
        basic.translate(translateX, translateY);
        basic.scale(0.4, -0.4);
        g.transform(basic);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, -8000, 0, 8000);
        g.drawLine(-8000, 0, 8000, 0); 
        basic.scale(190, 190);
        g.transform(basic);
        AffineTransform zooom = new AffineTransform();
        zooom.scale(zoom, zoom);
        g.transform(zooom);
        //AffineTransform at = new AffineTransform();
        //g.transform(at);
        // System.out.println("new canvas repaint PM");
        // model.displayAll(g, 3, pointsize);
        model.displayAll(g, 1, pointsize);
               
    }
//    public static Complex[] getInstance() {
//        return pi;
//    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        // g3.setSize(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
        // MouseData J = MouseData.process(e);
        java.awt.Point.Double MouseU = new java.awt.Point.Double(e.getX(), e.getY());
        boolean test1 = Plus.inside(MouseU);
        boolean test2 = Minus.inside(MouseU);
        boolean test3 = SizeMinus.inside(MouseU);
        boolean test4 = SizePlus.inside(MouseU);        
//        boolean test1 = Plus.inside(J.X);
//        boolean test2 = Minus.inside(J.X);
//        boolean test3 = SizeMinus.inside(J.X);
//        boolean test4 = SizePlus.inside(J.X);
        if(test1 == true)  {
            if (zoom > 1.0) {    
                zoom += 1; 
                // System.out.println("zoom+ " + zoom + ",  pointsize " + pointsize);
                repaint(); }
                if (zoom <= 1.0) 
                { zoom += 0.1; 
                repaint();  }
                }
        if(test2 == true)  {
                if (zoom > 1.0) {
                    // System.out.println("- zoom " + zoom + ",  pointsize " + pointsize);
                    zoom -= 1;  
                    // pointsize += 1;
                    repaint();}              
                if (zoom <= 1.0 && zoom > 0.01) {
                    // System.out.println("zoom/ " + zoom + ",  pointsize " + pointsize);
                    zoom = zoom - 0.01;
                    pointsize += 0.1;
                    // System.out.println("nach small zoom " + zoom + ",  pointsize " + pointsize);
                    repaint(); }
                 }
        if(test3 == true)  {
            // System.out.println("zoom minus " + zoom + ",  pointsize-   " + pointsize);
            if (zoom < 50 && pointsize > 0.01) { pointsize -= 0.01; }
            else if (zoom < 50 && pointsize > 0.001) { pointsize -= 0.001; }
            else if (zoom > 100 && pointsize > 0.001) { pointsize -= 0.001; }
            else { if (pointsize > 0.01) { pointsize -= 0.01; }}
            repaint(); }        
        if(test4 == true)  {
            System.out.println("more pointsize ... before zoom " + zoom + ",  pointsize+" + pointsize);
            if (zoom < 50) { pointsize += 0.001; }
            if (zoom < 20) { pointsize += 0.001; }
            if (zoom < 1) { pointsize += 0.1; }
            else if (zoom > 100) { pointsize += 0.01;}
            else { pointsize += 0.01; }
            repaint(); }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    	    referenceX = e.getX();
	    referenceY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.println("yes");
        int deltaX = e.getX() - referenceX;
	int deltaY = e.getY() - referenceY;
	referenceX = e.getX();
	referenceY = e.getY();
        translateX += deltaX;
	translateY += deltaY;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
