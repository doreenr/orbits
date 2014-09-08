/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;
import java.awt.Color;
import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Doreen
 */
public final class UModel {
    
    // constants
    
    // current state variables
    public int vertices = 99;
    public int n = 3;
    public int iterations = 100;
    public int mtimes = 30;
    private double a, b;
    private Gon polybig; //= new Gon(vertices, iteration);
    private MMatrix M;
    private Orbit o;
    private final Zeichnen zeichne = new Zeichnen();
    private int[] vert;
    private BigDecimal[] bigx, bigy, bigxn, bigyn, arrayx, arrayy, marrayz, marrayw;
    private String bigoutmat, hitlist, listPA, listPM;
    private StringBuilder polylistPA, polylistPM;
    private double[] PointsX, PointsY;
    
    UModel() {
        // Startpunkte 
        PointsX = new double[3];
        PointsY = new double[3];
        PointsX[0] = 7.0;
        PointsY[0] = 17.0;
        PointsX[1] = 11;
        PointsY[1] = 12;
        PointsX[2] = 17;
        PointsY[2] = 8;
        
        a = 0.8;
        b = 1.2;
        
        reset();
    }
    
    public void reset() {
        
        polybig = new Gon(n, vertices, PointsX, PointsY, mtimes, a, b);
        vert = new int[9];
        for (int i = 0; i < vert.length; ++i) { vert[i] = 0; }
        vert[0] = 1;
        polylistPA = new StringBuilder();
        polylistPM = new StringBuilder();
        bigx = polybig.getbigx();
        bigy = polybig.getbigy();
        bigxn = polybig.getbigxn();
        bigyn = polybig.getbigyn();
        o = new Orbit(iterations);
        M = polybig.getM();
        bigoutmat = setOutputMat(M);
    } 
    
    public void setPXs(double pxs, int which) {
        PointsX[which-1] = pxs;
    }
    public void setPYs(double pys, int which) {
        PointsY[which-1] = pys;
    }
    
    public double getPXs(int which) {
        return PointsX[which-1];
    }
    public double getPYs(int which) {
        return PointsY[which-1];
    }
    
    public void UpdateOutput() {
        listPA = setOutputIt(polylistPA);
        listPM = setOutputIt(polylistPM);        
    }
    
    public void updateOrbit(int newit) {
        o = new Orbit(iterations);
        listPA = setOutputIt(polylistPA);
        listPM = setOutputIt(polylistPM);        
        bigoutmat = setOutputMat(M);    
    }
    
    public void updateVert(int[] verti) {
        vert = verti;
    }
    
    public int getit() {
        return iterations;
    } 
    
    public int getn() {
        return n;
    }     
    
    public int[] getVert() {
        return vert;
    } 
    
    public int setit(int newit) {
        iterations = newit;
        return iterations;
    }
    
    void displayTail(Graphics2D g) {
        float f = 0.2F;
    g.setColor(new Color(25, 25, 112));
    for (int i = 1; i < PointsX.length+1; ++i) {
        double[] plotX = polybig.ReturnLPunkteX(i);
        double[] plotY = polybig.ReturnLPunkteY(i);
        for (int k = 0; k < plotX.length; ++k) {
            zeichne.Dot(g, plotX[k], plotY[k], 1, f);
        }}
    for (int i = 1; i < PointsX.length+1; ++i) {
        double[] plotX = polybig.ReturnRPunkteX(i);
        double[] plotY = polybig.ReturnRPunkteY(i);
        for (int k = 0; k < plotX.length; ++k) {
            zeichne.Dot(g, plotX[k], plotY[k], 1, f);
        }}
    }
    
