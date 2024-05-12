package dev._418cat.math.geometry.utils.Graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import dev._418cat.math.geometry.utils.events.eventResolver;
import dev._418cat.math.geometry.utils.events.listeners.mouse.MouseClick;
import dev._418cat.math.geometry.utils.events.listeners.mouse.MousePos;
import dev._418cat.math.geometry.utils.events.listeners.mouse.MouseScroll;

public class Frame
{

	/*
	 * JFrame items
	 */
	private Graphics g;
	private JFrame frame;
	private BufferStrategy bufferStrategy;
	private int[] size = new int[2];

	/*
	 * View parameters
	 */
	public double ZOOM = 1;
	private boolean CLEAR_EACH_FRAME = true;
	private int FPS_DISPLAY_INTERVAL_MS = 100;

	/*
	 * FPS variables
	 */
	private long start = System.nanoTime();
	private long lastFpsDisplayTime = System.currentTimeMillis();
	private double lastFpsDisplay = 0;
	private int framesSinceLastDisp = 0;
	private int fpsCap = 0;

	/*
	 * settings
	 */
	private boolean showDebug = false;

	private ArrayList<String> debugs = new ArrayList<String>();

	public Frame(int[] size, int[] location, eventResolver evResolver)
	{
		if (size.length != 2 || location.length != 2)
		{
			throw new IllegalArgumentException(
				"Frame constructor need `int[] size` and `int[] location` parameters to both be of length 2");
		}

		MousePos mP = new MousePos(evResolver);
		MouseClick mC = new MouseClick(evResolver);
		MouseScroll mS = new MouseScroll(evResolver);

		this.size[0] = size[0];
		this.size[1] = size[1];

		frame = new JFrame("Math test");
		frame.setLocation(location[0], location[1]);
		frame.setSize(this.size[0], this.size[1]);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);

		frame.getContentPane().setIgnoreRepaint(true);
		frame.createBufferStrategy(2);

		bufferStrategy = frame.getBufferStrategy();

		frame.addMouseMotionListener(mP);
		frame.addMouseListener(mC);
		frame.addMouseWheelListener(mS);

		try
		{
			// Give some time to jframe to initialize
			Thread.sleep(100);
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	public void start()
	{
		g = bufferStrategy.getDrawGraphics();
		if (CLEAR_EACH_FRAME)
			g.clearRect(0, 0, size[0], size[1]);

		if (showDebug)
			displayFps();
		start = System.nanoTime();
		framesSinceLastDisp++;
	}

	public void end()
	{
		if (showDebug)
			displayDebug();

		g.dispose();
		bufferStrategy.show();
	}

	private void displayDebug()
	{
		g.setFont(new Font("Monospaced", Font.PLAIN, 15));
		g.setColor(Color.red);
		for (int i = 0; i < debugs.size(); i++)
		{
			g.clearRect(5, 5 + 20 * i, debugs.get(i).length() * 10, 20);
			g.drawChars(debugs.get(i).toCharArray(), 0, debugs.get(i).length(), 10, 20 + 20 * i);
		}
		debugs.clear();
	}

	public void setClearMode(boolean setMode)
	{
		CLEAR_EACH_FRAME = setMode;
	}

	public void setFpsCap(int cap)
	{
		fpsCap = cap;
	}

	public int getFpsCap()
	{
		return fpsCap;
	}

	private void displayFps()
	{
		float deltaFPS = (System.currentTimeMillis() - lastFpsDisplayTime);
		if (deltaFPS >= FPS_DISPLAY_INTERVAL_MS)
		{
			lastFpsDisplay = framesSinceLastDisp / (deltaFPS / 1000.);

			framesSinceLastDisp = 0;
			lastFpsDisplayTime = System.currentTimeMillis();
		}

		debugPrint(String.format("Frame time: %.2fms", (float) (System.nanoTime() - start) * 0.000_001));
		debugPrint(String.format("FPS: %.1f", lastFpsDisplay));
	}

	public void showDebug(boolean show)
	{
		this.showDebug = show;
	}

	public void debugPrint(String debug)
	{
		debugs.add(debug);
	}

	public Graphics g()
	{
		return this.g;
	}

	public int[] getSize()
	{
		return size;
	}

}
