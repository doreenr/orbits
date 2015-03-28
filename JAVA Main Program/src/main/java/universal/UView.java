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
class UView extends JFrame {
    // constants 
    
    private final UModel model;
    private  CanvasOrbPM canvas5;
    private  CanvasOrbPA canvas4;    
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField9;
    private int[] verto;


    UView(UModel m) {
        model = m;
        verto = model.getVert();
        canvas5 = new CanvasOrbPM(model);
        canvas4 = new CanvasOrbPA(model);
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        buttonGroup3.add(jRadioButton1);
        buttonGroup3.add(jRadioButton2);
        buttonGroup4.add(jRadioButton3);
        buttonGroup4.add(jRadioButton4);
        jRadioButton1.setSelected(true);
        jRadioButton3.setSelected(true);

        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1400, 800));
        canvas4.setBackground(new java.awt.Color(255, 255, 255));
        canvas5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Pentagram Automaton Orbit"); jLabel2.setFont(new java.awt.Font("Helvetica", 0, 12));
        jLabel3.setText("Pentagram Map Orbit"); jLabel3.setFont(new java.awt.Font("Helvetica", 0, 12));
        jLabel5.setText("Iterations"); jLabel5.setFont(new java.awt.Font("Helvetica", 0, 11));
        jTextField9.setText("10000"); jTextField9.setFont(new java.awt.Font("Helvetica", 0, 11));
        jLabel6.setText("Pointsize"); jLabel6.setFont(new java.awt.Font("Helvetica", 0, 11));
        jTextField10.setText("1"); jTextField10.setFont(new java.awt.Font("Helvetica", 0, 11));
        jLabel7.setText("Automaton"); jLabel7.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton1.setText("Scale in"); jRadioButton1.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton2.setText("Scale out"); jRadioButton2.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton3.setText("Scale out"); jRadioButton3.setFont(new java.awt.Font("Helvetica", 0, 11));
        jRadioButton4.setText("Scale in"); jRadioButton4.setFont(new java.awt.Font("Helvetica", 0, 11));
        jLabel8.setText("Pentagram"); jLabel8.setFont(new java.awt.Font("Helvetica", 0, 11));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)))
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel7)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(canvas4, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(canvas5, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 414, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas4, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(canvas5, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {canvas4, canvas5});

        pack();
    }// </editor-fo
    
    void reset() {
        canvas4.repaint();
        canvas5.repaint();
    }    
    void addZoomListenerOPM(ActionListener click) {
        jRadioButton3.addActionListener(click); 
    }    
    void addZoomListenerIPM(ActionListener click) {
        jRadioButton4.addActionListener(click); 
    }     
    void addZoomListenerOPA(ActionListener click) {
        jRadioButton2.addActionListener(click); 
    }    
    void addZoomListenerIPA(ActionListener click) {
        jRadioButton1.addActionListener(click); 
    } 
    
    void addClickListener(ActionListener click) {
        // jButton1.addActionListener(click);
    } 
    
    void addIterationListener(ActionListener click) {
        jTextField9.addActionListener(click);       
    }    
    void addPSListener(ActionListener click) {
        jTextField10.addActionListener(click);       
    }
    
    public String getIteration() {
        String itnew = jTextField9.getText();
        return itnew;
    }     
    
    public String getPS() {
        String psnew = jTextField10.getText();
        return psnew;
    }  
    
    



    
                                        

                                           

                                           

     
    
}
