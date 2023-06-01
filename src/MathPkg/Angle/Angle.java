package MathPkg.Angle;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Angle {
	
	static public double angle(Vector2D vect1, Vector2D vect2)
	{
		double angle = (Math.atan2(vect1.dotProduct(vect2), vect1.determinant(vect2))- Math.PI/2) * 180/Math.PI;
		return(angle < 0 ? 360+angle : angle);
	}
	
	static public double angle(Vector2D vect)
	{
		return(angle(vect, new Vector2D(1, 0)));
	}
	
	public static double angle(Point2D A, Point2D anglePoint, Point2D B)
	{
		return(angle(new Vector2D(anglePoint, A), new Vector2D(anglePoint, B)));
	}
	
	public static double angle(Line2D A, Line2D B)
	{
		return(angle(A.vect, B.vect));
	}
	
	public static double angle(Line2D line, Point2D point)
	{
		return(angle(line.vect, new Vector2D(line.point, point)));
	}
	
	public static double angle(Segment2D segment, Point2D point)
	{
		return(angle(new Vector2D(segment), new Vector2D(segment.A, point)));
	}
	
	public static double angle(Segment2D AB, Segment2D AC)
	{
		return(angle(new Vector2D(AB), new Vector2D(AC)));
	}
	
	public static double angle(Segment2D AB, Vector2D AC)
	{
		return(angle(new Vector2D(AB), AC));
	}
	
	public static double angle(Line2D line)
	{
		return(angle(line.vect));
	}
	
	public static double angle(Segment2D seg)
	{
		return(angle(new Vector2D(seg)));
	}

}
