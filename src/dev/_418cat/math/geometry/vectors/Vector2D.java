package dev._418cat.math.geometry.vectors;

import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.segments.Segment2D;

/**
 * Vector object with two dimensions
 * 
 * @author 418cat
 *
 */
public class Vector2D
{

	public double x;
	public double y;

	/**
	 * Defines the vector from two points.
	 * 
	 * @param A first point
	 * @param B second point
	 * @see dev._418cat.math.geometry.points.Point2D
	 */
	public Vector2D(Point2D A, Point2D B)
	{
		this.x = B.x - A.x;
		this.y = B.y - A.y;
	}

	/**
	 * Defines the vector's coordinates
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Vector2D(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Defines the vector using the segment's two points
	 * 
	 * @param segment
	 * @see dev._418cat.math.geometry.segments.Segment2D
	 */
	public Vector2D(Segment2D segment)
	{
		this.x = segment.B.x - segment.A.x;
		this.y = segment.B.y - segment.A.y;
	}

	/**
	 * Defines the vector using the line's unit vector
	 * 
	 * @param line
	 * @see dev._418cat.math.geometry.lines.Line2D
	 */
	public Vector2D(Line2D line)
	{
		this.x = line.vect.x;
		this.y = line.vect.y;
	}

	/**
	 * Defines the vector as the closest point on the line to the supplied point
	 * 
	 * @param line
	 * @param pnt  Point the vector goes to
	 * @see dev._418cat.math.geometry.lines.Line2D
	 * @see dev._418cat.math.geometry.points.Point2D
	 */
	public Vector2D(Line2D line, Point2D pnt)
	{
		Vector2D tmp = new Vector2D(line.projection(pnt), pnt);
		this.x = tmp.x;
		this.y = tmp.y;
	}

	/**
	 * @return The vector's norm
	 */
	public double norm()
	{
		return (Math.sqrt(x * x + y * y));
	}

	/**
	 * Computes two vector's dot product
	 * 
	 * @param B second vector to this one
	 * @return both vector's dot product
	 */
	public double dotProduct(Vector2D B)
	{
		return (this.x * B.x + this.y * B.y);
	}

	/**
	 * Computes two vector's determinant
	 * 
	 * @param B second vector to this one
	 * @return both vector's determinant
	 */
	public double determinant(Vector2D B)
	{
		return (this.x * B.y - this.y * B.x);
	}

	/**
	 * Computes this vector's both opposed normal vectors; both having a norm of 1.
	 * 
	 * @return an array of size 2 containing this vector's normal vectors
	 */
	public Vector2D[] normalVectors()
	{
		return (new Vector2D[] { new Vector2D(-this.y, this.x).unit(), new Vector2D(this.y, -this.x).unit() });
	}

	public boolean isNormalVector(Vector2D vect)
	{
		return (dotProduct(vect) == 0);
	}

	public boolean isCollinear(Vector2D vect)
	{
		Vector2D thisUnit = this.unit();
		Vector2D vectUnit = vect.unit();
		return (thisUnit == vectUnit || thisUnit.negate() == vectUnit);
	}

	/**
	 * @return the opposite vector to this one
	 */
	public Vector2D negate()
	{
		return (new Vector2D(-this.x, -this.y));
	}

	/**
	 * Computes the coordinates of this vector turned by X degrees CLOCKWISE
	 * 
	 * @param angle the angle in degree to turn the vector of
	 * @return a new instance of this vector turned by X degrees
	 */
	public Vector2D turn(double angle)
	{
		return (new Vector2D(this.x * Math.cos(angle * Math.PI / 180) - this.y * Math.sin(angle * Math.PI / 180),
			this.x * Math.sin(angle * Math.PI / 180) + this.y * Math.cos(angle * Math.PI / 180)));
	}

	/**
	 * Adds two vectors together to form a third one
	 * 
	 * @param vect second vector to this one
	 * @return a new instance of this vector added to the supplied one
	 */
	public Vector2D add(Vector2D vect)
	{
		return (new Vector2D(this.x + vect.x, this.y + vect.y));
	}

	/**
	 * Moves a point using this vector's coordinates
	 * 
	 * @param point to move
	 * @return a new instance of the point with new coordinates
	 * @see dev._418cat.math.geometry.points.Point2D
	 */
	public Point2D transform(Point2D point)
	{
		return (new Point2D(point.x + this.x, point.y + this.y));
	}

	/**
	 * Multiples this vector by a supplied number
	 * 
	 * @param mult number of times to multiply the vector by
	 * @return a new instance of this vector multiplied by the supplied number
	 */
	public Vector2D multiply(double mult)
	{
		return (new Vector2D(this.x * mult, this.y * mult));
	}

	/**
	 * @return a new instance of this vector with a norm of 1
	 */
	public Vector2D unit()
	{
		return (this.multiply(1 / this.norm()));
	}

}
