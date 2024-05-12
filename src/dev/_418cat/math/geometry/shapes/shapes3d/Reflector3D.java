package dev._418cat.math.geometry.shapes.shapes3d;

import dev._418cat.math.geometry.points.Point3D;
import dev._418cat.math.geometry.rays.Ray3D;

public interface Reflector3D
{

	public boolean intersects(Ray3D ray);

	public Ray3D reflect(Ray3D ray);

	public Point3D[] intersection(Ray3D ray);

	default Point3D firstIntersection(Ray3D ray)
	{
		if (!this.intersects(ray))
			return null;
		Point3D closestPoint = null;
		double dist = Double.MAX_VALUE;

		for (Point3D pnt : this.intersection(ray))
		{
			if (pnt.distance(ray.origin) < dist)
			{
				closestPoint = pnt;
				dist = pnt.distance(ray.origin);
			}
		}
		return (closestPoint);
	}

}
