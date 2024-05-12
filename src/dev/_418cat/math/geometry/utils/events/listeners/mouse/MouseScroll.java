package dev._418cat.math.geometry.utils.events.listeners.mouse;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import dev._418cat.math.geometry.utils.events.eventResolver;
import dev._418cat.math.geometry.utils.types.MouseEv;

public class MouseScroll implements MouseWheelListener
{

	private eventResolver evResolver;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		MouseEv event = MouseEv.scroll;
		event.setValues(new int[] { e.getWheelRotation() });
		this.evResolver.addToQueue(event);
	}

	public MouseScroll(eventResolver evResolver)
	{
		this.evResolver = evResolver;
	}

}
