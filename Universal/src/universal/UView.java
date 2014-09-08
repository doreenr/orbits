/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author Doreen
 */
class UView extends JFrame {
    // constants 
    
    private final UModel model;
    private  CanvasIP canvas1;
    private  CanvasOrbPM canvas2;
    private  CanvasOrbPA canvas3;    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField9;
    private int[] verto;


    UView(UModel m) {
        model = m;
        verto = model.getVert();
        canvas1 = new CanvasIP(model);
        canvas2 = new CanvasOrbPM(model);
        canvas3 = new CanvasOrbPA(model);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jTextArea3 = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton1.setSelected(true);
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1400, 800));
        canvas1.setBackground(new Color(255, 255, 255));
        canvas2.setBackground(new java.awt.Color(255, 255, 255));
        canvas3.setBackground(new java.awt.Color(255, 255, 255));
        // canvas1.setVisible(false);
        // jScrollPane2.setBackground(new java.awt.Color(238, 238, 238));
        jLabel2.setText("Pentagram Automaton Orbit"); jLabel2.setFont(new java.awt.Font("Helvetica", 0, 12));
        jLabel3.setText("Pentagram Map Orbit"); jLabel3.setFont(new java.awt.Font("Helvetica", 0, 12));
        jButton1.setText("Initialize"); jButton1.setFont(new java.awt.Font("Helvetica", 0, 12));
        jButton2.setText("-100"); jButton2.setFont(new java.awt.Font("Helvetica", 0, 10)); 
        jButton4.setText("+100"); jButton4.setFont(new java.awt.Font("Helvetica", 0, 10));
        jButton3.setText("Apply"); jButton3.setFont(new java.awt.Font("Helvetica", 0, 12));
        jTextArea1.setText("Specify"); jTextArea1.setFont(new java.awt.Font("Helvetica", 0, 10));
        jTextArea4.setText("ResultPM"); jTextArea4.setFont(new java.awt.Font("Helvetica", 0, 10));
        jTextArea3.setText("ResultPA"); jTextArea3.setFont(new java.awt.Font("Helvetica", 0, 10));
        
        jTextArea1.setEditable(false);
        jTextArea4.setEditable(false);
        jTextArea3.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea4.setLineWrap(true);
        jTextArea3.setLineWrap(true);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
//        jTextArea2.setColumns(20);
//        jTextArea2.setRows(5);        
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        
        jScrollPane1.setViewportView(jTextArea3);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 255, 255)));
        jScrollPane2.setViewportView(jTextArea1);
        jScrollPane2.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 255, 255)));
        jScrollPane3.setViewportView(jTextArea4);
        jScrollPane3.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 255, 255)));

        jLabel4.setText("Input"); jLabel4.setFont(new java.awt.Font("Helvetica", 0, 12));
        jLabel5.setText("Iterations"); jLabel5.setFont(new java.awt.Font("Helvetica", 0, 12));
        jTextField9.setText("400"); jTextField9.setFont(new java.awt.Font("Helvetica", 0, 12));
        jButton5.setText("-1000"); jButton5.setFont(new java.awt.Font("Helvetica", 0, 10));
        jButton6.setText("+1000"); jButton6.setFont(new java.awt.Font("Helvetica", 0, 10));
        jRadioButton1.setText("V1");
        jRadioButton2.setText("V2");     
        jRadioButton3.setText("V3");
        jRadioButton4.setText("V4");
        jRadioButton9.setText("V5");
        jRadioButton10.setText("V6");
        jRadioButton11.setText("V7");
        jRadioButton12.setText("V8");
        jRadioButton13.setText("V9");        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton3)
                                    .addComponent(jRadioButton4)
                                    .addComponent(jRadioButton9)
                                    .addComponent(jRadioButton10)
                                    .addComponent(jRadioButton11)
                                    .addComponent(jRadioButton12)
                                    .addComponent(jRadioButton13)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(canvas3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(canvas2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(390, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton4, jButton5, jButton6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(canvas3, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(canvas2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane1, jScrollPane3});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {canvas3, canvas2});

        pack();
    }// </editor-fo
    
    void reset() {
        canvas1.repaint();
        canvas2.repaint();
        canvas3.repaint();
    }

    
    
    void addClickListener(ActionListener click) {
        jButton1.addActionListener(click);
    } 
    
    void addIterationListener(ActionListener click) {
        jTextField9.addActionListener(click);       
    }
    
    public String getIteration() {
        String itnew = jTextField9.getText();
        return itnew;
    }  
    
    void addV1Listener(ActionListener click) {
        jRadioButton1.addActionListener(click);       
        }
    
    public boolean VSelected(int i) {
        boolean ergebnis = false;
        switch (i) {
                case 1:
                    if (jRadioButton1.isSelected()) { ergebnis = true; }
                    break;
                case 2:
                    if (jRadioButton2.isSelected()) { ergebnis = true; }
                    break;
                case 3:
                    if (jRadioButton3.isSelected()) { ergebnis = true; }
                    break;                    
                case 4:
                    if (jRadioButton4.isSelected()) { ergebnis = true; }
                    break;                     
        }
        return ergebnis;
    }
    
    void addV2Listener(ActionListener click) {
        jRadioButton2.addActionListener(click);       
    }
    void addV3Listener(ActionListener click) {
        jRadioButton3.addActionListener(click);       
    }
    void addV4Listener(ActionListener click) {
        jRadioButton4.addActionListener(click);       
    }
    void addV5Listener(ActionListener click) {
        jRadioButton9.addActionListener(click);       
    }
    void addV6Listener(ActionListener click) {
        jRadioButton10.addActionListener(click);       
    }
    void addV7Listener(ActionListener click) {
        jRadioButton11.addActionListener(click);       
    }
    void addV8Listener(ActionListener click) {
        jRadioButton12.addActionListener(click);       
    }
    void addV9Listener(ActionListener click) {
        jRadioButton13.addActionListener(click);       
    }                
    
    void addIterationListener100p(ActionListener click) {
        jButton4.addActionListener(click);       
    }    
    void addIterationListener100m(ActionListener click) {
        jButton2.addActionListener(click);       
    }    
    
    void addIterationListener1000p(ActionListener click) {
        jButton6.addActionListener(click);       
    }    
    void addIterationListener1000m(ActionListener click) {
        jButton5.addActionListener(click);       
    }    
    
    void addProListener(ActionListener click) {
        jButton3.addActionListener(click);       
    }    
    
        
    void setOutput(String matrix, String resultPA, String resultPM) {
        jTextArea3.setText(resultPA);
        jTextArea4.setText(resultPM);
        jTextArea1.setText(matrix);
        // jLabel13.setText(specify);
        // jLabel4.setText(matrix);
    }
    

    
                                        

                                           

                                           

     
    
}
