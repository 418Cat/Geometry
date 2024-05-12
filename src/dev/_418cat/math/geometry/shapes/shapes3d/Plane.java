package dev._418cat.math.geometry.shapes.shapes3d;

import dev._418cat.math.geometry.points.Point3D;
import dev._418cat.math.geometry.vectors.Vector3D;

public class Plane
{

	public Vector3D vect1;
	public Vector3D vect2;
	public Point3D point;

	public Plane(Point3D point, Vector3D vect1, Vector3D vect2)
	{
		this.point = point;
		this.vect1 = vect1;
		this.vect2 = vect2;
	}

}
