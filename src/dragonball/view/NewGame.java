package dragonball.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewGame extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JPanel panel;
	private JLabel playerNameLabel, fighterNameLabel;
	private JTextField playerName, fighterName;
	private JComboBox<String> fighters;
	private JButton ready, back;
	
	public NewGame (){
		super(BACKGROUND);
		setSize(X,Y);
		
		registerFonts();
		
		panel = new JPanel();
		//panel.setLayout(new GridLayout(0, 1));
		panel.setSize(400,300);
		panel.setOpaque(false);
		panel.setLocation(X >> 4, Y >> 3);			//Equivalent to divide by 16 and divide by 8
		panel.setVisible(true);
		
		playerNameLabel = new JLabel("Enter player name");
		playerNameLabel.setFont(new Font("Saiyan Sans", Font.PLAIN, 30));
		panel.add(playerNameLabel);
		
		playerName = new JTextField(20);
		panel.add(playerName);
		
		fighterNameLabel = new JLabel("Enter fighter name");
		fighterNameLabel.setFont(new Font("Saiyan Sans", Font.PLAIN, 30));
		panel.add(fighterNameLabel);
		
		fighterName = new JTextField(20);
		panel.add(fighterName);
		
		fighters = new JComboBox<>();
		fighters.addItem("Earthling");
		fighters.addItem("Saiyan");
		fighters.addItem("Namekian");
		fighters.addItem("Frieza");
		fighters.addItem("Majin");
		panel.add(fighters);
		
//		ready = new JButton("Done");
		ready = new JButton("<html><b><font color=#ffff00><u>R</u>eady</font></b>");
		ready.setToolTipText("Let's Start The Game");
		ready.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		ready.setContentAreaFilled(false);
		ready.setBorderPainted(false);
		ready.setName("Done");
		ready.setSize(150,150);
		panel.add(ready);
		
//		back = new JButton("Back");
		back = new JButton("<html><b><font color=#ff0000><u>B</u>ack</font></b>");
		back.setToolTipText("Back To Previous Menu");
		back.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setName("Back");
		back.setSize(150,150);
		panel.add(back);
		
		add(panel);
		
		//System.err.println(fighters.getSelectedItem());
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
	
	public JTextField getPlayername() {
		return playerName;
	}
	
	public JTextField getFighterName (){
		return fighterName;
	}

	public JComboBox<String> getFighters() {
		return fighters;
	}

	public JButton getReady() {
		return ready;
	}

	public JButton getBack(){
		return back;
	}
	
	public boolean AreFieldsEmpty (){
		return playerName.getText().isEmpty() || fighterName.getText().isEmpty();
	}
	
	public void setMouseListeners (MouseListener ml){
		ready.addMouseListener(ml);
		back.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		NewGame ng = new NewGame();
		JFrame f = new JFrame("Test Frame");
		f.setSize(800, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(ng);
		f.setVisible(true);
		
	}

}
