package mainPkg;

import java.awt.Color;

import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Vectors.Vector3D;
import MathPkg.Shapes.Shapes3D.*;

public class Main {
	
	public static int[] frameSize = new int[] {1000, 1000};
	
	public static Ray3D[][] rayFrame = new Ray3D[frameSize[0]][frameSize[1]];
	public static Sphere[] spheres = new Sphere[] {new Sphere(new Point3D(1300, 900, 0), 300), new Sphere(new Point3D(1000, 0, 0), 500), new Sphere(new Point3D(500, -100, 200), 50)};
	
	public static Point3D camera = new Point3D(0, 0, 0);
	
	public static void main(String[] args) {
		
		Frame.initFrame(frameSize[0], frameSize[1], 1440, 1280);
		
	}
	
	
	public static void moveObj(int x, int y)
	{
		camera.y = -frameSize[0]/2 + x;
		camera.z = -frameSize[1]/2 + y;
	}
	
	public static void scroll(int scrollAmount)
	{
		camera.x += scrollAmount*10;
	}
	
	public static void draw()
	{
		//System.out.print("\rcam (" + camera.x + ", " + camera.y + ", " + camera.z + ");");
		Frame.clear();
		
		for(int y = 0; y < frameSize[0]; y++)
		{
			
			for(int z = 0; z < frameSize[1]; z++)
			{
				Frame.drawPix(y, z, new Color(255 - z/10, 255 - z/10, 255 - z/10));
				
				rayFrame[y][z] = new Ray3D(camera, new Vector3D(camera, new Point3D(camera.x + 50, camera.y + -frameSize[0]/2 + y, camera.z + -frameSize[1]/2 + z)));
				
				for(Sphere sphere : spheres)
				{
					if(sphere.intersects(rayFrame[y][z]))
					{
						Point3D[] intersections = sphere.intersection(rayFrame[y][z]);
						double dist = Double.MAX_VALUE;
						
						//System.out.println(intersections.length);
						
						for(Point3D pnt : intersections)
						{
							//System.out.println(pnt.x);
							dist = pnt.distance(camera);
						}
						
						//System.out.println(dist);
						
						int color = (int)((double)((dist*dist*dist)/9000)*255);
						color = 255 - (color > 255 ? 255 : color < 0 ? 0 : color);
						
						Frame.drawPix(y, z, new Color(color, color, color));
					}
				}
			}
		}
	}

}
