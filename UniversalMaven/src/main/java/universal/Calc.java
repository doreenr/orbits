/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universal;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.ejml.alg.dense.misc.RrefGaussJordanRowPivot;
import org.ejml.data.DenseMatrix64F;
import java.util.Random;
import org.ojalgo.matrix.BigMatrix;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.la4j.vector.Vector;
import org.ojalgo.matrix.BasicMatrix;





/**
 *
 * @author Doreen
 */
public final class Calc {
    UModel model;
    private int it, count, startindex;
    private BigDecimal[] pmU, pmV, pmW, pmX, pmY, pmZ, rpmU, rpmV, rpmW, rpmX, rpmY, rpmZ;
    private BigDecimal[] paU, paV, paW, paX, paZ, paY, rpaU, rpaV, rpaW, rpaX, rpaZ, rpaY;
    // Matrix matpa, matpm;
    

    Calc(UModel m) throws FileNotFoundException {
        count = 0;
        model = m;
        startindex = 21;
        it = model.iterations; // number of points
        pmU = model.getBigClouds(1, 0, 0); // (which map, which dim)
        pmV = model.getBigClouds(1, 1, 0); // (which map, which dim)
        pmW = model.getBigClouds(1, 0, 1); // (which map, which dim)        
        pmX = model.getBigClouds(1, 1, 1); // (which map, which dim)
        pmY = model.getBigClouds(1, 0, 2); // (which map, which dim)
        pmZ = model.getBigClouds(1, 1, 2); // (which map, which dim)  
        paU = model.getBigClouds(2, 0, 0); // (pa map, x, P1)
        paV = model.getBigClouds(2, 1, 0); // (pa map, y, P1)
        paW = model.getBigClouds(2, 0, 1); // (pa map, x, P2)        
        paX = model.getBigClouds(2, 1, 1); // (pa map, y, P2)
        paY = model.getBigClouds(2, 0, 2); // (pa map, x, P3)
        paZ = model.getBigClouds(2, 1, 2); // (pa map, y, P3)
//        for (int i = 0; i < paU.length; ++i){
//            System.out.print("; i:" + paU[i] + ", ");
//        }
        
//        int N = 6+1;
//        RandomP(N); // sets up random list of N points for both maps
//        double[][] mpm = Matrix(N, "PM");
//        double[][] mpa = Matrix(N, "PA");
//        RowReduce(mpm, N);
//        RowReduce(mpa, N);
//        DetRank(mpm);
//        DetRank(mpa);
//       Collinear("PA", N);
//       Coplanar("PA", N);// Coplanar("PM", N); // Collinear("PA1", N);
//        for (int i = 2; i < 70; ++i) {
//        Lines(0, 7, i*7);
//        }
        PrintAllPoints();
//        DistanceToPoint(startindex);
        
    }
    
    
//    private BigDecimal[][] fillPoints(BigDecimal[][] closePoints){
//        BigDecimal[] SPbig = new BigDecimal [6];
//        SPbig[0] = paU[startindex]; SPbig[1] = paV[startindex]; SPbig[2] = paW[startindex]; SPbig[3] = paX[startindex]; SPbig[4] = paY[startindex]; SPbig[5] = paZ[startindex];
//        for (int i = 0; i < (it-1); i++){
//        BigDecimal[] OPJbig = new BigDecimal [6];
//        OPJbig[0] = paU[i]; OPJbig[1] = paV[i]; OPJbig[2] = paW[i]; OPJbig[3] = paX[i]; OPJbig[4] = paY[i]; OPJbig[5] = paZ[i];
//        BigDecimal threshold = new BigDecimal("0.2");
//        // System.out.println("Dist: " +DIST(SPbig, OPJbig)); // + ".  " + OPJbig[0] + ", " + OPJbig[1] + ", " + OPJbig[2] + ", " + OPJbig[3] + ", " + OPJbig[4] + ", " + OPJbig[5]);
//        if (DIST(SPbig, OPJbig).compareTo(threshold) == -1) {
//            closePoints[0][i] = paU[i]; 
//            // closePoints[1][i] = paV[i]; closePoints[2][i] = paW[i]; closePoints[3][i] = paX[i]; closePoints[4][i] = paY[i]; closePoints[5][i] = paZ[i];
//        }
//    }
//    return closePoints;
//    }
    
