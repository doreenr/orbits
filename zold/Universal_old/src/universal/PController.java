/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;
import java.awt.event.*;

/**
 *
 * @author Doreen
 */
public class PController {
    
 private final UModel model;
 private final PView pview; 
 private final UView uview; 
 private int n;
 
 public PController (PView p, UView u, UModel m)
 {
  model = m;
  pview = p;
  uview = u;
  pview.addNListener(new nListener());
  pview.addAListener(new aListener());
  pview.addBListener(new bListener());
  pview.addUpdateListener(new UpdateListener());
  pview.addZoomListenerI(new ZoomIListener());
  pview.addZoomListenerO(new ZoomOListener());
  pview.addDragListener(new DragListener());
 }

class DragListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = false;
            boolean zoomout = false;
            boolean drag = true;
            model.updateZinput(zoomin, zoomout, drag);
      }
  }

class ZoomIListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = true;
            boolean zoomout = false;
            boolean drag = false;
            model.updateZinput(zoomin, zoomout, drag);      }
  }

class ZoomOListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = false;
            boolean zoomout = true;
            boolean drag = false;
            model.updateZinput(zoomin, zoomout, drag);
      }
  }

class UpdateListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            model.reset();
            model.updateOrbit();
            uview.reset();
            // update n in model!
      }
  }

class nListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            String newN = pview.getN();
            n = Integer.valueOf(newN);
            model.setn(n);
            model.reset();
            model.updateOrbit();
            pview.reset();
            System.out.println("N:" + n);
            // update n in model!
      }
  }
  
class aListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            String newA = pview.getA();
            double a = Double.parseDouble(newA);
            model.setA(a);
            model.reset();
            pview.reset();
      }
  }
class bListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            String newB = pview.getB();
            double b = Double.parseDouble(newB);
            model.setB(b);
            model.reset();
            pview.reset();

      }
  }
}  


 
