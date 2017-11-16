package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DragonView extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("Shenron.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JButton senzuBeans, AbilityPoints, UltimateAttack, SuperAttack;
	private JPanel panel;
	
	private TextPlay text = new TextPlay();
	
	public DragonView (){
		super(BACKGROUND);
		setSize(X,Y);
		
		panel = new JPanel();
		panel.setSize(400, 400);
		panel.setLocation(-10, 450);
		//panel.setLayout(new GridLayout(0, 1, 0, 5));
		panel.setOpaque(false);
		
		registerFont();
		
		senzuBeans = new JButton("Senzu Beans");
		senzuBeans.setName("Senzu Beans");
		senzuBeans.setToolTipText("Wish For An Amount Of Senzu Beans");
		senzuBeans.setFont(new Font("Saiyan Sans", Font.ITALIC, 40)); //25  35
		//senzuBeans.setForeground(Color.BLUE);
		cleanButton(senzuBeans);
		panel.add(senzuBeans);
		
		AbilityPoints = new JButton("Ability Points");
		AbilityPoints.setName("Ability Points");
		AbilityPoints.setToolTipText("Wish For An Amount Of Ability Points");
		AbilityPoints.setFont(new Font("Saiyan Sans", Font.ITALIC, 40)); //23   35
		cleanButton(AbilityPoints);
		panel.add(AbilityPoints);
		
		SuperAttack = new JButton("Super Attack");
		SuperAttack.setName("Super Attack");
		SuperAttack.setToolTipText("Wish For A Random Super Attack");
		SuperAttack.setFont(new Font("Saiyan Sans", Font.ITALIC, 40)); //24  35
		cleanButton(SuperAttack);
		panel.add(SuperAttack);
		
		UltimateAttack = new JButton("Ultimate Attack");
		UltimateAttack.setName("Ultimate Attack");
		UltimateAttack.setToolTipText("Wish For A Random Ultimate Attack");
		UltimateAttack.setFont(new Font("Saiyan Sans", Font.ITALIC, 40)); //20    35
		cleanButton(UltimateAttack);
		panel.add(UltimateAttack);
		
		add(panel);
		
		JPanel textPanel = new JPanel();
		textPanel = new JPanel();
		textPanel.setSize(1366, 200);
		textPanel.setLocation(0,590);
		textPanel.setOpaque(false);
		textPanel.add(text);
		
		add(textPanel);
	}
	
	public void setTextText (String s){
		text.setDisplay(s);
	}
	
	public void startTimer (){
		System.err.println(22);
		text.getTimer().start();
	}
	
	public void setTextVisibility (boolean f){
		text.setVisible(f);
	}
	
	private final void registerFont (){
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Saiyan-Sans.ttf")));
		}
		catch (IOException | FontFormatException e){
			e.printStackTrace();
		}
	}
	
	private void cleanButton (JButton b){
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setForeground(Color.GREEN);
	}
	
	public void setActionListeners (ActionListener l){
		senzuBeans.addActionListener(l);
		AbilityPoints.addActionListener(l);
		SuperAttack.addActionListener(l);
		UltimateAttack.addActionListener(l);
	}
	
	public void setMouseListeners (MouseListener ml){
		senzuBeans.addMouseListener(ml);
		AbilityPoints.addMouseListener(ml);
		SuperAttack.addMouseListener(ml);
		UltimateAttack.addMouseListener(ml);
	}
	
	public JButton getSenzuBeans() {
		return senzuBeans;
	}

	public JButton getAbilityPoints() {
		return AbilityPoints;
	}

	public JButton getUltimateAttack() {
		return UltimateAttack;
	}

	public JButton getSuperAttack() {
		return SuperAttack;
	}

	public static void main(String[] args) {
		JFrame test = new JFrame("Testing");
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(1366, 768);
		test.setVisible(true);
		test.add(new DragonView());
		test.revalidate();
	}

}
