/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

/**
 *
 * @author Doreen
 */
import java.awt.event.*;
import java.awt.*;

public class MouseData {
    int mode;
    Point X;

    public  MouseData() {}

    public static MouseData process(MouseEvent e) {
	Point X = new Point();
        int mode = 0;
        if(e.getButton() == MouseEvent.BUTTON1) mode=1;
        if(e.getButton() == MouseEvent.BUTTON2) mode=2;
        if(e.getButton() == MouseEvent.BUTTON3) mode=3;
        e.consume();
        X.x = e.getX();
        X.y = e.getY();
	MouseData J = new MouseData();
	J.X = X;
	J.mode = mode;
        // System.out.println("J.X.x = " + J.X.x + "  J.X.y = " +J.X.y);

	return(J);
    }


    public static MouseData process(MouseEvent e, int q) {
	Point X=new Point();
        int mode=0;
	if(q>0) mode=q;
	if(q==0) {
          if(e.getButton()==MouseEvent.BUTTON1) mode=1;
          if(e.getButton()==MouseEvent.BUTTON2) mode=2;
          if(e.getButton()==MouseEvent.BUTTON3) mode=3;
	}
        e.consume();
        X.x=e.getX();
        X.y=e.getY();
	MouseData J=new MouseData();
	J.X=X;
	J.mode=mode;
	return(J);
    }



    
}

    

