package dev._418cat.math.geometry.shapes.shapes2d;

import java.util.ArrayList;

import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;
import dev._418cat.math.geometry.segments.Segment2D;

public class Triangle implements Reflector2D
{

	Point2D A;
	Point2D B;
	Point2D C;

	public Triangle(Point2D A, Point2D B, Point2D C)
	{
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public Segment2D[] segments()
	{
		Segment2D[] segs = { new Segment2D(this.A, this.B), new Segment2D(this.A, this.C), new Segment2D(this.B, this.C) };
		return segs;
	}

	@Override
	public boolean intersects(Ray2D ray)
	{

		for (Segment2D seg : this.segments())
			if (seg.intersects(ray))
				return true;

		return (false);
	}

	public boolean intersects(Circle circ)
	{
		for (Segment2D seg : this.segments())
			if (circ.intersects(seg))
				return true;

		return false;
	}

	@Override
	public Ray2D reflect(Ray2D ray)
	{
		if (!this.intersects(ray))
			return null;

		Segment2D closestSegment = null;
		double dist = Double.MAX_VALUE;

		for (Segment2D seg : this.segments())
		{
			if (seg.intersects(ray))
			{
				double distTmp = seg.intersection(ray)[0].distance(ray.origin);
				if (distTmp < dist)
				{
					closestSegment = seg;
					dist = distTmp;
				}
			}
		}
		return (closestSegment.reflect(ray));

	}

	@Override
	public Point2D[] intersection(Ray2D ray)
	{
		if (!this.intersects(ray))
			return new Point2D[] {};

		ArrayList<Point2D> intersectionsAL = new ArrayList<Point2D>();

		for (Segment2D seg : this.segments())
		{
			for (Point2D pnt : seg.intersection(ray))
				intersectionsAL.add(pnt);
		}

		Point2D[] intersectArr = new Point2D[intersectionsAL.size()];

		for (int i = 0; i < intersectArr.length; i++)
		{
			intersectArr[i] = intersectionsAL.get(i);
		}

		return (intersectArr);
	}

	public Point2D[] intersection(Circle circ)
	{
		ArrayList<Point2D> intersectionsAL = new ArrayList<Point2D>();

		for (Segment2D seg : this.segments())
		{
			Point2D[] inters = circ.intersection(seg);

			for (Point2D pnt : inters)
			{
				intersectionsAL.add(pnt);
			}
		}

		Point2D[] intersectArr = new Point2D[intersectionsAL.size()];

		for (int i = 0; i < intersectArr.length; i++)
		{
			intersectArr[i] = intersectionsAL.get(i);
		}

		return (intersectArr);
	}

	public Point2D[] intersection(Line2D line)
	{
		ArrayList<Point2D> intersectionsAL = new ArrayList<Point2D>();

		for (Segment2D seg : this.segments())
		{

			for (Point2D pnt : line.intersection(new Line2D(seg)))
			{
				if (seg.PointBetween(pnt))
				{
					intersectionsAL.add(pnt);
				}
			}
		}

		Point2D[] intersectArr = new Point2D[intersectionsAL.size()];

		for (int i = 0; i < intersectArr.length; i++)
		{
			intersectArr[i] = intersectionsAL.get(i);
		}

		return (intersectArr);
	}

}
