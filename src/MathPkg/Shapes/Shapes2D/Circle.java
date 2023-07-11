package MathPkg.Shapes.Shapes2D;

import MathPkg.Angle.Angle2D.AbsAngle;
import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Circle implements Reflector2D {
	
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
	
	
	public int intersectionState(Point2D point)
	{
		return(center.distance(point) == radius ? 0 : center.distance(point) < radius ? 1 : -1);
	}
	public boolean intersects(Point2D point)
	{
		return(this.intersectionState(point) > -1);
	}
	
	
	public int intersectionState(Circle circ)
	{
		return(center.distance(circ.center) == radius + circ.radius ? 0 : center.distance(circ.center) < radius + circ.radius ? 1 : -1);
	}
	public boolean intersects(Circle circ)
	{
		return(this.intersectionState(circ) > -1);
	}
	
	
	public int intersectionState(Line2D line)
	{
		return(center.distance(line) == radius ? 0 : center.distance(line) < radius ? 1 : -1);
	}
	public boolean intersects(Line2D line)
	{
		return(this.intersectionState(line) > -1);
	}
	
	
	public int intersectionState(Segment2D segment)
	{
		return(center.distance(segment) == radius ? 0 : center.distance(segment) < radius ? 1 : -1);
	}
	public boolean intersects(Segment2D segment)
	{
		return(this.intersectionState(segment) > -1);
	}
	
	public int intersectionState(Ray2D ray)
	{
		if(ray.origin.distance(this.center) < this.radius) return 0;
		return(center.distance(ray) == radius ? 0 : center.distance(ray) < radius ? 1 : -1);
	}
	@Override
	public boolean intersects(Ray2D ray)
	{
		return(intersectionState(ray) > -1);
	}
	
	
	public double distance(Line2D line)
	{
		return(this.intersectionState(line) >= 0 ? 0 : line.projection(this.center).distance(this.center) - this.radius);
	}
	
	public Vector2D normalVect(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(point, this.center).negate();
		return(tmpVect.multiply(1/tmpVect.norm()));
	}
	
	public Point2D projection(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(this.center, point);
		return(tmpVect.unit().multiply(radius > tmpVect.norm() ? tmpVect.norm() : radius).transform(this.center));
	}
	
	public Point2D projection(Ray2D ray)
	{		
		return(this.projection(ray.projection(center)));
	}
	
	public Point2D projection(Line2D line)
	{
		return(this.projection(line.projection(this.center)));
	}
	
	
	public Point2D[] intersection(Line2D line)
	{
		if(this.intersectionState(line) == -1) return(new Point2D[] {});
		if(this.intersectionState(line) == 0) return(new Point2D[] {projection(line.projection(center))});
		
		Point2D tmpPoint = line.projection(this.center);
		Point2D[] intersectList = {
				line.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).transform(tmpPoint),
				line.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).negate().transform(tmpPoint)
		};
		
		return intersectList;
	}
	
	public Point2D[] intersection(Ray2D ray)
	{
		if(!this.intersects(ray)) return(new Point2D[] {});
		
		Point2D tmpPoint = ray.projection(this.center);
		
		if(this.intersectionState(ray) == 0)
		{
			if(tmpPoint.distance(this.center) > this.radius) return(new Point2D[] {tmpPoint});
			
			/**
			 * If the ray origin is in the circle and the ray is pointing outwards,
			 * the projected point will be closer to the border of the circle than the radius,
			 * so transforming the projected point will result in a point outside of the radius.
			 * So i'm using a line and seeing which point is in the axis of the ray to compensate for this
			 */
			Point2D[] lineIntersect = this.intersection(new Line2D(ray));
			return(new Point2D[] {AbsAngle.angle(ray, lineIntersect[0]) < 90 ? lineIntersect[0] : lineIntersect[1]});
		}
		
		Point2D[] intersectList = {
				ray.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).transform(tmpPoint),
				ray.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2))).negate().transform(tmpPoint)
		};
		
		return intersectList;
	}

	@Override
	public Ray2D reflect(Ray2D ray) {
		Point2D[] rayIntersect = this.intersection(ray);
		
		if(rayIntersect.length < 1) return null;

		Point2D closestPoint = rayIntersect.length > 1 ? rayIntersect[0].distance(ray.origin) > rayIntersect[1].distance(ray.origin) ? rayIntersect[1] : rayIntersect[0] : rayIntersect[0];
		Line2D normLine = new Line2D(this.center, closestPoint);
		
		return(new Ray2D(closestPoint, normLine.symmetry(new Segment2D(closestPoint, ray.origin)).B));
	}

}
