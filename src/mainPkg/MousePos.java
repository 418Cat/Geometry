package mainPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MousePos implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		Main.moveObj(e.getX(), e.getY());
		Main.draw();
		
	}

}