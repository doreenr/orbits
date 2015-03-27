/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Doreen
 */
public class Orbit {
    
    private final int iteration;
    private BigDecimal[][] bM; // rows, columns
    MathContext mc = new MathContext(50, RoundingMode.HALF_EVEN); 
        
    public Orbit(int it) {
        iteration = it;
    }
    
    public BigDecimal[][] BigPentMapOrbitX(BigDecimal[] bigxn, BigDecimal[] bigyn) {
        int n = bigxn.length;
        bM = new BigDecimal[iteration][n];
        BigDecimal xread[] = new BigDecimal[n]; // Länge: Vertices
        BigDecimal yread[] = new BigDecimal[n];
        BigDecimal xresult[] = new BigDecimal[n];
        BigDecimal yresult[] = new BigDecimal[n];
        BigDecimal one = BigDecimal.ONE;
        for (int i = 0; i < n; ++i) {
            xread[i] = bigxn[i];
            yread[i] = bigyn[i]; }
        for (int k = 0; k < iteration; ++k) { // ITERATION
            for (int i = 0; i < n; ++i){ // VERTICES
                int index1, index2, index3, index4;
                BigDecimal tempadd1, tempadd2, tempadd3, tempadd4, max1, max2, max3, max4;
                index1 = ((i-1) % n + n) % n;
                // System.out.println("index1 " + index1 + ", xread.length " + xread.length);
                // System.out.println("index1 " + index1 + ", yread.length " + yread.length);
                index2 = ((i+1) % n + n) % n;
                index3 = ((i+2) % n + n) % n;
                index4 = ((i) % n + n) % n; 
                tempadd1 = one.subtract(xread[index1].multiply(yread[index1]));
                tempadd2 = one.subtract(xread[index2].multiply(yread[index2]));
                tempadd3 = one.subtract(xread[index3].multiply(yread[index3]));
                tempadd4 = one.subtract(xread[index4].multiply(yread[index4]));
                xresult[i] = xread[index4].multiply((tempadd1.divide(tempadd2, 1, BigDecimal.ROUND_HALF_UP)));
                yresult[i] = xread[index2].multiply((tempadd3.divide(tempadd4, 1, BigDecimal.ROUND_HALF_UP)));
                bM[k][i] = xresult[i];  
                xread[i] = xresult[i];
                yread[i] = yresult[i]; 
            }
        }
        return bM; 
    }
    
        public BigDecimal[][] BigPentMapOrbitY(BigDecimal[] bigxn, BigDecimal[] bigyn) {
        int n = bigxn.length;
        bM = new BigDecimal[iteration][n];
        BigDecimal xread[] = new BigDecimal[n]; // Länge: Vertices
        BigDecimal yread[] = new BigDecimal[n];
        BigDecimal xresult[] = new BigDecimal[n];
        BigDecimal yresult[] = new BigDecimal[n];
        BigDecimal one = BigDecimal.ONE;
        for (int i = 0; i < n; ++i) {
            xread[i] = bigxn[i];
            yread[i] = bigyn[i]; }
        for (int k = 0; k < iteration; ++k) { // ITERATION
            for (int i = 0; i < n; ++i){ // VERTICES
                int index1, index2, index3, index4;
                BigDecimal tempadd1, tempadd2, tempadd3, tempadd4, max1, max2, max3, max4;
                index1 = ((i-1) % n + n) % n;
                // System.out.println("index1 " + index1 + ", xread.length " + xread.length);
                // System.out.println("index1 " + index1 + ", yread.length " + yread.length);
                index2 = ((i+1) % n + n) % n;
                index3 = ((i+2) % n + n) % n;
                index4 = ((i) % n + n) % n; 
                tempadd1 = one.subtract(xread[index1].multiply(yread[index1]));
                tempadd2 = one.subtract(xread[index2].multiply(yread[index2]));
                tempadd3 = one.subtract(xread[index3].multiply(yread[index3]));
                tempadd4 = one.subtract(xread[index4].multiply(yread[index4]));
                xresult[i] = xread[index4].multiply((tempadd1.divide(tempadd2, 1, BigDecimal.ROUND_HALF_UP)));
                yresult[i] = xread[index2].multiply((tempadd3.divide(tempadd4, 1, BigDecimal.ROUND_HALF_UP)));
                bM[k][i] = yresult[i];  
                xread[i] = xresult[i];
                yread[i] = yresult[i]; }
        }
        return bM;    
        
    } 
    public BigDecimal[][] BigPentAutOrbitX(BigDecimal[] bigxn, BigDecimal[] bigyn) {
        int n = bigxn.length;
        bM = new BigDecimal[iteration][n];
        BigDecimal xread[] = new BigDecimal[n]; // Länge: Vertices
        BigDecimal yread[] = new BigDecimal[n];
        BigDecimal xresult[] = new BigDecimal[n];
        BigDecimal yresult[] = new BigDecimal[n];
        for (int i = 0; i < n; ++i) {
            xread[i] = bigxn[i];
            yread[i] = bigyn[i]; }
        for (int k = 0; k < iteration; ++k) { // ITERATION
            for (int i = 0; i < n; ++i){ // VERTICES
                int index1, index2, index3, index4;
                BigDecimal zero, tempadd1, tempadd2, tempadd3, tempadd4, max1, max2, max3, max4;
                index1 = ((i-1) % n + n) % n;
                // System.out.println("index1 " + index1 + ", xread.length " + xread.length);
                // System.out.println("index1 " + index1 + ", yread.length " + yread.length);
                index2 = ((i+1) % n + n) % n;
                index3 = ((i+2) % n + n) % n;
                index4 = ((i) % n + n) % n;
                tempadd1 = xread[index1].add(yread[index1]);
                tempadd2 = xread[index2].add(yread[index2]);
                tempadd3 = xread[index3].add(yread[index3]);
                tempadd4 = xread[index4].add(yread[index4]);
                max1 = tempadd1.max(BigDecimal.ZERO);
                max2 = tempadd2.max(BigDecimal.ZERO);
                max3 = tempadd3.max(BigDecimal.ZERO);
                max4 = tempadd4.max(BigDecimal.ZERO);
                xresult[i] = (xread[index4].add(max1)).subtract(max2);
                yresult[i] = (yread[index2].add(max3)).subtract(max4);
                bM[k][i] = xresult[i];  
                xread[i] = xresult[i];
                yread[i] = yresult[i]; }
        }
        return bM; 
    }
    
