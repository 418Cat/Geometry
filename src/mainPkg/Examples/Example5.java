package mainPkg.Examples;

import MathPkg.Points.Point2D;
import MathPkg.Vectors.*;
import mainPkg.Frame;
import mainPkg.Main;

public class Example5 implements Example {
	
	Vector2D vect = new Vector2D(100, 100);
	Point2D pnt = new Point2D(Main.frameSize[0]/2, Main.frameSize[1]/2);
	
	@Override
	public void mouse(int x, int y)
	{
		pnt.x = x;
		pnt.y = y;
	}


	@Override
	public void scroll(int scrollAmount) {
		
		vect = vect.turn(scrollAmount);
		
	}


	@Override
	public void click(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void draw() {
		Frame.start();
		Frame.draw(pnt, vect);
		Frame.draw(pnt, " ");
		Frame.end();
	}

}
