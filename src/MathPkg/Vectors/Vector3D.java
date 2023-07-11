package MathPkg.Vectors;

import MathPkg.Points.Point3D;
import MathPkg.Shapes.Shapes3D.Plane;

public class Vector3D {
	 
	public double x;
	public double y;
	public double z;
	
	public Vector3D(Point3D A, Point3D B)
	{
		this.x = B.x - A.x;
		this.y = B.y - A.y;
		this.z = B.z - A.z;
	}
	
	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double norm()
	{
		return(Math.sqrt(x*x + y*y + z*z));
	}
	
	public double dotProduct(Vector3D B)
	{
		return(this.x*B.x + this.y*B.y + this.z*B.z);
	}
	
	public double determinant(Vector3D B, Vector3D C)
	{
		return(this.x*B.y*C.z + this.y*B.z*C.x + this.z*B.x*C.y + this.x*B.z*C.y + this.y*B.x*C.z + this.z*B.y*C.x);
	}
	
	public Vector3D[] normalVectorsInPlane(Plane plane)
	{
		return(new Vector3D[] {});
	}
	
	public Vector3D[] normalVectorsToPlane(Plane plane)
	{
		return(new Vector3D[] {});
	}
	
	public boolean isNormalVector(Vector3D vect)
	{
		return(dotProduct(vect) == 0);
	}

	public Vector3D negate()
	{
		return(new Vector3D(-this.x, -this.y, -this.z));
	}
	
	public Vector3D multiply(double mult)
	{
		return(new Vector3D(this.x*mult, this.y*mult, this.z*mult));
	}
	
	public Vector3D unit()
	{
		return(this.multiply(1/this.norm()));
	}

	public Point3D transform(Point3D pnt) {
		return(new Point3D(pnt.x + this.x, pnt.y + this.y, pnt.z + this.z));
	}
	
	public Vector3D add(Vector3D vect)
	{
		return(new Vector3D(this.x + vect.x, this.y + vect.y, this.z + vect.z));
	}

	public Vector3D turnXAxis(double xAxisTurn)
	{
		Vector2D YZVect = new Vector2D(this.y, this.z);
		YZVect = YZVect.turn(xAxisTurn);
		Vector3D tmpVect = this;

		tmpVect.y = YZVect.x;
		tmpVect.z = YZVect.y;

		return(tmpVect);
	}

	public Vector3D turnYAxis(double yAxisTurn)
	{
		Vector2D XZVect = new Vector2D(this.x, this.z);
		XZVect = XZVect.turn(yAxisTurn);
		Vector3D tmpVect = this;

		tmpVect.x = XZVect.x;
		tmpVect.z = XZVect.y;

		return(tmpVect);
	}

	public Vector3D turnZAxis(double ZAxisTurn)
	{
		Vector2D XYVect = new Vector2D(this.x, this.y);
		XYVect = XYVect.turn(ZAxisTurn);
		Vector3D tmpVect = this;

		tmpVect.x = XYVect.x;
		tmpVect.y = XYVect.y;

		return(tmpVect);
	}	
}
