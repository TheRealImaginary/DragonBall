package dragonball.model.controller;

import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import dragonball.model.game.Game;
import dragonball.view.GameView;

public class WindowEventHandler implements WindowListener , Serializable {
	
	private static final String SaveFile = "SavedGame";
	private Game game;
	private GameView gameView;
	
	public WindowEventHandler() {
	}
	
	@Override
	public void windowOpened(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowClosing(java.awt.event.WindowEvent e) {
//		System.err.println(1);
		if(!gameView.getMainMenu().isVisible() && !gameView.getNewGame().isVisible()){
			try {
				game.save(SaveFile);
			}
			catch (IOException | NullPointerException ioe){
				ioe.printStackTrace();
				//JOptionPane.showMessageDialog(e.getWindow(), "Error Occured While Trying To Save Your Game ! " + ioe.getMessage());
				int x = JOptionPane.showConfirmDialog(null, "Error Occured While Trying To Save Your Game ! " + ioe.getMessage() + " Do you wish to exit either way ?!");
				if(x == 0)
					System.exit(0);
			}
			JOptionPane.showMessageDialog(e.getWindow(), "Saved Game, Exiting now!");
			System.exit(0);
		}
		else 
			System.exit(0);
	}

	@Override
	public void windowClosed(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowIconified(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowActivated(java.awt.event.WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(java.awt.event.WindowEvent e) {
		
	}

	public void setGame (Game game){
		this.game = game;
	}
	
	public void setGameView (GameView gv){
		gameView = gv;
	}
	
	public static void main(String[] args) {
//		JFrame f = new JFrame("Testing");
//		f.setSize(300, 300);
//		f.addWindowListener(new WindowEventHandler());
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setVisible(true);
	}
}