    private void DistanceToPoint(int i) throws FileNotFoundException{
        double[] d = new double [6];
        BigDecimal[] SPbig = new BigDecimal [6];
        d[0] = paU[i].doubleValue(); d[1] = paV[i].doubleValue(); d[2] = paW[i].doubleValue(); d[3] = paX[i].doubleValue(); d[4] = paY[i].doubleValue(); d[5] = paZ[i].doubleValue();
        SPbig[0] = paU[i]; SPbig[1] = paV[i]; SPbig[2] = paW[i]; SPbig[3] = paX[i]; SPbig[4] = paY[i]; SPbig[5] = paZ[i];
        RealVector SP = new ArrayRealVector(d);
        // System.out.println("Startpoint P0.0: " + d[0] + ", Big.0: " + SPbig[0].toString());
        String filename = "outputclose" + it + "P" + i + ".csv";
        PrintStream outcl = new PrintStream(new FileOutputStream(filename));
        // PrintStream outcly = new PrintStream(new FileOutputStream("outputclose_P0y.txt"));
        for (int j = 0; j < (it-1); j++){
        // set up vector 0-PJ = OPJ 
        BigDecimal[] OPJbig = new BigDecimal [6];
        d[0] = paU[j].doubleValue(); d[1] = paV[j].doubleValue(); d[2] = paW[j].doubleValue(); d[3] = paX[j].doubleValue(); d[4] = paY[j].doubleValue(); d[5] = paZ[j].doubleValue();
        OPJbig[0] = paU[j]; OPJbig[1] = paV[j]; OPJbig[2] = paW[j]; OPJbig[3] = paX[j]; OPJbig[4] = paY[j]; OPJbig[5] = paZ[j];
        RealVector OPJ = new ArrayRealVector(d);
        // System.out.println("Distanz SP - PJ: " + SP.getDistance(OPJ));
        
        // print out vectors which are a distance of 0.2 away from SP
        BigDecimal threshold = new BigDecimal("0.2");
        // System.out.println("Dist: " +DIST(SPbig, OPJbig)); // + ".  " + OPJbig[0] + ", " + OPJbig[1] + ", " + OPJbig[2] + ", " + OPJbig[3] + ", " + OPJbig[4] + ", " + OPJbig[5]);
        if (DIST(SPbig, OPJbig).compareTo(threshold) == -1) {
            count += 1;
            // System.out.println("PUNKT:" + OPJbig[0] + ", " + OPJbig[1] + ", " + OPJbig[2] + ", " + OPJbig[3] + ", " + OPJbig[4] + ", " + OPJbig[5]);
           outcl.println(OPJbig[0] + ", " + OPJbig[1] + ", " + OPJbig[2] + ", " + OPJbig[3] + ", " + OPJbig[4] + ", " + OPJbig[5]);
//            outclx.println(OPJbig[0]);
//            outcly.println(OPJbig[1]); // + ", " + OPJbig[2] + ", " + OPJbig[3] + ", " + OPJbig[4] + ", " + OPJbig[5]);
        }}
        //System.out.println("count: " + count);
        System.setOut(outcl);
        // System.setOut(outcly);
        
//         System.out.println("Dot < 0.0001. Double: " + countdot + ", Big: " + countdot2);
    }
    
    private BigDecimal DOT(BigDecimal[] first, BigDecimal[] sec){
        BigDecimal sum = BigDecimal.ZERO;
        for (int n = 0; n < 6; ++n) {
            sum = sum.add(first[n].multiply(sec[n]).setScale(100, RoundingMode.HALF_EVEN));
        }
        return sum;
    }
    
