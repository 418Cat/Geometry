package MathPkg;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

public class Angle {
	
	public static double angle(Vector2D A, Vector2D B)
	{
		double div = A.dotProduct(B)/(A.norm() * B.norm());
		
		//to account for double imprecision
		if(div > 1 || div < -1)
		{
			return(div > 1 ? 0 : 180);
		}
		return(Math.acos(div) * 180/Math.PI);	
	}
	
	public static double angle(Point2D A, Point2D AnglePoint, Point2D B)
	{
		return(angle(new Vector2D(AnglePoint, A), new Vector2D(AnglePoint, B)));
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
	
	public static double angle(Vector2D vect)
	{
		return(angle(vect, new Vector2D(10, 0)));
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
