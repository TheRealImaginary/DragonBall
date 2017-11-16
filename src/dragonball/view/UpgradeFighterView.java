package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
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
import javax.swing.JTextArea;

import dragonball.model.character.fighter.PlayableFighter;

public class UpgradeFighterView extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JLabel points, hp, stamina, ki, blast, physical;
//	private JTextArea points;
	private JButton upgrade, back;
	private JComboBox<String> fighters, attributes;
	private JPanel main, panel, p, upward, info;
	
	public UpgradeFighterView (){
		super(BACKGROUND);
		setSize(X,Y);
		
		registerFonts();
		
		panel = new JPanel();
		panel.setOpaque(false);
		
		//upgrade = new JButton("Upgrade");
		upgrade = new JButton("<html><b><font color=#ff0000><u>U</u>pgrade</font></b>");
		upgrade.setContentAreaFilled(false);
		upgrade.setBorderPainted(false);
		upgrade.setFont(new Font("Saiyan Sans", Font.BOLD, 20));
		upgrade.setToolTipText("Upgrade Current Fighter With Selected Attributes");
		upgrade.setName("Upgrade");
		
		//back = new JButton("Back");
		back = new JButton("<html><b><font color=#ffff00><u>B</u>ack</font></b>");
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setFont(new Font("Saiyan Sans", Font.BOLD, 20));
		back.setToolTipText("Back To Previous Menu");
		back.setName("Back");
		
		fighters = new JComboBox<>();
		
		attributes = new JComboBox<>();
		attributes.addItem("Healh Points");
		attributes.addItem("Blast Damage");
		attributes.addItem("Physical Damage");
		attributes.addItem("Stamina");
		attributes.addItem("Ki");
		
		panel.add(fighters);
		panel.add(attributes);
		
		p = new JPanel();
		p.setOpaque(false);
		p.add(upgrade);
		p.add(back);
		
		upward = new JPanel();
		
		JLabel lb1 = new JLabel("Please Choose A Fighter!");
		JLabel lb2 = new JLabel("Please Choose An Attribute!");
		
		upward.add(lb1);
		upward.add(lb2);
		upward.setOpaque(false);
		
		points = new JLabel(new ImageIcon("AbilityPointsTest.resized.png"));
		points.setToolTipText("Fighter's Ability Points");
		points.setHorizontalTextPosition(JLabel.RIGHT);
		points.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		hp = new JLabel(new ImageIcon("HealthPoints.jpg"));
		hp.setToolTipText("Fighter's Max Health Points");
		hp.setHorizontalAlignment(JLabel.RIGHT);
		hp.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		blast = new JLabel(new ImageIcon("BlastDamage.png"));
		blast.setToolTipText("Fighter's Blast Damage");
		blast.setHorizontalAlignment(JLabel.RIGHT);
		blast.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		physical = new JLabel(new ImageIcon("PhysicalDamage.png"));
		physical.setToolTipText("Fighter's Physical Damage");
		physical.setHorizontalAlignment(JLabel.RIGHT);
		physical.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		stamina = new JLabel(new ImageIcon("Stamina.png"));
		stamina.setToolTipText("Fighter's Stamina");
		stamina.setHorizontalAlignment(JLabel.RIGHT);
		stamina.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		ki = new JLabel(new ImageIcon("Ki.png"));
		ki.setToolTipText("Fighter's Ki");
		ki.setHorizontalAlignment(JLabel.RIGHT);
		ki.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
//		points = new JTextArea();
//		points.setEditable(false);
//		points.setToolTipText("Ability Points");
//		points.setOpaque(false);
//		points.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 20));
		
		info = new JPanel();
		info.setLayout(new GridLayout(0, 1));
		info.setOpaque(false);
		
		info.add(hp);
		info.add(blast);
		info.add(physical);
		info.add(stamina);
		info.add(ki);
		info.add(points);
		
		main = new JPanel();
		main.setSize(750,400);
		main.setLocation(-70, 200);
		main.setOpaque(false);
		main.setLayout(new BorderLayout());
		main.add(panel);
		main.add(info, BorderLayout.EAST);
		//main.add(points, BorderLayout.EAST);
		main.add(upward,BorderLayout.NORTH);
		main.add(p,BorderLayout.SOUTH);
		
		add(main);
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

	
	public JComboBox<String> getFighters(){
		return fighters;
	}
	
	public void setHealthPoints (int x){
		hp.setText(":" + x);
	}
	
	public void setBlastDamage (int x){
		blast.setText(": " + x);
	}
	
	public void setPhysicalDamage (int x){
		physical.setText(": " + x);
	}
	
	public void setStamina (int x){
		stamina.setText(":   " + x);
	}
	
	public void setKi (int x){
		ki.setText(":   " + x);
	}
	
	public void setPoints (int x){
		points.setText(": " + x);
		points.repaint();
		points.revalidate();
	}
	
	public JButton getUpgrade (){
		return upgrade;
	}
	
	public JButton getBack (){
		return back;
	}
	
	public int getFighterIndex (){
		return fighters.getSelectedIndex();
	}
	
	public void setFighters (ArrayList<PlayableFighter> fighters){
		this.fighters.removeAllItems();
		for(PlayableFighter pf : fighters){
			this.fighters.addItem(pf.getName() + " " + pf.getClass().getSimpleName());
		}
	}
	
	public char getAttribute (){
		return ((String)attributes.getSelectedItem()).charAt(0);
	}
	
	public void setItemListener (ItemListener il){
		fighters.addItemListener(il);
	}
	
	public void setListener (ActionListener l){
		upgrade.addActionListener(l);
		back.addActionListener(l);
	}
	
	public void setMouseListener (MouseListener ml){
		upgrade.addMouseListener(ml);
		back.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		JFrame test = new JFrame("TEST");
		test.setSize(1366, 768);
		test.add(new UpgradeFighterView());
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

}
