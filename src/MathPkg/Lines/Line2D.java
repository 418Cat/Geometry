package MathPkg.Lines;
import MathPkg.Angle.Angle;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Line2D {
	
	public Point2D point;
	public Vector2D vect;
	
	public Line2D(Point2D A, Point2D B)
	{
		this.point = A;
		this.vect = new Vector2D(A, B);
	}
	
	public Line2D(Point2D A, Vector2D vect)
	{
		this.point = A;
		this.vect = vect;
	}
	
	public Line2D(Point2D A, double angle)
	{
		this.point = A;
		this.vect = new Vector2D(A, new Point2D(A.x + 10 * Math.cos(angle * Math.PI/(double)180), A.y + 10 * Math.sin(angle * Math.PI/(double)180)));
	}
	
	public Point2D projection(Point2D pnt)
	{
		double coef = pnt.distance(this)/this.vect.normalVectors()[0].norm();
		int normVect = Angle.angle(this, pnt) > 180 ? 1 : 0;
		Vector2D pntLine = new Vector2D(this.vect.normalVectors()[normVect].x * coef, this.vect.normalVectors()[normVect].y * coef);
		
		return(pntLine.transform(pnt));
	}
	
	public boolean IsPointOnLine(Point2D pnt)
	{
		return(pnt.distance(this) == 0);
	}
	
	public Line2D negate()
	{
		return(new Line2D(point, vect.negate()));
	}
	
	public Point2D symmetry(Point2D pnt)
	{
		double coef = pnt.distance(this)/this.vect.normalVectors()[0].norm();
		int normVect = Angle.angle(this, pnt) > 180 ? 1 : 0;
		Vector2D pntLine = new Vector2D(this.vect.normalVectors()[normVect].x * coef, this.vect.normalVectors()[normVect].y * coef);
		
		return(pntLine.multiply(2).transform(pnt));
	}
	
	public Segment2D symmetry(Segment2D seg)
	{
		return(new Segment2D(this.symmetry(seg.A), this.symmetry(seg.B)));
	}

}
