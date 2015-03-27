/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.math.BigDecimal;
import math.geom2d.Box2D;
import math.geom2d.line.StraightLine2D;

/**
 *
 * @author Doreen
 */
public class Zeichnen extends Canvas {
    
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
        g3.fill(circle);}
        
    public void Dot(Graphics2D g3, Point2D point, double pointsize, float aleph){
        float alpha = aleph;
        Ellipse2D.Double circle = new Ellipse2D.Double(point.getX()-(pointsize/2), point.getY()-(pointsize/2), pointsize, pointsize);
        g3.setComposite(makeComposite(alpha));
        g3.fill(circle);
    }
    
    public void Line(Graphics2D g, Point2D p, Point2D q){
       g.setStroke(new BasicStroke(0.1F));
       Line2D todraw = new Line2D.Double(p.getX(), p.getY(), q.getX(), q.getY());
       g.draw(todraw);
    }  
    
    public void Line(Graphics2D g, Vector v){
       g.setStroke(new BasicStroke(0.01F));
       g.setComposite(makeComposite(0.2F));
       StraightLine2D todraw = new StraightLine2D(v.x[0], v.x[1], v.x[2]);
       // System.out.println("0: "  + v.x[0]+", 1: " + v.x[1]+ ", 2: " + v.x[2]);
       Box2D box = new Box2D(-1, 50, -1, 50);
       todraw.clip(box).draw(g);
    } 
    
    public void ShortLine(Graphics2D g, Vector p1, Vector p2){
       g.setStroke(new BasicStroke(0.06F));
       Point2D start = new Point2D.Double(p1.getX(), p1.getY());
       Point2D end = new Point2D.Double(p2.getX(), p2.getY());
       Line2D todraw = new Line2D.Double(start, end);
       g.draw(todraw);
    } 
    
    public void FlatOrbit(Graphics2D g, double[] arrx, double[] arry, double ps, float f){
        g.setComposite(makeComposite(f));
        for (int i = 0; i < arrx.length; ++i) {
            // System.out.println("i!!!:" + i);
            Ellipse2D.Double p = new Ellipse2D.Double(arrx[i]-(ps/2), arry[i]-(ps/2), ps, ps);
            g.fill(p);}
    } 
    
    public void FlatOrbit(Graphics2D g, BigDecimal[] arrx, BigDecimal[] arry, double ps, float f){
        g.setComposite(makeComposite(f));
        for (int i = 0; i < arrx.length-1; ++i) {
//            System.out.println("i:" + i + ", arrx: " + arrx[i]);
//            System.out.println("i:" + i + ", arry: " + arry[i]);
            double plotx = arrx[i].doubleValue();
            double ploty = arry[i].doubleValue();
            Ellipse2D.Double p = new Ellipse2D.Double(plotx-(ps/2), ploty-(ps/2), ps, ps);
            g.fill(p);
        }
    }
    
    public void DeepOrbit(Graphics2D g, BigDecimal[] arrx, BigDecimal[] arry, double ps, float f, int[] vertigo){
        g.setComposite(makeComposite(f));
                   g.setColor(Color.blue); 
                   double plotx = arrx[0].doubleValue();
                   double ploty = arry[0].doubleValue();
                   Ellipse2D.Double p = new Ellipse2D.Double(plotx, ploty, ps, ps);
                   g.fill(p);
        // for (int i = 0; i < arrx.length; ++i) {
            int vo = vertigo[0]; // change back to vertige[0]
            if (vo != 0) {
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
            // }
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
            case "Plusso":
                g.drawString("+", 536, 38);
                break;
            case "Minusso":
                g.drawString("-", 508, 38);
                break;            
            case "Plus":
                g.drawString("+", 33, 613);
                break;
            case "SizePlus":
                g.drawString("+", 33, 613);
                break;
            case "Minus":
                g.drawString("-", 12, 613);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("ZOOM", 55, 613);
                break;
            case "SizeMinus":
                g.drawString("-", 12, 613);
                g.setFont(new Font("Helvetica", Font.PLAIN, 10));
                g.setColor(Color.black);
                g.drawString("POINTSIZE", 55, 613);
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
    
//    public void ExtLine(Graphics2D g, Point2D p, Point2D q){
//       double slope, y3, y4;
//       g.setStroke(new BasicStroke(0.03F));
//       // solve point slope form for x3 = 1000 and x4 = -1000
//       slope = (q.getY() - p.getY())/(q.getX() - p.getX());
//       y3 = (slope * (1000 - p.getX())) + p.getY();
//       y4 = (slope * (-1000 - p.getX())) + p.getY();
//       // System.out.println(p.x + ", p.y  " + p.y + ", q.x  "+ q.x +", q.y  "+ q.y);
//       g.setColor(Color.LIGHT_GRAY);
//       Line2D d = new Line2D.Double(p.getX(), p.getY(), 1000, y3);
//       Line2D d2 = new Line2D.Double(p.getX(), p.getY(), -1000, y4);
//       g.setColor(Color.black);
//       d.draw(g);
//       d2.draw(g);
//     
//    } 
    
//    public void Line2DZeichnen(StraightLine2D l1){
//        l1.draw(g);
//    }
            
    
//    public Line2D ExteLine(Point2D p, Point2D q){
//       double slope, y3, y4;
//       // solve point slope form for x3 = 1000 and x4 = -1000
//       slope = (q.getY() - p.getY())/(q.getX() - p.getX());
//       y3 = (slope * (1000000 - p.getX())) + p.getY();
//       y4 = (slope * (-1000000 - p.getX())) + p.getY();
//       // System.out.println(p.x + ", p.y  " + p.y + ", q.x  "+ q.x +", q.y  "+ q.y);
//       Point2D out1 = new Point2D(1000000, y3);
//       Point2D out2 = new Point2D(-1000000, y4);
//       Line2D line = new Line2D(out1, out2);
//       // g.draw(line);
//       return line; }
       

    


}