    private BigDecimal LENGTH(BigDecimal[] vector){
        return sqrt(DOT(vector,vector), 100);
    }    
    
    private BigDecimal DIST(BigDecimal[] first, BigDecimal[] sec){
        BigDecimal[] sub = new BigDecimal[6];
        for (int k = 0; k < 6; ++k) {
            sub[k] = first[k].subtract(sec[k]);
        }  
        return LENGTH(sub);
    }
    
    public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
    BigDecimal x0 = new BigDecimal("0");
    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
    while (!x0.equals(x1)) {
        x0 = x1;
        x1 = A.divide(x0, SCALE, RoundingMode.HALF_UP);
        x1 = x1.add(x0);
        x1 = x1.divide(new BigDecimal(2), SCALE, RoundingMode.HALF_UP);
    }
    return x1;
}
      
    private void PrintAllPoints() throws FileNotFoundException{
        double[] d = new double [6];
        String filename = "outputallP3" + it + ".csv";
        PrintStream out = new PrintStream(new FileOutputStream(filename));
        for (int i= 0; i < it; ++i) {
            d[0] = paU[i].doubleValue(); d[1] = paV[i].doubleValue(); d[2] = paW[i].doubleValue(); d[3] = paX[i].doubleValue(); d[4] = paY[i].doubleValue(); d[5] = paZ[i].doubleValue();
            // RealVector p = new ArrayRealVector(d);
            out.println(d[0] + ", " + d[1] + ", " + d[2] + ", " + d[3] + ", " + d[4] + ", " + d[5]);
            System.out.print(d[5] + ", ");
        }
        System.setOut(out);
    }
    
    
    private void Lines(int p0init, int p1init, int check){
        double[] d = new double [6];
        d[0] = paU[p0init].doubleValue(); d[1] = paV[p0init].doubleValue(); d[2] = paW[p0init].doubleValue(); d[3] = paX[p0init].doubleValue(); d[4] = paY[p0init].doubleValue(); d[5] = paZ[p0init].doubleValue(); 
        RealVector p0 = new ArrayRealVector(d);
        d[0] = paU[p1init].doubleValue(); d[1] = paV[p1init].doubleValue(); d[2] = paW[p1init].doubleValue(); d[3] = paX[p1init].doubleValue(); d[4] = paY[p1init].doubleValue(); d[5] = paZ[p1init].doubleValue(); 
        RealVector p1 = new ArrayRealVector(d);
        RealVector v = p0.subtract(p1);
        System.out.print("\n");
        System.out.println(p0.toString());
        System.out.println(p1.toString());
        System.out.println(v.toString());
        d[0] = paU[check].doubleValue(); d[1] = paV[check].doubleValue(); d[2] = paW[check].doubleValue(); d[3] = paX[check].doubleValue(); d[4] = paY[check].doubleValue(); d[5] = paZ[check].doubleValue(); 
        RealVector p3 = new ArrayRealVector(d);
        RealVector v2 = p0.subtract(p3);
        Divisible(v, v2);
        
    }
    
    private boolean Divisible(RealVector v, RealVector v2){
        for (int i = 0; i<6; i++) {
            System.out.println("div: " + v.getEntry(i)/v2.getEntry(i) + ", mod: " + v.getEntry(i) % v2.getEntry(i));
        }
        if (v.getEntry(0) % v2.getEntry(0) == 0) { 
            System.out.print("y,");
            if (v.getEntry(1) % v2.getEntry(1) == 0){
                System.out.print("y,");
                if (v.getEntry(2) % v2.getEntry(2) == 0){
                    System.out.print("y,");        
        if (v.getEntry(3) % v2.getEntry(3) == 0) { 
            System.out.print("y,");
            if (v.getEntry(4) % v2.getEntry(4) == 0){
                System.out.print("y,");
                if (v.getEntry(5) % v2.getEntry(5) == 0){
                    System.out.print("y,");                
                    if (v.getEntry(6) % v2.getEntry(6) == 0){
                        System.out.print("y,");
                        return true;
        }}}}}}}
        return false;
    }
    
    
