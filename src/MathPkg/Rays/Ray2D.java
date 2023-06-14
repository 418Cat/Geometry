package MathPkg.Rays;

import MathPkg.Angle.Angle2D.AbsAngle;
import MathPkg.Angle.Angle2D.Angle;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Ray2D {
	
	public Point2D origin;
	public Vector2D vect;
	
	public Ray2D(Point2D origin, Vector2D vect)
	{
		this.origin = origin;
		this.vect = vect;
	}
	
	public Ray2D(Point2D origin, Point2D pnt)
	{
		this.origin = origin;
		this.vect = new Vector2D(origin, pnt);
	}
	
	public Ray2D(Segment2D seg)
	{
		this.origin = seg.A;
		this.vect = new Vector2D(seg);
	}
	
	public Point2D projection(Point2D pnt)
	{
		double angle = AbsAngle.angle(this, pnt);
		
		if(angle >= 90) return this.origin;
		
		double coef = pnt.distance(this)/this.vect.normalVectors()[0].norm();
		int normVect = Angle.angle(this, pnt) > 180 ? 1 : 0;
		Vector2D pntLine = new Vector2D(this.vect.normalVectors()[normVect].x * coef, this.vect.normalVectors()[normVect].y * coef);
		
		return(pntLine.transform(pnt));
	}
	
}
