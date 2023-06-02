package MathPkg.Shapes;

import MathPkg.Angle.AbsAngle;
import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Ray.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;
import mainPkg.Main;

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
	
	public int intersects(Ray2D ray)
	{
		return(center.distance(ray) == radius ? 0 : center.distance(ray) < radius ? 1 : -1);
	}
	
	public double distance(Line2D line)
	{
		return(this.intersects(line) >= 0 ? 0 : line.projection(this.center).distance(this.center) - this.radius);
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
	
	public Point2D projection(Ray2D ray)
	{
		//if(AbsAngle.angle(ray, this.center) >= 90) return(this.projection(ray.origin));
		
		return(this.projection(ray.projection(center)));
	}
	
	public Point2D projection(Line2D line)
	{
		return(this.projection(line.projection(this.center)));
	}
	
	
	public Point2D[] intersection(Line2D line)
	{
		if(this.intersects(line) == -1) return(new Point2D[] {});
		if(this.intersects(line) == 0) return(new Point2D[] {projection(line.projection(center))});
		
		Point2D tmpPoint = line.projection(this.center);
		Point2D[] intersectList = {
				line.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).transform(tmpPoint),
				line.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).negate().transform(tmpPoint)
		};
		
		return intersectList;
	}
	
	public Point2D[] intersection(Ray2D ray)
	{
		if(this.intersects(ray) == -1) return(new Point2D[] {});
		if(this.intersects(ray) == 0) return(new Point2D[] {projection(ray.projection(center))});
		
		Point2D tmpPoint = ray.projection(this.center);
		
		Point2D[] intersectList = {
				ray.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).transform(tmpPoint),
				ray.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).negate().transform(tmpPoint)
		};
		
		if(AbsAngle.angle(ray, intersectList[0]) > 90) return new Point2D[] {intersectList[1]};
		if(AbsAngle.angle(ray, intersectList[1]) > 90) return new Point2D[] {intersectList[0]};
		
		return intersectList;
	}
	
	public Ray2D hitSymmetry(Ray2D ray)
	{
		if(intersects(ray) < 0) return new Ray2D(new Point2D(0, 0), new Vector2D(1, 1));
		
		Point2D[] rayIntersect = this.intersection(ray);

		Point2D closestPoint = rayIntersect.length > 1 ? rayIntersect[0].distance(ray.origin) > rayIntersect[1].distance(ray.origin) ? rayIntersect[1] : rayIntersect[0] : rayIntersect[0];
		
		Line2D normLine = new Line2D(this.center, closestPoint);
		return(new Ray2D(closestPoint, normLine.symmetry(new Segment2D(closestPoint, ray.origin)).B));
		
	}

}
