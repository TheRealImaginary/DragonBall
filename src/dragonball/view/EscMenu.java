package dragonball.view;

import java.awt.FlowLayout;
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

public class EscMenu extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static int X = 1366;
	private static int Y = 768;
	
	private JButton switchFighter, createNewFighter, AssignAttacks, UpgradeFighter, Resume, Save_and_Quit, mute_sounds;  //Assuming we will have music
	private JPanel panel;
	
	public EscMenu (){
		super(BACKGROUND);
		setSize(X,Y);
		
		registerFonts();
		
		panel = new JPanel();
		panel.setSize(300,300);
		panel.setLocation(X / 16, Y / 22);
		panel.setLayout(new GridLayout(0, 1));
		panel.setOpaque(false);
		
		//switchFighter = new JButton("Switch Fighter");
		switchFighter = new JButton("<html><b><font color=#ffff00><u>S</u>witch</font><font color=#ff0000> Fighter</font></b>");
		switchFighter.setToolTipText("Toggle Between Fighters And Switch Between Them");
		switchFighter.setName("Switch Fighter");
		switchFighter.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		switchFighter.setContentAreaFilled(false);
		switchFighter.setBorderPainted(false);
	
		//createNewFighter = new JButton("Create New Fighter");
		createNewFighter = new JButton("<html><b><font color=#ffff00><u>C</u>reate</font><font color=#ff0000> New</font><font color=#ff0000> Fighter</font></b>");
		createNewFighter.setToolTipText("Create A New Fighter");
		createNewFighter.setName("Create New Fighter");
		createNewFighter.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		createNewFighter.setContentAreaFilled(false);
		createNewFighter.setBorderPainted(false);
		
		//AssignAttacks = new JButton("Assign Attacks");
		AssignAttacks = new JButton("<html><b><font color=#ffff00><u>A</u>ssign</font><font color=#ff0000> Attacks</font></b>");
		AssignAttacks.setToolTipText("Assign New Attacks To Any Fighter");
		AssignAttacks.setName("Assign Attacks");
		AssignAttacks.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		AssignAttacks.setContentAreaFilled(false);
		AssignAttacks.setBorderPainted(false);
		
		//UpgradeFighter = new JButton("Upgrade Fighter");
		UpgradeFighter = new JButton("<html><b><font color=#ffff00><u>U</u>pgrade</font><font color=#ff0000> Fighter</font></b>");
		UpgradeFighter.setToolTipText("Upgrade Your Fighters");
		UpgradeFighter.setName("Upgrade Fighter");
		UpgradeFighter.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		UpgradeFighter.setContentAreaFilled(false);
		UpgradeFighter.setBorderPainted(false);
		
		//Resume = new JButton("Resume");
		Resume = new JButton("<html><b><font color=#ffff00><u>R</u>esume</font>");
		Resume.setToolTipText("Return To Current Game");
		Resume.setName("Resume");
		Resume.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		Resume.setContentAreaFilled(false);
		Resume.setBorderPainted(false);
		
		//Save_and_Quit = new JButton("Save and Quit");
		Save_and_Quit = new JButton("<html><b><font color=#ffff00><u>S</u>ave</font><font color=#ff0000> And</font><font color=#ff0000> Quit</font></b>");
		Save_and_Quit.setToolTipText("Save And Quit Current Game");
		Save_and_Quit.setName("Save and Quit");
		Save_and_Quit.setFont(new Font("Saiyan Sans", Font.ITALIC, 30));
		Save_and_Quit.setContentAreaFilled(false);
		Save_and_Quit.setBorderPainted(false);
		
		panel.add(switchFighter);
		panel.add(createNewFighter);
		panel.add(AssignAttacks);
		panel.add(UpgradeFighter);
		panel.add(Resume);
		panel.add(Save_and_Quit);
		
		add(panel);
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
	
	public JButton getSwitchFighter() {
		return switchFighter;
	}

	public JButton getCreateNewFighter() {
		return createNewFighter;
	}

	public JButton getAssignAttacks() {
		return AssignAttacks;
	}

	public JButton getUpgradeFighter() {
		return UpgradeFighter;
	}

	public JButton getResume() {
		return Resume;
	}

	public JButton getSave_and_Quit() {
		return Save_and_Quit;
	}

	public JButton getMute_sounds() {
		return mute_sounds;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setListeners (ActionListener l){
		switchFighter.addActionListener(l);
		createNewFighter.addActionListener(l);
		AssignAttacks.addActionListener(l);
		UpgradeFighter.addActionListener(l);
		Resume.addActionListener(l);
		Save_and_Quit.addActionListener(l);
		//mute_sounds.addActionListener(l);
	}

	public void setMouseListeners (MouseListener ml){
		switchFighter.addMouseListener(ml);
		createNewFighter.addMouseListener(ml);
		AssignAttacks.addMouseListener(ml);
		UpgradeFighter.addMouseListener(ml);
		Resume.addMouseListener(ml);
		Save_and_Quit.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		EscMenu em = new EscMenu();
		JFrame f = new JFrame("Test Frame");
		f.setSize(800, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(em);
		f.setVisible(true);
	}

}
