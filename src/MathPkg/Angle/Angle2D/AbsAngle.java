package MathPkg.Angle.Angle2D;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Vectors.Vector2D;

/**
 * Static tool to calculate an angle between two objects. From 0 to 180 degrees.
 * @see MathPkg.Angle
 * @author 418cat
 */
public class AbsAngle {
	
	/**
	 * Static tool to calculate an angle between two vectors. From 0 to 180 degrees.
	 * @param B Vector B
	 * @return Absolute angle between A and B in degree
	 */
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
	
	/**
	 * Static tool to calculate the angle formed by 3 points. From 0 to 180 degrees.
	 * @param A First point
	 * @param anglePoint Point where the angle is located at
	 * @param B Second Point
	 * @return Absolute angle formed by A and B at anglePoint
	 */
	public static double angle(Point2D A, Point2D anglePoint, Point2D B)
	{
		return(angle(new Vector2D(anglePoint, A), new Vector2D(anglePoint, B)));
	}
	
	/**
	 * Static tool to calculate the angle between to lines. From 0 to 180 degrees.
	 * @param A Line A
	 * @param B Line B
	 * @return the angle between two lines
	 */
	public static double angle(Line2D A, Line2D B)
	{
		return(angle(A.vect, B.vect));
	}
	
	
	/**
	 * This function doesn't really make sense as the angle formed by a line and a point is either 90 or 0. It computes the angle formed by {@link MathPkg.Lines.Line2D Line}'s point variable transformed by its vector and this point
	 * </br><h1>Make sure it's the right use case before calling this function</h1></br>
	 * @param line
	 * @param point
	 * @return the angle formed by the line's point variable transformed by its vector, the point parameter and the line's point variable as the angle point
	 */
	public static double angle(Line2D line, Point2D point)
	{
		return(angle(line.vect, new Vector2D(line.point, point)));
	}
	
	/**
	 * Computes the angle formed by the segment's B point, segment's A point and the C point. Forming BAC with A as the angle point. From 0 to 180 degrees.
	 * @param segment Segment containing A and B points
	 * @param C Third point
	 * @return BAC angle
	 */
	public static double angle(Segment2D segment, Point2D C)
	{
		return(angle(new Vector2D(segment), new Vector2D(segment.A, C)));
	}
	
	/**
	 * Computes the angle formed by two segments, the two segments' first point as the angle point. They don't need to touch. From 0 to 180 degrees.
	 * @param AB
	 * @param AC
	 * @return BAC angle
	 */
	public static double angle(Segment2D AB, Segment2D AC)
	{
		return(angle(new Vector2D(AB), new Vector2D(AC)));
	}
	
	/**
	 * Computes the angle formed by a segment and a vector, and its first point transformed by the vector AC. From 0 to 180 degrees.
	 * @param AB
	 * @param AC
	 * @return BAC angle
	 */
	public static double angle(Segment2D AB, Vector2D AC)
	{
		return(angle(new Vector2D(AB), AC));
	}
	
	/**
	 * Computes the angle of a vector compared to a (1, 0) vector. From 0 to 180 degrees.
	 * @param vect
	 * @return angle of the given vector
	 */
	public static double angle(Vector2D vect)
	{
		return(angle(vect, new Vector2D(10, 0)));
	}
	
	/**
	 * Computes the angle of a line compared to a (1, 0) vector. From 0 to 180 degrees.
	 * @param line
	 * @return angle of the given line
	 */
	public static double angle(Line2D line)
	{
		return(angle(line.vect));
	}
	
	/**
	 * Computes the angle of a segments compared to a (1, 0)  vector. From 0 to 180 degrees.
	 * @param seg
	 * @return angle of the given segment
	 */
	public static double angle(Segment2D seg)
	{
		return(angle(new Vector2D(seg)));
	}
	
	/**
	 * Computes the angle of a ray and a point. The ray's origin being the angle point. From 0 to 180 degrees.
	 * @param ray
	 * @param pnt
	 * @return angle
	 */
	public static double angle(Ray2D ray, Point2D pnt)
	{
		return(angle(ray.vect, new Vector2D(ray.origin, pnt)));
	}
}
