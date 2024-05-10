package mainPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import mainPkg.events.types.MouseEv;

public class MousePos implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		final MouseEv event = MouseEv.move;
		event.setValues(new int[] {(int)(e.getX()/Frame.ZOOM), (int)(e.getY()/Frame.ZOOM)});
		Main.ex.addToQueue(event);
	}

}
