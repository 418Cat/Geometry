package mainPkg;

import java.awt.Color;
import java.awt.Graphics;

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
	
	public static void initFrame(int sizeX, int sizeY, int locX, int locY)
	{
		MousePos mP = new MousePos();
		MouseClick mC = new MouseClick();
		MouseScroll mS = new MouseScroll();
		
		JFrame frame = new JFrame("Math test");
		frame.setLocation(locX, locY);
		frame.setSize(sizeX, sizeY);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		g = frame.getGraphics();
		
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
	
	public static void clear()
	{
		g.clearRect(0, 0, Main.frameSize[0], Main.frameSize[1]);
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
	}
	
	public static void draw(Line2D line)
	{
		int mult = (int)(Math.sqrt(Main.frameSize[0]*Main.frameSize[0] + Main.frameSize[1]*Main.frameSize[1])/ZOOM);
		g.setColor(Color.green);
		g.drawLine((int)(line.point.x*ZOOM), (int)(line.point.y*ZOOM), (int)((line.point.x + line.vect.unit().x*mult)*ZOOM), (int)((line.point.y + line.vect.unit().y*mult)*ZOOM));
		g.drawLine((int)(line.point.x*ZOOM), (int)(line.point.y*ZOOM), (int)((line.point.x - line.vect.unit().x*mult)*ZOOM), (int)((line.point.y - line.vect.unit().y*mult)*ZOOM));
	}
	
	public static void draw(Circle circle)
	{
		g.setColor(Color.pink);
		g.drawOval((int)((circle.center.x - circle.radius)*ZOOM), (int)((circle.center.y - circle.radius)*ZOOM), (int)((circle.radius*2)*ZOOM), (int)((circle.radius*2)*ZOOM));
	}
	
	public static void draw(Segment2D segA, Segment2D segB, int radius)
	{
		g.setColor(Color.cyan);
		g.drawArc((int)segA.A.x-radius, (int)segA.A.y-radius, radius*2, radius*2, (int)AbsAngle.angle(segA, new Vector2D(1, 0)),(int)AbsAngle.angle(segA, segB));
	}
	
	public static void draw(Segment2D segment)
	{
		g.setColor(Color.gray);
		g.drawLine((int)(segment.A.x*ZOOM), (int)(segment.A.y*ZOOM), (int)(segment.B.x*ZOOM), (int)(segment.B.y*ZOOM));
	}
	
	public static void draw(Ray2D ray)
	{
		int mult = (int)((Math.sqrt(Main.frameSize[0]*Main.frameSize[0] + Main.frameSize[1]*Main.frameSize[1])/ZOOM));
		g.setColor(Color.magenta);
		g.drawLine((int)(ray.origin.x*ZOOM), (int)(ray.origin.y*ZOOM), (int)((ray.origin.x + ray.vect.unit().x*mult)*ZOOM), (int)((ray.origin.y + ray.vect.unit().y*mult)*ZOOM));
	}

}
