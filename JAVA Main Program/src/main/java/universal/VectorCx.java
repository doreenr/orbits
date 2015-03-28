/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universal;

import java.awt.geom.GeneralPath;


public class VectorCx {
    
    double[] x = new double[3];
    
    public VectorCx(){}

    public VectorCx(Complex z) {
	x[0]=z.x;
	x[1]=z.y;
	x[2]=1.0;
    }
    
    public VectorCx(Point z) {
	x[0]=z.getX();
	x[1]=z.getY();
	x[2]=1.0;
    }
    
    public VectorCx(VectorCx V) {
	for(int i=0;i<3;++i) x[i]=V.x[i];
    }

    public VectorCx(double xx, double yy) {
	x[0]=xx;
	x[1]=yy;
	x[2]=1.0;
    }
    
    public VectorCx(double xx, double yy, double zz) {
	x[0]=xx;
	x[1]=yy;
	x[2]=zz;
    }
    
    public double getX(){
	return (x[0]/x[2]);
    }

    public double getY(){
	return (x[1]/x[2]);
    }

    public static double dist(VectorCx V1, VectorCx V2) {
	VectorCx W1=V1.normalize();
	VectorCx W2=V2.normalize();
	double xx=W1.x[0]-W2.x[0];
	double yy=W1.x[1]-W2.x[1];
	double d=xx*xx+yy*yy;
	d=Math.sqrt(d);
	return(d);
    }


    public int between(VectorCx V1,VectorCx V2,VectorCx V3) {
	double d1=dist(V1,V2);
	double d2=dist(V2,V3);
	double d3=dist(V1,V3);
	if(d1+d2-d3<.00000000001) return(1);
	return(0);
    }


    public VectorCx pullback(Complex z) {
	VectorCx V=new VectorCx();
	V.x[0]=x[0]-z.x;
	V.x[1]=x[1]-z.y;
	V.x[2]=1.0;
	return(V);
    }

    public static double  dot(VectorCx v,VectorCx w) {
	return(v.x[0]*w.x[0]+v.x[1]*w.x[1]+v.x[2]*w.x[2]);
    }

    public static VectorCx cross(VectorCx v,VectorCx w) {
	VectorCx X = new VectorCx();
	X.x[0]=v.x[1]*w.x[2]-w.x[1]*v.x[2];
	X.x[1]=v.x[2]*w.x[0]-w.x[2]*v.x[0];
	X.x[2]=v.x[0]*w.x[1]-w.x[0]*v.x[1];
	return(X);
    }

    public static VectorCx plus(VectorCx v1,VectorCx v2) {
	VectorCx w=new VectorCx();
	for(int i=0;i<3;++i) {
	    w.x[i]=v1.x[i]+v2.x[i];
	}
	return(w);
    }


    public void print() {
	System.out.println("Vector: " + x[0]+" "+x[1]+" "+x[2]);
    }


    public VectorCx normalize() {
	VectorCx W=new VectorCx();
	W.x[0]=this.x[0]/this.x[2];
	W.x[1]=this.x[1]/this.x[2];
	W.x[2]=1;
	return(W);
    }



    public static double tripleProduct(VectorCx v1,VectorCx v2,VectorCx v3) {
	return(dot(cross(v1,v2),v3));
    }

    public static double tripleProduct2(Complex z1,Complex z2,Complex z3) {
	VectorCx v1=new VectorCx(z1);
	VectorCx v2=new VectorCx(z2);
	VectorCx v3=new VectorCx(z3);
	return(tripleProduct(v1,v2,v3));
    }



    public Complex  toComplex() {
	return(new Complex(x[0]/x[2],x[1]/x[2]));
    }

    public static VectorCx findCross(VectorCx v1,VectorCx v2,VectorCx v3,VectorCx v4) {
	VectorCx v5=cross(v1,v2);
	VectorCx v6=cross(v3,v4);
	VectorCx v7=cross(v5,v6);
	return(v7);
    }

    public static Complex findCross2(Complex z1,Complex z2,Complex z3,Complex z4) {
	VectorCx v1=new VectorCx(z1);
	VectorCx v2=new VectorCx(z2);
	VectorCx v3=new VectorCx(z3);	
        VectorCx v4=new VectorCx(z4);
	VectorCx v5=findCross(v1,v2,v3,v4);
	return(v5.toComplex());
    }
    
    public static GeneralPath toGeneralPath(VectorCx[] vect) {
        GeneralPath gp = new GeneralPath();
        gp.moveTo((vect[0].getX()),(vect[0].getY()));
        for (int i = 1; i < vect.length; ++i) {
            gp.lineTo((vect[i].getX()),(vect[i].getY()));
        } 
        return gp;
    }




}





