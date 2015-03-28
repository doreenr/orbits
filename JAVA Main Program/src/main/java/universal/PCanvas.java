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
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public final class PCanvas extends Canvas implements MouseListener, MouseMotionListener {
    Graphics2D g3;
    int h, w, n, v;
    private final Zeichnen zeichne;
    private final UModel model;
    private ListenSquare[] dragPSCREEN, dragPCOMP;
    // private ListenSquare Plus, Minus;
    private boolean[] _canDragP;
    private boolean zoomin, zoomout, drag;
    private double ps;
    private double[] px, py; //  p2x, p2y, p3x, p3y;
    AffineTransform[] A = new AffineTransform[2];
    Complex SOURCE = new Complex(0,0);
    Point2D SOURCED = new Point2D.Double(0,0);
    private GeneralPath GG;
    private VectorCx[] Ps;
        
    
    public PCanvas(UModel m) {
        model = m; 
        n = model.getn();
        ps = 0.2;
        addMouseListener(this);
        addMouseMotionListener(this);
        // Plus = new ListenSquare(530, 24, 20, 20); // 51
        // Minus = new ListenSquare(500, 24, 20, 20);
        zeichne = new Zeichnen();
        px = new double[n];
        py = new double[n];
        dragPSCREEN = new ListenSquare[n];
        dragPCOMP = new ListenSquare[n];
        _canDragP = new boolean[n];
        for (int i = 0; i < n; ++i) {
            _canDragP[i] = false;
            px[i]= model.getPXs(i);
            py[i]= model.getPYs(i);
        }
        if (n < 5) {
        A[0]=AffineTransform.getTranslateInstance(-120, 600);
        A[1]=AffineTransform.getScaleInstance(20, -20);        
        }
        updateComputations();
    }
    
    public void updateComputations() {
        // for when n changes
        if (n != model.getn()) {
        n = model.getn();
        px = new double[n];
        py = new double[n];
        dragPSCREEN = new ListenSquare[n];
        dragPCOMP = new ListenSquare[n];
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
        VectorCx[] smallv = new VectorCx[small.length];
        for (int i = 0; i < small.length; ++i) {
          smallv[i] = new VectorCx(small[i].getX(), small[i].getY()); }  
        double[] crUpsm = new double[smallv.length-5];
        for (int i = 0; i < smallv.length-5; ++i) { 
            VectorCx exitpoint = new VectorCx(smallv[l-i-2]);
            VectorCx l1 = new VectorCx(VectorCx.cross(smallv[l-i-1], exitpoint)); 
            VectorCx l2 = new VectorCx(VectorCx.cross(exitpoint, smallv[l-i-3]));
            VectorCx l3 = new VectorCx(VectorCx.cross(exitpoint, smallv[l-i-4]));
            VectorCx l4 = new VectorCx(VectorCx.cross(exitpoint, smallv[l-i-5]));
            VectorCx m = new VectorCx(VectorCx.cross(smallv[l-i-4], smallv[l-i-5]));
            crUpsm[i] = model.CrossRatio(m, l1, l2, l3, l4);}
            double[] crDownsm = new double[smallv.length-5];
            for (int i = 0; i < smallv.length-5; ++i) { 
            VectorCx exitpoint = new VectorCx(smallv[i+2]);
            VectorCx l1 = new VectorCx(VectorCx.cross(smallv[i+1], exitpoint)); 
            VectorCx l2 = new VectorCx(VectorCx.cross(exitpoint, smallv[i+3]));
            VectorCx l3 = new VectorCx(VectorCx.cross(exitpoint, smallv[i+4]));
            VectorCx l4 = new VectorCx(VectorCx.cross(exitpoint, smallv[i+5]));
            VectorCx m = new VectorCx(VectorCx.cross(smallv[i+4], smallv[i+5]));
            crDownsm[i] = model.CrossRatio(m, l1, l2, l3, l4);}
        model.setFlagsUsm(crUpsm);
        model.setFlagsDsm(crDownsm);
        // for now transform Point2D array to Point / extract 3-vector given in homogeneous coordinates 
        Ps = new VectorCx[ALL.length];
        int k = ALL.length;
        // zeichne edges
        for (int i = 0; i < ALL.length; ++i) {
          // System.out.println("CANVAS ALL i:" + i + ", x: " + ALL[i].getX() + ", y: " + ALL[i].getY() );
          Ps[i] = new VectorCx(ALL[i].getX(), ALL[i].getY());
        }
        // GG = VectorCx.toGeneralPath(Ps);
        // zeichne vertices //for (int i = 0; i < Ps.length; ++i) { g.setColor(Color.black); zeichne.Dot(g, Ps[i].getX(), Ps[i].getY(), 0.3, 0.9f); }
        // compute cross ratio UPWARDS
        double[] crUp = new double[Ps.length-5];
        for (int i = 0; i < Ps.length-5; ++i) { 
            VectorCx exitpoint = new VectorCx(Ps[k-i-2]);
            VectorCx l1 = new VectorCx(VectorCx.cross(Ps[k-i-1], exitpoint)); 
            VectorCx l2 = new VectorCx(VectorCx.cross(exitpoint, Ps[k-i-3]));
            VectorCx l3 = new VectorCx(VectorCx.cross(exitpoint, Ps[k-i-4]));
            VectorCx l4 = new VectorCx(VectorCx.cross(exitpoint, Ps[k-i-5]));
            VectorCx m = new VectorCx(VectorCx.cross(Ps[k-i-4], Ps[k-i-5]));
            crUp[i] = model.CrossRatio(m, l1, l2, l3, l4); }
        // compute cross ratio downwards
        double[] crDown = new double[Ps.length-5];
        for (int i = 0; i < Ps.length-5; ++i) { 
            VectorCx exitpoint = new VectorCx(Ps[i+2]);
            VectorCx l1 = new VectorCx(VectorCx.cross(Ps[i+1], exitpoint)); 
            VectorCx l2 = new VectorCx(VectorCx.cross(exitpoint, Ps[i+3]));
            VectorCx l3 = new VectorCx(VectorCx.cross(exitpoint, Ps[i+4]));
            VectorCx l4 = new VectorCx(VectorCx.cross(exitpoint, Ps[i+5]));
            VectorCx m = new VectorCx(VectorCx.cross(Ps[i+4], Ps[i+5]));
            crDown[i] = model.CrossRatio(m, l1, l2, l3, l4); }
        model.setFlagsU(crUp);
        model.setFlagsD(crDown);
    }
    
    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(1.1f));
        drawSource(g);
        drawDrags(g);
    }
    
    public void drawSource(Graphics2D g) {
	GeneralPath gp = new GeneralPath();
        gp.moveTo((Ps[0].getX()),(Ps[0].getY()));
        // System.out.println("drawSource Ps0: " + Ps[0].getX() + ", " + Ps[0].getY());
        for (int i = 1; i < Ps.length; ++i) {
            gp.lineTo((Ps[i].getX()),(Ps[i].getY()));} 
	gp = transform(gp);
        g.setColor(Color.black);
	g.draw(gp);
    }
    
    public void drawDrags(Graphics2D g) {
        for (int i = 0; i < n; ++i) {
            Point2D p = new Point2D.Double(px[i], py[i]);
            dragPCOMP[i] = new ListenSquare(p.getX(), p.getY(), 40, 40);
            p = transform(p);
            // dragPSCREEN is now in screen coordinates
            dragPSCREEN[i] = new ListenSquare(p.getX(), p.getY(), 40, 40);
            zeichne.Bg(g, dragPSCREEN[i]);
            g.setColor(Color.black);
            zeichne.Dot(g, p, 4, 0.9f);
            }}
    
    public Point2D transform(Point2D before) {
        Point2D after = new Point2D.Double();
        Point2D after2 = new Point2D.Double();
	after = A[1].transform(before, after);   //scale
	after2 = A[0].transform(after, after2);   //scale
	return(after2);
    }
    
    public GeneralPath transform(GeneralPath H) {
	GeneralPath HH = new GeneralPath(H);
	HH.transform(A[1]);   //scale
	HH.transform(A[0]);   //translate
	return(HH);
    }
        
    @Override
    public void mousePressed(MouseEvent e) {
        MouseData J = MouseData.process(e, 1);
        // System.out.println("MouseEvent: " +e.getX() + ", " + e.getY());
        // System.out.println("MouseEvent: SOURCE " +SOURCE.x + ", SOURCE " + SOURCE.y);
        zoomin = model.zoomIn();
        zoomout = model.zoomOut();
        drag = model.Drag();
        if (zoomin == true) {scaleUp(J.getXX(), -1); zoomout = false; drag = false;}
        else if (zoomout == true) {scaleUp(J.getXX(), +1); zoomin = false; drag = false;}
        else if (drag == true) {
            zoomin = false; 
            zoomout = false;
            for (int i = 0; i < dragPSCREEN.length; ++i) {
            // System.out.println("click  untransformed   x: " + unTransform(J.getXX()).x + ", y: " + unTransform(J.getXX()).y);
            boolean test = dragPCOMP[i].inside(unTransform(J.getXX()));
            // System.out.println("post   t.x: " + unTransform(J.X, 1).x + ", C t.y: " + unTransform(J.X, 1).y);
            _canDragP[i] = test;
            // System.out.println("========= test: " + test);        
        }}}
     
    @Override
    public void mouseDragged(MouseEvent e) {
        MouseData J = MouseData.process(e, 1);
        Point2D temp = unTransform(J.getXX());
        for (int i = 0; i < _canDragP.length; ++i) {
            if (_canDragP[i]) {
                px[i] = temp.getX();
                py[i] = temp.getY();
                px[i] = Math.max(px[i], 0);
                px[i] = Math.min(px[i], getWidth() - ps*5);                
                py[i] = Math.max(py[i], 0);
                py[i] = Math.min(py[i], getWidth() - ps*5);
                
                // System.out.println("i:" + i + ", px: " + px[i] + ", py: " + py[i]);
                model.setPXs(px[i], i);
                model.setPYs(py[i], i);
                model.reset();
                updateComputations();
                this.repaint();
                
            }}}
    
    

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
    public void mouseMoved(MouseEvent e) {}
    
    @Override
    public void mouseClicked(MouseEvent e) {
//        MouseData J = MouseData.process(e);
//        if(zoomin == true) scaleUp(J.X,-1);
//        if(zoomout == true) scaleUp(J.X,+1);
//        Complex temp = unTransform(J.X);
// 	SOURCE = temp;
    }

    public void setScales(int m, int n) {
	A[0]=AffineTransform.getTranslateInstance(m, m);
	A[1]=AffineTransform.getScaleInstance(n,-n);
    }
    
    public Complex unTransform(java.awt.Point X) {
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
       return(new Complex(tx,ty));
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
    
    public void doScale(MouseEvent e) {

//	if(mode == 2) {
//            Complex temp = unTransform(J.X);
//	    SOURCE = temp;}
    }

}
