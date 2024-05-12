package dev._418cat.math.geometry.utils.main.Examples;

import java.util.ArrayList;

import dev._418cat.math.geometry.lines.Line2D;
import dev._418cat.math.geometry.points.Point2D;
import dev._418cat.math.geometry.shapes.shapes2d.Circle;
import dev._418cat.math.geometry.shapes.shapes2d.Reflector2D;
import dev._418cat.math.geometry.shapes.shapes2d.Triangle;
import dev._418cat.math.geometry.utils.Graphics.DrawUtils;
import dev._418cat.math.geometry.utils.events.Event;
import dev._418cat.math.geometry.utils.main.Main;
import dev._418cat.math.geometry.utils.types.MouseEv;
import dev._418cat.math.geometry.vectors.Vector2D;

public class Example5 implements Example
{

	private ArrayList<Event<?>> queue = new ArrayList<>();

	Reflector2D[] shapes = { new Triangle(new Point2D(200, 475), new Point2D(250, 375), new Point2D(377, 325)),
		new Circle(new Point2D(150, 150), 50) };

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
		if (event == null)
			return;
		if (event.getClass() != MouseEv.class)
			return;

		MouseEv mev = (MouseEv) event;

		switch (mev)
		{
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
		((ArrayList<Event<?>>) queue.clone()).forEach((ev) -> resolveEvent(ev));
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

	public void render()
	{
		DrawUtils.draw(projectPoint, "A", Main.frame);
		DrawUtils.draw(movingCircle, Main.frame);
		DrawUtils.draw(line, Main.frame);

		DrawUtils.draw(movingCircle.center, "O", Main.frame);

		Point2D oProj = line.projection(movingCircle.center);
		Vector2D oProjA = new Vector2D(oProj, projectPoint).unit().multiply(125);

		DrawUtils.draw(oProj, oProjA, Main.frame);
		DrawUtils.draw(oProj, "O's Projection", Main.frame);

		DrawUtils.draw(movingCircle.projection(projectPoint), "A's Projection", Main.frame);

		for (Reflector2D ref : shapes)
		{
			DrawUtils.draw(ref, Main.frame);

			for (Point2D pnt : ref.intersection(movingCircle))
			{
				DrawUtils.draw(pnt, "", Main.frame);
			}

			for (Point2D pnt : ref.intersection(line))
			{
				DrawUtils.draw(pnt, "intersect", Main.frame);
			}
		}

		for (Point2D pnt : line.intersection(movingCircle))
		{
			DrawUtils.draw(pnt, " ", Main.frame);
		}
	}
}