    void displayAll(Graphics2D g, int choice, double ps) {
        float f = 0.1F;
        if (choice == 1) {
            for (int i = 0; i < (bigx.length-1); ++i) {
                // if (i % n == 0 && f < 0.9F ) { f += 0.1F; }
                g.setColor(Color.black);
                // System.out.println("i " + i + ", bx " + bigx[i] +", " + bigy[i]);
                zeichne.Dot(g, bigx[i], bigy[i], 1, f);  }
            f = 0.6F;
            for (int i = 0; i < bigxn.length; ++i) {
                g.setColor(Color.RED);
                zeichne.Dot(g, bigxn[i], bigyn[i], 1, f); }
        }
        else if (choice == 2) {
        arrayx = bigxn;
        arrayy = bigyn;
        g.setColor(Color.BLACK);
        zeichne.FlatOrbit(g, bigxn, bigyn, ps, 0.9f);
        for (int k = 0; k < iterations; ++k) {
            BigDecimal[] bnewarrx = new BigDecimal[n];
            BigDecimal[] bnewarry = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
                bnewarrx[i] = o.phi(arrayx[i], o.maxim(o.smallsum(arrayx[o.minusone(i,n)], arrayy[o.minusone(i,n)])), o.maxim(o.smallsum(arrayx[o.plusone(i,n)], arrayy[o.plusone(i,n)])));
                bnewarry[i] = o.phi(arrayy[o.plusone(i, n)], o.maxim(o.smallsum(arrayx[o.plustwo(i,n)], arrayy[o.plustwo(i,n)])), o.maxim(o.smallsum(arrayx[o.simple(i,n)], arrayy[o.simple(i,n)])));
                }
            zeichne.DeepOrbit(g, bnewarrx, bnewarry, ps, 0.4f, vert);
            polylistPA = finito(k, polylistPA, bigxn, bigyn, bnewarrx, bnewarry);
            UpdateOutput();
            arrayx = bnewarrx;
            arrayy = bnewarry;
            }
        }
        else if (choice == 3) {
        marrayz = bigxn;
        marrayw = bigyn;
        g.setColor(Color.BLACK);
        zeichne.FlatOrbit(g, bigxn, bigyn, ps, 0.9f);
        for (int k = 0; k < iterations; ++k) {
            BigDecimal[] bnewarrayz = new BigDecimal[n];
            BigDecimal[] bnewarrayw = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
//                BigDecimal oben = o.einsminus(o.mal(marrayz[o.minusone(i, n)], marrayw[o.minusone(i, n)]));    
//                BigDecimal unten = o.einsminus(o.mal(marrayz[o.plusone(i, n)], marrayw[o.plusone(i, n)])); 
//                bnewarrayz[i] = o.mal(marrayz[o.simple(i, n)], o.bruch(oben, unten));
//                oben = o.einsminus(o.mal(marrayz[o.plustwo(i, n)], marrayw[o.plustwo(i, n)]));
//                unten = o.einsminus(o.mal(marrayz[o.simple(i, n)], marrayw[o.simple(i, n)]));
//                bnewarrayw[i] = o.mal(marrayw[o.plusone(i, n)], o.bruch(oben, unten));
                    BigDecimal one = new BigDecimal("1");
                    int index1, index2, index3, index4;
                    BigDecimal tempadd1, tempadd2, tempadd3, tempadd4;
                    MathContext mc = new MathContext(10, RoundingMode.HALF_EVEN); 
                    index1 = o.minusone(i, n);
                    index2 = o.plusone(i, n);
                    index3 = o.plustwo(i, n);
                    index4 = o.simple(i, n);
                    tempadd1 = one.subtract(marrayz[index1].multiply(marrayw[index1], mc), mc);
                    tempadd2 = one.subtract(marrayz[index2].multiply(marrayw[index2], mc), mc);
                    tempadd3 = one.subtract(marrayz[index3].multiply(marrayw[index3], mc), mc);
                    tempadd4 = one.subtract(marrayz[index4].multiply(marrayw[index4], mc), mc);
                    bnewarrayz[i] = marrayz[index4].multiply(tempadd1.divide(tempadd2, mc), mc);
                    bnewarrayw[i] = marrayw[index2].multiply(tempadd3.divide(tempadd4, mc), mc);
                }
            zeichne.DeepOrbit(g, bnewarrayz, bnewarrayw, ps, 0.4f, vert); 
            polylistPM = finito(k, polylistPM, bigxn, bigyn, bnewarrayz, bnewarrayw);
            UpdateOutput();
            marrayz = bnewarrayz;
            marrayw = bnewarrayw;
            }
        }
    }
    
    public StringBuilder finito(int i, StringBuilder polylist, BigDecimal[] initx, BigDecimal[] inity, BigDecimal[] newx, BigDecimal[] newy) {
        int countlength = 0;
        BigDecimal abso;
        BigDecimal threshold = new BigDecimal("0.00001");
        for (int j = 0; j < initx.length; ++j) {
            abso = initx[j].subtract(newx[j]);
            abso = abso.abs();
            // System.out.println("initx j: " + initx[j] + ", inity j: " + inity[j]);
            if (abso.compareTo(threshold) == -1) {
                countlength += 1;
            }
            }
        if (countlength == initx.length) {
            polylist.append(i);
            polylist.append(", ");
            // System.out.println(polylist);
        }            

        return polylist;
    }
    
    public void setn(int newn) {
        n = newn;
    }
    
    public void setA(double newa) {
        a = newa;
    }
    
    public void setB(double newb) {
        b = newb;
    }
    
    public void setv(int newv) {
        vertices = newv;
    }
    public int getv() {
        return vertices;
    }  
    
    public void setpointsX(double[] nPoints) {
        PointsX = nPoints;
    }
    public double[] getpointsX() {
        return PointsX;
    }   
    
    public String getoutitPA() {
        return listPA;
    }     
    public String getoutitPM() {
        return listPM;
    }     
    
    public String getoutmat() {
        return bigoutmat;
    }    
    
    public String setOutputMat(MMatrix M) {
        String label = "Matrix. ";
        // String labelv = "Initial vertices. ";
        String labeli = "Iterations. ";
        String M1 = String.format("|  %.2f", M.element[0][0]);        
        String M2 = String.format("   %.0f  |", M.element[0][1]);        
        String M3 = String.format("|  %.0f", M.element[1][0]);        
        String M4 = String.format("   %.2f  |", M.element[1][1]);
        String iPA = String.valueOf(iterations);
        // String last = "Choice of 2D-         projection from        matrices MX & MY,  for (s,t) where         s,t = {0,1, ... n-1}";
        String last1 = "V1 [blue]                 V2 [red]                 V3 [orange]             V4 [magenta]          V5 [turquoise]         V6 [green]               V7 [gray]";
        String s3 = label +'\n'+'\n'+ M1 + M2 +'\n'+ M3+ M4 +'\n'+ '\n'+ labeli + iPA + '\n'+'\n'+ last1;
        return s3;
    }
        
    public String setOutputIt(StringBuilder polylist) {
        String liste = polylist.toString();
        String labellist = "Returns at Iterations: ";
        String s3 = labellist + liste;
        return s3;
    }     
}    

    
    
    
    

