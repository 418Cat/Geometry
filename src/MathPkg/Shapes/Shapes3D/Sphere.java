package MathPkg.Shapes.Shapes3D;

import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Vectors.Vector3D;

public class Sphere {
	
	public Point3D center;
	public double radius;
	
	public Sphere(Point3D center, double radius)
	{
		this.center = center;
		this.radius = radius;
	}
	
	public Sphere(Point3D center, Point3D border)
	{
		this.center = center;
		this.radius = center.distance(border);
	}
	
	public int intersectionState(Point3D point)
	{
		return(center.distance(point) == radius ? 0 : center.distance(point) < radius ? 1 : -1);
	}
	public boolean intersects(Point3D point)
	{
		return(this.intersectionState(point) > -1);
	}
	
	public Point3D projection(Point3D point)
	{
		Vector3D tmpVect = new Vector3D(point, this.center);
		return(tmpVect.multiply((radius)/tmpVect.norm()).negate().transform(this.center));
	}
	
	
	public int intersectionState(Ray3D ray)
	{
		return(center.distance(ray) == radius ? 0 : center.distance(ray) < radius ? 1 : -1);
	}
	public boolean intersects(Ray3D ray)
	{
		return(intersectionState(ray) > -1);
	}
	
	public Point3D projection(Ray3D ray)
	{
		return(this.projection(ray.projection(center)));
	}
	
	public Point3D[] intersection(Ray3D ray)
	{
		if(this.intersectionState(ray) == -1) return new Point3D[] {};
		
		if(this.intersectionState(ray) == 0) return new Point3D[] {this.projection(ray)};
		
		Point3D projCent = ray.projection(center);
		
		double distProjCentIntersect = Math.sqrt(radius*radius - Math.pow(ray.projection(center).distance(center), 2));
		
		Vector3D projCentIntersectVect = ray.vect.unit().multiply(distProjCentIntersect);
		
		return(new Point3D[] {projCentIntersectVect.transform(projCent), projCentIntersectVect.negate().transform(projCent)});
	}

}
