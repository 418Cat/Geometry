package mainPkg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClick implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Main.ex.click(e.getX(), e.getY());
		Main.ex.draw();
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
