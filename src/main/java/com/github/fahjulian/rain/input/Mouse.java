package com.github.fahjulian.rain.input;

import com.github.fahjulian.rain.math.Position;
import com.github.fahjulian.rain.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    public static Position pos;
    public static boolean right, left, middle;

	public Mouse() {
		if (pos == null) 
			pos = new Position(0, 0);
		else
			System.err.println("Mouse should only be initialized once");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) left = true;
		else if (e.getButton() == MouseEvent.BUTTON2) middle = true;
		else if (e.getButton() == MouseEvent.BUTTON3) right = true;
 	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) left = false;
		else if (e.getButton() == MouseEvent.BUTTON2) middle = false;
		else if (e.getButton() == MouseEvent.BUTTON3) right = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        pos.x = e.getX() / Game.scale;
        pos.y = e.getY() / Game.scale;
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
        pos.x = e.getX() / Game.scale;
        pos.y = e.getY() / Game.scale;	
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
	}
	@Override
	public void mouseEntered(MouseEvent e) {		
	}
	@Override
	public void mouseExited(MouseEvent e) {		
	}
}
