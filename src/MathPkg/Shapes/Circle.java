package MathPkg.Shapes;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Circle {
	
	public Point2D center;
	public double radius;
	
	public Circle(Point2D center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point2D center, Point2D border)
	{
		this.center = center;
		this.radius = center.distance(border);
	}
	
	public int intersects(Point2D point)
	{
		return(center.distance(point) == radius ? 0 : center.distance(point) < radius ? 1 : -1);
	}
	
	public int intersects(Circle circ)
	{
		return(center.distance(circ.center) == radius + circ.radius ? 0 : center.distance(circ.center) < radius + circ.radius ? 1 : -1);
	}
	
	public int intersects(Line2D line)
	{
		return(center.distance(line) == radius ? 0 : center.distance(line) < radius ? 1 : -1);
	}
	
	public int intersects(Segment2D segment)
	{
		return(center.distance(segment) == radius ? 0 : center.distance(segment) < radius ? 1 : -1);
	}
	
	public Vector2D normalVect(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(point, this.center).negate();
		return(tmpVect.multiply(1/tmpVect.norm()));
	}
	
	public Point2D projection(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(point, this.center);
		return(tmpVect.multiply((radius)/tmpVect.norm()).negate().transform(this.center));
	}

}
