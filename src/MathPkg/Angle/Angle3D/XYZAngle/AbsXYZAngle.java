package MathPkg.Angle.Angle3D.XYZAngle;

import MathPkg.Vectors.*;
import MathPkg.Angle.Angle2D.AbsAngle;

public class AbsXYZAngle {
	/**
	 *static tool used to compute the angle between two vectors
	 *@return double[] angles along the x, y and z axis, in this order
	 * */
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
			new Vector2D(vect2.x, vect2.y)}
		};

		for(int axis = 0; axis < 3; axis++)
		{
			angles[axis] = AbsAngle.angle(axisVectors[axis][0], axisVectors[axis][1]);
			angles[axis] = angles[axis] != angles[axis] ? 0 : angles[axis]; //check if NaN
		}
		return(angles);
	}

}
