package dev._418cat.math.geometry.shapes.shapes2d;

import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;

public interface Reflector2D
{

	boolean intersects(Ray2D ray);

	Ray2D reflect(Ray2D ray);

	Point2D[] intersection(Ray2D ray);

	default Point2D firstIntersection(Ray2D ray)
	{
		if (!this.intersects(ray))
			return null;
		Point2D closestPoint = null;
		double dist = Double.MAX_VALUE;

		for (Point2D pnt : this.intersection(ray))
		{
			if (pnt.distance(ray.origin) < dist)
			{
				closestPoint = pnt;
				dist = pnt.distance(ray.origin);
			}
		}
		return (closestPoint);
	}

	Point2D[] intersection(Circle circ);

	Point2D[] intersection(Line2D line);

}
