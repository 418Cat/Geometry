package MathPkg.Lines;

import MathPkg.Points.Point3D;
import MathPkg.Vectors.Vector3D;

public class Line3D {
	
	public Point3D point;
	public Vector3D vect;
	
	public Line3D(Point3D point, Vector3D vect)
	{
		this.point = point;
		this.vect = vect.unit();
	}
	
	public Line3D(Point3D pntA, Point3D pntB)
	{
		this.point = pntA;
		this.vect = new Vector3D(pntA, pntB).unit();
	}
	
	public Point3D projection(Point3D pnt)
	{
		return(this.vect.multiply(new Vector3D(this.point, pnt).dotProduct(vect)/this.vect.norm()).transform(this.point));
	}
	
	public Point3D symmetry(Point3D pnt)
	{
		return(new Vector3D(pnt, this.projection(pnt)).multiply(2).transform(pnt));
	}

}
