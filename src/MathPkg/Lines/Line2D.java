package MathPkg.Lines;
import MathPkg.Angle.Angle2D.Angle;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

/**
 * Line object composed of a point and a unit vector
 * @author 418cat
 * @see Point2D
 * @see Vector2D
 */
public class Line2D {
	
	public Point2D point;
	public Vector2D vect;
	
	/**
	 * Defines this line as passing by two points.
	 * @param A first point
	 * @param B second point
	 */
	public Line2D(Point2D A, Point2D B)
	{
		this.point = A;
		this.vect = new Vector2D(A, B).unit();
	}
	
	/**
	 * Defines this line passing by a point with a unit vector.
	 * @param A point
	 * @param vect unit vector
	 */
	public Line2D(Point2D A, Vector2D vect)
	{
		this.point = A;
		this.vect = vect.unit();
	}
	
	/**
	 * Defines this line as passing by a segment's both edge points
	 * @param seg
	 */
	public Line2D(Segment2D seg)
	{
		this.point = seg.A;
		this.vect = new Vector2D(seg.A, seg.B).unit();
	}
	
	/**
	 * 
	 * @param pnt
	 * @return
	 */
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
	
	public Point2D intersection(Line2D line)
	{
		Point2D projPoint = line.projection(this.point);
		
		double angle = Angle.angle(this, projPoint);
		double opposite = this.point.distance(projPoint)*Math.tan(-angle * Math.PI/180);
		
		return(line.vect.unit().multiply(opposite).transform(projPoint));
	}
	
	public Point2D symmetry(Point2D pnt)
	{
		return(new Vector2D(pnt, this.projection(pnt)).multiply(2).transform(pnt));
	}
	
	public Segment2D symmetry(Segment2D seg)
	{
		return(new Segment2D(this.symmetry(seg.A), this.symmetry(seg.B)));
	}

}
