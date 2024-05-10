package mainPkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import MathPkg.Angle.Angle2D.AbsAngle;
import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import MathPkg.Shapes.Shapes2D.Triangle;
import MathPkg.Vectors.Vector2D;

public class Frame {
	
	public static Graphics g;
	public static double ZOOM = 1; 
	public static JFrame frame;
	public static BufferStrategy bufferStrategy;
	private static boolean clearMode = true;
	
	private static long start;
	
	private static long lastFpsDisplayTime = System.currentTimeMillis();
	private static int MIN_FPS_DISPLAY_TIME_MS = 100;
	private static float lastFpsDisplay = 0;
	private static float fpsSum;
	private static int fpsReadings = 0;
	
	private static ArrayList<String> debugs = new ArrayList<String>();
	
	public static void initFrame(int sizeX, int sizeY, int locX, int locY)
	{
		MousePos mP = new MousePos();
		MouseClick mC = new MouseClick();
		MouseScroll mS = new MouseScroll();
		
		frame = new JFrame("Math test");
		frame.setLocation(locX, locY);
		frame.setSize(sizeX, sizeY);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Frame.frame.getContentPane().setIgnoreRepaint(true);
		Frame.frame.createBufferStrategy(2);
		
		bufferStrategy = frame.getBufferStrategy();
		
		frame.addMouseMotionListener(mP);
		frame.addMouseListener(mC);
		frame.addMouseWheelListener(mS);
		
		try
		{
			Thread.sleep(100);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void start()
	{
		g = bufferStrategy.getDrawGraphics();
		if(clearMode) g.clearRect(0, 0, Main.frameSize[0], Main.frameSize[1]);
		
		start = System.nanoTime();
	}
	
	public static void end()
	{
		
		Long deltaNs = System.nanoTime() - start;
		fpsSum += (1000_000_000. / deltaNs);
		fpsReadings++;
		
		if(System.currentTimeMillis() - lastFpsDisplayTime >= MIN_FPS_DISPLAY_TIME_MS)
		{
			lastFpsDisplay = fpsSum / fpsReadings;
			fpsSum = 0;
			fpsReadings = 0;
			
			lastFpsDisplayTime = System.currentTimeMillis();
		}
		
		double ms = 0.000001 * deltaNs;
		
		String frameTime = String.format("%.2fms", ms);
		String fps = String.format("%.1ffps", lastFpsDisplay);
		
		g.clearRect(5, 5, frameTime.length() * 10, 20);
		g.clearRect(5, 25, fps.length() * 10, 20);
		
		g.setFont(new Font("Monospaced", Font.PLAIN, 15));
		g.setColor(Color.red);
		g.drawChars(frameTime.toCharArray(), 0, frameTime.length(), 10, 20);
		g.drawChars(fps.toCharArray(), 0, fps.length(), 10, 40);
		
		for(int i = 0; i < debugs.size(); i++)
		{
			g.clearRect(5, 45 + 20*i, debugs.get(i).length() * 10, 20);
			g.drawChars(debugs.get(i).toCharArray(), 0, debugs.get(i).length(), 10, 60 + 20*i);
		}
		debugs.clear();
		
		g.dispose();
		bufferStrategy.show();
	}
	
	public static void setClearMode(boolean setMode)
	{
		clearMode = setMode;
	}
	
	public static void debugPrint(String debug)
	{
		debugs.add(debug);
	}
	
	public static void clear(Color color)
	{
		g.setColor(color);
		g.fillRect(0, 0, Main.frameSize[0], Main.frameSize[1]);
	}
	
	public static void drawPix(int x, int y, Color color)
	{
		g.setColor(color);
		g.fillRect((int)((float)(x*ZOOM)), (int)((float)(y*ZOOM)), 1, 1);
	}
	
	public static void draw(Reflector2D ref)
	{
		if(ref.getClass() == Circle.class) draw((Circle)ref);
		if(ref.getClass() == Segment2D.class) draw((Segment2D)ref);
		if(ref.getClass() == Line2D.class) draw((Line2D)ref);
		if(ref.getClass() == Triangle.class) draw((Triangle)ref);
		if(ref.getClass() == Ray2D.class) draw((Ray2D)ref);
	}
	
	public static void draw(Triangle tri)
	{
		for(Segment2D seg : tri.segments())
		{
			draw(seg);
		}
	}
	
	public static void draw(Point2D a, String name)
	{
		g.setColor(Color.red);
		g.fillOval((int)((a.x - 5)*ZOOM), (int)((a.y - 5)*ZOOM), (int)((10*ZOOM)), (int)((10*ZOOM)));
		
		g.setColor(Color.red);
		g.drawChars(name.toCharArray(), 0, name.length(), (int)((a.x - 3)*ZOOM), (int)((a.y + 20)*ZOOM));
	}
	
	public static void draw(Point2D pnt, Vector2D vect)
	{
		g.setColor(Color.blue);
		g.drawLine((int)(pnt.x*ZOOM), (int)(pnt.y*ZOOM), (int)((pnt.x + vect.x)*ZOOM), (int)((pnt.y + vect.y)*ZOOM));
		g.fillOval((int)(((pnt.x + vect.x) - 3)*ZOOM), (int)(((pnt.y + vect.y)-3)*ZOOM), (int)(float)(6*ZOOM), (int)(float)(6*ZOOM));
		
		Point2D textCoords = vect.multiply(0.5).transform(vect.normalVectors()[1].multiply(10.0).transform(pnt));
		g.setColor(Color.black);
		
		String coordsDisp = String.format("%.2f;  %.2f" , vect.x, vect.y);
		g.drawChars(coordsDisp.toCharArray(), 0, coordsDisp.length(), (int)textCoords.x, (int)textCoords.y);
	}
	
	public static void draw(Line2D line)
	{
		int mult = (int)(Math.sqrt(Main.frameSize[0]*Main.frameSize[0] + Main.frameSize[1]*Main.frameSize[1])/ZOOM);
		g.setColor(Color.green);
		g.drawLine((int)(line.point.x*ZOOM), (int)(line.point.y*ZOOM), (int)((line.point.x + line.vect.unit().x*mult)*ZOOM), (int)((line.point.y + line.vect.unit().y*mult)*ZOOM));
		g.drawLine((int)(line.point.x*ZOOM), (int)(line.point.y*ZOOM), (int)((line.point.x - line.vect.unit().x*mult)*ZOOM), (int)((line.point.y - line.vect.unit().y*mult)*ZOOM));
	}
	
	private static void draw(Circle circle)
	{
		g.setColor(Color.pink);
		g.drawOval((int)((circle.center.x - circle.radius)*ZOOM), (int)((circle.center.y - circle.radius)*ZOOM), (int)((circle.radius*2)*ZOOM), (int)((circle.radius*2)*ZOOM));
	}
	
	public static void draw(Segment2D segA, Segment2D segB, int radius)
	{
		g.setColor(Color.cyan);
		g.drawArc((int)segA.A.x-radius, (int)segA.A.y-radius, radius*2, radius*2, (int)AbsAngle.angle(segA, new Vector2D(1, 0)),(int)AbsAngle.angle(segA, segB));
	}
	
	private static void draw(Segment2D segment)
	{
		g.setColor(Color.gray);
		g.drawLine((int)(segment.A.x*ZOOM), (int)(segment.A.y*ZOOM), (int)(segment.B.x*ZOOM), (int)(segment.B.y*ZOOM));
		
		/*Vector2D vecNorms[] = new Vector2D(segment).normalVectors();
		Segment2D seg1 = new Segment2D(vecNorms[0].transform(segment.A), vecNorms[0].transform(segment.B));
		Segment2D seg2 = new Segment2D(vecNorms[0].transform(segment.A), vecNorms[0].transform(segment.B));
		
		g.drawLine((int)(seg1.A.x*ZOOM), (int)(seg1.A.y*ZOOM), (int)(seg1.B.x*ZOOM), (int)(seg1.B.y*ZOOM));
		g.drawLine((int)(seg2.A.x*ZOOM), (int)(seg2.A.y*ZOOM), (int)(seg2.B.x*ZOOM), (int)(seg2.B.y*ZOOM));*/
	}
	
	public static void draw(Ray2D ray)
	{
		int mult = (int)((Math.sqrt(Main.frameSize[0]*Main.frameSize[0] + Main.frameSize[1]*Main.frameSize[1])/ZOOM));
		g.setColor(Color.magenta);
		g.drawLine((int)(ray.origin.x*ZOOM), (int)(ray.origin.y*ZOOM), (int)((ray.origin.x + ray.vect.unit().x*mult)*ZOOM), (int)((ray.origin.y + ray.vect.unit().y*mult)*ZOOM));
	}

}
