/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package universal;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Doreen
 */
public final class UModel {
    
    // current state variables
    public int n = 3;
    public int iterations = 1000;
    private int mtimes = 20;
    private double a, b, pointsize;
    private Gon polybig, polysmall; //= new Gon(vertices, iteration);
    private Orbit o;
    private final Zeichnen zeichne = new Zeichnen();
    private int[] vert;
    private BigDecimal[] midn_arrayx, midn_arrayy;
    private BigDecimal[] flagup, flagdown, flagupsmall, flagdownsmall, allxn, allyn;
    private String bigoutmat, listPA, listPM;
    private StringBuilder polylistPA, polylistPM;
    private double[] pointsX, pointsY;
    private Point2D[] ALL, small; 
    private Point2D[] Puns;
    private int which;
    private double[] flagsU, flagsD, flagsUsm, flagsDsm;
    private boolean zoomin, zoomout, drag, zoominpm, zoomoutpm, zoominpa, zoomoutpa;
    
    UModel() {
        // Startpunkte 
        zoomin = true;
        pointsX = new double[n];
        pointsY = new double[n];
        pointsize = 1;
        if (n == 2) {
        pointsX[0] = 12.350000000000001; pointsY[0] = 18.549999999999997;
        pointsX[1] = 13.90; pointsY[1] = 16.95;}        
        if (n == 3) {
        pointsX[0] = 13; pointsY[0] = 18;
        pointsX[1] = 13.90; pointsY[1] = 16.95;
        pointsX[2] = 15; pointsY[2] = 16; }
        if (n == 4) {
        pointsX[0] = 13.2; pointsY[0] = 17.75;
        pointsX[1] = 13.95; pointsY[1] = 16.9;
        pointsX[2] = 14.8; pointsY[2] = 16.15;
        pointsX[3] = 15.65; pointsY[3] = 15.399999999999999;
        } 
        if (n == 5) {
        pointsX[0] = 14.200000000000001; pointsY[0] = 16.65; //
        pointsX[1] = 14.850000000000001; pointsY[1] = 16.05;
        pointsX[2] = 15.55; pointsY[2] = 15.5; //
        pointsX[3] = 16.3; pointsY[3] = 14.95; // 
        pointsX[4] = 16.950000000000003; pointsY[4] = 14.45;
        }        
        if (n == 6) {
        pointsX[0] = 13.450000000000001; pointsY[0] = 17.65;
        pointsX[1] = 13.950000000000001; pointsY[1] = 17.1;
        pointsX[2] =  14.450000000000001; pointsY[2] = 16.65; // 
        pointsX[3] = 14.950000000000001; pointsY[3] = 16.2; //
        pointsX[4] = 15.55; pointsY[4] = 15.75; //
        pointsX[5] = 16.1; pointsY[5] = 15.3; //
        }
        a = 0.8;
        b = 1.2;
        flagsU = new double[1];
        flagsD = new double[1];        
        flagsUsm = new double[1];
        flagsDsm = new double[1];
        for (int i = 0; i < 1; ++i) {
            flagsU[i] = flagsD[i] = 1; flagsUsm[i] = flagsDsm[i] = 1;}
        transFlags(flagsU, flagsD);
        transFlagsm(flagsUsm, flagsDsm); // make flagup and flagdown available
        reset();
        //JZY1 start = new JZY1();
    }
    
    public void reset() {
        which = (n*(mtimes-1)); 
        // System.out.println(" m    " + mtimes);
        polybig = new Gon(n, pointsX, pointsY, mtimes, a, b);
        if (n < 2) { polysmall = new Gon(n, pointsX, pointsY, n, a, b); }
        else { polysmall = new Gon(n, pointsX, pointsY, (n+8), a, b); }
        ALL = polybig.ReturnALL();
        small = polysmall.ReturnALL();
        // System.out.println("smallness:" + ALL.length);
        for (int i = 0; i < small.length; ++i) {
             // System.out.println("updateOrbit() flagsUD i:" + i + ", "+ flagsUsm[i]+ ", "+ flagsDsm[i]);
             // System.out.println("reset polysmall return i:" + i + ", "+ small[i]+ ", "+ small[i]);
             // smaller list of flags gives length 8 for n=3, length 21 for n=4, ...
        }
        o = new Orbit(iterations);
        vert = new int[9];
        for (int i = 0; i < vert.length; ++i) { vert[i] = 0; }
        vert[0] = 1;
    }   
    // ___________________________________________________________________________________________________________________________________________________________
    // UPDATING PLOT AND OUTPUT
    
