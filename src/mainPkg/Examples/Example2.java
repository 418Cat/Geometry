package mainPkg.Examples;

import java.awt.Color;
import java.util.ArrayList;

import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Shapes.Shapes3D.Reflector3D;
import MathPkg.Shapes.Shapes3D.Sphere;
import MathPkg.Vectors.Vector3D;
import mainPkg.Frame;
import mainPkg.Main;

public class Example2 implements Example {
	
	ArrayList<eventType> queue = new ArrayList<>();
	
	public static Ray3D[][] rayFrame = new Ray3D[Main.frameSize[0]][Main.frameSize[1]];
	public static Reflector3D[] refs = new Reflector3D[] {//new Sphere(new Point3D(1300, 900, 0), 300),
			new Sphere(new Point3D(1000, 0, 0), 500)/*,
			new Sphere(new Point3D(500, -100, 200), 50),
			new Sphere(new Point3D(800, 100, 500), 500)*/};
	public static Point3D camera = new Point3D(0, 0, 0);
	
	public static int MAX_BOUNCES = 10;
	
	public Example2()
	{
		for(int y = 0; y < Main.frameSize[0]; y++)
		{
			for(int z = 0; z < Main.frameSize[1]; z++)
			{
				rayFrame[y][z] = new Ray3D(camera, new Vector3D(camera, new Point3D(camera.x + 300, camera.y + -Main.frameSize[0]/2 + y, camera.z + -Main.frameSize[1]/2 + z)));
			}
		}
	}
	
	public void addToQueue(eventType ev)
	{
		queue.add(ev);
	}
	
	private void resolveEvent(eventType event)
	{
		if(event == null) return;
		switch (event) {
		case click:
		{
			break;
		}
		case mouse:
		{
			int values[] = event.getValues();
			camera.y = -Main.frameSize[0]/2 + values[0];
			camera.z = -Main.frameSize[1]/2 + values[1];
			break;
		}
		case scroll:
		{
			int scrollAmount = event.getValues()[0];
			camera.x += scrollAmount*50;
			System.out.println("went forward " + scrollAmount*50);
			break;
		}
	}
	}
	
	@SuppressWarnings("unchecked")
	public void resolveQueue()
	{
		((ArrayList<eventType>)queue.clone()).forEach((ev) -> resolveEvent(ev));
		queue.clear();
	}
	
	public void draw()
	{
		//System.out.print("\rcam (" + camera.x + ", " + camera.y + ", " + camera.z + ");");
		Frame.start();
		
		for(int y = 0; y < Main.frameSize[0]; y++)
		{
			
			for(int z = 0; z < Main.frameSize[1]; z++)
			{
				//draw background
				Frame.drawPix(y, z, new Color(255 - z/10, 255 - z/10, 255 - z/10));
				
				Ray3D currentRay = rayFrame[y][z];
				
				int bounces = 0;
				
				for(int bounce = 0; bounce <= MAX_BOUNCES; bounce++)
				{
					
					Reflector3D closestRef = null;
					
					for(Reflector3D ref : refs)
					{
						if(ref.intersects(rayFrame[y][z]))
						{
							Frame.drawPix(y, z, Color.black);
							System.out.println("dist ray sphere = " + currentRay.projection(((Sphere)ref).center).distance(((Sphere)ref).center));
							Point3D[] intersections = ref.intersection(currentRay);

							double dist = Double.MAX_VALUE;
							
							for(Point3D pnt : intersections)
							{
								double tmpDist = pnt.distance(currentRay.origin);
								if(tmpDist < dist)
								{
									closestRef = ref;
									dist = tmpDist;
								}
							}
						}
					}
					
					if(closestRef == null) break;
					System.out.println("aaaaaaa");
					bounces++;
					currentRay = closestRef.reflect(currentRay);
					
				}
				
				if(bounces > 0) {
					Frame.drawPix(y, z, new Color(bounces*10, bounces*10, bounces*10));
				}
				
				
			}
		}
		Frame.end();
	}

}
