package mainPkg.Examples;

import java.awt.Color;
import java.util.ArrayList;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import mainPkg.Frame;
import mainPkg.events.Event;
import mainPkg.events.types.MouseEv;

public class Example3 implements Example {
	
	private ArrayList<Event<?>> queue = new ArrayList<>();
	
	public static Reflector2D[] refs = new Reflector2D[75];
	
	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);
	
	public static Ray2D ray = new Ray2D(A, Aprime);
	
	public static final int RAY_NB = 750;
	public static final int FOV = 360;
	
	public static int MAX_BOUNCES = 2;
	
	public Example3()
	{
		for(int i = 0; i < refs.length; i++)
		{
			int x = (int)(Math.random() * 1000.);
			int y = (int)(Math.random() * 1000.);
			
			int r = (int)(Math.random() * 75.);
			
			refs[i] = new Circle(new Point2D(x, y), r);
		}
		
		/*refs[refs.length-4] = new Line2D(new Point2D(0, 0), new Point2D(0, 999));
		refs[refs.length-3] = new Line2D(new Point2D(0, 999), new Point2D(999, 999));
		refs[refs.length-2] = new Line2D(new Point2D(999, 999), new Point2D(999, 0));
		refs[refs.length-1] = new Line2D(new Point2D(999, 0), new Point2D(0, 0));*/
	}
	
	public void addToQueue(Event<?> ev)
	{
		queue.add(ev);
	}
	
	private void resolveEvent(Event<?> event)
	{
		if(event == null) return;
		if(event.getClass() != MouseEv.class) return;
		
		MouseEv mev = (MouseEv)event;
		
		switch (mev) {
			case click:
			{
				int values[] = mev.getValues();
				ray.origin.x = values[0];
				ray.origin.y = values[1];
				break;
			}
			case move:
			{
				int values[] = mev.getValues();
				ray.origin.x = values[0];
				ray.origin.y = values[1];
				break;
			}
			case scroll:
			{
				int scrollAmount = mev.getValues()[0];
				MAX_BOUNCES+=scrollAmount;
				MAX_BOUNCES = MAX_BOUNCES < 0 ? 0 : MAX_BOUNCES;
				break;
			}
			
			default:
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void resolveQueue()
	{
		((ArrayList<Event<?>>)queue.clone()).forEach((ev) -> resolveEvent(ev));
		queue.clear();
	}
	
	public void draw()
	{
		Frame.clear(Color.black);
		
		
		Frame.debugPrint(String.format("RAY_NB: %d", RAY_NB));
		Frame.debugPrint(String.format("MAX_BOUNCES: %d", MAX_BOUNCES));
		
		double degreePerRay = (double)FOV/(double)RAY_NB;
		
		//double initialTurn = Math.random() * degreePerRay;
		
		for(int rayNb = 0; rayNb < RAY_NB; rayNb++)
		{
			
			Ray2D currentRay = new Ray2D(ray.origin, ray.vect.turn(-(double)FOV/2 + rayNb*degreePerRay));
			//currentRay.vect = currentRay.vect.turn(initialTurn);
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
				
				//Frame.draw(new Segment2D(currentRay.origin, closestPoint));
				Frame.drawPix((int)closestPoint.x, (int)closestPoint.y, new Color((distTravel / 2)%256, (int)((Math.abs(currentRay.vect.x / currentRay.vect.norm())) * 255), (int)((Math.abs(currentRay.vect.y / currentRay.vect.norm())) * 255)));
				currentRay = closestRef.reflect(currentRay);
				lastRef = closestRef;
			}
			
		}
	}
	
}
