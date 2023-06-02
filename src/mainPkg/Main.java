package mainPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import MathPkg.Points.Point2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Circle;
import MathPkg.Vectors.Vector2D;
import MathPkg.Angle.*;
import MathPkg.Lines.Line2D;
import MathPkg.Ray.*;

public class Main {
	
	public static Circle[] circList = new Circle[100];
	public static Line2D[] lineList = new Line2D[1];
	public static ArrayList<Circle> circArr = new ArrayList<>();
	
	public static Graphics g;
	public static Ray2D ray;
	public static Line2D line;
	public static int MAX_BOUNCES = 2;
	public static int RAY_NB = 600;
	public static double FOV = 360.0;

	public static void main(String[] args) {
		
		MousePos mP = new MousePos();
		MouseClick mC = new MouseClick();
		
		JFrame frame = new JFrame("Math test");
		frame.setLocation(1000, 1080);
		frame.setSize(1440, 1440);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		g = frame.getGraphics();
		
		frame.addMouseMotionListener(mP);
		frame.addMouseListener(mC);
		
		try {
			Thread.sleep(100);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		line = new Line2D(new Point2D(500, 200), new Vector2D(50, 10));
		
		int MAX_HEIGHT = 1440;
		int MAX_WIDTH = 1440;
		int MAX_RADIUS = 75;
		
		int DIST = 100;
		
		for(int i = 0; i < circList.length; i++)
		{
			circList[i] = new Circle(new Point2D(Math.random()*MAX_WIDTH, Math.random()*MAX_HEIGHT), Math.random()*MAX_RADIUS);
		}
		/*int nbCirc = 0;
		for(int x = 0; x < MAX_WIDTH; x+=DIST)
		{
			for(int y = 0; y < MAX_HEIGHT; y+=DIST)
			{
				if(nbCirc < circList.length -1) {
					circList[nbCirc] = new Circle(new Point2D(Math.random()*MAX_WIDTH, Math.random()*MAX_HEIGHT), Math.random()*MAX_RADIUS);
					nbCirc++;
				}
			}
		}*/
		
		lineList[0] = new Line2D(new Point2D(200, 800), new Point2D(800, 800));
		
		line.point= new Point2D(720, 720);
		
		ray = new Ray2D(new Point2D(720, 720), new Vector2D(1, 1));
		
		/*for(int i = 0; i < 7200; i++)
		{
			g.clearRect(0, 0, 1440, 1440);
			
			ray.vect = ray.vect.turn(0.1);
			drawRay(ray, 2000, g);
			for(Circle circ : circList)
			{
				drawCircle(circ, g);
				for(Point2D pnt : circ.intersection(ray))
				{
					drawPoint(pnt, g, ' ');
				}
			}
			
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/
		
		
		
		/*Point2D A = new Point2D(400, 400);
		Point2D B = new Point2D(600, 545);
		Point2D C = new Point2D(650, 650);
		Line2D ABLine = new Line2D(A, B);
		Circle CCircle = new Circle(C, 200);
		Circle CSymCircle = new Circle(ABLine.symmetry(C), 100);
		Circle CSymSymCircle = new Circle(new Line2D(A, CSymCircle.center).symmetry(C), 50);
		Circle CSymSymSymCircle = new Circle(new Line2D(A, CSymSymCircle.center).symmetry(C), 25);*/
		
		/*for(int x = 0; x < 20000; x+=1)
		{
			g.clearRect(0,  0,  1440,  1440);
			
			ABLine = new Line2D(A, ABLine.vect.turn(0.05
					));
			CSymCircle = new Circle(ABLine.symmetry(C), 100);
			CSymSymCircle = new Circle(new Line2D(A, CSymCircle.center).symmetry(C), 50);
			CSymSymSymCircle = new Circle(new Line2D(A, CSymSymCircle.center).symmetry(C), 25);
			
			drawPoint(A, g, 'A');
			drawPoint(C, g, 'C');
			/*drawPoint(CSymCircle.center, g, 'D');
			drawPoint(CSymSymCircle.center, g, 'E');
			drawPoint(CSymSymSymCircle.center, g, 'F');
			drawCircle(CCircle, g);
			/*drawCircle(CSymCircle, g);
			drawCircle(CSymSymCircle, g);
			drawCircle(CSymSymSymCircle, g);
			drawLine(ABLine, 500, g);
			
			for(Point2D pnt : CCircle.intersection(ABLine))
			{
				drawPoint(pnt, g, 'G');
			}
			drawPoint(CCircle.projection(ABLine), g, 'G');
			
			System.out.println("Distance of line from circle : " + CCircle.distance(ABLine));
			
			/*for(Point2D pnt : CSymCircle.intersection(ABLine))
			{
				drawPoint(pnt, g, 'H');
			}
			drawPoint(CSymCircle.projection(ABLine), g, 'H');
			
			for(Point2D pnt : CSymSymCircle.intersection(ABLine))
			{
				drawPoint(pnt, g, 'I');
			}
			drawPoint(CSymSymCircle.projection(ABLine), g, 'I');
			
			for(Point2D pnt : CSymSymSymCircle.intersection(ABLine))
			{
				drawPoint(pnt, g, 'J');
			}
			drawPoint(CSymSymSymCircle.projection(ABLine), g, 'J');
			
			try {
				Thread.sleep(12);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/
		
	}
	
	public static void testSymmetry(int x, int y)
	{
		g.clearRect(0, 0, 1440, 1440);
		Circle circ = new Circle(new Point2D(300, 300), 200);
		
		Point2D center = new Point2D(720, 720);
		Ray2D mouse = new Ray2D(center, new Point2D(x, y));
		
		drawCircle(circ, g);
		drawPoint(center, g, 'O');
		drawRay(mouse, 1000, g);
		
		for(Point2D pnt : circ.intersection(mouse))
		{
			drawPoint(pnt, g, ' ');
			Line2D normLine = new Line2D(circ.center, pnt);
			drawLine(normLine, 200, g);
			
			//drawRay(symRay, 500, g);
			
		}
		
	}
	
	public static void moveRay(int x, int y)
	{
		ray.origin.x = x;
		ray.origin.y = y;
	}
	
	public static void renderLine(int x, int y)
	{
		g.clearRect(0, 0, 1440, 1440);
		
		line.vect = new Vector2D(line.point, new Point2D(x, y));
		drawLine(line, 500, g);
		
		for(Line2D seg : lineList)
		{
			drawPoint(seg.intersection(line), g, ' ');
			drawLine(seg, 500, g);
		}
	}
	
	public static void renderRay(int x, int y)
	{
		int iter = 0;
		double DEGREE_PER_RAY = FOV/RAY_NB;
		//ray.vect = new Vector2D(ray.origin, new Point2D(x, y));
		ray.origin.x = x;
		ray.origin.y = y;
		ray.vect = ray.vect.turn(-FOV/(double)2);
		long startMS = System.currentTimeMillis();
		g.setColor(Color.black);
		g.fillRect(0, 0, 1440, 1440);
		//g.clearRect(0, 0, 1440, 1440);
		
		for(int rayNb = 0; rayNb < RAY_NB; rayNb++)
		{
			double rayLength = 0;
			ray.vect = ray.vect.turn(DEGREE_PER_RAY);
			ArrayList<Segment2D> segm = new ArrayList<>();
			ArrayList<Point2D> bounces = new ArrayList<>();
			
			Ray2D currentRay = ray;
			drawPoint(ray.origin, g, 'O');
			Circle lastCirc = null;
			
			for(int i = 0; i <= MAX_BOUNCES; i++)
			{
				
				Circle closestCirc = null;
				Point2D closestPoint = null;
				double dist = Double.MAX_VALUE;
				
				for(Circle circ : circList)
				{
					if(circ.intersects(ray.origin) > -1)
					{
						Point2D ext = circ.projection(ray.origin);
						return;
					}
					
					for(Point2D pnt : circ.intersection(currentRay))
					{
						if(circ == lastCirc) break; //making sure the ray isn't tempted to collide with the same circle as the last one, this goofy ray
						
						if(pnt.distance(currentRay.origin) < dist)
						{
							closestCirc = circ;
							closestPoint = pnt;
							dist = pnt.distance(currentRay.origin);
						}
						iter++;
					}
					//drawCircle(circ, g);
				}
				
				/*for(Line2D seg : lineList)
				{
					drawLine(seg, 500, g);
				}*/
				
				if(closestPoint != null)
				{
					bounces.add(closestPoint);
					Segment2D prevSegm = new Segment2D(currentRay.origin, closestPoint);
					segm.add(prevSegm);
					
					rayLength+=prevSegm.norm();
					//drawPoint(closestPoint, g, ' ');
					//System.out.println(rayLength);
					int color = (int)(-0.1*rayLength + 255);
					color = color > 255 ? 255 : color < 0 ? 0 : color;
					//System.out.println(color);*/
					g.setColor(new Color(color, color, color, 200));
					g.drawOval((int)closestPoint.x, (int)closestPoint.y, 1, 1);
					//drawSegment(prevSegm, g);

					currentRay = closestCirc.hitSymmetry(currentRay);
					
					lastCirc = closestCirc;
				}
				
				
			}
			
			//bounces.forEach(p -> drawPoint(p, g, ' '));
			//if(bounces.size() == 0) drawRay(currentRay, 1500, g);
		}
		
		char[] fps = String.valueOf((int)(1000/(System.currentTimeMillis() - startMS))).toCharArray();
		//System.out.print("\riter" + iter + "; ms:"+(System.currentTimeMillis() - startMS));
		g.drawChars(fps, 0, fps.length, 50, 50);
		
	}
	
	public static void drawPoint(Point2D a, Graphics g, char name)
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
		g.drawLine((int)line.point.x, (int)line.point.y, (int)(line.point.x - line.vect.unit().x*mult), (int)(line.point.y - line.vect.unit().y*mult));
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
	
	static void drawRay(Ray2D ray, int mult, Graphics g)
	{
		g.setColor(Color.magenta);
		g.drawLine((int)ray.origin.x, (int)ray.origin.y, (int)(ray.origin.x + ray.vect.unit().x*mult), (int)(ray.origin.y + ray.vect.unit().y*mult));
	}

}
