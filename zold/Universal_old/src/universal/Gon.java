/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.awt.geom.Point2D;
;

/**
 *
 * @author Doreen
 */
public final class Gon {
    private final int n;
    // private static MMatrix M;  
    // private BigDecimal[] big, bign;
    // private final BigDecimal[] bigx, bigy, bigxn, bigyn;
    // private final BigDecimal A, B;
    private double matrixa;
    private int countdown, mtimes;
    private double[] endpX, endpY;
    private double[] anfpX, anfpY;
    private double[][] endXarr, endYarr, anfXarr, anfYarr;
    
    public Gon (int ns, double[] pointsX, double[] pointsY, int m, double a, double b) {
        // vertices = vert;// = Math.abs(ns);
        n = ns;
        mtimes = m;
        // for (int i = 0; i < pointsX.length; ++i) {System.out.println("XArray[i]: " + pointsX[i] + ", YArray: " + pointsY[i]);}
        endXarr = new double[n][mtimes];
        endYarr = new double[n][mtimes];
        anfXarr = new double[n][mtimes];
        anfYarr = new double[n][mtimes];
        for (int i = 0; i < pointsX.length; ++i) {
            // System.out.println("X: " + pointsX[i] + ", Y: " + pointsY[i]);
}
        for (int i = 0; i < n; ++i) {
            // System.out.println("i+1 = " + (i+1));
            endpX = PunkteLinks(pointsX, a, mtimes, (i+1)); 
            endpY = PunkteLinks(pointsY, b, mtimes, (i+1)); 
            anfpX = PunkteRechts(pointsX, a, mtimes, (i+1)); 
            anfpY = PunkteRechts(pointsY, b, mtimes, (i+1));
            // System.out.println("EndpunktX[i]: " + endpX[i] + ", EndpunktY: " + endpY[i]);
            // System.out.println("EX: " + endpX.length + ", EY: " + endpY.length + ", AX: " + anfpX.length + ", AY: " + anfpY.length);
            endXarr[i] = endpX;
            endYarr[i] = endpY;
            anfXarr[i] = anfpX;
            anfYarr[i] = anfpY;
        }
        
        for (int k = 0; k < endXarr.length; ++k) {
            for (int j = 0; j < endXarr[0].length; ++j) {
                // System.out.println("(" + k + "," + j + ") Ex: " + endXarr[k][j] + ", Ey: " + endYarr[k][j]);
            }
        }
    }
    
    
    public Point2D[] ReturnALL() {
        Point2D[] ALLHEAD = new Point2D[mtimes*n]; // 3 is number of input points
        Point2D[] ALLTAIL = new Point2D[mtimes*n]; // 3 is number of input points minus normal Ps
        // System.out.println("ReturnALL(): mtimes = " + mtimes + ", n: " + n);
        int p = 0;
        for (int i = (endXarr[0].length-1); i >= 0; i--) {
            for (int k = 0; k < endXarr.length; k++) {
                        // System.out.println("h " + "(" + k + "," + i+ "), AX: " + endXarr[k][i]+ ", AY: " + endYarr[k][i]);
                        ALLHEAD[p] = new Point2D.Double(endXarr[k][i], endYarr[k][i]);
                        p++;
            }
        }
        p = 0;
        for (int i = 0; i < (anfXarr[0].length); i++) {
            for (int k = 0; k < anfXarr.length; k++) {
                        // System.out.println("p tail = " + p + ", k = " + k + ", m = " + i + ", AX: " + anfXarr[k][i]+ ", AY: " + anfYarr[k][i]);
                        ALLTAIL[p] = new Point2D.Double(anfXarr[k][i], anfYarr[k][i]);
                        p++;
            }
        }

        Point2D[] RES = new Point2D[(ALLHEAD.length + (ALLTAIL.length - (n+1)))]; 
        // System.out.println("head length: " + ALLHEAD.length + ", tail length: " + ALLTAIL.length + ", res length " + RES.length);
        for (int i = 0; i < RES.length; ++i) {
            if (i < ALLHEAD.length) { 
                // System.out.println("HEAD i: " + i +  ", " + ALLHEAD[i]);
                RES[i] = ALLHEAD[i]; }
            if  ((n <= i) && (i < ALLTAIL.length)) { // ((n <= i) &&
                // System.out.println("TAIL i-1: " + (i) +  ", " + ALLTAIL[i] + ", TAIL i: " + (i) +  ", " + ALLTAIL[i]);
                RES[(ALLTAIL.length+i-(n+1))] = ALLTAIL[i-1];
            } 
            }
        for (int i = 0; i < RES.length; ++i ) {
              // System.out.println("RES i: " + i +  ", x: " + RES[i].getX()+  ", y: " + RES[i].getY());
        }
        return RES;

    }
    
