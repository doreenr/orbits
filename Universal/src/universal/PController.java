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
 private int n;
 
 public PController (PView p, UModel m)
 {
  model = m;
  pview = p;
  pview.addNListener(new nListener());
  pview.addAListener(new aListener());
  pview.addBListener(new bListener());
 }

  
class nListener implements ActionListener {
      @Override
      public void actionPerformed (ActionEvent evt) {
            String newN = pview.getN();
            n = Integer.valueOf(newN);
            model.setn(n);
            System.out.println("Update N in model.");
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


 
