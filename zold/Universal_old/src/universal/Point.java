/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universal;

/**
 *
 * @author Doreen
 */
/**
 * This class represents a point in the real projective plane.
 * 
 * @author W. Patrick Hooper <wphooper@gmail.com>
 * @version 1
 */
public class Point {
    /** The following array stores the homogeneous coordinates for the point. */
    double[] c=new double[3];

    public Point(){
	c[0]=c[1]=0;
	c[2]=1;
    }

    public Point(double x, double y){
	c[0]=x;
	c[1]=y;
	c[2]=1;
    }

    // Given in Projective coordinates
    public Point(double x, double y, double z){
	c[0]=x;
	c[1]=y;
	c[2]=z;
    }

    // Given in Projective coordinates given by an array
    public Point(double[] x){
	c[0]=x[0];
	c[1]=x[1];
	c[2]=x[2];
    }

    public Point(Point P){
	double d[]=P.getProjectiveCoordinates();
	c[0]=d[0];
	c[1]=d[1];
	c[2]=d[2];
    }

    public double distanceSquared(Point P){
	return 
	    ((getX()-P.getX())*(getX()-P.getX())+
	     (getY()-P.getY())*(getY()-P.getY()));
    }

    public boolean isNotInfinite(){
	return (c[2]!=0);
    }

    
    public boolean isInfinite(){
	return (c[2]==0);
    }
	    
    public double getX(){
	return (c[0]/c[2]);
    }

    public double getY(){
	return (c[1]/c[2]);
    }

    public double[] getProjectiveCoordinates(){
	return c;
    }

    public String toString(){
	return "("+
	    Double.toString(c[0])+":"+
	    Double.toString(c[1])+":"+
	    Double.toString(c[2])+")";
    }

}


