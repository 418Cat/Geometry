package mainPkg;

import java.awt.Color;

import MathPkg.Lines.Line3D;
import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Points.Point3D;
import MathPkg.Rays.Ray3D;
import MathPkg.Vectors.Vector2D;
import MathPkg.Vectors.Vector3D;
import MathPkg.Shapes.Shapes3D.*;

public class Main {
	
	public static int[] frameSize = new int[] {1000, 1000};
	
	public static int MAX_BOUNCES = 4;
	
	public static Ray3D[][] rayFrame = new Ray3D[frameSize[0]][frameSize[1]];
	public static Sphere[] spheres = new Sphere[] {new Sphere(new Point3D(1300, 900, 0), 300), new Sphere(new Point3D(1000, 0, 0), 500),
			new Sphere(new Point3D(500, -100, 200), 50),
			/*new Sphere(new Point3D(800, 100, 500), 500)*/};
	public static Point3D camera = new Point3D(0, 0, 0);
	
	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);
	
	public static Line2D[] lines = new Line2D[] {
			new Line2D(new Point2D(800, 50), new Vector2D(5, 60)),
			new Line2D(new Point2D(200, 50), new Vector2D(50, 3)),
			new Line2D(new Point2D(300, 900), new Vector2D(45, -45)),
			new Line2D(new Point2D(600, 750), new Vector2D(-300, -20))
	};
	
	
	public static void main(String[] args) {
		
		Frame.initFrame(frameSize[0], frameSize[1], 1440, 1280);
		
		
		
		/*for(int y = 0; y < frameSize[0]; y++)
		{
			for(int z = 0; z < frameSize[1]; z++)
			{
				rayFrame[y][z] = new Ray3D(camera, new Vector3D(camera, new Point3D(camera.x + 500, camera.y + -frameSize[0]/2 + y, camera.z + -frameSize[1]/2 + z)));
			}
		}*/
		
	}
	
	public static void moveObj(int x, int y)
	{
		Aprime.x = x;
		Aprime.y = y;
		line.vect = new Vector2D(line.point, Aprime).unit();
	}
	
	public static void scroll(int scrollAmount)
	{
		
	}
	
	public static void draw()
	{
		Frame.clear();
		
		Frame.draw(line);
		Frame.draw(line.point, "O");
		
		for(int line1 = 0; line1 < lines.length; line1++)
		{
			Frame.draw(line.intersection(lines[line1]), "A");
			Frame.draw(lines[line1]);
			for(int line2 = 0; line2 < lines.length; line2++)
			{
				if(line1 == line2) break;; 
				Point2D intersect = lines[line1].intersection(lines[line2]);
				Frame.draw(intersect, "intersection");
				System.out.println("intersect coords : " + intersect.x + ", " + intersect.y +"; index : " + line1 + " intersects " + line2);
			}
		}
		
	}
	
	/*public static void moveObj(int x, int y)
	{
		camera.y = -frameSize[0]/2 + x;
		camera.z = -frameSize[1]/2 + y;
	}
	
	public static void scroll(int scrollAmount)
	{
		camera.x += scrollAmount*50;
		System.out.println("went forward " + scrollAmount*50);
	}
	
	public static void draw()
	{
		//System.out.print("\rcam (" + camera.x + ", " + camera.y + ", " + camera.z + ");");
		Frame.clear();
		
		for(int y = 0; y < frameSize[0]; y++)
		{
			
			for(int z = 0; z < frameSize[1]; z++)
			{
				//draw background
				Frame.drawPix(y, z, new Color(255 - z/10, 255 - z/10, 255 - z/10));
				
				Ray3D currentRay = rayFrame[y][z];
				
				int bounces = 0;
				
				for(int bounce = 0; bounce < MAX_BOUNCES; bounce++)
				{
					
					Point3D closestPoint = null;
					Sphere closestSphere = null;
					
					for(Sphere sphere : spheres)
					{
						if(sphere.intersects(rayFrame[y][z]))
						{
							Point3D[] intersections = sphere.intersection(currentRay);
							double dist = Double.MAX_VALUE;

							for(Point3D pnt : intersections)
							{
								closestPoint = pnt;
								closestSphere = sphere;
								dist = pnt.distance(camera);
							}
						}
					}
					
					if(closestPoint == null) break;
					//Frame.drawPix(y, z, Color.white);
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
	}*/

}
