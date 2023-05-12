package MathPkg.Points;

import MathPkg.Angle;
import MathPkg.Lines.Line2D;
import MathPkg.Segments.Segment2D;

public class Point2D {
	
	public double x;
	public double y;
	
	public Point2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point2D point)
	{
		return(Math.sqrt(
				Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2)
				));
	}
	
	public double distance(Line2D line)
	{	
		return(Math.sin(Angle.angle(line, this) * Math.PI/180) * this.distance(line.point));
	}
	
	public double distance(Segment2D AB)
	{
		if(!AB.contains(this)) //if the point isn't in between the segment, the closest point is one of the extremities
		{
			return(Math.min(this.distance(AB.A), this.distance(AB.B)));
		}
		System.out.println("A : (" + AB.A.x + ", " + AB.A.y + ")");
		System.out.println("B : (" + AB.B.x + ", " + AB.B.y + ")");
		System.out.println("C : (" + this.x + ", " + this.y + ")");
		Segment2D AC = new Segment2D(AB.A, this);
		return(Math.sin(Angle.angle(AB, this) * Math.PI/180) * this.distance(AB.A));
		
	}

}
