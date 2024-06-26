package dev._418cat.math.geometry.segments;

import dev._418cat.math.geometry.angle.angle2d.AbsAngle;
import dev._418cat.math.geometry.angle.angle2d.Angle;
import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;
import dev._418cat.math.geometry.shapes.shapes2d.Circle;
import dev._418cat.math.geometry.shapes.shapes2d.Reflector2D;
import dev._418cat.math.geometry.vectors.Vector2D;

public class Segment2D implements Reflector2D
{

	public Point2D A;
	public Point2D B;

	public Segment2D(Point2D A, Point2D B)
	{
		this.A = A;
		this.B = B;
	}

	public Segment2D(Point2D A, Vector2D vect)
	{
		this.A = A;
		this.B = new Point2D(A.x + vect.x, A.y + vect.y);
	}

	public boolean contains(Point2D point)
	{
		return (AbsAngle.angle(this, point) == 0 && AbsAngle.angle(this.negate(), point) == 0);
	}

	public boolean PointBetween(Point2D point)
	{
		return (AbsAngle.angle(this, point) <= 90 && AbsAngle.angle(this.negate(), point) <= 90);
	}

	public Segment2D negate()
	{
		return (new Segment2D(B, A));
	}

	public double norm()
	{
		return (Math.sqrt(Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.x, 2)));
	}

	public Point2D symmetry(Point2D pnt)
	{
		Vector2D tmpVect = new Vector2D(this.A, this.B);

		double coef = pnt.distance(this) / tmpVect.normalVectors()[0].norm();
		int normVect = Angle.angle(this, pnt) > 180 ? 1 : 0;
		Vector2D pntLine = new Vector2D(tmpVect.normalVectors()[normVect].x * coef,
			tmpVect.normalVectors()[normVect].y * coef);

		return (pntLine.multiply(2).transform(pnt));
	}

	public Segment2D symmetry(Segment2D seg)
	{
		return (new Segment2D(this.symmetry(seg.A), this.symmetry(seg.B)));
	}

	public Point2D projection(Point2D pnt)
	{
		if (this.PointBetween(pnt))
		{
			Vector2D tmpVect = new Vector2D(this.A, this.B);

			double coef = pnt.distance(this) / tmpVect.normalVectors()[0].norm();
			int normVect = Angle.angle(this, pnt) > 180 ? 1 : 0;
			Vector2D pntLine = new Vector2D(tmpVect.normalVectors()[normVect].x * coef,
				tmpVect.normalVectors()[normVect].y * coef);

			return (pntLine.transform(pnt));
		}

		return (pnt.distance(this.A) > pnt.distance(this.B) ? this.B : this.A);
	}

	@Override
	public boolean intersects(Ray2D ray)
	{

		Line2D line = new Line2D(this);

		if (!line.intersects(ray))
			return false;

		return (this.PointBetween(line.intersection(ray)[0]));
	}

	@Override
	public Ray2D reflect(Ray2D ray)
	{
		if (!this.intersects(ray))
			return null;

		return (new Line2D(this).reflect(ray));

	}

	@Override
	public Point2D[] intersection(Ray2D ray)
	{
		if (!this.intersects(ray))
			return new Point2D[] {};

		return (new Line2D(this).intersection(ray));
	}

	public Point2D[] intersection(Line2D line)
	{
		Point2D pnt = line.intersection(new Line2D(this))[0];

		if (this.PointBetween(pnt))
			return new Point2D[] { pnt };

		return new Point2D[] {};
	}

	public Point2D[] intersection(Circle circ)
	{
		return circ.intersection(this);
	}

}
