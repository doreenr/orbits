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



public class CanvasOrbPA extends Canvas implements MouseListener, MouseMotionListener {

    Graphics2D g3;
    private int h, w;
    private final int factor;
    private double zoom, pointsize;
    private final UModel model;
    ListenSquare Plus, Minus, SizePlus, SizeMinus;
    Zeichnen zeichne;
    int referenceX, referenceY;
    double translateX, translateY;
    String[] proj;

    public CanvasOrbPA(UModel m) {
        model = m;
        addMouseListener(this);
        addMouseMotionListener(this);
        Plus = new ListenSquare(30, 600, 16, 16); // 51
        Minus = new ListenSquare(7, 600, 16, 16);
        SizePlus = new ListenSquare(30, 576, 16, 16); // 51
        SizeMinus = new ListenSquare(7, 576, 16, 16); 
        zeichne = new Zeichnen();
        factor = 10;
        zoom = 10.0;
        pointsize = 0.1;
        translateX = 0;
	translateY = 0;
        }
    

    
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
        basic.translate(w/2, h/2);
        basic.translate(translateX, translateY);
        basic.scale(1, -1);
        g.transform(basic);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, -8000, 0, 8000);
        g.drawLine(-8000, 0, 8000, 0);        
        AffineTransform zooom = new AffineTransform();
        zooom.scale(zoom, zoom);
        g.transform(zooom);
        model.displayAll(g, 2, pointsize);
    }

    public void addStuff(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        // super.setSize(width, height);
        // model.displayAll(g, 2, pointsize, iterations);
    }
    
    @Override
    public void setSize(int width, int height) {
        // super.setSize(width, height);
        zeichne.setSize(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
//        double a = e.getX();
//        double a1 = getX();
//        double b = e.getY();
//        double b1 = e.getY();
//        System.out.println("e.getx() = " + a + " getx() = " + a1);
//        System.out.println("e.gety() = " + b + " gety() = " + b1);
        MouseData J = MouseData.process(e);
        boolean test1 = Plus.inside(J.X);
        boolean test2 = Minus.inside(J.X);
        boolean test3 = SizeMinus.inside(J.X);
        boolean test4 = SizePlus.inside(J.X);
        // SOURCE = new Complex(J.X.x, J.X.y);
        // System.out.println("PA test3 = " + test3 + "  test4 = " + test4);        // SOURCE = new Complex(J.X.x, J.X.y);
        // System.out.println("test1 = " + test1 + "  test2 = " + test2);
        if(test1 == true)  {
                zoom += 1;
                // System.out.println("zoom+ " + zoom + ",  pointsize " + pointsize);
                repaint();
                }
        if(test2 == true)  {
                if (zoom > 1.0) {
                    // System.out.println("zoom-" + zoom + ",  pointsize " + pointsize);
                    zoom -= 1;
                    repaint(); }
                 }
        if(test3 == true)  {
            // System.out.println("zoom " + zoom + ",  pointsize-   " + pointsize);
            if (zoom < 50 && pointsize > 0.01) { pointsize -= 0.01; }
            else if (zoom > 100 && pointsize > 0.001) { pointsize -= 0.001; }
            else { if (pointsize > 0.01) { pointsize -= 0.01; }}
            repaint(); }        
        if(test4 == true)  {
            // System.out.println("zoom " + zoom + ",  pointsize+" + pointsize);
            if (zoom < 50) { pointsize += 0.1; }
            else if (zoom > 100) { pointsize += 0.01;}
            else { pointsize += 0.05; }
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
    
    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    } 


    

    

 



}
