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
public class UController {
    
 private final UModel model;
 private final UView view; 
 private int iterations;
 private double pointsize;
 private int[] ve;
 
 public UController (UView v, UModel m)
 {
  model = m;
  view = v;
  iterations = model.getit();
  view.addClickListener(new InitialListener());
  view.addIterationListener(new IterationListener());
  view.addZoomListenerIPM(new ZoomIListenerPM());
  view.addZoomListenerOPM(new ZoomOListenerPM());  
  view.addZoomListenerIPA(new ZoomIListenerPA());
  view.addZoomListenerOPA(new ZoomOListenerPA());
  view.addPSListener(new PSListener());
  ve = model.getVert();
  

 }
class ZoomIListenerPM implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = true;
            boolean zoomout = false;
            model.updateZPM(zoomin, zoomout);      
      }
  }

class ZoomOListenerPM implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = false;
            boolean zoomout = true;
            model.updateZPM(zoomin, zoomout);  
      }
  }
class ZoomIListenerPA implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = true;
            boolean zoomout = false;
            model.updateZPA(zoomin, zoomout);      
      }
  }

class ZoomOListenerPA implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            boolean zoomin = false;
            boolean zoomout = true;
            model.updateZPA(zoomin, zoomout);  
      }
  }
  
  class PSListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            String newps = view.getPS();
            pointsize = Double.valueOf(newps);
            model.setps(pointsize);
            model.reset();
            view.reset();
      }
  } 
   
  
  class InitialListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            model.reset();
            ve = model.getVert();
            view.reset();
            String newit = view.getIteration();
            iterations = Integer.valueOf(newit);
      }
  }
  
  
  
  class IterationListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            String newit = view.getIteration();
            iterations = Integer.valueOf(newit);
//            mtimes = iterations/3+3;
            model.setit(iterations);
//            model.setmtimes(mtimes);
            model.updateIteration(iterations);
            model.reset();
            view.reset();
            
      }
  }  

 
 
}  


 
