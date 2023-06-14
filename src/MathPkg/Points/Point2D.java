package MathPkg.Points;

import MathPkg.Angle.Angle2D.AbsAngle;
import MathPkg.Lines.Line2D;
import MathPkg.Rays.Ray2D;
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
		return(Math.sin(AbsAngle.angle(line, this) * Math.PI/180) * this.distance(line.point));
	}
	
	public double distance(Ray2D ray)
	{
		if(AbsAngle.angle(ray, this) >= 90) return(this.distance(ray.origin));
		
		return(Math.sin(AbsAngle.angle(ray, this) * Math.PI/180) * this.distance(ray.origin));
	}
	
	public double distance(Segment2D AB)
	{
		if(!AB.contains(this)) //if the point isn't in between the segment, the closest point is one of the extremities
		{
			return(Math.min(this.distance(AB.A), this.distance(AB.B)));
		}
		return(Math.sin(AbsAngle.angle(AB, this) * Math.PI/180) * this.distance(AB.A));
		
	}

}
