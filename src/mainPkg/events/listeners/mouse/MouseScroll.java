package mainPkg.events.listeners.mouse;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import mainPkg.events.eventResolver;
import mainPkg.events.types.MouseEv;

public class MouseScroll implements MouseWheelListener
{

	private eventResolver evResolver;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		MouseEv event = MouseEv.scroll;
		event.setValues(new int[] {e.getWheelRotation()});
		this.evResolver.addToQueue(event);
	}
	
	public MouseScroll(eventResolver evResolver)
	{
		this.evResolver = evResolver;
	}

}
