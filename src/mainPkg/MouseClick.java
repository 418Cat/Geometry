package mainPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mainPkg.events.types.MouseEv;

public class MouseClick implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e)
	{
		MouseEv event = MouseEv.click;
		event.setValues(new int[] {(int)(e.getX()/Frame.ZOOM), (int)(e.getY()/Frame.ZOOM)});
		Main.ex.addToQueue(event);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