    public void updateOrbit() {
        flagsU = getFlagsU();
        flagsD = getFlagsD();        
        flagsUsm = getFlagsUsm();
        flagsDsm = getFlagsDsm();
        // System.out.println("updateOrbit() " + flagsUsm.length + ", " + flagsU.length);
        transFlags(flagsU, flagsD); 
        transFlagsm(flagsUsm, flagsDsm); 
//        for (int i = 0; i < flagsDsm.length; ++i) {
//             // System.out.println("updateOrbit() flagsUD i:" + i + ", "+ flagsUsm[i]+ ", "+ flagsDsm[i]);
//             // System.out.println("updateOrbit() flagupsmall i:" + i + ", "+ flagupsmall[i]+ ", "+ flagdownsmall[i]);
//             // smaller list of flags gives length 8 for n=3, length 21 for n=4, ...
//        }
    } 
    
    public void updateZinput(boolean in, boolean out, boolean dragi) {
        zoomin = in;
        zoomout = out;
        drag = dragi;
    }     
    public void updateZPM(boolean in, boolean out) {
        zoominpm = in;
        zoomoutpm = out;
    }     
    public void updateZPA(boolean in, boolean out) {
        zoominpa = in;
        zoomoutpa = out;
    }    
    public boolean zoomIn() {
        return zoomin;
    }    
    public boolean zoomOut() {
        return zoomout;
    }    
    public boolean zoomInPM() {
        return zoominpm;
    }    
    public boolean zoomOutPM() {
        return zoomoutpm;
    }     
    public boolean zoomInPA() {
        return zoominpa;
    }    
    public boolean zoomOutPA() {
        return zoomoutpa;
    }    
    public boolean Drag() {
        return drag;
    }
    
    public void updateIteration(int it) {
        which = (n*(mtimes-1)); 
        // System.out.println(" which    " + which);
        polybig = new Gon(n, pointsX, pointsY, mtimes, a, b);
        o = new Orbit(iterations);        
    }
    
    public void UpdateOutput() {
        listPA = setOutputIt(polylistPA);
        listPM = setOutputIt(polylistPM);}
    // ___________________________________________________________________________________________________________________________________________________________
    // PLOTTT
    