//    private void Coplanar(String mapchoice, int N){
//        double[][] mat = new double[4][N-1];
//        if (mapchoice.equals("PA")){
//            mat[0][0] = rpaU[0]; mat[0][1] = rpaV[0]; mat[0][2] = rpaW[0]; mat[0][3] = rpaX[0]; mat[0][4] = rpaY[0]; mat[0][5] = rpaZ[0];
//            mat[1][0] = rpaU[1]; mat[1][1] = rpaV[1]; mat[1][2] = rpaW[1]; mat[1][3] = rpaX[1]; mat[1][4] = rpaY[1]; mat[1][5] = rpaZ[1];
//            mat[2][0] = rpaU[2]; mat[2][1] = rpaV[2]; mat[2][2] = rpaW[2]; mat[2][3] = rpaX[2]; mat[2][4] = rpaY[2]; mat[2][5] = rpaZ[2];
//            mat[3][0] = rpaU[3]; mat[3][1] = rpaV[3]; mat[3][2] = rpaW[3]; mat[3][3] = rpaX[3]; mat[3][4] = rpaY[3]; mat[3][5] = rpaZ[3];
//        }        
//        else {
//            mat[0][0] = rpmU[0]; mat[0][1] = rpmV[0]; mat[0][2] = rpmW[0]; mat[0][3] = rpmX[0]; mat[0][4] = rpmY[0]; mat[0][5] = rpmZ[0];
//            mat[1][0] = rpmU[1]; mat[1][1] = rpmV[1]; mat[1][2] = rpmW[1]; mat[1][3] = rpmX[1]; mat[1][4] = rpmY[1]; mat[1][5] = rpmZ[1];
//            mat[2][0] = rpmU[2]; mat[2][1] = rpmV[2]; mat[2][2] = rpmW[2]; mat[2][3] = rpmX[2]; mat[2][4] = rpmY[2]; mat[2][5] = rpmZ[2];
//            mat[3][0] = rpmU[3]; mat[3][1] = rpmV[3]; mat[3][2] = rpmW[3]; mat[3][3] = rpmX[3]; mat[3][4] = rpmY[3]; mat[3][5] = rpmZ[3];
//        }        
//
//        
//        // MatrixBuilder<BigDecimal> biggie = BigMatrix.getBuilder(4, 6);
//
//        
//       for (int k = 0; k < 100; k++) {
//            BasicMatrix basic = BigMatrix.FACTORY.copy(mat);
//            System.out.println("basic.rank: " + basic.getRank());
//            RandomP(4);
//       }
//    }
       
//    private void Collinear(String mapchoice, int N){
//        double[][] mat = new double[3][N-1];
//        if (mapchoice.equals("PA")){
//            mat[0][0] = rpaU[0]; mat[0][1] = rpaV[0]; mat[0][2] = rpaW[0]; mat[0][3] = rpaX[0]; mat[0][4] = rpaY[0]; mat[0][5] = rpaZ[0];
//            mat[1][0] = rpaU[1]; mat[1][1] = rpaV[1]; mat[1][2] = rpaW[1]; mat[1][3] = rpaX[1]; mat[1][4] = rpaY[1]; mat[1][5] = rpaZ[1];
//            mat[2][0] = rpaU[2]; mat[2][1] = rpaV[2]; mat[2][2] = rpaW[2]; mat[2][3] = rpaX[2]; mat[2][4] = rpaY[2]; mat[2][5] = rpaZ[2];
//        }        
//        if (mapchoice.equals("PA1")){
//            mat = new double[3][N];
//            mat[0][0] = 1; mat[0][1] =paU[0];  mat[0][2] =paV[0];  mat[0][3] =paW[0];  mat[0][4] =paX[0];  mat[0][5] =paY[0];  mat[0][6] =paZ[0];
//            mat[1][0] = 1; mat[1][1] =paU[1];  mat[1][2] =paV[1];  mat[1][3] =paW[1];  mat[1][4] =paX[1];  mat[1][5] =paY[1];  mat[1][6] =paZ[1];
//            mat[2][0] = 1; mat[2][1] =paU[2];  mat[2][2] =paV[2];  mat[2][3] =paW[2];  mat[2][4] =paX[2];  mat[2][5] =paY[2];  mat[2][6] =paZ[2];
//        }
//
//       
//       for (int k = 0; k < 100; k++) {
//           
//            BasicMatrix basic = BigMatrix.FACTORY.copy(mat);
//            System.out.println("basic.rank: " + basic.getRank());
////            RealMatrix m = MatrixUtils.createRealMatrix(mat);
////            SingularValueDecomposition svd = new SingularValueDecomposition(m);
////            int rank = svd.getRank();
////            System.out.println("Rank: " + rank + ", rpaU[0]:" + rpaU[0]);
//            RandomP(3);
//       }
////       
////        Jama.Matrix mattpa = new Jama.Matrix(mat);
////        System.out.println("Jama Rank: " + mattpa.rank());
//    }
        
         
    
