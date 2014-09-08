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
public class UGlobal {

    public UGlobal() {
    UModel model  = new UModel();
    UView view = new UView(model);
    PView view2 = new PView(model);
    UController control = new UController(view, model);
    PController pcontrol = new PController(view2, model);
    // view.setVisible(true);
    view2.setVisible(true);
  }
    
}