    public Point2D[] getDisplay(int choice) {
        Puns = new Point2D[iterations];
            if (choice == 1 && flagupsmall.length != 1) {
            // System.out.println("hier n:" + n + ", length flagup: " + flagupsmall.length);
            BigDecimal[] extrU = new BigDecimal[n];
            BigDecimal[] extrD = new BigDecimal[n];
            for (int i = 0; i < n; ++i) {
                // System.out.println("extrU i:" + i + ", "+ flagupsmall[i]+ ","+ flagdownsmall[i]);
                extrU[i] = flagupsmall[i];
                extrD[i] = flagdownsmall[i];
                // does not plot initial points for now
                // System.out.println("extr i:" + i + ", "+ extrU[i]+ ","+ extrD[i]);
            }
            for (int k = 0; k < iterations; ++k) {
                BigDecimal[] writez = new BigDecimal[n];
                BigDecimal[] writew = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
                    int index1, index2, index3, index4;
                    BigDecimal tempadd1, tempadd2, tempadd3, tempadd4;
                    MathContext mc = new MathContext(30, RoundingMode.HALF_EVEN); 
                    index1 = o.minusone(i, n);
                    index2 = o.plusone(i, n);
                    index3 = o.plustwo(i, n);
                    index4 = o.simple(i, n);
                    tempadd1 = BigDecimal.ONE.subtract(extrU[index1].multiply(extrD[index1]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd2 = BigDecimal.ONE.subtract(extrU[index2].multiply(extrD[index2]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd3 = BigDecimal.ONE.subtract(extrU[index3].multiply(extrD[index3]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd4 = BigDecimal.ONE.subtract(extrU[index4].multiply(extrD[index4]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    if (tempadd4 != BigDecimal.ZERO) { writez[i] = extrU[index4].multiply(tempadd1.divide(tempadd2, mc), mc);}
                    else {System.out.println("0 Division");}
                    writew[i] = extrD[index2].multiply(tempadd3.divide(tempadd4, mc), mc);
                    if (i == 0) {
                    double plotdoublex = writez[i].doubleValue();
                    double plotdoubley = writew[i].doubleValue();
                    Point2D temp = new Point2D.Double(plotdoublex, plotdoubley);
                    Puns[k] = temp;
                    // System.out.println("k:" + k + ", i:" + i + ", temp:" + Puns[k].getX());
                    }}
            extrU = writez;
            extrD = writew;    
        }}
        else if (choice == 2 && flagupsmall.length != 1) {
        Point2D[] alle =  getALL();
        transfALL(alle);
        midn_arrayx = new BigDecimal[n];
        midn_arrayy = new BigDecimal[n];
        for (int i = 0; i < n; ++i) {
            midn_arrayx[i] = allxn[which-i];
            midn_arrayy[i] = allyn[which-i];}
        for (int k = 0; k < iterations; ++k) {
            BigDecimal[] bnewarrx = new BigDecimal[n];
            BigDecimal[] bnewarry = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
                bnewarrx[i] = o.phi(midn_arrayx[i], o.maxim(o.smallsum(midn_arrayx[o.minusone(i,n)], midn_arrayy[o.minusone(i,n)])), o.maxim(o.smallsum(midn_arrayx[o.plusone(i,n)], midn_arrayy[o.plusone(i,n)])));
                bnewarry[i] = o.phi(midn_arrayy[o.plusone(i, n)], o.maxim(o.smallsum(midn_arrayx[o.plustwo(i,n)], midn_arrayy[o.plustwo(i,n)])), o.maxim(o.smallsum(midn_arrayx[o.simple(i,n)], midn_arrayy[o.simple(i,n)])));
                    if (i == 0) {
                    double plotdoublex = bnewarrx[i].doubleValue();
                    double plotdoubley = bnewarry[i].doubleValue();
                    Point2D temp = new Point2D.Double(plotdoublex, plotdoubley);
                    Puns[k] = temp;}}                    
            midn_arrayx = bnewarrx;
            midn_arrayy = bnewarry;
            }}
        
        return Puns;
    }
    void displayAll(Graphics2D g, int choice, double ps) {
        float f = 0.1F;
        if (choice == 1 && flagupsmall.length != 1) {
            g.setColor(Color.yellow);
            // System.out.println("hier n:" + n + ", length flagup: " + flagupsmall.length);
            BigDecimal[] extrU = new BigDecimal[n];
            BigDecimal[] extrD = new BigDecimal[n];
            for (int i = 0; i < n; ++i) {
                // System.out.println("extrU i:" + i + ", "+ flagupsmall[i]+ ","+ flagdownsmall[i]);
                extrU[i] = flagupsmall[i];
                extrD[i] = flagdownsmall[i];
                System.out.println("extr i:" + i + ", "+ extrU[i]+ ","+ extrD[i]);
                zeichne.Dot(g, extrU[i], extrD[i], ps, 0.9f);
            }
            for (int k = 0; k < iterations; ++k) {
                BigDecimal[] writez = new BigDecimal[n];
                BigDecimal[] writew = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
//                BigDecimal oben = o.einsminus(o.mal(marrayz[o.minusone(i, n)], marrayw[o.minusone(i, n)]));    
//                BigDecimal unten = o.einsminus(o.mal(marrayz[o.plusone(i, n)], marrayw[o.plusone(i, n)])); 
//                writez[i] = o.mal(marrayz[o.simple(i, n)], o.bruch(oben, unten));
//                oben = o.einsminus(o.mal(marrayz[o.plustwo(i, n)], marrayw[o.plustwo(i, n)]));
//                unten = o.einsminus(o.mal(marrayz[o.simple(i, n)], marrayw[o.simple(i, n)]));
//                writew[i] = o.mal(marrayw[o.plusone(i, n)], o.bruch(oben, unten));
                    // 
                    int index1, index2, index3, index4;
                    BigDecimal tempadd1, tempadd2, tempadd3, tempadd4;
                    MathContext mc = new MathContext(30, RoundingMode.HALF_EVEN); 
                    index1 = o.minusone(i, n);
                    index2 = o.plusone(i, n);
                    index3 = o.plustwo(i, n);
                    index4 = o.simple(i, n);
//                    tempadd1 = BigDecimal.ONE.subtract(extrU[index1].multiply(extrD[index1], mc), mc);
//                    tempadd2 = BigDecimal.ONE.subtract(extrU[index2].multiply(extrD[index2], mc), mc);
//                    tempadd3 = BigDecimal.ONE.subtract(extrU[index3].multiply(extrD[index3], mc), mc);
//                    tempadd4 = BigDecimal.ONE.subtract(extrU[index4].multiply(extrD[index4], mc), mc);
                    tempadd1 = BigDecimal.ONE.subtract(extrU[index1].multiply(extrD[index1]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd2 = BigDecimal.ONE.subtract(extrU[index2].multiply(extrD[index2]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd3 = BigDecimal.ONE.subtract(extrU[index3].multiply(extrD[index3]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    tempadd4 = BigDecimal.ONE.subtract(extrU[index4].multiply(extrD[index4]).setScale(100, RoundingMode.HALF_EVEN)).setScale(100, RoundingMode.HALF_EVEN);
                    
                    if (tempadd4 != BigDecimal.ZERO) { writez[i] = extrU[index4].multiply(tempadd1.divide(tempadd2, mc), mc);}
                    else {System.out.println("0 Division");}
                    writew[i] = extrD[index2].multiply(tempadd3.divide(tempadd4, mc), mc);
                    
                    if (i == 0) {
                    g.setColor(new Color(0,0,130));
                    zeichne.Dot(g, writez[i], writew[i], ps, 0.2f);}                    
//                    else if (i == 1) {
//                    g.setColor(Color.MAGENTA);
//                    zeichne.Dot(g, writez[i], writew[i], ps, 0.2f);
//                    }                    
//                    else if (i == 2) {
//                    g.setColor(Color.ORANGE);
//                    zeichne.Dot(g, writez[i], writew[i], ps, 0.2f);
//                    }                    
//                    else if (i == 3) {
//                    g.setColor(Color.green);
//                    zeichne.Dot(g, writez[i], writew[i], ps, 0.2f);}                    
//                    else if (i == 4) {
//                    g.setColor(Color.CYAN);
//                    zeichne.Dot(g, writez[i], writew[i], ps, 0.2f);}
                }
                // g.setColor(new Color(0,0,130));
            // zeichne.DeepOrbit(g, writez, writew, ps, 0.4f, vert); 
            extrU = writez;
            extrD = writew;    
            // zeichne.FlatOrbit(g, extrU, extrD, (ps+1), 0.4f);
        }}
        else if (choice == 3 && flagup.length != 1) {
        // PENTAGRAM MAP PLOTS from FLAGS
        // System.out.println("mtimes: " + mtimes + ", iterations: " + iterations + ", which: " + which + ", flagup.length: " + flagup.length+ ", flagd.length: " + flagdown.length);    
        // ALL = polybig.ReturnALL();
        g.setColor(Color.RED);
//        marrayz = new BigDecimal[n];
//        marrayw = new BigDecimal[n];
//        for (int i = 0; i <= n; ++i) {
//            marrayz[i] = flagup[which+i];
//            marrayw[i] = flagdown[which+i];}
//        zeichne.FlatOrbit(g, marrayz, marrayw, ps, 0.9f);
        zeichne.FlatOrbit(g, flagup, flagdown, ps, 0.2f);
//        for (int i = 0; i < flagup.length/3; ++i) {
//            // System.out.println("i:" + i + ", up: " + flagup[i]+ ", down: " + flagdown[i]);
//        }
        BigDecimal[] readfromz = flagup;
        BigDecimal[] readfromw = flagdown;
        for (int k = 0; k < iterations; ++k) {
            if (readfromz.length > n) {
            BigDecimal[] writetoz = new BigDecimal[readfromz.length-n];
            BigDecimal[] writetow = new BigDecimal[readfromz.length-n];
            BigDecimal nulll = BigDecimal.ZERO;
            for (int i = 0; i < readfromz.length - n; ++i) {
                //System.out.println("k: " + k + ", readfromz i: " + readfromz[i] + ", readfromw i: " + readfromw[i]);
//                System.out.println("produkt1 = o.mal(readfromz[i], readfromw[i]): " + (o.mal(readfromz[i], readfromw[i])));
                // indices in written to array are one further i than in read from array! // i-1 = i; i = i+1; i+1 = i+2; i+2 = i+3;
                BigDecimal oben = o.einsminus(o.mal(readfromz[i], readfromw[i]));
                //System.out.println("i: " + i + ", oben (1-produkt): " + oben);
                BigDecimal unten1 = o.einsminus(o.mal(readfromz[i+2], readfromw[i+2]));
                //System.out.println("produkt2 = o.mal(readfromz[i+2], readfromw[i+2]): " + (o.mal(readfromz[i+2], readfromw[i+2])));
                //System.out.println("i+2: " + (i+2) + ", unten1 (1-produkt): " + unten1);
                if (unten1.compareTo(nulll) != 0) {writetoz[i] = o.mal(readfromz[i+1], o.bruch(oben, unten1));}
                //System.out.println("i: " + i + ", writetoz i: " + writetoz[i]); // + ", writetow i: " + writetow[i]);
                oben = o.einsminus(o.mal(readfromz[i+3], readfromw[i+3]));
                BigDecimal unten2 = o.einsminus(o.mal(readfromz[i+1], readfromw[i+1]));
                if (unten2.compareTo(nulll) != 0) {writetow[i] = o.mal(readfromz[i+2], o.bruch(oben, unten2));}
                g.setColor(Color.blue);
                if (unten1.compareTo(nulll) != 0 && unten2.compareTo(nulll) != 0) {
                zeichne.Dot(g, writetoz[i], writetow[i], ps, 0.2f);
                // g.setColor(Color.orange);
                // zeichne.Dot(g, writetoz[i-1], writetow[i-1], ps, 0.1f);
                }}
            readfromz = writetoz;
            readfromw = writetow; 
            }}}
        else if (choice == 2 && flagupsmall.length != 1) {
        Point2D[] alle =  getALL();
        transfALL(alle);
        midn_arrayx = new BigDecimal[n];
        midn_arrayy = new BigDecimal[n];
        for (int i = 0; i < n; ++i) {
            midn_arrayx[i] = allxn[which-i];
            midn_arrayy[i] = allyn[which-i];}
        g.setColor(Color.red);
        for (int i = 0; i < n; ++i) {
            zeichne.Dot(g, midn_arrayx[i], midn_arrayy[i], ps, 0.8f);}    
        for (int k = 0; k < iterations; ++k) {
            BigDecimal[] bnewarrx = new BigDecimal[n];
            BigDecimal[] bnewarry = new BigDecimal[n];
                for (int i = 0; i < n; ++i) {
                bnewarrx[i] = o.phi(midn_arrayx[i], o.maxim(o.smallsum(midn_arrayx[o.minusone(i,n)], midn_arrayy[o.minusone(i,n)])), o.maxim(o.smallsum(midn_arrayx[o.plusone(i,n)], midn_arrayy[o.plusone(i,n)])));
                bnewarry[i] = o.phi(midn_arrayy[o.plusone(i, n)], o.maxim(o.smallsum(midn_arrayx[o.plustwo(i,n)], midn_arrayy[o.plustwo(i,n)])), o.maxim(o.smallsum(midn_arrayx[o.simple(i,n)], midn_arrayy[o.simple(i,n)])));
                    if (i == 0) {
                    g.setColor(new Color(0,0,130));
                    zeichne.Dot(g, bnewarrx[i], bnewarry[i], ps, 0.2f);}                    
//                    else if (i == 1) {
//                    g.setColor(Color.MAGENTA);
//                    zeichne.Dot(g, bnewarrx[i], bnewarry[i], ps, 0.2f);
//                    }                    
//                    else if (i == 2) {
//                    g.setColor(Color.ORANGE);
//                    zeichne.Dot(g, bnewarrx[i], bnewarry[i], ps, 0.2f);
//                    }                    
//                    else if (i == 3) {
//                    g.setColor(Color.green);
//                    zeichne.Dot(g, bnewarrx[i], bnewarry[i], ps, 0.2f);}                    
//                    else if (i == 4) {
//                    g.setColor(Color.CYAN);
//                    zeichne.Dot(g, bnewarrx[i], bnewarry[i], ps, 0.2f);}
                }
            // zeichne.DeepOrbit(g, bnewarrx, bnewarry, ps, f, vert);
            // polylistPA = finito(k, polylistPA, bigxn, bigyn, bnewarrx, bnewarry);
            // UpdateOutput();
            midn_arrayx = bnewarrx;
            midn_arrayy = bnewarry;
            }
        }
        else if (choice == 4) {}}
    
    // ___________________________________________________________________________________________________________________________________________________________
    // CONSTRUCT ALL POINTS (RESP GET FLAGS)
    // 
    
    public double[] getFlagsU() {
        return flagsU;}    
    public double[] getFlagsD() {
        return flagsD;}
    public double[] getFlagsUsm() {
        return flagsUsm;}    
    public double[] getFlagsDsm() {
        return flagsDsm;}
    public void setFlagsU(double[] newflagu) {
        flagsU = newflagu;}    
    public void setFlagsD(double[] newflagd) {
        flagsD = newflagd;}    
    public void setFlagsUsm(double[] newflagu) {
        flagsUsm = newflagu;}    
    public void setFlagsDsm(double[] newflagd) {
        flagsDsm = newflagd;}
    
    public Point2D[] getALL() { 
        return ALL; }
    public Point2D[] getSmall() { 
        return small; }
    
    public void transfALL(Point2D[] ALL) {
        allxn = new BigDecimal[ALL.length];
        allyn = new BigDecimal[ALL.length];
        // System.out.println(ALL.length + "ALL2 length"); 
        for (int j = 0; j < ALL.length; ++j) {
            //System.out.println(j + " ALLx: " + ALL[j].getX()+ " ALLy: " + ALL[j].getY());
            allxn[j] = new BigDecimal(ALL[j].getX());
            allyn[j] = new BigDecimal(ALL[j].getY()); }}
    
    public void transFlags(double[] flaggenU, double[] flaggenD) {
        flagup = new BigDecimal[flaggenU.length];
        flagdown = new BigDecimal[flaggenD.length];
        for (int j = 0; j < flaggenD.length; ++j) {
            flagup[j] = new BigDecimal(flaggenU[j]);
            flagdown[j] = new BigDecimal(flaggenD[j]); }}
    public void transFlagsm(double[] flaggenU, double[] flaggenD) {
        flagupsmall = new BigDecimal[flaggenU.length];
        flagdownsmall = new BigDecimal[flaggenD.length];
        for (int j = 0; j < flaggenD.length; ++j) {
            flagupsmall[j] = new BigDecimal(flaggenU[j]);
            flagdownsmall[j] = new BigDecimal(flaggenD[j]); }}
    
    // ___________________________________________________________________________________________________________________________________________________________
    // Zähle Recurrence
    
    public StringBuilder finito(int i, StringBuilder polylist, BigDecimal[] initx, BigDecimal[] inity, BigDecimal[] newx, BigDecimal[] newy) {
        int countlength = 0;
        BigDecimal abso;
        BigDecimal threshold = new BigDecimal("0.00001");
        for (int j = 0; j < initx.length; ++j) {
            abso = initx[j].subtract(newx[j]);
            abso = abso.abs();
            // System.out.println("initx j: " + initx[j] + ", inity j: " + inity[j]);
            if (abso.compareTo(threshold) == -1) {
                countlength += 1;}}
        if (countlength == initx.length) {
            polylist.append(i);
            polylist.append(", ");
            // System.out.println(polylist);
        }            
        return polylist;}
    
    // ___________________________________________________________________________________________________________________________________________________________
    // BASICS
    
    public int getmtimes() {
        return mtimes;}     
    public void setmtimes(int newm) {
        mtimes = newm;}
    public int getit() {
        return iterations;} 
    public void setit(int newit) {
        iterations = newit;}     
    public void setps(double newps) {
        pointsize = newps;}    
    public double getps() {
        return pointsize;}    
    public int getn() {
        return n;}  
    public void setn(int newn) {
        n = newn;
        a = 0.8;
        b = 1.2;
        pointsX = new double[n];
        pointsY = new double[n];
        if (n == 1) {
        pointsX[0] = 13; pointsY[0] = 18;}
        else if (n == 2) {
        // System.out.println("N == 2" );
        pointsX[0] = 12.350000000000001; pointsY[0] = 18.549999999999997;
        pointsX[1] = 13.90; pointsY[1] = 16.95;}
        else if (n == 3) {
        pointsX[0] = 13; pointsY[0] = 18;
        pointsX[1] = 13.90; pointsY[1] = 16.95;
        pointsX[2] = 15; pointsY[2] = 16; }
        else if (n == 4) {
        pointsX[0] = 13.9; pointsY[0] = 16.9;
        pointsX[1] = 14.4; pointsY[1] = 16.4;
        pointsX[2] = 14.8; pointsY[2] = 16.05;
        pointsX[3] = 15.350000000000001; pointsY[3] = 15.59999999999999;}        
        else if (n == 5) {
        pointsX[0] = 14.200000000000001; pointsY[0] = 16.65; //
        pointsX[1] = 14.850000000000001; pointsY[1] = 16.05;
        pointsX[2] = 15.55; pointsY[2] = 15.5; //
        pointsX[3] = 16.3; pointsY[3] = 14.95; // 
        pointsX[4] = 16.950000000000003; pointsY[4] = 14.45;
        }  
        else if (n == 6) {
        pointsX[0] = 13.450000000000001; pointsY[0] = 17.65;
        pointsX[1] = 13.950000000000001; pointsY[1] = 17.1;
        pointsX[2] = 14.450000000000001; pointsY[2] = 16.65; // 
        pointsX[3] = 14.950000000000001; pointsY[3] = 16.2; //
        pointsX[4] = 15.55; pointsY[4] = 15.75; //
        pointsX[5] = 16.1; pointsY[5] = 15.3;
        for (int i = 0; i<n; ++i) {
            pointsX[i] = pointsX[i]*1.5;
            pointsY[i] = pointsY[i]*1.5; }
        } 
        else if (n == 7) {
        pointsX[0] = 13.450000000000001; pointsY[0] = 17.65;
        pointsX[1] = 13.950000000000001; pointsY[1] = 17.1;
        pointsX[2] = 14.450000000000001; pointsY[2] = 16.65; // 
        pointsX[3] = 14.950000000000001; pointsY[3] = 16.2; //
        pointsX[4] = 15.55; pointsY[4] = 15.75; //
        pointsX[5] = 16.1; pointsY[5] = 15.3;
        pointsX[6] = 16.55; pointsY[6] = 14.95;        
        for (int i = 0; i<n; ++i) {
            pointsX[i] = pointsX[i]*1.8;
            pointsY[i] = pointsY[i]*1.8; }
        } 
        else if (n == 8) {
        pointsX[0] = 13.450000000000001; pointsY[0] = 17.65;
        pointsX[1] = 13.950000000000001; pointsY[1] = 17.1;
        pointsX[2] = 14.450000000000001; pointsY[2] = 16.65; // 
        pointsX[3] = 14.950000000000001; pointsY[3] = 16.2; //
        pointsX[4] = 15.55; pointsY[4] = 15.75; //
        pointsX[5] = 16.1; pointsY[5] = 15.3;
        pointsX[6] = 16.55; pointsY[6] = 14.95; 
        pointsX[7] = 17.1; pointsY[7] = 14.5;
        for (int i = 0; i<n; ++i) {
            pointsX[i] = pointsX[i]*2.1;
            pointsY[i] = pointsY[i]*2.1; }        
        }
        else if (n == 9) {
        pointsX[0] = 13.450000000000001; pointsY[0] = 17.65;
        pointsX[1] = 13.950000000000001; pointsY[1] = 17.1;
        pointsX[2] = 14.450000000000001; pointsY[2] = 16.65; // 
        pointsX[3] = 14.950000000000001; pointsY[3] = 16.2; //
        pointsX[4] = 15.55; pointsY[4] = 15.75; //
        pointsX[5] = 16.1; pointsY[5] = 15.3;
        pointsX[6] = 16.55; pointsY[6] = 14.95; 
        pointsX[7] = 17.1; pointsY[7] = 14.5;
        pointsX[8] = 17.8; pointsY[8] = 14.05;
        for (int i = 0; i<n; ++i) {
            pointsX[i] = pointsX[i]*2.4;
            pointsY[i] = pointsY[i]*2.4; }
        }
        
    }
    public void setA(double newa) {
        a = newa; }
    public void setB(double newb) {
        b = newb; }
    
    // ___________________________________________________________________________________________________________________________________________________________
    // GON SPECS FROM CANVAS to setup polybig   
    
    public void setPXs(double pxs, int which) {
        pointsX[which] = pxs;}
    public void setPYs(double pys, int which) {
        pointsY[which] = pys; }
    public double getPXs(int which) {
        // System.out.println(which + ", px " + pointsX[which]);
        return pointsX[which]; }
    public double getPYs(int which) {
        return pointsY[which]; }
    public double[] getPX() {
        return pointsX; }
    public double[] getPY() {
        return pointsY; }
    
    public double CrossRatio(Vector m, Vector l1, Vector l2, Vector l3, Vector l4) {
        Vector m1 = new Vector(Vector.cross(l1, m));
        Vector m2 = new Vector(Vector.cross(l2, m));
        Vector m3 = new Vector(Vector.cross(l3, m));
        Vector m4 = new Vector(Vector.cross(l4, m));
        double distoben1 = Vector.dist(m1, m2);
        double distoben2 = Vector.dist(m3, m4);
        double distunten3 = Vector.dist(m1, m3);
        double distunten4 = Vector.dist(m2, m4);
        double c = (distoben1*distoben2)/(distunten3*distunten4);
        return c;
    }
    
    // ___________________________________________________________________________________________________________________________________________________________    
    // VERTICES ZU ZEICHNEN
    
    public void updateVert(int[] verti) {
        vert = verti; }
    public int[] getVert() {
        return vert; } 
//    public void setv(int newv) {
//        vertices = newv;}
//    public int getv() {
//        return vertices; }  
    
    // ___________________________________________________________________________________________________________________________________________________________    
    // OUTPUT SPECS
    
    public String getoutitPA() {
        return listPA; }     
    public String getoutitPM() {
        return listPM; }     
    public String getoutmat() {
        return bigoutmat; }    

        
    public String setOutputIt(StringBuilder polylist) {
        String liste = polylist.toString();
        String labellist = "Returns at Iterations: ";
        String s3 = labellist + liste;
        return s3; }  
    

}    

    
    
    
    

