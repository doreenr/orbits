/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.event.*;
import javax.swing.JFrame;

/**
 *
 * @author Doreen
 */
class PView extends JFrame {
    // constants 
    
    private final UModel model;
    private javax.swing.ButtonGroup buttonGroup1;
    private PCanvas canvas1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;

    PView(UModel m) {
        model = m;
        buttonGroup1 = new javax.swing.ButtonGroup();
        canvas1 = new PCanvas(m);
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        jRadioButton2.setSelected(true);
        
     
setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1400, 800));
        canvas1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Input"); jLabel1.setFont(new java.awt.Font("Helvetica", 0, 12)); 
        jTextField1.setText("3"); jTextField1.setFont(new java.awt.Font("Helvetica", 0, 11)); 
        jLabel2.setText("n");jLabel2.setFont(new java.awt.Font("Helvetica", 0, 11)); 
        jButton1.setText("Update");jButton1.setFont(new java.awt.Font("Helvetica", 0, 11));
        jLabel3.setText("a");jLabel3.setFont(new java.awt.Font("Helvetica", 0, 11));
        jTextField2.setText("0.8");jTextField2.setFont(new java.awt.Font("Helvetica", 0, 11)); 
        jLabel4.setText("b");jLabel4.setFont(new java.awt.Font("Helvetica", 0, 11));
        jTextField3.setText("1.2");jTextField3.setFont(new java.awt.Font("Helvetica", 0, 11)); 
        jRadioButton1.setText("Scale Out"); jRadioButton1.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton2.setText("Scale In"); jRadioButton2.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton3.setText("Enable Drag"); jRadioButton3.setFont(new java.awt.Font("Helvetica", 0, 11));

//        jLabel5.setText("Matrix");
//        jLabel6.setText("Points");
//        jLabel5.setVisible(false);
//        jLabel6.setVisible(false);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField2))))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(60, 60, 60)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>    
    
    void reset() {
        canvas1.updateComputations();
        canvas1.repaint();
    }
    
    void addUpdateListener(ActionListener click) {
        jButton1.addActionListener(click);
    } 
    
    void addNListener(ActionListener click) {
        jTextField1.addActionListener(click); 
    }
    void addAListener(ActionListener click) {
        jTextField2.addActionListener(click); 
    }
    void addBListener(ActionListener click) {
        jTextField3.addActionListener(click); 
    }
    void addZoomListenerO(ActionListener click) {
        jRadioButton1.addActionListener(click); 
    }    
    void addZoomListenerI(ActionListener click) {
        jRadioButton2.addActionListener(click); 
    }    
    void addDragListener(ActionListener click) {
        jRadioButton3.addActionListener(click); 
    }

    public String getN() {
        String Nnew = jTextField1.getText();
        return Nnew;
    }
    
    public String getA() {
        String Anew = jTextField2.getText();
        return Anew;
    }
    
    public String getB() {
        String Bnew = jTextField3.getText();
        return Bnew;
    }
    

}
