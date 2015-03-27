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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class CanvasOrbPA extends Canvas implements MouseListener, MouseMotionListener {

    Graphics2D g3;
    private int h, w;
    private double pointsize;
    private final UModel model;
    Zeichnen zeichne;
    AffineTransform[] A = new AffineTransform[2];
    private Point2D[] puns;

    public CanvasOrbPA(UModel m) {
        addMouseListener(this);
        addMouseMotionListener(this);
        zeichne = new Zeichnen();
        model = m; 
        h = getSize().height;       
	w = getSize().width;
        A[0]=AffineTransform.getTranslateInstance(300, 400);
        A[1]=AffineTransform.getScaleInstance(3, -3); 
        }
    

    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        h = getSize().height;       
	w = getSize().width; 
        drawKreuz(g);
        puns = model.getDisplay(2);
        if (puns[0] != null) {
        drawOrbit(g);}
  
    }

    
    public void drawKreuz(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        Point2D one = new Point2D.Double(0, -1000); one = transform(one);
        Point2D two = new Point2D.Double(0, 1000); two = transform(two);
        Point2D three = new Point2D.Double(-1000, 0); three = transform(three);
        Point2D four = new Point2D.Double(1000, 0); four = transform(four);
        Line2D l1 = new Line2D.Double(one,two); g.draw(l1);
        l1 = new Line2D.Double(three,four); g.draw(l1);
    }
    
    public void drawOrbit(Graphics2D g) {
            pointsize = model.getps();
            for (int i = 0; i < puns.length; ++i) {
            Point2D p = new Point2D.Double(puns[i].getX(), puns[i].getY());
            Point2D p2 = transform(p);
            g.setColor(new Color(0,0,130));
            zeichne.Dot(g, p2, pointsize, 0.1f);
    }}
    
    public Point2D transform(Point2D before) {
        Point2D after = new Point2D.Double();
        Point2D after2 = new Point2D.Double();
	after = A[1].transform(before, after);   //scale
	after2 = A[0].transform(after, after2);   //scale
	return(after2);
    }
    public void setScales(int m, int n) {
	A[0]=AffineTransform.getTranslateInstance(m, m);
	A[1]=AffineTransform.getScaleInstance(n,-n);
    }
    
    public Point2D.Double unTransform(Point2D.Double X) {
       double ux = A[0].getTranslateX();
       double uy = A[0].getTranslateY();
       double tx = X.x;
       double ty = X.y;
       double sx = A[1].getScaleX();
       double sy = A[1].getScaleY();
       ux=(ux-tx)+tx;
       uy=(uy-ty)+ty;
       tx=tx-ux;
       ty=ty-uy;
       tx=tx/sx;
       ty=ty/sy;
       return(new Point2D.Double(tx,ty));
    }
    
    public void scaleUp(Point2D X, int k) {
	double scale = Math.pow(2, 0.25);
	double ss = scale;
	if (k==1) ss = 1/scale;
	double sx = A[1].getScaleX();
	double sy = A[1].getScaleY();
        // System.out.println("ScaleUp: getScale "+sx + ", " + sy);
	double tx = X.getX();
	double ty = X.getY();
	double ux0 = A[0].getTranslateX();
	double uy = A[0].getTranslateY();
        // System.out.println("ScaleUp: getTransl "+ux0 + ", " + uy);
	double ux1 = ss*(ux0-tx)+tx;
	uy=ss*(uy-ty)+ty;
	sx=sx*ss;
        sy=sy*ss;
        // System.out.println("ScaleUp: setScale "+sx + ", " + sy);
        // System.out.println("ScaleUp: setTransl "+ux1 + ", " + uy);
	A[1]=AffineTransform.getScaleInstance(sx,sy);
	A[0]=AffineTransform.getTranslateInstance(ux1,uy);
	repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        MouseData J = MouseData.process(e, 1);
        boolean zoomin = model.zoomInPA();
        boolean zoomout = model.zoomOutPA();
        if (zoomin == true) {scaleUp(J.getXX(), -1); zoomout = false; }
        else if (zoomout == true) {scaleUp(J.getXX(), +1); zoomin = false; }
    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
    
    @Override
        public void mouseDragged(MouseEvent e) {    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    } 


    

    

 



}
