package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JLabel {
	
	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JPanel menu;
	private JButton newGame, loadGame;
	
	public MainMenu (){
		super(BACKGROUND);
		setSize(X,Y);
		
		menu = new JPanel();
		menu.setSize(200, 200);
		menu.setOpaque(false);
		menu.setLocation(X >> 3,Y >> 2);		//Equivalent to divide by 8 and divide by 4
		menu.setVisible(true);
		
		registerFonts ();
		
		//newGame = new JButton("New Game");
		newGame = new JButton("<html><b><font color=#ffff00><u>N</u>ew</font><font color=#ff0000> Game</font></b>");
		newGame.setName("New Game");
		newGame.setFont(new Font("Saiyan Sans", Font.BOLD, 40));
		newGame.setContentAreaFilled(false);
		newGame.setBorderPainted(false);
		//newGame.setForeground(Color.BLACK);
		//newGame.setSize(150, 150);
		newGame.setToolTipText("Starts a new game!");
		
		//loadGame = new JButton("Load Game");
		loadGame = new JButton("<html><b><font color=#ffff00><u>L</u>oad</font><font color=#ff0000> Game</font></b>");
		loadGame.setName("Load Game");
		loadGame.setFont(new Font("Saiyan Sans", Font.BOLD, 40));
		loadGame.setContentAreaFilled(false);
		loadGame.setBorderPainted(false);
		//loadGame.setForeground(Color.red);
		//loadGame.setSize(150,150);
		loadGame.setToolTipText("Load a previously saved game!");
		
		menu.add(newGame);
		menu.add(loadGame);
		
		add(menu);
	}
	
	private void registerFonts (){
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Saiyan-Sans.ttf")));
		}
		catch (IOException | FontFormatException e){
			e.printStackTrace();
		}
	}
	
	public JButton getNewGame (){
		return newGame;
	}
	
	public JButton getLoadGame(){
		return loadGame;
	}
	
	public void setMouseListener (MouseListener ml){
		newGame.addMouseListener(ml);
		loadGame.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		MainMenu m = new MainMenu();
		JFrame f = new JFrame("Test Frame");
		f.setSize(1366, 768);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(m);
		f.setVisible(true);
	}

}