//    private void RandomP(int N){
//        rpaU = new double[N]; rpaV = new double[N]; rpaW = new double[N]; rpaX = new double[N]; rpaY = new double[N]; rpaZ = new double[N];
//        rpmU = new double[N]; rpmV = new double[N]; rpmW = new double[N]; rpmX = new double[N]; rpmY = new double[N]; rpmZ = new double[N];
//        System.out.println("Randoms: ");
//        Random random = new Random();
//        for (int i = 0; i < N; i++) {
//            int rando = random.nextInt(it - 0) + 0; System.out.print(rando + ". ");
//            rpaU[i] = paU[rando]; rpmU[i] = pmU[rando]; 
//            rpaV[i] = paV[rando]; rpmV[i] = pmV[rando]; 
//            rpaW[i] = paW[rando]; rpmW[i] = pmW[rando]; 
//            rpaX[i] = paX[rando]; rpmX[i] = pmX[rando]; 
//            rpaY[i] = paY[rando]; rpmY[i] = pmY[rando]; 
//            rpaZ[i] = paZ[rando]; rpmZ[i] = pmZ[rando];
//        }        
//    }
    
//    private double[][] Matrix(int N, String mapchoice){
//        double[][] matpa = new double[N][N];
//        double[][] matpm = new double[N][N];
//            matpa[0][0] = 1; matpa[0][1] = rpaU[0]; matpa[0][2] = rpaV[0]; matpa[0][3] = rpaW[0]; matpa[0][4] = rpaX[0]; matpa[0][5] = rpaY[0]; matpa[0][6] = rpaZ[0];
//            matpm[0][0] = 1; matpm[0][1] = rpmU[0]; matpm[0][2] = rpmV[0]; matpm[0][3] = rpmW[0]; matpm[0][4] = rpmX[0]; matpm[0][5] = rpmY[0]; matpm[0][6] = rpmZ[0];
//            matpa[1][0] = 1; matpa[1][1] = rpaU[1]; matpa[1][2] = rpaV[1]; matpa[1][3] = rpaW[1]; matpa[1][4] = rpaX[1]; matpa[1][5] = rpaY[1]; matpa[1][6] = rpaZ[1];
//            matpm[1][0] = 1; matpm[1][1] = rpmU[1]; matpm[1][2] = rpmW[1]; matpm[1][3] = rpmW[1]; matpm[1][4] = rpmX[1]; matpm[1][5] = rpmY[1]; matpm[1][6] = rpmZ[1];
//            matpa[2][0] = 1; matpa[2][1] = rpaU[2]; matpa[2][2] = rpaV[2]; matpa[2][3] = rpaW[2]; matpa[2][4] = rpaX[2]; matpa[2][5] = rpaY[2]; matpa[2][6] = rpaZ[2];
//            matpm[2][0] = 1; matpm[2][1] = rpmU[2]; matpm[2][2] = rpmW[2]; matpm[2][3] = rpmW[2]; matpm[2][4] = rpmX[2]; matpm[2][5] = rpmY[2]; matpm[2][6] = rpmZ[2];
//            matpa[3][0] = 1; matpa[3][1] = rpaU[3]; matpa[3][2] = rpaV[3]; matpa[3][3] = rpaW[3]; matpa[3][4] = rpaX[3]; matpa[3][5] = rpaY[3]; matpa[3][6] = rpaZ[3];
//            matpm[3][0] = 1; matpm[3][1] = rpmU[3]; matpm[3][2] = rpmW[3]; matpm[3][3] = rpmW[3]; matpm[3][4] = rpmX[3]; matpm[3][5] = rpmY[3]; matpm[3][6] = rpmZ[3];
//            matpa[4][0] = 1; matpa[4][1] = rpaU[4]; matpa[4][2] = rpaV[4]; matpa[4][3] = rpaW[4]; matpa[4][4] = rpaX[4]; matpa[4][5] = rpaY[4]; matpa[4][6] = rpaZ[4];
//            matpm[4][0] = 1; matpm[4][1] = rpmU[4]; matpm[4][2] = rpmW[4]; matpm[4][3] = rpmW[4]; matpm[4][4] = rpmX[4]; matpm[4][5] = rpmY[4]; matpm[4][6] = rpmZ[4];
//            matpa[5][0] = 1; matpa[5][1] = rpaU[5]; matpa[5][2] = rpaV[5]; matpa[5][3] = rpaW[5]; matpa[5][4] = rpaX[5]; matpa[5][5] = rpaY[5]; matpa[5][6] = rpaZ[5];
//            matpm[5][0] = 1; matpm[5][1] = rpmU[5]; matpm[5][2] = rpmW[5]; matpm[5][3] = rpmW[5]; matpm[5][4] = rpmX[5]; matpm[5][5] = rpmY[5]; matpm[5][6] = rpmZ[5];
//            matpa[6][0] = 1; matpa[6][1] = rpaU[6]; matpa[6][2] = rpaV[6]; matpa[6][3] = rpaW[6]; matpa[6][4] = rpaX[6]; matpa[6][5] = rpaY[6]; matpa[6][6] = rpaZ[6];
//            matpm[6][0] = 1; matpm[6][1] = rpmU[6]; matpm[6][2] = rpmW[6]; matpm[6][3] = rpmW[6]; matpm[6][4] = rpmX[6]; matpm[6][5] = rpmY[6]; matpm[6][6] = rpmZ[6];
//
//    if ("PA".equals(mapchoice)) {return matpa;}
//    else return matpm;
//    }
    
   private void DetRank(double[][] dm){
        BasicMatrix basic = BigMatrix.FACTORY.copy(dm);
        System.out.println("All.rank: " + basic.getRank());
        System.out.println("All.det: " + basic.getDeterminant());
       
       
//       RealMatrix m = MatrixUtils.createRealMatrix(dm);
//       SingularValueDecomposition svd = new SingularValueDecomposition(m);
//       LUDecomposition lud = new LUDecomposition(m);
//       int rank = svd.getRank();
//       double det = lud.getDeterminant();
//       System.out.println("Apache Rank: " + rank);
//       System.out.println("Apache Det: " + det);
       
//       Jama.Matrix mattpa = new Jama.Matrix(matpa);
//       Jama.Matrix mattpm = new Jama.Matrix(matpm);
//       System.out.println("Jama PA Det: " + mattpa.det() + ", PM: " + mattpm.det() );
//       System.out.println("Jama PA Rank: " + mattpa.rank() + ", PM: " + mattpm.rank() );
       
   }
   private void RowReduce(double[][] dm, int N) {
        DenseMatrix64F x = new DenseMatrix64F(dm);
        System.out.println("Before\n" + x.toString());
        RrefGaussJordanRowPivot neee = new RrefGaussJordanRowPivot();
        neee.reduce(x, N);
        System.out.println("After\n" + x.toString());
   }
   
}
