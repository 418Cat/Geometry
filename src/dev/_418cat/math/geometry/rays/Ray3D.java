package dev._418cat.math.geometry.rays;

import dev._418cat.math.geometry.angle.angle3d.AbsAngle;
import dev._418cat.math.geometry.points.Point3D;
import dev._418cat.math.geometry.vectors.Vector3D;

public class Ray3D
{

	public Point3D origin;
	public Vector3D vect;

	public Ray3D(Point3D origin, Vector3D vect)
	{
		this.origin = origin;
		this.vect = vect;
	}

	public Ray3D(Point3D origin, Point3D pnt)
	{
		this.origin = origin;
		this.vect = new Vector3D(origin, pnt);
	}

	public Point3D projection(Point3D pnt)
	{
		double angle = AbsAngle.angle(this, pnt);

		if (angle > 90)
			return this.origin;

		double distPntRay = pnt.distance(this);
		double distPntRayOrigin = pnt.distance(this.origin);
		double distProjRayOrigin = Math.sqrt(distPntRayOrigin * distPntRayOrigin + distPntRay * distPntRay);

		return (this.vect.unit().multiply(distProjRayOrigin).transform(pnt));
	}

}
