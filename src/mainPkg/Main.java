package mainPkg;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Circle;
import MathPkg.Vectors.Vector2D;
import MathPkg.Angle.*;
import MathPkg.Lines.Line2D;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Math test");
		frame.setLocation(1000, 1080);
		frame.setSize(1440, 1440);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Graphics g = frame.getGraphics();		
		
		try {
			Thread.sleep(100);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		

	}
	
	static void drawPoint(Point2D a, Graphics g, char name)
	{
		g.setColor(Color.red);
		g.fillOval((int)a.x - 5, (int)a.y - 5, 10, 10);
		
		g.setColor(Color.black);
		g.drawChars(new char[] {name}, 0, 1, (int)a.x - 3, (int)a.y + 20);
	}
	
	static void drawVect(Point2D pnt, Vector2D vect, Graphics g)
	{
		g.setColor(Color.blue);
		g.drawLine((int)pnt.x, (int)pnt.y, (int)(pnt.x + vect.x), (int)(pnt.y + vect.y));
		g.fillOval((int)(pnt.x + vect.x) - 3, (int)(pnt.y + vect.y)-3, 6, 6);
	}
	
	static void drawLine(Line2D line, int mult, Graphics g)
	{
		g.setColor(Color.green);
		g.drawLine((int)line.point.x, (int)line.point.y, (int)(line.point.x + line.vect.x*mult), (int)(line.point.y + line.vect.y*mult));
	}
	
	static void drawCircle(Circle circle, Graphics g)
	{
		g.setColor(Color.pink);
		g.drawOval((int)(circle.center.x - circle.radius), (int)(circle.center.y - circle.radius), (int)circle.radius*2, (int)circle.radius*2);
	}
	
	static void drawAngle(Segment2D segA, Segment2D segB, int radius, Graphics g)
	{
		g.setColor(Color.cyan);
		g.drawArc((int)segA.A.x-radius, (int)segA.A.y-radius, radius*2, radius*2, (int)AbsAngle.angle(segA, new Vector2D(1, 0)),(int)AbsAngle.angle(segA, segB));
	}
	
	static void drawSegment(Segment2D segment, Graphics g)
	{
		g.setColor(Color.gray);
		g.drawLine((int)segment.A.x, (int)segment.A.y, (int)segment.B.x, (int)segment.B.y);
	}

}