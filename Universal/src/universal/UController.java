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
 private int[] ve;
 
 public UController (UView v, UModel m)
 {
  model = m;
  view = v;
  view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
  iterations = model.getit();
  view.addClickListener(new InitialListener());
  view.addIterationListener100p(new IterationListenerPlus100());
  view.addIterationListener100m(new IterationListenerMinus100());  
  view.addIterationListener1000p(new IterationListenerPlus1000());
  view.addIterationListener1000m(new IterationListenerMinus1000());
  view.addIterationListener(new IterationListener());
  view.addV1Listener(new V1Listen());
  view.addV2Listener(new V2Listen());
  view.addV3Listener(new V3Listen());
  view.addV4Listener(new V4Listen());
  view.addV5Listener(new V5Listen());
  view.addV6Listener(new V6Listen());
  view.addV7Listener(new V7Listen());
  view.addV8Listener(new V8Listen());  
  view.addV9Listener(new V9Listen());  
  ve = model.getVert();
  

 }

  
 
   
  
  class InitialListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            model.reset();
            ve = model.getVert();
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
            String newit = view.getIteration();
            iterations = Integer.valueOf(newit);
      }
  }
  
  class V1Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(1) == true) {
                ve[0] = 1;
                model.updateVert(ve);
                view.reset();}
            else {
                ve[0] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  }
  
  class V2Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(2) == true) {
                ve[1] = 2;
                model.updateVert(ve);
                view.reset();}
            else {
                ve[1] = 0;
                model.updateVert(ve);
                view.reset();
                
            }
      }
  }
  
  class V3Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(3) == true) {
                ve[2] = 3;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[2] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  }
    class V4Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(4) == true) {
                System.out.println("yes V4"); 
                ve[3] = 4;
                model.updateVert(ve);
                view.reset();
            }
            else {
                System.out.println("no V4");
                ve[3] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  }
  class V5Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(5) == true) {
                ve[4] = 5;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[4] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  } 
  class V6Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(6) == true) {
                ve[5] = 6;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[5] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  } 
    class V7Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(7) == true) {
                ve[6] = 7;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[6] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  } 
    class V8Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(8) == true) {
                ve[7] = 8;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[7] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  } 
      class V9Listen implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            if (view.VSelected(9) == true) {
                ve[8] = 9;
                model.updateVert(ve);
                view.reset();
            }
            else {
                ve[8] = 0;
                model.updateVert(ve);
                view.reset();
            }
      }
  }   
    
  class IterationListenerPlus100 implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            iterations += 100;
            model.setit(iterations);
            model.updateOrbit(iterations);
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
      }
  }  
  class IterationListenerMinus100 implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            if (iterations >= 100) {iterations -= 100;}
            model.setit(iterations);
            model.updateOrbit(iterations);
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
      }
  }
  
  class IterationListenerPlus1000 implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            iterations += 1000;
            model.setit(iterations);
            model.updateOrbit(iterations);
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
      }
  }  
  class IterationListenerMinus1000 implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            if (iterations >= 1000) {iterations -= 1000;}
            model.setit(iterations);
            model.updateOrbit(iterations);
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
      }
  }  
  
  class IterationListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            // System.out.println("getit " + iterations);
            String newit = view.getIteration();
            iterations = Integer.valueOf(newit);
            model.setit(iterations);
            model.updateOrbit(iterations);
            view.setOutput(model.getoutmat(), model.getoutitPA(), model.getoutitPM());
            view.reset();
      }
  }  

 
 
}  


 