    public double[] PunkteRechts(double[] Punkte, double faktor, int mtimes, int Durchlauf) {
        matrixa = 1/faktor;
        int Diff = Punkte.length - (Durchlauf);
        double[] neuePunkte = new double[mtimes];
        for (int i = 0; i < (Punkte.length-Diff); ++i) {
            double pix = Punkte[i];
            for (int k = 0; k < mtimes; ++k) {
                countdown = k;
                neuePunkte[k] = newp(pix);
                // System.out.println("i: " + i + ", In rechts: " + pix + ", "+  k + ", Out: " + neuePunkte[k]);
            }}
        return neuePunkte;}
    
    private double[] PunkteLink(double point, double faktor, int mtimes) {
        double[] neuePunkte = new double[mtimes];
        // System.out.println("In link: " + point);
        for (int k = 0; k < mtimes; ++k) {
                countdown = k;
                neuePunkte[k] = newp(point);
                // System.out.println("Out: " + neuePunkte[k]);
                }
        return neuePunkte;
    }
    
    public double[] PunkteLinks(double[] Punkte, double faktor, int mtimes, int Durchlauf) {
        matrixa = faktor;
        int Diff = Punkte.length - Durchlauf;
        // System.out.println("Durchlauf: " + Durchlauf + ", Punkte.length - Durchlauf: " + Diff + ", P.l: " + Punkte.length);
        double[] neuePunkte = new double[mtimes];
        for (int i = 0; i < (Punkte.length-Diff); ++i) {
            // System.out.println("L i: " + i + ", P: " + Punkte[i]);
            double pix = Punkte[i];
            for (int k = 0; k < mtimes; ++k) {
                countdown = k;
                neuePunkte[k] = newp(pix);
                // System.out.println("i: " + i + ", In links: " + pix + ", "+  k + ", Out: " + neuePunkte[k]);
            }}
        return neuePunkte;}
    
