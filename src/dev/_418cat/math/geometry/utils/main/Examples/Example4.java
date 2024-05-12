package dev._418cat.math.geometry.utils.main.Examples;

import java.awt.Color;
import java.util.ArrayList;

import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.rays.Ray2D;
import dev._418cat.math.geometry.segments.Segment2D;
import dev._418cat.math.geometry.shapes.shapes2d.Circle;
import dev._418cat.math.geometry.shapes.shapes2d.Reflector2D;
import dev._418cat.math.geometry.shapes.shapes2d.Triangle;
import dev._418cat.math.geometry.utils.Graphics.DrawUtils;
import dev._418cat.math.geometry.utils.events.Event;
import dev._418cat.math.geometry.utils.main.Main;
import dev._418cat.math.geometry.utils.types.MouseEv;
import dev._418cat.math.geometry.vectors.Vector2D;

public class Example4 implements Example
{

	private ArrayList<Event<?>> queue = new ArrayList<>();

	public static Reflector2D[] refs = { new Line2D(new Point2D(0, 900), new Vector2D(1, 0)), new Circle(new Point2D(300, 300), 100),
		new Circle(new Point2D(650, 500), 50), new Circle(new Point2D(500, 420), 75),
		new Circle(new Point2D(800, 300), 200), new Segment2D(new Point2D(100, 100), new Point2D(900, 200)),
		new Triangle(new Point2D(200, 200), new Point2D(300, 250), new Point2D(350, 500)),
		new Triangle(new Point2D(950, 850), new Point2D(700, 800), new Point2D(900, 850)),
		new Line2D(new Point2D(150, 0), new Point2D(10, 800)),
		new Line2D(new Point2D(990, 0), new Point2D(990, 800)), };

	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);

	public static Ray2D ray = new Ray2D(A, Aprime);
	public static int fov = 360;
	public static int rayNb = 100;

	public static int MAX_BOUNCES = 3;

	public void addToQueue(Event<?> event)
	{
		queue.add(event);
	}

	private void resolveEvent(Event<?> event)
	{
		if (event == null)
			return;
		if (event.getClass() != MouseEv.class)
			return;

		MouseEv mev = (MouseEv) event;

		switch (mev)
		{
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
			MAX_BOUNCES += scrollAmount;
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
		((ArrayList<Event<?>>) queue.clone()).forEach((ev) -> resolveEvent(ev));
		queue.clear();
	}

	public void render()
	{

		DrawUtils.clear(Color.black, Main.frame);

		DrawUtils.draw(line.point, "O", Main.frame);

		float rayPerDeg = (float) fov / (float) rayNb;

		for (int i = 0; i < rayNb; i++)
		{
			Ray2D currentRay = new Ray2D(ray.origin, ray.vect);
			currentRay.vect = currentRay.vect.turn(i * rayPerDeg - fov / 2);

			Reflector2D lastRef = null;
			for (int bounce = 0; bounce <= MAX_BOUNCES; bounce++)
			{
				double dist = Double.MAX_VALUE;
				Reflector2D closestRef = null;
				Point2D closestPoint = null;

				for (Reflector2D ref : refs)
				{
					if (lastRef != ref)
					{
						for (Point2D pnt : ref.intersection(currentRay))
						{
							double tmpDist = currentRay.origin.distance(pnt);
							if (tmpDist < dist)
							{
								dist = tmpDist;
								closestRef = ref;
								closestPoint = pnt;
							}
						}
					}

				}
				if (closestRef == null)
					break;
				DrawUtils.draw(new Segment2D(currentRay.origin, closestPoint), Main.frame);
				currentRay = closestRef.reflect(currentRay);
				lastRef = closestRef;
			}
		}
	}

}
