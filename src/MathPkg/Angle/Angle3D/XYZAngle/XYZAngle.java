package MathPkg.Angle.Angle3D.XYZAngle;

import MathPkg.Angle.Angle2D.Angle;
import MathPkg.Vectors.*;

public class XYZAngle {
	
	public static double[] angle(Vector3D vect1, Vector3D vect2)
	{
		double[] angles = new double[3];

		Vector2D[][] axisVectors = new Vector2D[][]
		{{
			new Vector2D(vect1.y, vect1.z),
			new Vector2D(vect2.y, vect2.z)},
		{
			new Vector2D(vect1.x, vect1.z),
			new Vector2D(vect2.x, vect2.z)},
		{
			new Vector2D(vect1.x, vect1.y),
			new Vector2D(vect2.x, vect2.y)}};

		for(int axis = 0; axis < 3; axis++)
		{
			angles[axis] = Angle.angle(axisVectors[axis][0], axisVectors[axis][1]);
		}
		return(angles);
	}

	public static double[] angle(Vector3D vect)
	{
		double[] angles = new double[] {
			Angle.angle(new Vector2D(vect.y, vect.z), new Vector2D(1, 0)),
			Angle.angle(new Vector2D(vect.x, vect.z), new Vector2D(1, 0)),
			Angle.angle(new Vector2D(vect.x, vect.y), new Vector2D(1, 0))
		};

		return(angles);
	}
}