    public double newp(double p) {
        if (countdown == 0) { return p; }
        countdown -= 1;
        return matrixa*newp(p);}
    
//    public BigDecimal[] getbigx() {
//        return bigx;}    
//    public BigDecimal[] getbigy() {
//        return bigy;}
//    public BigDecimal[] getbigxn() {
//        return bigxn;}    
//    public BigDecimal[] getbigyn() {
//        return bigyn;}    
//    public MMatrix getM() {
//        return M;}   
//    public BigDecimal[] getnBigPoints(BigDecimal[] C) {
//        bign = new BigDecimal[n];
//        for (int i = 0; i < n; ++i) {
//            bign[i] = C[i+n];
//            // System.out.println("C.length: " + C.length);
//            // System.out.println("big i + n: " + C[i+n]);
//        }
//        return bign;}
    
//    public BigDecimal[] getBigPoints(BigDecimal C) {
//        big = new BigDecimal[vertices];
//        if (n == 1) { for (int i = 0; i < vertices; ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
////            System.out.println("***************");
//            } 
//        }
//        else if (n == 2) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
////            System.out.println("***************");            
//            i += 1; } }
//        else if (n == 3) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
////            System.out.println("***************");            
//            i += 2; } }
//        else if (n == 4) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
////            System.out.println("***************");            
//            i += 3; } }  
//        else if (n == 5) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
//            BigDecimal VB5 = C.multiply(VB4);
////            System.out.println("VB5:  " + VB5);  
//            big[i+4] = VB5;  
////            System.out.println("***************");            
//            i += 4; } } 
//        else if (n == 6) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
//            BigDecimal VB5 = C.multiply(VB4);
////            System.out.println("VB5:  " + VB5);  
//            big[i+4] = VB5;  
//            BigDecimal VB6 = C.multiply(VB5);
////            System.out.println("VB6:  " + VB6);  
//            big[i+5] = VB6; 
////            System.out.println("***************");            
//            i += 5; } }  
//        else if (n == 7) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
//            BigDecimal VB5 = C.multiply(VB4);
////            System.out.println("VB5:  " + VB5);  
//            big[i+4] = VB5;  
//            BigDecimal VB6 = C.multiply(VB5);
////            System.out.println("VB6:  " + VB6);  
//            big[i+5] = VB6; 
//            BigDecimal VB7 = C.multiply(VB6);
////            System.out.println("VB7:  " + VB7);  
//            big[i+6] = VB7;
////            System.out.println("***************");            
//            i += 6; } } 
//        else if (n == 8) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
//            BigDecimal VB5 = C.multiply(VB4);
////            System.out.println("VB5:  " + VB5);  
//            big[i+4] = VB5;  
//            BigDecimal VB6 = C.multiply(VB5);
////            System.out.println("VB6:  " + VB6);  
//            big[i+5] = VB6; 
//            BigDecimal VB7 = C.multiply(VB6);
////            System.out.println("VB7:  " + VB7);  
//            big[i+6] = VB7;
//            BigDecimal VB8 = C.multiply(VB7);
////            System.out.println("VB8:  " + VB8);  
//            big[i+7] = VB8;
////            System.out.println("***************");            
//            i += 7; } } 
//        else if (n == 9) { for (int i = 0; i < (vertices-1); ++i) {
////            System.out.println("C:  " + C);
//            BigDecimal iB = BigDecimal.valueOf(i);
////            System.out.println("iB:  " + iB);
//            BigDecimal VB = C.multiply(iB);
////            System.out.println("VB:  " + VB);
//            big[i] = VB;
//            BigDecimal VB2 = C.multiply(VB);
////            System.out.println("VB2:  " + VB2);  
//            big[i+1] = VB2;
//            BigDecimal VB3 = C.multiply(VB2);
////            System.out.println("VB3:  " + VB3);  
//            big[i+2] = VB3;            
//            BigDecimal VB4 = C.multiply(VB3);
////            System.out.println("VB4:  " + VB4);  
//            big[i+3] = VB4;  
//            BigDecimal VB5 = C.multiply(VB4);
////            System.out.println("VB5:  " + VB5);  
//            big[i+4] = VB5;  
//            BigDecimal VB6 = C.multiply(VB5);
////            System.out.println("VB6:  " + VB6);  
//            big[i+5] = VB6; 
//            BigDecimal VB7 = C.multiply(VB6);
////            System.out.println("VB7:  " + VB7);  
//            big[i+6] = VB7;
//            BigDecimal VB8 = C.multiply(VB7);
////            System.out.println("VB8:  " + VB8);  
//            big[i+7] = VB8;
//            BigDecimal VB9 = C.multiply(VB8);
//            big[i+8] = VB9;
////            System.out.println("***************");            
//            i += 8; } }         
//        return big; 
//        }    

//    private static MMatrix getMatrix(BigDecimal a, BigDecimal b) {
//        MMatrix New = new MMatrix(2,2);
//        New.element[0][0] = a.doubleValue(); // a = c.x
//        New.element[0][1] = 0;
//        New.element[1][0] = 0;
//        New.element[1][1] = b.doubleValue(); // b = c.y
//        return New;  }
//    
//    private static BigDecimal bigrand(String choice) {
//        BigDecimal A = new Big().getB();
//        BigDecimal smallness = new BigDecimal("0.001");
//        if ("a".equals(choice)) {
//           return A;  }
//        else if ("b".equals(choice)) {
//            BigDecimal B = A.multiply(smallness);
//            B = B.add(new BigDecimal("1"));
//            return B; }
//        else { return A; } }


    
    
    
}
