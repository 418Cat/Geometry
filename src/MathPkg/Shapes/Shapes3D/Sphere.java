package MathPkg.Shapes.Shapes3D;

import MathPkg.Lines.Line3D;
import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Vectors.Vector3D;

public class Sphere implements Reflector3D {
	
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
		Vector3D tmpVect = new Vector3D(this.center, point);
		return(tmpVect.unit().multiply(this.radius).transform(this.center));
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
		if(!this.intersects(ray)) return new Point3D[] {};
		
		if(this.intersectionState(ray) == 0) return new Point3D[] {this.projection(ray)};
		
		Point3D projCent = ray.projection(center);
		
		double distProjCentIntersect = Math.sqrt(radius*radius - Math.pow(ray.projection(center).distance(center), 2));
		
		Vector3D projCentIntersectVect = ray.vect.unit().multiply(distProjCentIntersect);
		System.out.println("\n\n" + distProjCentIntersect);
		System.out.println(ray.projection(center).distance(center));
		
		return(new Point3D[] {projCentIntersectVect.transform(projCent), projCentIntersectVect.negate().transform(projCent)});
	}

	@Override
	public Ray3D reflect(Ray3D ray) {
		
		if(!this.intersects(ray)) return null;
		
		double dist = Double.MAX_VALUE;
		Point3D closestIntersect = null;
		
		for(Point3D pnt : this.intersection(ray))
		{
			double tmpDist = pnt.distance(ray.origin);
			if(tmpDist < dist)
			{
				dist = tmpDist;
				closestIntersect = pnt;
			}
		}
		if(closestIntersect == null && this.intersects(ray)) System.out.println("\n\n it intersects but it seems like there's no point coming out\n\n");
		Line3D normLine = new Line3D(this.center, closestIntersect);
		
		Ray3D tmpRay = new Ray3D(closestIntersect, normLine.symmetry(ray.origin));
		
		return tmpRay;
		
	}

}
