package dragonball.view;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateFighter extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JPanel main, btn;
	private JLabel chooseFighter, enterName;
	private JTextField fighterName;
	private JButton create ,back;
	private JComboBox<String> fighters;
	
	public CreateFighter(){
		super(BACKGROUND);
		setSize(X, Y);
		
		registerFonts();
		
		main = new JPanel();
		main.setOpaque(false);
		//main.setLayout(new BorderLayout());
		main.setSize(300,300);
		//main.setLocation(X / 16, Y / 22);
		main.setLocation(150,150);
		
		enterName = new JLabel("Please enter fighter name");
		enterName.setFont(new Font("Saiyan Sans", Font.PLAIN, 30));
		main.add(enterName);
		
		fighterName = new JTextField(20);
		main.add(fighterName);
		
		chooseFighter = new JLabel("Please choose a fighter");
		chooseFighter.setFont(new Font("Saiyan Sans", Font.PLAIN, 30));
		main.add(chooseFighter);
		
		fighters = new JComboBox<>();
		fighters.addItem("Earthling");
		fighters.addItem("Saiyan");
		fighters.addItem("Namekian");
		fighters.addItem("Frieza");
		fighters.addItem("Majin");
		main.add(fighters);
		
		btn = new JPanel();
		btn.setOpaque(false);
		
		//create = new JButton("Create");
		create = new JButton("<html><b><font color=#ff0000><u>C</u>reate</font></b>");
		create.setContentAreaFilled(false);
		create.setBorderPainted(false);
		create.setFont(new Font("Saiyan Sans", Font.BOLD, 25));
		create.setToolTipText("Create A New Fighter");
		create.setName("Create");
		
		btn.add(create);
		
		//back = new JButton("Back");
		back = new JButton("<html><b><font color=#ffff00><u>B</u>ack</font></b>");
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setFont(new Font("Saiyan Sans", Font.BOLD, 25));
		back.setToolTipText("Create A New Fighter");
		back.setName("Back");
		
		btn.add(back);
		
		main.add(btn);
		
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
	
	public JPanel getBtn() {
		return btn;
	}

	public JButton getCreate() {
		return create;
	}

	public JButton getBack() {
		return back;
	}

	public JComboBox<String> getFighters() {
		return fighters;
	}

	public String getFighterName (){
		return fighterName.getText();
	}
	
	public void setListeners (ActionListener l){
		back.addActionListener(l);
		create.addActionListener(l);
	}
	
	public void setMouseListener (MouseListener ml){
		create.addMouseListener(ml);
		back.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		JFrame test = new JFrame("Testing");
		test.setSize(1366, 768);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(new CreateFighter());
		test.setVisible(true);
	}

}
