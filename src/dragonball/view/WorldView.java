package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorldView extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("Map1.png");		//WorldBackground.png
	private static final ImageIcon SENZU_BEANS = new ImageIcon("senzubeans.png");
	private static final ImageIcon DRAGON_BALLS = new ImageIcon("dragonball1.png");
	private static final ImageIcon LEVEL_ICON = new ImageIcon("leveltest.png");
	
	private static final int X = 1366;
	private static final int Y = 768;		//new ImageIcon("dragon-ball-super-wallpaper-3.jpg")
	
	private JPanel panel, info, textPanel;
	private JButton[][] map;
	private JButton options;
	private JLabel playerName, fighterName, fighterLevel, SenzuBeans, DragonBalls;
	
	private TextPlay text;
	
	public WorldView (){			//Can set a borderlayout
		super(BACKGROUND);
		setSize(X,Y);
		//setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(10,10));
		panel.setSize(1340,700);
		panel.setLocation(0,45);
		panel.setOpaque(false);
		
		info = new JPanel();
		info.setLocation(-250, -5);
		info.setSize(1550, 50);				//1150
		info.setOpaque(false);
		
		playerName = new JLabel();
		playerName.setFont(new Font("Ubuntu", Font.BOLD, 25));
		playerName.setForeground(Color.RED);
		
		fighterName = new JLabel();
		fighterName.setFont(new Font("Ubuntu", Font.BOLD, 20));
		fighterName.setForeground(Color.GREEN);
	
		fighterLevel = new JLabel();	
		fighterLevel.setIcon(LEVEL_ICON);							
		fighterLevel.setHorizontalAlignment(JLabel.RIGHT);
		fighterLevel.setForeground(Color.ORANGE);
		fighterLevel.setFont(new Font("Ubuntu", Font.BOLD, 25));
		
		SenzuBeans = new JLabel(SENZU_BEANS);
		SenzuBeans.setHorizontalTextPosition(JLabel.RIGHT);
		SenzuBeans.setForeground(Color.ORANGE);
		SenzuBeans.setFont(new Font("Ubuntu", Font.BOLD, 25));
		
		DragonBalls = new JLabel(DRAGON_BALLS);
		DragonBalls.setHorizontalTextPosition(JLabel.RIGHT);
		DragonBalls.setForeground(Color.ORANGE);
		DragonBalls.setFont(new Font("Ubuntu", Font.BOLD, 25));
		
		options = new JButton("Options");
		options.setToolTipText("Access Options!");
		options.setForeground(Color.WHITE);
		options.setName("Options");
		options.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		options.setBorderPainted(false);
		options.setContentAreaFilled(false);
		
		info.add(playerName);
		info.add(fighterName);
		info.add(fighterLevel);
		info.add(SenzuBeans);
		info.add(DragonBalls);
		info.add(options);
		
		map = new JButton[10][10];
		
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				map[i][j] = new JButton();
				map[i][j].setContentAreaFilled(false);
				map[i][j].setBorderPainted(false);
				panel.add(map[i][j]);
			}
		}
		
		text = new TextPlay();
		
		textPanel = new JPanel();
		textPanel.setSize(1366, 200);
		textPanel.setLocation(0,590);
		textPanel.setOpaque(false);
		textPanel.add(text);
		
		add(info);
		add(panel);
		add(textPanel);
		
		text.setVisible(false);
	}
	
	public void setKeyListeners (KeyListener l){
		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)
				map[i][j].addKeyListener(l);
	}
	
	public void setListeners (ActionListener l){
		options.addActionListener(l);
	}
	
	public void setMouseListeners (MouseListener ml){
		options.addMouseListener(ml);
	}
	
	public JButton [][] getMap (){		//TODO : Add setters for specific attributes (Check controller)
		return map;
	}
	
	public JLabel getPlayerName(){
		return playerName;
	}
	
	public JLabel getFighterName (){
		return fighterName;
	}
	
	public JLabel getFighterLevel (){
		return fighterLevel;
	}
	
	public JLabel getSenzuBeans (){
		return SenzuBeans;
	}
	
	public JLabel getDragonBalls (){
		return DragonBalls;
	}
	
	public JPanel getPanel (){
		return panel;
	}
	
	public TextPlay getTextPlay (){
		return text;
	}
	
	public void setTextText (String text){
		this.text.setDisplay(text);
	}
	
	public void setTextTimer (int milliseconds){
		text.setTimerTime(milliseconds);
	}
	
	public void startTextTimer (){
		text.getTimer().start();
	}
	
	public void clearText(){
		text.setText("");
		text.setDisplay("");
	}
	
	public void setTextVisibility (boolean f){
		text.setVisible(f);
	}
	
	public static void main(String[] args) {
		WorldView wv = new WorldView();
		JFrame f = new JFrame("Test Frame");
		f.setSize(1366 ,768);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(wv);
		f.setVisible(true);
	}
}
