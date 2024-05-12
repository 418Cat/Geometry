package dev._418cat.math.geometry.utils.main;

import dev._418cat.math.geometry.utils.Graphics.Frame;
import dev._418cat.math.geometry.utils.main.Examples.*;

public class Main
{
	public static int[] frameSize = new int[] { 1000, 1000 };

	public static Example ex = new Example1();
	public static Frame frame = new Frame(frameSize, new int[] { 0, 0 }, ex);

	public static void main(String[] args)
	{
		frame.setFpsCap(15);
		frame.showDebug(true);

		while (true)
		{
			frame.start();
			ex.render();
			frame.end();

			ex.resolveQueue();
		}
	}
}