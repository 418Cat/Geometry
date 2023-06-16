package mainPkg.Examples;

import MathPkg.Angle.Angle3D.XYZAngle.*;
import MathPkg.Vectors.Vector3D;

public class Example4 implements Example {

	public Example4()
	{
		Vector3D vect1 = new Vector3D(-1, -1, 1);
		Vector3D vect2 = new Vector3D(0, 1, -1);
		
		double[] angles = XYZAngle.angle(vect1, vect2);

		System.out.println("angle : " + angles[0] + ", " + angles[1] + ", " + angles[2]);
	}

	public void mouse(int x, int y){}
	public void scroll(int scrollAmount){}
	public void click(int x, int y){}
	public void draw(){}

}
