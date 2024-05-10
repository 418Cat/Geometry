package mainPkg.Examples;

import java.util.ArrayList;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import MathPkg.Shapes.Shapes2D.Triangle;
import MathPkg.Vectors.Vector2D;
import mainPkg.Frame;
import mainPkg.events.Event;
import mainPkg.events.types.MouseEv;

public class Example5 implements Example {
	
	private ArrayList<Event<?>> queue = new ArrayList<>();
	
	Reflector2D[] shapes = 
	{
		new Triangle(new Point2D(200, 475), new Point2D(250,375), new Point2D(377, 325)),
		new Circle(new Point2D(150, 150), 50)
	};
	
	Point2D projectPoint = new Point2D(350, 150);
	Circle movingCircle = new Circle(new Point2D(150, 150), 50);
	
	Line2D line = new Line2D(new Point2D(250, 250), new Vector2D(-1, -1));
	
	Circle rotating = new Circle(new Point2D(150, 400), 75);
	
	
	public Example5()
	{
		
	}
	
	public void addToQueue(Event<?> event)
	{
		queue.add(event);
	}
	
	private void resolveEvent(Event<?> event)
	{
		if(event == null) return;
		if(event.getClass() != MouseEv.class) return;
		
		MouseEv mev = (MouseEv)event;
		
		switch (mev) {
			case click:
			{
				break;
			}
			case move:
			{
				int values[] = mev.getValues();
				movingCircle.center.x = values[0];
				movingCircle.center.y = values[1];
				break;
			}
			case scroll:
			{
				break;
			}
			case drag:
			{
				Point2D newPoint = new Point2D(mev.getValues()[0], mev.getValues()[1]);
				
				movingCircle.radius = movingCircle.center.distance(newPoint);
			}
			
			default:
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void resolveQueue()
	{
		((ArrayList<Event<?>>)queue.clone()).forEach((ev) -> resolveEvent(ev));
		queue.clear();
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
		Frame.draw(projectPoint, "A");
		Frame.draw(movingCircle);
		Frame.draw(line);
		
		Frame.draw(movingCircle.center, "O");
		
		Point2D oProj = line.projection(movingCircle.center);
		Vector2D oProjA = new Vector2D(oProj, projectPoint).unit().multiply(125);
		
		Frame.draw(oProj, oProjA);
		Frame.draw(oProj, "O's Projection");
		
		Frame.draw(movingCircle.projection(projectPoint), "A's Projection");
		
		for(Reflector2D ref : shapes)
		{
			Frame.draw(ref);
			
			for(Point2D pnt : ref.intersection(movingCircle))
			{
				Frame.draw(pnt, "");
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
