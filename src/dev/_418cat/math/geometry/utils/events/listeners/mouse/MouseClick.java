package dev._418cat.math.geometry.utils.events.listeners.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev._418cat.math.geometry.utils.events.eventResolver;
import dev._418cat.math.geometry.utils.types.MouseEv;

public class MouseClick implements MouseListener
{

	private eventResolver evResolver;

	@Override
	public void mouseClicked(MouseEvent e)
	{
		MouseEv event = MouseEv.click;
		event.setValues(new int[] { e.getX(), e.getY() });
		evResolver.addToQueue(event);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	public MouseClick(eventResolver evResolver)
	{
		this.evResolver = evResolver;
	}

}
