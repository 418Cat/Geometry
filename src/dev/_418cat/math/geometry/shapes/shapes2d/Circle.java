package dev._418cat.math.geometry.shapes.shapes2d;

import java.util.ArrayList;

import dev._418cat.math.geometry.angle.angle2d.AbsAngle;
import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;
import dev._418cat.math.geometry.segments.Segment2D;
import dev._418cat.math.geometry.vectors.Vector2D;

public class Circle implements Reflector2D
{

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
		return (center.distance(point) == radius ? 0 : center.distance(point) < radius ? 1 : -1);
	}

	public boolean intersects(Point2D point)
	{
		return (this.intersectionState(point) > -1);
	}

	public int intersectionState(Circle circ)
	{
		return (center.distance(circ.center) == radius + circ.radius ? 0
			: center.distance(circ.center) < radius + circ.radius ? 1 : -1);
	}

	public boolean intersects(Circle circ)
	{
		return (this.intersectionState(circ) > -1);
	}

	public int intersectionState(Line2D line)
	{
		return (center.distance(line) == radius ? 0 : center.distance(line) < radius ? 1 : -1);
	}

	public boolean intersects(Line2D line)
	{
		return (this.intersectionState(line) > -1);
	}

	public int intersectionState(Segment2D segment)
	{
		return (center.distance(segment) == radius ? 0 : center.distance(segment) < radius ? 1 : -1);
	}

	public boolean intersects(Segment2D segment)
	{
		return (this.intersectionState(segment) > -1);
	}

	public int intersectionState(Ray2D ray)
	{
		if (ray.origin.distance(this.center) < this.radius)
			return 0;
		return (center.distance(ray) == radius ? 0 : center.distance(ray) < radius ? 1 : -1);
	}

	@Override
	public boolean intersects(Ray2D ray)
	{
		return (intersectionState(ray) > -1);
	}

	public double distance(Line2D line)
	{
		return (this.intersectionState(line) >= 0 ? 0
			: line.projection(this.center).distance(this.center) - this.radius);
	}

	public Vector2D normalVect(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(point, this.center).negate();
		return (tmpVect.multiply(1 / tmpVect.norm()));
	}

	public Point2D projection(Point2D point)
	{
		Vector2D tmpVect = new Vector2D(this.center, point);
		return (tmpVect.unit().multiply(radius > tmpVect.norm() ? tmpVect.norm() : radius).transform(this.center));
	}

	public Point2D projection(Ray2D ray)
	{
		return (this.projection(ray.projection(center)));
	}

	public Point2D projection(Line2D line)
	{
		return (this.projection(line.projection(this.center)));
	}

	public Point2D[] intersection(Line2D line)
	{
		if (this.intersectionState(line) == -1)
			return (new Point2D[] {});
		if (this.intersectionState(line) == 0)
			return (new Point2D[] { projection(line.projection(center)) });

		Point2D tmpPoint = line.projection(this.center);
		Point2D[] intersectList = { line.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2)))
			.transform(tmpPoint),
			line.vect.unit()
				.multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2)))
				.negate().transform(tmpPoint) };

		return intersectList;
	}

	public Point2D[] intersection(Ray2D ray)
	{
		if (ray == null || !this.intersects(ray))
			return (new Point2D[] {});

		Point2D tmpPoint = ray.projection(this.center);

		if (this.intersectionState(ray) == 0)
		{
			if (tmpPoint.distance(this.center) > this.radius)
				return (new Point2D[] { tmpPoint });

			/**
			 * If the ray origin is in the circle and the ray is pointing outwards, the
			 * projected point will be closer to the border of the circle than the radius,
			 * so transforming the projected point will result in a point outside of the
			 * radius. So i'm using a line and seeing which point is in the axis of the ray
			 * to compensate for this
			 */
			Point2D[] lineIntersect = this.intersection(new Line2D(ray));
			if (lineIntersect.length == 0)
				return new Point2D[] {};
			if (lineIntersect.length == 1)
				return new Point2D[] { lineIntersect[0] };

			return (new Point2D[] { AbsAngle.angle(ray, lineIntersect[0]) < 90 ? lineIntersect[0] : lineIntersect[1] });
		}

		Point2D[] intersectList = { ray.vect.unit().multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2)))
			.transform(tmpPoint),
			ray.vect.unit()
				.multiply(Math.sqrt(Math.pow(this.radius, 2) - Math.pow(tmpPoint.distance(this.center), 2)))
				.negate().transform(tmpPoint) };

		return intersectList;
	}

	public Point2D[] intersection(Circle circ)
	{
		if (this.intersectionState(circ) != 1)
			return (new Point2D[] {});

		Vector2D vec = new Vector2D(center, circ.center).unit()
			.multiply(radius + 0.5 * (center.distance(circ.center) - (radius + circ.radius)));
		// vec(P1, P2).unit() * (R1 + 0.5(D - (R1 + R2)))
		// Multiply the unit vector formed by (P1, P2) by the radius to have a point on
		// the edge of the circle, then subtract half of the difference.
		// This gives us a point on the line formed by the two intersecting circles

		Line2D line = new Line2D(vec.transform(center), vec.normalVectors()[0]);

		return this.intersection(line);
	}

	public Point2D[] intersection(Segment2D seg)
	{
		// if(!this.intersects(seg)) return new Point2D[] {};

		Point2D[] intersectLine = this.intersection(new Line2D(seg));
		ArrayList<Point2D> arr = new ArrayList<Point2D>();

		for (Point2D pnt : intersectLine)
		{
			if (seg.PointBetween(pnt))
			{
				arr.add(pnt);
			}
		}

		Point2D[] inters = new Point2D[arr.size()];

		for (int i = 0; i < arr.size(); i++)
		{
			inters[i] = arr.get(i);
		}

		return inters;

	}

	@Override
	public Ray2D reflect(Ray2D ray)
	{
		Point2D[] rayIntersect = this.intersection(ray);

		if (rayIntersect.length < 1)
			return null;

		Point2D closestPoint = rayIntersect.length > 1
			? rayIntersect[0].distance(ray.origin) > rayIntersect[1].distance(ray.origin) ? rayIntersect[1]
				: rayIntersect[0]
			: rayIntersect[0];
		Line2D normLine = new Line2D(this.center, closestPoint);

		return (new Ray2D(closestPoint, normLine.symmetry(new Segment2D(closestPoint, ray.origin)).B));
	}

}
