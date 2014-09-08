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
import java.awt.geom.NoninvertibleTransformException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PCanvas extends Canvas implements MouseListener, MouseMotionListener {

    Graphics2D g3;
    int h, w, n, v, ps;
    private Zeichnen zeichne;
    private final UModel model;
    private ListenSquare dragP1, dragP2, dragP3;
    private BigDecimal[] P1, P2, P3, P4, P5, P6, P7, P8, P9;
    private boolean _canDragP1  = false;
    private boolean _canDragP2  = false;
    private boolean _canDragP3  = false;
    private double translateX, translateY;
    private double p1x, p1y, p2x, p2y, p3x, p3y;
    private int referenceX, referenceY;
    private AffineTransform initial, trans, reverse;

    public PCanvas(UModel m) {
        model = m; 
        n = model.getn();
        v = model.getv();
        ps = 1;
        addMouseListener(this);
        addMouseMotionListener(this);
        zeichne = new Zeichnen();
//        P1 = new BigDecimal[2]; P1[0] = new BigDecimal("5"); P1[1] = new BigDecimal("10"); 
//        P2 = new BigDecimal[2]; P2[0] = new BigDecimal("6"); P2[1] = new BigDecimal("6"); 
//        P3 = new BigDecimal[2]; P3[0] = new BigDecimal("10"); P3[1] = new BigDecimal("5"); 
        p1x = model.getPXs(1);
        p1y = model.getPYs(1);
        p2x = model.getPXs(2);
        p2y = model.getPYs(2);
        p3x = model.getPXs(3);
        p3y = model.getPYs(3);

    }
    
    @Override
    public void paint(Graphics g2) {
        
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        initial = g.getTransform();
        trans = AffineTransform.getTranslateInstance(10, 410);
        trans.scale(4, -4);
        g.setTransform(trans);
        try {
            reverse = trans.createInverse();
        } catch (NoninvertibleTransformException ex) {
            Logger.getLogger(PCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        dragP1 = new ListenSquare(p1x, p1y, ps*3, ps*3);
        dragP2 = new ListenSquare(p2x, p2y, ps*3, ps*3);
        dragP3 = new ListenSquare(p3x, p3y, ps*3, ps*3);
        zeichne.Bg(g, dragP1); zeichne.Bg(g, dragP2); zeichne.Bg(g, dragP3);
        g.setColor(new Color(25, 25, 112));
        zeichne.Dot(g3, p1x, p1y, ps, 0.9f);
        zeichne.Dot(g3, p2x, p2y, ps, 0.9f);
        zeichne.Dot(g3, p3x, p3y, ps, 0.9f);
//        System.out.println("p1x: " + p1x + ", p1y: " + p1y);
//        System.out.println("p2x: " + p2x + ", p2y: " + p2y);
//        System.out.println("p3x: " + p3x + ", p3y: " + p3y);
        model.displayTail(g3);
        } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // MouseData J = MouseData.process(e);
        // boolean test1 = vPlus.inside(J.X);
        // boolean test2 = vMinus.inside(J.X);
        // boolean test3 = nMinus.inside(J.X);
        // boolean test4 = nPlus.inside(J.X);
        }
    

    @Override
    public void mousePressed(MouseEvent e) {        
        Point MouseU = new Point();
        reverse.transform(new Point(e.getX(), e.getY()), MouseU);
        boolean test1 = dragP1.inside(MouseU);
        boolean test2 = dragP2.inside(MouseU);
        boolean test3 = dragP3.inside(MouseU);
        if (test1 == true) {
            _canDragP1 = true;
            referenceX = MouseU.x;
            referenceY = MouseU.y; }
        else { _canDragP1 = false; }
        
        if (test2 == true) {
            _canDragP2 = true;
            referenceX = MouseU.x;
            referenceY = MouseU.y; }
        else { _canDragP2 = false; }
        
    
        if (test3 == true) {
            _canDragP3 = true;
            referenceX = MouseU.x;
            referenceY = MouseU.y; }
        else { _canDragP3 = false; }
        }
    

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        _canDragP1 = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point MouseU = new Point();
        reverse.transform(new Point(e.getX(), e.getY()), MouseU);
        if (_canDragP1) {
            p1x = MouseU.x;
            p1y = MouseU.y;
            p1x = Math.max(p1x, 0);
            p1x = Math.min(p1x, getWidth() - ps*3);
            p1y = Math.max(p1y, 0);
            p1y = Math.min(p1y, getHeight() - ps*3); 
            // System.out.println("p1x: " + p1x + ", p1y: " + p1y);
            this.repaint();
            model.setPXs(p1x, 1);
            model.setPYs(p1y, 1);
            model.reset();
        }
        if (_canDragP2) {
            p2x = MouseU.x;
            p2y = MouseU.y;
            p2x = Math.max(p2x, 0);
            p2x = Math.min(p2x, getWidth() - ps*3);
            p2y = Math.max(p2y, 0);
            p2y = Math.min(p2y, getHeight() - ps*3); 
            // System.out.println("p1x: " + p1x + ", p1y: " + p1y);
            this.repaint();
            model.setPXs(p2x, 2);
            model.setPYs(p2y, 2);
            model.reset();
        }       
        if (_canDragP3) {
            p3x = MouseU.x;
            p3y = MouseU.y;
            p3x = Math.max(p3x, 0);
            p3x = Math.min(p3x, getWidth() - ps*3);
            p3y = Math.max(p3y, 0);
            p3y = Math.min(p3y, getHeight() - ps*3); 
            
            this.repaint();
            model.setPXs(p3x, 3);
            model.setPYs(p3y, 3);
            model.reset();
        }        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
