package mainPkg.Examples;

import java.awt.Color;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import mainPkg.Frame;

public class Example3 implements Example {
	
	/*public static Reflector2D[] refs = {
			new Line2D(new Point2D(0, 900), new Vector2D(1, 0)),
			new Circle(new Point2D(300, 300), 100),
			new Circle(new Point2D(650, 500), 50),
			new Circle(new Point2D(500, 420), 75),
			new Circle(new Point2D(800, 300), 200),
			new Segment2D(new Point2D(100, 100), new Point2D(900, 200)),
			new Triangle(new Point2D(200, 200), new Point2D(300, 250), new Point2D(350, 500)),
			new Triangle(new Point2D(950, 850), new Point2D(700, 800), new Point2D(900, 850))
	};*/
	
	public static Reflector2D[] refs = new Reflector2D[100];
	
	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);
	
	public static Ray2D ray = new Ray2D(A, Aprime);
	
	public static final int RAY_NB = 1000;
	public static final int FOV = 360;
	
	public static int MAX_BOUNCES = 5;
	
	public Example3()
	{
		for(int i = 0; i < refs.length; i++)
		{
			int x = (int)(Math.random() * 1000.);
			int y = (int)(Math.random() * 1000.);
			
			int r = (int)(Math.random() * 75.);
			
			refs[i] = new Circle(new Point2D(x, y), r);
		}
	}
	
	public void mouse(int x, int y)
	{
		/*Aprime.x = x;
		Aprime.y = y;
		line.vect = new Vector2D(line.point, Aprime).unit();
		ray.vect = line.vect;*/
		ray.origin.x = x;
		ray.origin.y = y;
	}
	
	public void scroll(int scrollAmount)
	{
		MAX_BOUNCES+=scrollAmount;
		MAX_BOUNCES = MAX_BOUNCES < 0 ? 0 : MAX_BOUNCES;
	}
	
	public void click(int x, int y)
	{
		ray.origin.x = x;
		ray.origin.y = y;
	}
	
	public void draw()
	{
		Frame.clear(Color.black);
		
		double degreePerRay = (double)FOV/(double)RAY_NB;
		
		for(int rayNb = 0; rayNb < RAY_NB; rayNb++)
		{
			
			//Frame.draw(ray);
			
			Ray2D currentRay = new Ray2D(ray.origin, ray.vect.turn(-(double)FOV/2 + rayNb*degreePerRay));
			Reflector2D lastRef = null;
			for(int bounce = 0; bounce <= MAX_BOUNCES; bounce++)
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
				
				if(closestRef == null) break;
				
				Segment2D travel = new Segment2D(currentRay.origin, closestPoint);
				int distTravel = (int)travel.norm();
				
				Frame.drawPix((int)closestPoint.x, (int)closestPoint.y, new Color((distTravel / 2)%256, (int)((Math.abs(currentRay.vect.x / currentRay.vect.norm())) * 255), (int)((Math.abs(currentRay.vect.y / currentRay.vect.norm())) * 255)));
				currentRay = closestRef.reflect(currentRay);
				lastRef = closestRef;
			}
			
		}
	}
	
}
