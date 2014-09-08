/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

/**
 *
 * @author Doreen
 */
public class Zeichnen extends Canvas {
    
    private final Color turq  = new Color( 0,  153, 153);    
    int[] px;
    int[] py;
    int np;
    
    public Zeichnen() {}

        public void Dot(Graphics2D g3, BigDecimal x1, BigDecimal y1, double pointsize, float aleph){
        double plotdoublex = x1.doubleValue();
        double plotdoubley = y1.doubleValue();
        float alpha = aleph;
        Ellipse2D.Double circle = new Ellipse2D.Double(plotdoublex-(pointsize/2), plotdoubley-(pointsize/2), pointsize, pointsize);
        g3.setComposite(makeComposite(alpha));
        g3.fill(circle);
    }
        public void Dot(Graphics2D g3, double plotdoublex, double plotdoubley, double pointsize, float aleph){
        float alpha = aleph;
        Ellipse2D.Double circle = new Ellipse2D.Double(plotdoublex-(pointsize/2), plotdoubley-(pointsize/2), pointsize, pointsize);
        g3.setComposite(makeComposite(alpha));
        g3.fill(circle);
    }
    public void Line(Graphics2D g, Complex p, Complex q){
       g.setColor(Color.black);
       g.draw(new Line2D.Double(p.x,p.y,q.x,q.y));
    } 
    
    public void FlatOrbit(Graphics2D g, double[] arrx, double[] arry, double ps, float f){
        g.setComposite(makeComposite(f));
        for (int i = 0; i < arrx.length; ++i) {
            Ellipse2D.Double p = new Ellipse2D.Double(arrx[i], arry[i], ps, ps);
            g.fill(p);}
    } 
    
    public void FlatOrbit(Graphics2D g, BigDecimal[] arrx, BigDecimal[] arry, double ps, float f){
        g.setComposite(makeComposite(f));
        for (int i = 0; i < arrx.length; ++i) {
            // System.out.println("i:" + i + ", a: " + arrx[i]);
            double plotx = arrx[i].doubleValue();
            double ploty = arry[i].doubleValue();
            Ellipse2D.Double p = new Ellipse2D.Double(plotx, ploty, ps, ps);
            g.fill(p);
        }
    }
    
    public void DeepOrbit(Graphics2D g, BigDecimal[] arrx, BigDecimal[] arry, double ps, float f, int[] vertigo){
        g.setComposite(makeComposite(f));
        for (int i = 0; i < arrx.length; ++i) {
            int vo = vertigo[i];
            if (vo != 0) {
            double plotx;
            double ploty;
            Ellipse2D.Double p;
            if ( vo == 1) {
                   g.setColor(Color.blue); 
                   plotx = arrx[0].doubleValue();
                   ploty = arry[0].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); }
            if ( vo == 2) {
                   g.setColor(Color.red); 
                   if (arrx.length > 1) {
                   plotx = arrx[1].doubleValue();
                   ploty = arry[1].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 3) {
                   g.setColor(Color.orange); 
                   if (arrx.length > 2) {
                   plotx = arrx[2].doubleValue();
                   ploty = arry[2].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 4) {
                   g.setColor(Color.MAGENTA); 
                   if (arrx.length > 3) {
                   plotx = arrx[3].doubleValue();
                   ploty = arry[3].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 5) {
                   g.setColor(new Color( 0,  153, 153));  
                   if (arrx.length > 4) {
                   plotx = arrx[4].doubleValue();
                   ploty = arry[4].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 6) {
                   g.setColor(Color.yellow); 
                   if (arrx.length > 5) {
                   plotx = arrx[5].doubleValue();
                   ploty = arry[5].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 7) {
                   g.setColor(Color.GREEN); 
                   if (arrx.length > 6) {
                   plotx = arrx[6].doubleValue();
                   ploty = arry[6].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 8) {
                   g.setColor(Color.DARK_GRAY);
                   if (arrx.length > 7) {
                   plotx = arrx[7].doubleValue();
                   ploty = arry[7].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            if ( vo == 9) {
                   g.setColor(Color.PINK);
                   if (arrx.length > 8) {
                   plotx = arrx[8].doubleValue();
                   ploty = arry[8].doubleValue();
                   p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p); } }
            }
            }
        }
     
        
    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    } 
    
    public void Bg(Graphics2D g, ListenSquare L){
          float alpha = 0.3F;
          g.setComposite(makeComposite(alpha));
          g.setColor(Color.black);
          L.renderround(g, Color.LIGHT_GRAY);
    
      }
   

    public void Bg(Graphics2D g, ListenSquare L, String s){
          float alpha = 0.90F;
          g.setComposite(makeComposite(alpha));
          g.setColor(Color.black);
          L.render(g, Color.LIGHT_GRAY);
          g.setColor(Color.white);
          g.setFont(new Font("Helvetica", Font.BOLD, 17));  
        switch (s) {
            case "Plus":
                g.drawString("+", 33, 613);
                break;
            case "SizePlus":
                g.drawString("+", 33, 589);
                break;
            case "Minus":
                g.drawString("-", 12, 613);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("ZOOM", 55, 613);
                break;
            case "SizeMinus":
                g.drawString("-", 12, 589);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("POINTSIZE", 55, 589);
                break;
            case "vPlus":
                g.drawString("+", 163, 21);
                break;
            case "nPlus":
                g.drawString("+", 203, 222);
                break;
            case "nMinus":
                g.drawString("-", 183, 222);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("N", 158, 223);
                break;
            case "vMinus":
                g.drawString("-", 143, 21);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("V", 124, 21);
                break;
        }
      }
    public Point2D.Double getIntersectionPoint(Line2D.Double line1, Line2D.Double line2) {
    if (! line1.intersectsLine(line2) ) return null;
      double s1 = line1.getX1(),
            sp2 = line1.getY1(),
            rx = line1.getX2()-s1,
            ry = line1.getY2()-sp2;
      double qx = line2.getX1(),
            qy = line2.getY1(),
            sx = line2.getX2()-qx,
            sy = line2.getY2()-qy;
      double det = sx*ry - sy*rx;
      if (det == 0) {
        return null;
      } 
      else {
        double z = (sx*(qy-sp2)+sy*(s1-qx))/det;
        if (z==0 ||  z==1) return null;  // intersection at end point!
        return new Point2D.Double(
          (double)(s1+z*rx), (double)(sp2+z*ry));
      }
    }
    
    public void ExtLine(Graphics2D g, Complex p, Complex q){
       double slope, y3, y4;
       // solve point slope form for x3 = 1000 and x4 = -1000
       slope = (q.y - p.y)/(q.x - p.x);
       y3 = (slope * (1000 - p.x)) + p.y;
       y4 = (slope * (-1000 - p.x)) + p.y;
       // System.out.println(p.x + ", p.y  " + p.y + ", q.x  "+ q.x +", q.y  "+ q.y);
       g.setColor(Color.LIGHT_GRAY);
       g.draw(new Line2D.Double(p.x, p.y, 1000, y3));
       g.draw(new Line2D.Double(p.x, p.y, -1000, y4));
       g.setColor(Color.black);
       Line2D line = new Line2D.Double(p.x,p.y,q.x,q.y);
       g.draw(line);
    }  


}





