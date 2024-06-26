package dev._418cat.math.geometry.utils.main.Examples;

import java.awt.Color;
import java.util.ArrayList;

import dev._418cat.math.geometry.points.Point3D;
import dev._418cat.math.geometry.rays.Ray3D;
import dev._418cat.math.geometry.shapes.shapes3d.Reflector3D;
import dev._418cat.math.geometry.shapes.shapes3d.Sphere;
import dev._418cat.math.geometry.utils.Graphics.DrawUtils;
import dev._418cat.math.geometry.utils.events.Event;
import dev._418cat.math.geometry.utils.main.Main;
import dev._418cat.math.geometry.utils.types.MouseEv;
import dev._418cat.math.geometry.vectors.Vector3D;

public class Example2 implements Example
{

	ArrayList<Event<?>> queue = new ArrayList<>();

	public static Ray3D[][] rayFrame = new Ray3D[Main.frameSize[0]][Main.frameSize[1]];
	public static Reflector3D[] refs = new Reflector3D[] { // new Sphere(new Point3D(1300, 900, 0), 300),
		new Sphere(new Point3D(1000, 0, 0), 500)/*
												 * , new Sphere(new Point3D(500, -100, 200), 50), new Sphere(new Point3D(800,
												 * 100, 500), 500)
												 */ };
	public static Point3D camera = new Point3D(0, 0, 0);

	public static int MAX_BOUNCES = 10;

	public Example2()
	{
		for (int y = 0; y < Main.frameSize[0]; y++)
		{
			for (int z = 0; z < Main.frameSize[1]; z++)
			{
				rayFrame[y][z] = new Ray3D(camera, new Vector3D(camera, new Point3D(camera.x + 300,
					camera.y + -Main.frameSize[0] / 2 + y, camera.z + -Main.frameSize[1] / 2 + z)));
			}
		}
	}

	public void addToQueue(Event<?> ev)
	{
		queue.add(ev);
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
			break;
		}
		case move:
		{
			int values[] = mev.getValues();
			camera.y = -Main.frameSize[0] / 2 + values[0];
			camera.z = -Main.frameSize[1] / 2 + values[1];
			break;
		}
		case scroll:
		{
			int scrollAmount = mev.getValues()[0];
			camera.x += scrollAmount * 50;
			System.out.println("went forward " + scrollAmount * 50);
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
		// System.out.print("\rcam (" + camera.x + ", " + camera.y + ", " + camera.z +
		// ");");

		for (int y = 0; y < Main.frameSize[0]; y++)
		{

			for (int z = 0; z < Main.frameSize[1]; z++)
			{
				// draw background
				DrawUtils.drawPix(y, z, new Color(255 - z / 10, 255 - z / 10, 255 - z / 10), Main.frame);

				Ray3D currentRay = rayFrame[y][z];

				int bounces = 0;

				for (int bounce = 0; bounce <= MAX_BOUNCES; bounce++)
				{

					Reflector3D closestRef = null;

					for (Reflector3D ref : refs)
					{
						if (ref.intersects(rayFrame[y][z]))
						{
							DrawUtils.drawPix(y, z, Color.black, Main.frame);
							System.out.println("dist ray sphere = "
								+ currentRay.projection(((Sphere) ref).center).distance(((Sphere) ref).center));
							Point3D[] intersections = ref.intersection(currentRay);

							double dist = Double.MAX_VALUE;

							for (Point3D pnt : intersections)
							{
								double tmpDist = pnt.distance(currentRay.origin);
								if (tmpDist < dist)
								{
									closestRef = ref;
									dist = tmpDist;
								}
							}
						}
					}

					if (closestRef == null)
						break;
					System.out.println("aaaaaaa");
					bounces++;
					currentRay = closestRef.reflect(currentRay);

				}

				if (bounces > 0)
				{
					DrawUtils.drawPix(y, z, new Color(bounces * 10, bounces * 10, bounces * 10), Main.frame);
				}

			}
		}
	}

}
