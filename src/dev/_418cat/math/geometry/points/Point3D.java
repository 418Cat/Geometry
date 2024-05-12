package dev._418cat.math.geometry.points;

import dev._418cat.math.geometry.angle.angle3d.AbsAngle;
import dev._418cat.math.geometry.rays.Ray3D;
import dev._418cat.math.geometry.vectors.Vector3D;

public class Point3D
{

	public double x;
	public double y;
	public double z;

	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distance(Point3D pnt)
	{
		return (Math.sqrt(Math.pow(this.x - pnt.x, 2) + Math.pow(this.y - pnt.y, 2) + Math.pow(this.z - pnt.z, 2)));
	}

	public double distance(Point3D pnt, boolean print)
	{
		if (print)
			System.out.println(Math.pow(this.x - pnt.x, 2));

		return (Math.sqrt(Math.pow(this.x - pnt.x, 2) + Math.pow(this.y - pnt.y, 2) + Math.pow(this.z - pnt.z, 2)));
	}

	public double distance(Ray3D ray)
	{
		double angle = AbsAngle.angle(ray, this);

		if (angle > 90)
			return (this.distance(ray.origin));

		Vector3D tmpVect = new Vector3D(this, ray.origin);

		return (tmpVect.norm() * Math.sin(angle * Math.PI / 180));
	}

}
