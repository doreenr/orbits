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
import math.geom2d.AffineTransform2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PCanvas1 extends Canvas implements MouseListener, MouseMotionListener {
    Graphics2D g3;
    int h, w, n, v;
    private final Zeichnen zeichne;
    private final UModel model;
    private ListenSquare[] dragP;
    // private ListenSquare Plus, Minus;
    private boolean[] _canDragP;
    private boolean zoomin, zoomout;
    private double ps, zoom;
    private double[] px, py; //  p2x, p2y, p3x, p3y;
    private double referenceX, referenceY;
    private AffineTransform initial, trans, reverse, zooom;
    private AffineTransform2D u;
    AffineTransform[] A = new AffineTransform[2];
    Complex SOURCE = new Complex(0,0);
        
    
    public PCanvas1(UModel m) {
        model = m; 
        n = model.getn();
        ps = 0.2;
        addMouseListener(this);
        addMouseMotionListener(this);
        // Plus = new ListenSquare(530, 24, 20, 20); // 51
        // Minus = new ListenSquare(500, 24, 20, 20);
        zeichne = new Zeichnen();
        zoom = 1;
        referenceX = 0;
        referenceY = 0;
        px = new double[n];
        py = new double[n];
        dragP = new ListenSquare[n];
        _canDragP = new boolean[n];
        for (int i = 0; i < n; ++i) {
            _canDragP[i] = false;
            px[i]= model.getPXs(i);
            py[i]= model.getPYs(i);
        }
    }
    
    public void updateComputations() {
        // for when n changes
        if (n != model.getn()) {
        n = model.getn();
        px = new double[n];
        py = new double[n];
        dragP = new ListenSquare[n];
        _canDragP = new boolean[n];
        for (int i = 0; i < n; ++i) {
            _canDragP[i] = false;
            px[i]= model.getPXs(i);
            py[i]= model.getPYs(i);
            }}
                model.reset();
        Point2D[] ALL = model.getALL();
        Point2D[] small = model.getSmall();
        if (n == 1) {small = ALL;}
        int l = small.length;
        // System.out.println("small.length:" + small.length + ", l " + l);
        Vector[] smallv = new Vector[small.length];
        for (int i = 0; i < small.length; ++i) {
          // System.out.println("small i:" + i + ", x: " + small[i].getX() + ", y: " + small[i].getY() );
          smallv[i] = new Vector(small[i].getX(), small[i].getY()); }  
        double[] crUpsm = new double[smallv.length-5];
        for (int i = 0; i < smallv.length-5; ++i) { 
            // System.out.println("k:" + k);
            Vector exitpoint = new Vector(smallv[l-i-2]);
            Vector l1 = new Vector(Vector.cross(smallv[l-i-1], exitpoint)); 
            Vector l2 = new Vector(Vector.cross(exitpoint, smallv[l-i-3]));
            Vector l3 = new Vector(Vector.cross(exitpoint, smallv[l-i-4]));
            Vector l4 = new Vector(Vector.cross(exitpoint, smallv[l-i-5]));
            Vector m = new Vector(Vector.cross(smallv[l-i-4], smallv[l-i-5]));
            // m.print();
//            g.setColor(Color.blue);
//            zeichne.Line(g, m);
            crUpsm[i] = model.CrossRatio(m, l1, l2, l3, l4);}
            double[] crDownsm = new double[smallv.length-5];
            for (int i = 0; i < smallv.length-5; ++i) { 
            Vector exitpoint = new Vector(smallv[i+2]);
            Vector l1 = new Vector(Vector.cross(smallv[i+1], exitpoint)); 
            Vector l2 = new Vector(Vector.cross(exitpoint, smallv[i+3]));
            Vector l3 = new Vector(Vector.cross(exitpoint, smallv[i+4]));
            Vector l4 = new Vector(Vector.cross(exitpoint, smallv[i+5]));
            Vector m = new Vector(Vector.cross(smallv[i+4], smallv[i+5]));
//            g.setColor(Color.red);
//            zeichne.Line(g, m);
            crDownsm[i] = model.CrossRatio(m, l1, l2, l3, l4);}
        model.setFlagsUsm(crUpsm);
        model.setFlagsDsm(crDownsm);
        // for now transform Point2D array to Point / extract 3-vector given in homogeneous coordinates 
        Vector[] homAll = new Vector[ALL.length];
        int k = ALL.length;
        // zeichne edges
        for (int i = 0; i < ALL.length; ++i) {
          // System.out.println("CANVAS ALL i:" + i + ", x: " + ALL[i].getX() + ", y: " + ALL[i].getY() );
          homAll[i] = new Vector(ALL[i].getX(), ALL[i].getY());
          // if (0 < i && i < k+1) { g.setColor(Color.black); zeichne.ShortLine(g, homAll[i-1], homAll[i]); }
        }
        // zeichne vertices
        //for (int i = 0; i < homAll.length; ++i) { g.setColor(Color.black); zeichne.Dot(g, homAll[i].getX(), homAll[i].getY(), 0.3, 0.9f); }
        // compute cross ratio UPWARDS
        double[] crUp = new double[homAll.length-5];
        for (int i = 0; i < homAll.length-5; ++i) { 
            // System.out.println("k:" + k);
            Vector exitpoint = new Vector(homAll[k-i-2]);
            Vector l1 = new Vector(Vector.cross(homAll[k-i-1], exitpoint)); 
            Vector l2 = new Vector(Vector.cross(exitpoint, homAll[k-i-3]));
            Vector l3 = new Vector(Vector.cross(exitpoint, homAll[k-i-4]));
            Vector l4 = new Vector(Vector.cross(exitpoint, homAll[k-i-5]));
            Vector m = new Vector(Vector.cross(homAll[k-i-4], homAll[k-i-5]));
//          l1.print();l2.print();l3.print();l4.print(); 
//             m.print();
//            g.setColor(Color.red); zeichne.Line(g, l1); zeichne.Line(g, l2); zeichne.Line(g, l3); zeichne.Line(g, l4);
//            g.setColor(Color.blue);
//            zeichne.Line(g, m);
            crUp[i] = model.CrossRatio(m, l1, l2, l3, l4);
        }
        // compute cross ratio downwards
        double[] crDown = new double[homAll.length-5];
        for (int i = 0; i < homAll.length-5; ++i) { 
            // System.out.println("k:" + k + ", i " + i);
            Vector exitpoint = new Vector(homAll[i+2]);
            Vector l1 = new Vector(Vector.cross(homAll[i+1], exitpoint)); 
            Vector l2 = new Vector(Vector.cross(exitpoint, homAll[i+3]));
            Vector l3 = new Vector(Vector.cross(exitpoint, homAll[i+4]));
            Vector l4 = new Vector(Vector.cross(exitpoint, homAll[i+5]));
            Vector m = new Vector(Vector.cross(homAll[i+4], homAll[i+5]));
//            l1.print();l2.print();l3.print(); 
//            System.out.print("exitpoint: "); exitpoint.print();
//            System.out.print("homAll[i+5]: "); homAll[i+5].print();
//            System.out.print("l4: "); l4.print(); 
//            m.print();
//            g.setColor(Color.orange); 
//            zeichne.Line(g, l1); zeichne.Line(g, l2); zeichne.Line(g, l3); zeichne.Line(g, l4);
//            g.setColor(Color.blue);
//            zeichne.Line(g, m);
            crDown[i] = model.CrossRatio(m, l1, l2, l3, l4);
            // System.out.println(crUp[i]);
        }
        model.setFlagsU(crUp);
        model.setFlagsD(crDown);
        // System.out.println("crUp.length: " + (crUp.length) + ", crDown.length: " + (crDown.length));
//          allcross = new double[crUp.length+crDown.length];          
//          for (int i = 0; i < allcross.length; ++i) {
//              if (i % 2 == 0) {allcross[i] = crUp[i/2];}              
//              if (i % 2 == 1) {allcross[i] = crDown[i/2];}}// }
          // Put back later      
//        g.setColor(Color.RED);
//        zeichne.Dot(g3, p1x, p1y, ps, 0.9f);
//        zeichne.Dot(g3, p2x, p2y, ps, 0.9f);
//        zeichne.Dot(g3, p3x, p3y, ps, 0.9f);
    
    }
    
    @Override
    public void paint(Graphics g2) {
        updateComputations();
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        initial = g.getTransform();
        if (n < 5) { trans = AffineTransform.getTranslateInstance(-100, 660); }
        else if (n == 5) {trans = AffineTransform.getTranslateInstance(-150, 640); trans.scale(1.1, 1.1);}
        else if (n == 6) {trans = AffineTransform.getTranslateInstance(-280, 860); trans.scale(1.2, 1.2);}
        else if (n == 7) {trans = AffineTransform.getTranslateInstance(-280, 840);}
        else if (n == 8) {trans = AffineTransform.getTranslateInstance(-320, 880);}
        else if (n == 9) {trans = AffineTransform.getTranslateInstance(-340, 920);}
        trans.scale(20, -20);
//        if (zoom != 0) { trans.scale(zoom, zoom);}
//        if (referenceX != 0) { trans.translate(referenceX, referenceY);}
        g.setTransform(trans);
        try {
            reverse = trans.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("excäption");
            Logger.getLogger(PCanvas1.class.getName()).log(Level.SEVERE, null, ex); }
        for (int i = 0; i < dragP.length; ++i) {
            dragP[i] = new ListenSquare(px[i], py[i], ps*6, ps*6);
            zeichne.Bg(g, dragP[i]);}

    }
    
    

    

    @Override
    public void mousePressed(MouseEvent e) {   
        java.awt.Point.Double MouseU = new java.awt.Point.Double();
        reverse.transform(new java.awt.Point.Double(e.getX(), e.getY()), MouseU);
        int countfalse = 0;
        for (int i = 0; i < dragP.length; ++i) {
            boolean test = dragP[i].inside(MouseU); 
//            System.out.println("i " + i + ", test: " + test);
//            System.out.println("referenceX.x: " + MouseU.x + ", referenceX.y: " + MouseU.y);
//            System.out.println("dragP.x: " + dragP[i].x + ", dragP.y: " + dragP[i].y);
            _canDragP[i] = test;
            if (test == true) {referenceX = MouseU.x; referenceY = MouseU.y;}
            else countfalse += 1;        }
//        if (countfalse == dragP.length) {
//            zoomin = model.zoomIn();
//            zoomout = model.zoomOut();
//            if(zoomin == true)  {
//            if (zoom >= 1.0) { zoom += 0.1; 
//                referenceX -= 0.1;
//                referenceY -= 0.1;
//                repaint(); }
//                }
//         if(zoomout == true)  {
//                if (zoom >= 0.03) {zoom -= 0.1;  
//                referenceX += 0.1;
//                referenceY += 0.1;
//                repaint();}              
//                }
//        }
    }
        
    
    

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // _canDragP1 = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        java.awt.Point.Double MouseU = new java.awt.Point.Double();
        reverse.transform(new java.awt.Point(e.getX(), e.getY()), MouseU);
        for (int i = 0; i < _canDragP.length; ++i) {
            if (_canDragP[i]) {
                px[i] = MouseU.getX();
                py[i] = MouseU.getY();
                px[i] = Math.max(px[i], 0);
                px[i] = Math.min(px[i], getWidth() - ps*5);                
                py[i] = Math.max(py[i], 0);
                py[i] = Math.min(py[i], getWidth() - ps*5);
                this.repaint();
                System.out.println("i:" + i + ", px: " + px[i] + ", py: " + py[i]);
                model.setPXs(px[i], i);
                model.setPYs(py[i], i);
                model.reset();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {
        MouseData J = MouseData.process(e);
        if(zoomin == true) scaleUp(J.X,-1);
        if(zoomout == true) scaleUp(J.X,+1);
    }

    
    public void setScales(int m, int n) {
	A[0]=AffineTransform.getTranslateInstance(m,m);
	A[1]=AffineTransform.getScaleInstance(n,-n);
    }
    
    public Complex unTransform(java.awt.Point X) {
       double ux=A[0].getTranslateX();
       double uy=A[0].getTranslateY();
       double tx=X.x;
       double ty=X.y;
       double sx=A[1].getScaleX();
       double sy=A[1].getScaleY();
       ux=(ux-tx)+tx;
       uy=(uy-ty)+ty;
       tx=tx-ux;
       ty=ty-uy;
       tx=tx/sx;
       ty=ty/sy;
       return(new Complex(tx,ty));
    }
    
    public void scaleUp(java.awt.Point X, int k) {
	double scale = Math.pow(2,0.25);
	double ss = scale;
	if(k==1) ss = 1/scale;
	double sx = A[1].getScaleX();
	double sy = A[1].getScaleY();
	double tx = X.x;
	double ty = X.y;
	double ux0 = A[0].getTranslateX();
	double uy = A[0].getTranslateY();
	double ux1 = ss*(ux0-tx)+tx;
	uy=ss*(uy-ty)+ty;
	sx=sx*ss;
        sy=sy*ss;
	A[1]=AffineTransform.getScaleInstance(sx,sy);
	A[0]=AffineTransform.getTranslateInstance(ux1,uy);
	repaint();
    }
    
//    public GeneralPath transform(GeneralPath H) {
//	GeneralPath HH = new GeneralPath(H);
//	HH.transform(A[1]);   //scale
//	HH.transform(A[0]);   //translate
//	return(HH);
//    }
//    
//    public void drawSource(Graphics2D g, Color C, Complex z, double d) {
//	GeneralPath gp = new GeneralPath();
//	float x=(float)(z.x);
//	float y=(float)(z.y);
//	gp.moveTo((float)(x-d),(float)(y-d));
//	gp.lineTo((float)(x-d),(float)(y+d));
//	gp.lineTo((float)(x+d),(float)(y+d));
//	gp.lineTo((float)(x+d),(float)(y-d));
//	gp.closePath();
//	gp = transform(gp);
//	g.setColor(C);
//	g.draw(gp);
//    }
    
    public void doScale(MouseEvent e) {

//	if(mode == 2) {
//            Complex temp = unTransform(J.X);
//	    SOURCE = temp;}
    }

}
