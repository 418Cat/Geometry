package mainPkg.Examples;

import java.awt.Color;

import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Shapes.Shapes3D.Sphere;
import MathPkg.Vectors.Vector3D;
import mainPkg.Frame;
import mainPkg.Main;
import MathPkg.Lines.Line3D;

public class Example2 implements Example {
	
	public static Ray3D[][] rayFrame = new Ray3D[Main.frameSize[0]][Main.frameSize[1]];
	public static Sphere[] spheres = new Sphere[] {new Sphere(new Point3D(1300, 900, 0), 300), new Sphere(new Point3D(1000, 0, 0), 500),
			new Sphere(new Point3D(500, -100, 200), 50),
			new Sphere(new Point3D(800, 100, 500), 500)};
	public static Point3D camera = new Point3D(0, 0, 0);
	
	public static int MAX_BOUNCES = 0;
	
	public Example2()
	{
		for(int y = 0; y < Main.frameSize[0]; y++)
		{
			for(int z = 0; z < Main.frameSize[1]; z++)
			{
				rayFrame[y][z] = new Ray3D(camera, new Vector3D(camera, new Point3D(camera.x + 100, camera.y + -Main.frameSize[0]/2 + y, camera.z + -Main.frameSize[1]/2 + z)));
			}
		}
	}
	
	public void mouse(int x, int y)
	{
		camera.y = -Main.frameSize[0]/2 + x;
		camera.z = -Main.frameSize[1]/2 + y;
	}
	
	public void scroll(int scrollAmount)
	{
		camera.x += scrollAmount*50;
		System.out.println("went forward " + scrollAmount*50);
	}
	
	public void click(int x, int y)
	{
		
	}
	
	public void draw()
	{
		//System.out.print("\rcam (" + camera.x + ", " + camera.y + ", " + camera.z + ");");
		Frame.clear();
		
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
					
					Point3D closestPoint = null;
					Sphere closestSphere = null;
					
					for(Sphere sphere : spheres)
					{
						if(sphere.intersects(rayFrame[y][z]))
						{
							Point3D[] intersections = sphere.intersection(currentRay);

							for(Point3D pnt : intersections)
							{
								closestPoint = pnt;
								closestSphere = sphere;
							}
						}
					}
					
					if(closestPoint == null) break;
					bounces++;
					
					Line3D symLine = new Line3D(closestSphere.center, closestPoint);
					currentRay = new Ray3D(closestPoint, symLine.symmetry(currentRay.origin));
					
				}
				
				if(bounces > 0) {
					int color = (int)((double)bounces * ((double)255/MAX_BOUNCES));
					Frame.drawPix(y, z, new Color(color, color, color));
				}
				
				
			}
		}
	}

}
