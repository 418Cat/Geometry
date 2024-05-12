package dev._418cat.math.geometry.angle.angle3d;

import dev._418cat.math.geometry.points.Point3D;
import dev._418cat.math.geometry.rays.Ray3D;
import dev._418cat.math.geometry.vectors.Vector3D;

public class AbsAngle
{

	public static double angle(Vector3D vect1, Vector3D vect2)
	{
		return (Math.acos(vect1.dotProduct(vect2) / (vect1.norm() * vect2.norm())) * 180 / Math.PI);
	}

	public static double angle(Ray3D ray, Point3D pnt)
	{
		return (angle(ray.vect, new Vector3D(ray.origin, pnt)));
	}

}
