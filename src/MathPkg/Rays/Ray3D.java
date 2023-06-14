package MathPkg.Rays;

import MathPkg.Angle.Angle3D.AbsAngle;
import MathPkg.Points.Point3D;
import MathPkg.Vectors.Vector3D;

public class Ray3D {
	
	public Point3D origin;
	public Vector3D vect;
	
	public Ray3D(Point3D origin, Vector3D vect)
	{
		this.origin = origin;
		this.vect = vect;
	}
	
	public Ray3D(Point3D origin, Point3D pnt)
	{
		this.origin = origin;
		this.vect = new Vector3D(origin, pnt);
	}
	
	public Point3D projection(Point3D pnt)
	{
		double angle = AbsAngle.angle(this, pnt);
		
		if(angle > 90) return this.origin;
		
		double distOriginProjPnt = new Vector3D(this.origin, pnt).norm() * Math.cos(angle * Math.PI/180);
		
		return(this.vect.unit().multiply(distOriginProjPnt).transform(pnt));
	}
	

}
