package dragonball.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.model.character.fighter.PlayableFighter;

public class SwitchFighterView extends JLabel {
	
	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	
	private JComboBox<String> fighters;
	private JButton back, Switch;
	private JPanel panel;
	
	public SwitchFighterView (){
		super(BACKGROUND);
		setSize(1366,768);
		
		registerFonts();
		
		this.fighters = new JComboBox<>();
		this.fighters.setLocation(100, 50);
		this.fighters.setSize(210, 30);
		
		//setFighters(fighters);
		
		panel = new JPanel();
		panel.setSize(350,200);
		panel.setLocation(60, 35);
		panel.setOpaque(false);
		//panel.setLayout(null);					//Bad idea, but I like to live dangerously
	
		//Switch = new JButton("Switch");
		Switch = new JButton("<html><b><font color=#ff0000><u>S</u>witch</font></b>");
		Switch.setContentAreaFilled(false);
		Switch.setBorderPainted(false);
		Switch.setFont(new Font("Saiyan Sans", Font.BOLD, 25));
		Switch.setToolTipText("Switch To Currently Selected Fighter");
		Switch.setName("Switch");
		//Switch.setLocation(100, 100);
		//Switch.setSize(100,30);
		
		//back = new JButton("Back");
		back = new JButton("<html><b><font color=#ffff00><u>B</u>ack</font></b>");
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setFont(new Font("Saiyan Sans", Font.BOLD, 25));
		back.setToolTipText("Back To Previous Menu");
		back.setName("Back");
		//back.setLocation(210, 100);
		//back.setSize(100,30);
		
		JLabel label = new JLabel("Please choose a fighter");
		label.setFont(new Font("Saiyan Sans", Font.CENTER_BASELINE, 30));
		label.setLocation(110,10);
		label.setSize(300,15);
		
		panel.add(label);
		panel.add(this.fighters);
		panel.add(Switch);
		panel.add(back);
		
		add(panel);
	}
	
	private void registerFonts (){
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Pokemon GB.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Saiyan-Sans.ttf")));
		}
		catch (IOException | FontFormatException e){
			e.printStackTrace();
		}
	}
	
	public void setFighters (ArrayList<PlayableFighter> fighters){
		//Vector<String> vector = new Vector<>();
		//panel.remove(this.fighters);
		//panel.invalidate();
		//panel.validate();
		this.fighters.removeAllItems();
		//System.err.println(fighters.size() + " SIZE" );
		for(PlayableFighter pf : fighters){
			this.fighters.addItem(pf.getName() + " " + pf.getClass().getSimpleName());
			//temp.addItem(pf.getName() + " " + pf.getClass().getSimpleName());
			//vector.add(pf.getName() + " " + pf.getClass().getSimpleName());
		}
		//this.fighters = new JComboBox<String>(vector);
		//panel.add(this.fighters);
		//this.fighters = temp;
		//temp = null;
	}
	
	public JPanel getPanel (){
		return panel;
	}
	
	public JComboBox<String> getFighters (){
		return fighters;
	}
	
	public void setListeners (ActionListener l){
		Switch.addActionListener(l);
		back.addActionListener(l);
	}
	
	public void setMouseListener (MouseListener ml){
		Switch.addMouseListener(ml);
		back.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		SwitchFighterView test = new SwitchFighterView();
		JFrame f = new JFrame("TEST");
		f.setSize(1366, 768);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(test);
		f.setVisible(true);
		
		
	}

}
