/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

/*This is a basic class for a rectangular button button*/

public class ListenSquare {
    public double x,y,w,h;

    public ListenSquare () {}

    public ListenSquare(double x, double y, double w, double h) {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
    }

    public boolean inside(Point p) {
        // System.out.println("x " + x + "  px " + p.x + "  x+w " + (x+w) + "  y " + y + "  p.y " + p.y + "  y+h " + (y+h));
	if ((p.x >= x) && (p.x < x + w) && (p.y >= y) && (p.y < y + h)) {return(true);}
        
        else {return(false);}
    }

    public void render(Graphics g, Color C) {
	g.setColor(C);
	g.fillRect((int)(x),(int)(y),(int)(w),(int)(h));
	// g.setColor(Color.black);
	// g.drawRect((int)(x),(int)(y),(int)(w),(int)(h));
    }
    
    public void renderround(Graphics2D g, Color C) {
	g.setColor(C);
        Ellipse2D.Double p = new Ellipse2D.Double(x-w/2,y-h/2, w, h);
	g.fill(p);
	// g.setColor(Color.black);
	// g.drawRect((int)(x),(int)(y),(int)(w),(int)(h));
    }

    void addKlickListener(ActionListener click) {
        addActionListener(click);
    }    

    private void addActionListener(ActionListener click) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
