package dragonball.model.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JButton;

import dragonball.view.DragonView;
import dragonball.view.GameView;

public class MouseEventHandler implements MouseListener , Serializable {

	private GameView gameView;
	
	public MouseEventHandler() {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		DragonView dv = gameView.getDragonView();
		if (e.getSource() == dv.getSenzuBeans() || e.getSource() == dv.getAbilityPoints()
				|| e.getSource() == dv.getSuperAttack() || e.getSource() == dv.getUltimateAttack()) {
			((JButton) e.getSource()).setForeground(new Color(0, 100, 0));
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.err.println("MOUSE ENTERED!");
		//System.err.println(gameView);
		gameView.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gameView.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}
	
	public void setGameView (GameView gv){
		gameView = gv;
	}
}
