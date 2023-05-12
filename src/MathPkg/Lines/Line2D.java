package MathPkg.Lines;
import MathPkg.Points.Point2D;
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
	
	public boolean pointIsOnLine(Point2D pnt)
	{
		return(pnt.distance(this) == 0);
	}
	
	public Line2D negate()
	{
		return(new Line2D(point, vect.negate()));
	}

}
