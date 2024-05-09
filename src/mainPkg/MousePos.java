package mainPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import mainPkg.Examples.Example;
import mainPkg.Examples.Example.eventType;

public class MousePos implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		final Example.eventType event = eventType.mouse;
		event.setValues(new int[] {(int)(e.getX()/Frame.ZOOM), (int)(e.getY()/Frame.ZOOM)});
		Main.ex.addToQueue(event);
	}

}
