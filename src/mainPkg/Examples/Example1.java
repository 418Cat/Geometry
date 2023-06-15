package mainPkg.Examples;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import MathPkg.Shapes.Shapes2D.Triangle;
import MathPkg.Vectors.Vector2D;
import mainPkg.Frame;
import mainPkg.Main;

public class Example1 implements Example {
	
	public static Reflector2D[] refs = {
			new Line2D(new Point2D(0, 900), new Vector2D(1, 0)),
			new Circle(new Point2D(300, 300), 100),
			new Circle(new Point2D(650, 500), 50),
			new Circle(new Point2D(500, 420), 75),
			new Circle(new Point2D(800, 300), 200),
			new Segment2D(new Point2D(100, 100), new Point2D(900, 200)),
			new Triangle(new Point2D(200, 200), new Point2D(300, 250), new Point2D(350, 500))
	};
	
	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);
	
	public static Ray2D ray = new Ray2D(A, Aprime);
	
	public void mouse(int x, int y)
	{
		Aprime.x = x;
		Aprime.y = y;
		line.vect = new Vector2D(line.point, Aprime).unit();
		ray.vect = line.vect;
	}
	
	public void scroll(int scrollAmount)
	{
		Main.MAX_BOUNCES+=scrollAmount;
		Main.MAX_BOUNCES = Main.MAX_BOUNCES < 0 ? 0 : Main.MAX_BOUNCES;
	}
	
	public void click(int x, int y)
	{
		ray.origin.x = x;
		ray.origin.y = y;
	}
	
	public void draw()
	{
		Frame.clear();
		Frame.draw(line.point, "O");
		
		for(Reflector2D ref : refs)
		{
			Frame.draw(ref);
		}
		
		Ray2D currentRay = ray;
		Reflector2D lastRef = null;
		for(int bounce = 0; bounce < Main.MAX_BOUNCES; bounce++)
		{
			double dist = Double.MAX_VALUE;
			Reflector2D closestRef = null;
			Point2D closestPoint = null;
			
			for(Reflector2D ref : refs)
			{
				if(lastRef != ref)
				{
					for(Point2D pnt : ref.intersection(currentRay))
					{
						double tmpDist = currentRay.origin.distance(pnt);
						if(tmpDist < dist)
						{
							dist = tmpDist;
							closestRef = ref;
							closestPoint = pnt;
						}
					}
				}
				
			}
			
			if(closestRef == null)
			{
				Frame.draw(currentRay);
				break;
			}
			Frame.draw(new Segment2D(currentRay.origin, closestPoint));
			currentRay = closestRef.reflect(currentRay);
			lastRef = closestRef;
		}
		
	}
	
}
