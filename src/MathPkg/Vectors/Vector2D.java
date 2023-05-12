package MathPkg.Vectors;

import MathPkg.Angle;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Lines.Line2D;

public class Vector2D {
	
	public double x;
	public double y;
	
	public Vector2D(Point2D A, Point2D B)
	{
		this.x = B.x - A.x;
		this.y = B.y - A.y;
	}
	
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Segment2D segment)
	{
		this.x = segment.B.x - segment.A.x;
		this.y = segment.B.y - segment.A.y;
	}
	
	public Vector2D(Line2D line)
	{
		this.x = line.vect.x;
		this.y = line.vect.y;
	}
	
	public double norm()
	{
		return(Math.sqrt(x*x + y*y));
	}
	
	public double dotProduct(Vector2D B)
	{
		return(this.x*B.x + this.y*B.y);
	}
	
	public Vector2D[] normalVectors()
	{
		return(new Vector2D[] {new Vector2D(-this.y, this.x), new Vector2D(this.y, -this.x)});
	}
	
	public Vector2D negate()
	{
		return(new Vector2D(-this.x, -this.y));
	}
	
	public Vector2D turn(double angle)
	{
		double thisAngle = Angle.angle(this, new Vector2D(1, 0)) * Math.PI/180;
		double x = this.norm() * (Math.cos(angle * Math.PI/180 + thisAngle));
		double y = this.norm() * (Math.sin(angle * Math.PI/180 + thisAngle));
		
		return(new Vector2D(x, y));
	}
	
	public Vector2D add(Vector2D vect)
	{
		return(new Vector2D(this.x + vect.x, this.y + vect.y));
	}
	
	public Vector2D symmetry(Vector2D symVect)
	{
		return(this.turn(0)); //TODO
	}
	
}
