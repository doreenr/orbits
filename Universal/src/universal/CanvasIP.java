/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;



public class CanvasIP extends Canvas implements MouseListener {

    Graphics2D g3;
    int h, w, n, v;
    Zeichnen zeichne;
    private final UModel model;
    ListenSquare vPlus, vMinus, nPlus, nMinus;

    public CanvasIP(UModel m) {
        model = m; 
        n = model.getn();
        v = model.getv();
        addMouseListener(this);
        // vPlus = new ListenSquare(160, 8, 16, 16); // 51
        // vMinus = new ListenSquare(138, 8, 16, 16);
        nPlus = new ListenSquare(200, 210, 16, 16); // 51
        nMinus = new ListenSquare(178, 210, 16, 16);
        zeichne = new Zeichnen();
    }
    
    @Override
    public void paint(Graphics g2) {
        
        Graphics2D g = (Graphics2D) g2;
        g3 = g;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        zeichne.Bg(g, nMinus, "nMinus");
        zeichne.Bg(g, nPlus, "nPlus");
        AffineTransform at = new AffineTransform();
        at.translate(10, 222);  // Move the origin to the lower left
        at.scale(5, -5);
        g.transform(at);
        g.setColor(Color.LIGHT_GRAY);
        g.setColor(Color.black);
        model.displayAll(g, 1, 1);
        } 
    
    
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MouseData J = MouseData.process(e);
        // boolean test1 = vPlus.inside(J.X);
        // boolean test2 = vMinus.inside(J.X);
        boolean test3 = nMinus.inside(J.X);
        boolean test4 = nPlus.inside(J.X);
//        if (test1 == true) {
//            if (v < 100) {
//            v += 1;
//            model.setv(v);
//            model.reset();
//            repaint(); }
//        }
//        if (test2 == true) {
//            if (v > 0) {
//            v += 1;
//            model.setv(v);
//            model.reset();
//            repaint(); }
//        }
        if (test3 == true) {
            if (n > 1) {
            n -= 1;
            model.setn(n);
            if (n > 0 && v % n != 0) {
                v -= 1;
                if (v % n != 0) {v -= 1;}
                if (v % n != 0) {v -= 1;}         
                if (v % n != 0) {v -= 1;}       
                if (v % n != 0) {v -= 1;}         
                if (v % n != 0) {v -= 1;}      
                if (v % n != 0) {v -= 1;}                
                if (v % n != 0) {v -= 1;}
                model.setv(v);}
            model.reset();
            repaint(); }
        }
        if (test4 == true) {
            if (n < 9) {
            n += 1;
            model.setn(n);
            if (v % n != 0) {
                v += 1;
                if (v % n != 0) {v += 1;}
                if (v % n != 0) {v += 1;}
                if (v % n != 0) {v += 1;}
                if (v % n != 0) {v += 1;}
                if (v % n != 0) {v += 1;}
                if (v % n != 0) {v += 1;}
                model.setv(v);}    
            model.reset();
            System.out.println("n: " + n);
            repaint(); }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
