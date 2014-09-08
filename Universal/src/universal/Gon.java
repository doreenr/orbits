/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.math.BigDecimal;

/**
 *
 * @author Doreen
 */
public final class Gon {
    private final int n, vertices;
    private static MMatrix M;  
    private BigDecimal[] big, bign;
    private final BigDecimal[] bigx, bigy, bigxn, bigyn;
    private final BigDecimal A, B;
    private double matrixa, matrixb;
    private int countdown;
    private double[] EndpunkteXP1, EndpunkteXP2, EndpunkteXP3, EndpunkteYP1, EndpunkteYP2, EndpunkteYP3;
    private double[] AnfpunkteXP1, AnfpunkteXP2, AnfpunkteXP3, AnfpunkteYP1, AnfpunkteYP2, AnfpunkteYP3;
    
    public Gon (int ns, int vert, double[] pointsX, double[] pointsY, int mtimes, double a, double b) {
        vertices = vert;// = Math.abs(ns);
        n = ns;
        A = bigrand("a");
        B = bigrand("b");
        M = getMatrix(A, B);
        bigx = getBigPoints(A);
        bigy = getBigPoints(B);
        bigxn = getnBigPoints(bigx);
        bigyn = getnBigPoints(bigy); 
        
        EndpunkteXP1 = PunkteLinks(pointsX, a, mtimes, 1);
        EndpunkteXP2 = PunkteLinks(pointsX, a, mtimes, 2);
        EndpunkteXP3 = PunkteLinks(pointsX, a, mtimes, 3);        
        EndpunkteYP1 = PunkteLinks(pointsY, b, mtimes, 1);
        EndpunkteYP2 = PunkteLinks(pointsY, b, mtimes, 2);
        EndpunkteYP3 = PunkteLinks(pointsY, b, mtimes, 3);        
        AnfpunkteXP1 = PunkteRechts(pointsX, a, mtimes, 1);
        AnfpunkteXP2 = PunkteRechts(pointsX, a, mtimes, 2);
        AnfpunkteXP3 = PunkteRechts(pointsX, a, mtimes, 3);        
        AnfpunkteYP1 = PunkteRechts(pointsY, b, mtimes, 1);
        AnfpunkteYP2 = PunkteRechts(pointsY, b, mtimes, 2);
        AnfpunkteYP3 = PunkteRechts(pointsY, b, mtimes, 3);
    }
    
        
    public double[] ReturnLPunkteX(int which) {
        switch (which) {
        case 1: return EndpunkteXP1; 
        case 2: return EndpunkteXP2; 
        case 3: return EndpunkteXP3; 
        }
        return null;
    }
    
    public double[] ReturnLPunkteY(int which) {
        switch (which) {
        case 1: return EndpunkteYP1; 
        case 2: return EndpunkteYP2; 
        case 3: return EndpunkteYP3; 
        }
        return null;
    }
    public double[] ReturnRPunkteX(int which) {
        switch (which) {
        case 1: return AnfpunkteXP1; 
        case 2: return AnfpunkteXP2; 
        case 3: return AnfpunkteXP3; 
        }
        return null;
    }
    
    public double[] ReturnRPunkteY(int which) {
        switch (which) {
        case 1: return AnfpunkteYP1; 
        case 2: return AnfpunkteYP2; 
        case 3: return AnfpunkteYP3; 
        }
        return null;
    }
    
    public double[] PunkteRechts(double[] Punkte, double faktor, int mtimes, int Durchlauf) {
        matrixa = 1/faktor;
        int Diff = Punkte.length - Durchlauf;
        double[] neuePunkte = new double[mtimes];
        for (int i = 0; i < (Punkte.length-Diff); ++i) {
            double pix = Punkte[i];
            for (int k = 0; k<mtimes; ++k) {
                countdown = k;
                neuePunkte[k] = newp(pix);
                // System.out.println("countdown  " + countdown + ", " + neuePunkte[k]);
            }
        }
        return neuePunkte;
    }
    
    public double[] PunkteLinks(double[] Punkte, double faktor, int mtimes, int Durchlauf) {
        matrixa = faktor;
        int Diff = Punkte.length - Durchlauf;
        double[] neuePunkte = new double[mtimes];
        for (int i = 0; i < (Punkte.length-Diff); ++i) {
            double pix = Punkte[i];
            for (int k = 0; k<mtimes; ++k) {
                countdown = k;
                neuePunkte[k] = newp(pix);
                // System.out.println("countdown  " + countdown + ", " + neuePunkte[k]);
            }
        }
        return neuePunkte;
    }
    
    public double newp(double p) {
        if (countdown == 0) { return p; }
        countdown -= 1;
        return matrixa*newp(p);
    }
    
    public BigDecimal[] getbigx() {
        return bigx;
    }    
    public BigDecimal[] getbigy() {
        return bigy;
    }
    public BigDecimal[] getbigxn() {
        return bigxn;
    }    
    public BigDecimal[] getbigyn() {
        return bigyn;
    }    

