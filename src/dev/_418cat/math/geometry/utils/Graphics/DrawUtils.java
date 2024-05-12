package dev._418cat.math.geometry.utils.Graphics;

import java.awt.Color;

import dev._418cat.math.geometry.angle.angle2d.AbsAngle;
import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;
import dev._418cat.math.geometry.segments.Segment2D;
import dev._418cat.math.geometry.shapes.shapes2d.Circle;
import dev._418cat.math.geometry.shapes.shapes2d.Reflector2D;
import dev._418cat.math.geometry.shapes.shapes2d.Triangle;
import dev._418cat.math.geometry.vectors.Vector2D;

public class DrawUtils
{
	public static void clear(Color color, Frame f)
	{
		f.g().setColor(color);
		f.g().fillRect(0, 0, f.getSize()[0], f.getSize()[1]);
	}

	public static void drawPix(int x, int y, Color color, Frame f)
	{
		f.g().setColor(color);
		f.g().fillRect((int) ((float) (x * f.ZOOM)), (int) ((float) (y * f.ZOOM)), 1, 1);
	}

	public static void draw(Reflector2D ref, Frame f)
	{
		if (ref.getClass() == Circle.class)
			draw((Circle) ref, f);
		if (ref.getClass() == Segment2D.class)
			draw((Segment2D) ref, f);
		if (ref.getClass() == Line2D.class)
			draw((Line2D) ref, f);
		if (ref.getClass() == Triangle.class)
			draw((Triangle) ref, f);
		if (ref.getClass() == Ray2D.class)
			draw((Ray2D) ref, f);
	}

	public static void draw(Triangle tri, Frame f)
	{
		for (Segment2D seg : tri.segments())
		{
			draw(seg, f);
		}
	}

	public static void draw(Point2D a, String name, Frame f)
	{
		f.g().setColor(Color.red);
		f.g().fillOval((int) ((a.x - 5) * f.ZOOM), (int) ((a.y - 5) * f.ZOOM), (int) ((10 * f.ZOOM)),
			(int) ((10 * f.ZOOM)));

		f.g().setColor(Color.red);
		f.g().drawChars(name.toCharArray(), 0, name.length(), (int) ((a.x - 3) * f.ZOOM), (int) ((a.y + 20) * f.ZOOM));
	}

	public static void draw(Point2D pnt, Vector2D vect, Frame f)
	{
		f.g().setColor(Color.blue);
		f.g().drawLine((int) (pnt.x * f.ZOOM), (int) (pnt.y * f.ZOOM), (int) ((pnt.x + vect.x) * f.ZOOM),
			(int) ((pnt.y + vect.y) * f.ZOOM));
		f.g().fillOval((int) (((pnt.x + vect.x) - 3) * f.ZOOM), (int) (((pnt.y + vect.y) - 3) * f.ZOOM),
			(int) (float) (6 * f.ZOOM), (int) (float) (6 * f.ZOOM));

		Point2D textCoords = vect.multiply(0.5).transform(vect.normalVectors()[1].multiply(10.0).transform(pnt));
		f.g().setColor(Color.black);

		String coordsDisp = String.format("%.2f;  %.2f", vect.x, vect.y);
		f.g().drawChars(coordsDisp.toCharArray(), 0, coordsDisp.length(), (int) textCoords.x, (int) textCoords.y);
	}

	public static void draw(Line2D line, Frame f)
	{
		int mult = (int) (Math.sqrt(f.getSize()[0] * f.getSize()[0] + f.getSize()[1] * f.getSize()[1]) / f.ZOOM);
		f.g().setColor(Color.green);
		f.g().drawLine((int) (line.point.x * f.ZOOM), (int) (line.point.y * f.ZOOM),
			(int) ((line.point.x + line.vect.unit().x * mult) * f.ZOOM),
			(int) ((line.point.y + line.vect.unit().y * mult) * f.ZOOM));
		f.g().drawLine((int) (line.point.x * f.ZOOM), (int) (line.point.y * f.ZOOM),
			(int) ((line.point.x - line.vect.unit().x * mult) * f.ZOOM),
			(int) ((line.point.y - line.vect.unit().y * mult) * f.ZOOM));
	}

	private static void draw(Circle circle, Frame f)
	{
		f.g().setColor(Color.pink);
		f.g().drawOval((int) ((circle.center.x - circle.radius) * f.ZOOM),
			(int) ((circle.center.y - circle.radius) * f.ZOOM), (int) ((circle.radius * 2) * f.ZOOM),
			(int) ((circle.radius * 2) * f.ZOOM));
	}

	public static void draw(Segment2D segA, Segment2D segB, int radius, Frame f)
	{
		f.g().setColor(Color.cyan);
		f.g().drawArc((int) segA.A.x - radius, (int) segA.A.y - radius, radius * 2, radius * 2,
			(int) AbsAngle.angle(segA, new Vector2D(1, 0)), (int) AbsAngle.angle(segA, segB));
	}

	private static void draw(Segment2D segment, Frame f)
	{
		f.g().setColor(Color.gray);
		f.g().drawLine((int) (segment.A.x * f.ZOOM), (int) (segment.A.y * f.ZOOM), (int) (segment.B.x * f.ZOOM),
			(int) (segment.B.y * f.ZOOM));

		/*
		 * Vector2D vecNorms[] = new Vector2D(segment).normalVectors(); Segment2D seg1 =
		 * new Segment2D(vecNorms[0].transform(segment.A),
		 * vecNorms[0].transform(segment.B)); Segment2D seg2 = new
		 * Segment2D(vecNorms[0].transform(segment.A),
		 * vecNorms[0].transform(segment.B));
		 * 
		 * g.drawLine((int)(seg1.A.x*ZOOM), (int)(seg1.A.y*ZOOM), (int)(seg1.B.x*ZOOM),
		 * (int)(seg1.B.y*ZOOM)); g.drawLine((int)(seg2.A.x*ZOOM), (int)(seg2.A.y*ZOOM),
		 * (int)(seg2.B.x*ZOOM), (int)(seg2.B.y*ZOOM));
		 */
	}

	public static void draw(Ray2D ray, Frame f)
	{
		int mult = (int) ((Math.sqrt(f.getSize()[0] * f.getSize()[0] + f.getSize()[1] * f.getSize()[1]) / f.ZOOM));
		f.g().setColor(Color.magenta);
		f.g().drawLine((int) (ray.origin.x * f.ZOOM), (int) (ray.origin.y * f.ZOOM),
			(int) ((ray.origin.x + ray.vect.unit().x * mult) * f.ZOOM),
			(int) ((ray.origin.y + ray.vect.unit().y * mult) * f.ZOOM));
	}
}
