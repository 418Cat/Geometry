package mainPkg;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import mainPkg.events.types.MouseEv;

public class MouseScroll implements MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		MouseEv event = MouseEv.scroll;
		event.setValues(new int[] {e.getWheelRotation()});
		Main.ex.addToQueue(event);

	}

}
