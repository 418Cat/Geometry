package mainPkg;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseScroll implements MouseWheelListener {

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Main.ex.scroll(e.getWheelRotation());
		Main.ex.draw();
	}

}