    public MMatrix getM() {
        return M;
    }   

    
    public BigDecimal[] getnBigPoints(BigDecimal[] C) {
        bign = new BigDecimal[n];
        for (int i = 0; i < n; ++i) {
            bign[i] = C[i+n];
            // System.out.println("C.length: " + C.length);
            // System.out.println("big i + n: " + C[i+n]);
        }
        return bign;
    }
    
    
    public BigDecimal[] getBigPoints(BigDecimal C) {
        big = new BigDecimal[vertices];
        if (n == 1) { for (int i = 0; i < vertices; ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
//            System.out.println("***************");
            } 
        }
        else if (n == 2) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
//            System.out.println("***************");            
            i += 1; } }
        else if (n == 3) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
//            System.out.println("***************");            
            i += 2; } }
        else if (n == 4) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
//            System.out.println("***************");            
            i += 3; } }  
        else if (n == 5) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
            BigDecimal VB5 = C.multiply(VB4);
//            System.out.println("VB5:  " + VB5);  
            big[i+4] = VB5;  
//            System.out.println("***************");            
            i += 4; } } 
        else if (n == 6) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
            BigDecimal VB5 = C.multiply(VB4);
//            System.out.println("VB5:  " + VB5);  
            big[i+4] = VB5;  
            BigDecimal VB6 = C.multiply(VB5);
//            System.out.println("VB6:  " + VB6);  
            big[i+5] = VB6; 
//            System.out.println("***************");            
            i += 5; } }  
        else if (n == 7) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
            BigDecimal VB5 = C.multiply(VB4);
//            System.out.println("VB5:  " + VB5);  
            big[i+4] = VB5;  
            BigDecimal VB6 = C.multiply(VB5);
//            System.out.println("VB6:  " + VB6);  
            big[i+5] = VB6; 
            BigDecimal VB7 = C.multiply(VB6);
//            System.out.println("VB7:  " + VB7);  
            big[i+6] = VB7;
//            System.out.println("***************");            
            i += 6; } } 
        else if (n == 8) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
            BigDecimal VB5 = C.multiply(VB4);
//            System.out.println("VB5:  " + VB5);  
            big[i+4] = VB5;  
            BigDecimal VB6 = C.multiply(VB5);
//            System.out.println("VB6:  " + VB6);  
            big[i+5] = VB6; 
            BigDecimal VB7 = C.multiply(VB6);
//            System.out.println("VB7:  " + VB7);  
            big[i+6] = VB7;
            BigDecimal VB8 = C.multiply(VB7);
//            System.out.println("VB8:  " + VB8);  
            big[i+7] = VB8;
//            System.out.println("***************");            
            i += 7; } } 
        else if (n == 9) { for (int i = 0; i < (vertices-1); ++i) {
//            System.out.println("C:  " + C);
            BigDecimal iB = BigDecimal.valueOf(i);
//            System.out.println("iB:  " + iB);
            BigDecimal VB = C.multiply(iB);
//            System.out.println("VB:  " + VB);
            big[i] = VB;
            BigDecimal VB2 = C.multiply(VB);
//            System.out.println("VB2:  " + VB2);  
            big[i+1] = VB2;
            BigDecimal VB3 = C.multiply(VB2);
//            System.out.println("VB3:  " + VB3);  
            big[i+2] = VB3;            
            BigDecimal VB4 = C.multiply(VB3);
//            System.out.println("VB4:  " + VB4);  
            big[i+3] = VB4;  
            BigDecimal VB5 = C.multiply(VB4);
//            System.out.println("VB5:  " + VB5);  
            big[i+4] = VB5;  
            BigDecimal VB6 = C.multiply(VB5);
//            System.out.println("VB6:  " + VB6);  
            big[i+5] = VB6; 
            BigDecimal VB7 = C.multiply(VB6);
//            System.out.println("VB7:  " + VB7);  
            big[i+6] = VB7;
            BigDecimal VB8 = C.multiply(VB7);
//            System.out.println("VB8:  " + VB8);  
            big[i+7] = VB8;
            BigDecimal VB9 = C.multiply(VB8);
            big[i+8] = VB9;
//            System.out.println("***************");            
            i += 8; } }         
        return big; 
        }    

    private static MMatrix getMatrix(BigDecimal a, BigDecimal b) {
        MMatrix New = new MMatrix(2,2);
        New.element[0][0] = a.doubleValue(); // a = c.x
        New.element[0][1] = 0;
        New.element[1][0] = 0;
        New.element[1][1] = b.doubleValue(); // b = c.y
        return New; 
    }
    
    
    private static BigDecimal bigrand(String choice) {
        BigDecimal A = new Big().getB();
        BigDecimal smallness = new BigDecimal("0.001");
        if ("a".equals(choice)) {
           return A; 
        }
        else if ("b".equals(choice)) {
            BigDecimal B = A.multiply(smallness);
            B = B.add(new BigDecimal("1"));
            return B;
        }
        else { return A; } 
    }
    
    
    
}
