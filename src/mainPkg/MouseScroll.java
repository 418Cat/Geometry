package mainPkg;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import mainPkg.Examples.Example;
import mainPkg.Examples.Example.eventType;

public class MouseScroll implements MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Example.eventType event = eventType.scroll;
		event.setValues(new int[] {e.getWheelRotation()});
		Main.ex.addToQueue(event);

	}

}
