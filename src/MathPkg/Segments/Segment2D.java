package MathPkg.Segments;

import MathPkg.Angle.AbsAngle;
import MathPkg.Points.Point2D;
import MathPkg.Vectors.Vector2D;

public class Segment2D {
	
	public Point2D A;
	public Point2D B;
	
	public Segment2D(Point2D A, Point2D B)
	{
		this.A = A;
		this.B = B;
	}
	
	public Segment2D(Point2D A, Vector2D vect)
	{
		this.A = A;
		this.B = new Point2D(A.x + vect.x, A.y + vect.y);
	}
	
	public boolean contains(Point2D point)
	{
		return(AbsAngle.angle(this, point) == 0 && AbsAngle.angle(this.negate(), point) == 0);
	}
	
	public boolean PointBetween(Point2D point)
	{
		return(AbsAngle.angle(this, point) <= 90 && AbsAngle.angle(this.negate(), point) <= 90);
	}
	
	public Segment2D negate()
	{
		return(new Segment2D(B, A));
	}
	
	public double norm()
	{
		return(Math.sqrt(
				Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.x, 2)
				));
	}

}
