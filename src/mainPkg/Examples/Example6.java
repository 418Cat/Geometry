package mainPkg.Examples;

import java.awt.image.BufferStrategy;
import java.util.Date;
import java.util.Timer;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import MathPkg.Shapes.Shapes2D.Triangle;
import MathPkg.Vectors.Vector2D;
import mainPkg.Frame;

public class Example6 implements Example {
	
	Reflector2D[] shapes = 
	{
		new Triangle(new Point2D(700, 853), new Point2D(900,750), new Point2D(750, 600)),
		new Circle(new Point2D(300, 300), 100)
	};
	
	Point2D movingPnt = new Point2D(500, 300);
	Circle movingCircle = new Circle(new Point2D(300, 300), 100);
	
	Line2D line = new Line2D(new Point2D(500, 500), new Vector2D(-1, -1));
	
	Circle rotating = new Circle(new Point2D(300, 800), 150);
	
	
	public Example6()
	{
		
	}
	
	public void scroll(int scrollAmount)
	{
		
	}
	
	public void mouse(int x, int y)
	{
		movingCircle.center.x = x;
		movingCircle.center.y = y;
	}
	
	public void click(int x, int y)
	{
		
	}
	
	public void draw()
	{
		Frame.draw(movingPnt, "A");
		Frame.draw(movingCircle);
		Frame.draw(line);
		
		Frame.draw(movingCircle.projection(movingPnt), "A's Projection");
		
		for(Reflector2D ref : shapes)
		{
			Frame.draw(ref);
			
			for(Point2D pnt : ref.intersection(movingCircle))
			{
				Frame.draw(pnt, "intersect");
			}
			
			for(Point2D pnt : ref.intersection(line))
			{
				Frame.draw(pnt, "intersect");
			}
		}
		
		for(Point2D pnt : line.intersection(movingCircle))
		{
			Frame.draw(pnt, " ");
		}
	}

}