    public BigDecimal[][] BigPentAutOrbitY(BigDecimal[] bigxn, BigDecimal[] bigyn) {
        int n = bigxn.length;
        bM = new BigDecimal[iteration][n];
        BigDecimal xread[] = new BigDecimal[n]; // Länge: Vertices
        BigDecimal yread[] = new BigDecimal[n];
        BigDecimal xresult[] = new BigDecimal[n];
        BigDecimal yresult[] = new BigDecimal[n];
        BigDecimal zero = new BigDecimal("0");
        for (int i = 0; i < n; ++i) {
            xread[i] = bigxn[i];
            yread[i] = bigyn[i]; }
        for (int k = 0; k < iteration; ++k) { // ITERATION
            for (int i = 0; i < n; ++i){ // VERTICES
                int index1, index2, index3, index4;
                BigDecimal tempadd1, tempadd2, tempadd3, tempadd4, max1, max2, max3, max4;
                index1 = ((i-1) % n + n) % n;
                index2 = ((i+1) % n + n) % n;
                index3 = ((i+2) % n + n) % n;
                index4 = ((i) % n + n) % n;
                tempadd1 = xread[index1].add(yread[index1]);
                tempadd2 = xread[index2].add(yread[index2]);
                tempadd3 = xread[index3].add(yread[index3]);
                tempadd4 = xread[index4].add(yread[index4]);
                max1 = tempadd1.max(zero);
                max2 = tempadd2.max(zero);
                max3 = tempadd3.max(zero);
                max4 = tempadd4.max(zero);
                xresult[i] = (xread[index4].add(max1)).subtract(max2);
                yresult[i] = (yread[index2].add(max3)).subtract(max4);
                bM[k][i] = yresult[i];  
                xread[i] = xresult[i];
                yread[i] = yresult[i]; 
            }
        }
        return bM; 
    }
   
    
    public int simple(int i, int mod) {
        return i % mod;
    }
    
    public int minusone(int i, int mod) {
        if (i-1 == -1) { i = (mod-1);}
        else {i = (i-1) % mod;}
        return i;
    }
    
    public int plusone(int i, int mod) {
        if (i+1 == mod) { i = 0;}
        else {i = (i+1) % mod;}
        return i;        
    }
    
    public int plustwo(int i, int mod) {
        if (i+2 == mod) { i = 0; }
        // else if (i+1 == mod) { i = 1;}
        else {i = (i+2) % mod;}
        return i;
    }  
    
    public BigDecimal smallsum(BigDecimal s1, BigDecimal s2) {
        return s1.add(s2);
    }
    
    public double smallsum(double d1, double d2) {
        return d1+d2;
    } 
    
    public BigDecimal maxim(BigDecimal a) {
        BigDecimal nu = BigDecimal.ZERO;
        if (a.compareTo(nu) == -1) { a = nu; }
        return a;
        }
    
    public double maxim(double a) {
        if (a < 0) { a = 0; }
        return a;
        }
    
    public BigDecimal phi(BigDecimal summand1, BigDecimal max1, BigDecimal max2) {
        return (summand1.add(max1)).subtract(max2);
    } 
    
    public double phi(double summand1, double max1, double max2) {
        return summand1 + max1 - max2;
    } 
    
    public BigDecimal mal(BigDecimal m1, BigDecimal m2) {
        // return m1.multiply(m2, mc);        
        return m1.multiply(m2).setScale(100, RoundingMode.HALF_EVEN);        
    }
    
    public BigDecimal einsminus(BigDecimal m2) {
        BigDecimal one = BigDecimal.ONE;
        return one.subtract(m2, mc);
    }
    
    public BigDecimal bruch(BigDecimal oben, BigDecimal unten) {
        return oben.divide(unten, mc);
    }
    
    

            
            
    
    
}





