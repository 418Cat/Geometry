package dev._418cat.math.geometry.utils.events.listeners.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import dev._418cat.math.geometry.utils.events.eventResolver;
import dev._418cat.math.geometry.utils.types.MouseEv;

public class MousePos implements MouseMotionListener
{

	private eventResolver evResolver;

	@Override
	public void mouseDragged(MouseEvent e)
	{
		final MouseEv event = MouseEv.drag;
		event.setValues(new int[] { e.getX(), e.getY() });
		this.evResolver.addToQueue(event);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		final MouseEv event = MouseEv.move;
		event.setValues(new int[] { e.getX(), e.getY() });
		this.evResolver.addToQueue(event);
	}

	public MousePos(eventResolver evResolver)
	{
		this.evResolver = evResolver;
	}

}
