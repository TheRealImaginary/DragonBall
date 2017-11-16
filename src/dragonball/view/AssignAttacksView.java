package dragonball.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
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

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.PlayableFighter;

public class AssignAttacksView extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	
	private JPanel superAttacks, ultimateAttacks, options, numbers;
	private JLabel choseSuper, choseUltimate, choseSuperReplace, choseUltimateReplace, numSuper, numUltimate;
	
	private JComboBox<String> fighters, sAttacks, oldSuper, UltAttacks, oldUlt;
	private JButton assignSuper, assignUltimate, Back;			//Cannot add same button to different panels
	
	
	public AssignAttacksView (){
		super(BACKGROUND);
		setSize(X, Y);
		
		registerFonts();
		
		options = new JPanel();
		options.setSize(300,100);
		options.setLocation(275, 10);
		options.setOpaque(false);
		
		fighters = new JComboBox<>();
		
		//Back = new JButton("Back");
		Back = new JButton("<html><b><font color=#ffff00><u>B</u>ack</font></b>");
		Back.setContentAreaFilled(false);
		Back.setBorderPainted(false);
		Back.setFont(new Font("Saiyan Sans", Font.BOLD, 22));
		Back.setToolTipText("Back To Previous Menu");
		Back.setName("Back");
		
		options.add(fighters);
		options.add(Back);
		
		add(options);
		
		superAttacks = new JPanel();
		superAttacks.setSize(475,300);
		superAttacks.setLocation(10,175);
		superAttacks.setOpaque(false);
		
		choseSuper = new JLabel("Please Choose a Super Attack!");
		choseSuper.setFont(new Font("Saiyan Sans", Font.PLAIN, 25));
		superAttacks.add(choseSuper);
		
		sAttacks = new JComboBox<>();
		superAttacks.add(sAttacks);
		
		choseSuperReplace = new JLabel("Please Choose a Super Attack To Replace (Could be Empty)!");
		choseSuperReplace.setFont(new Font("Saiyan Sans", Font.PLAIN, 20));
		superAttacks.add(choseSuperReplace);
		
		oldSuper = new JComboBox<>();
		superAttacks.add(oldSuper);
		
		//assignSuper = new JButton("Assign Super Attack");
		assignSuper = new JButton("<html><b><font color=#ff0000><u>A</u>ssign Super Attack</font></b>");
		assignSuper.setContentAreaFilled(false);
		assignSuper.setBorderPainted(false);
		assignSuper.setFont(new Font("Saiyan Sans", Font.BOLD, 22));
		assignSuper.setToolTipText("Assign Selected Super Attack To Selected Fighter");
		assignSuper.setName("Assign Super Attack");
		
		superAttacks.add(assignSuper);
		
		add(superAttacks);
		
		ultimateAttacks = new JPanel();
		ultimateAttacks.setSize(480,300);
		ultimateAttacks.setLocation(475,175);
		ultimateAttacks.setOpaque(false);
		
		choseUltimate = new JLabel("Please Choose an Ultimate Attack!");
		choseUltimate.setFont(new Font("Saiyan Sans", Font.PLAIN, 25));
		ultimateAttacks.add(choseUltimate);
		
		UltAttacks = new JComboBox<>();
		ultimateAttacks.add(UltAttacks);
		
		choseUltimateReplace = new JLabel("Plase Choose an Ultimate Attack To Replace (Could be Empty)!");
		choseUltimateReplace.setFont(new Font("Saiyan Sans", Font.PLAIN, 20));
		ultimateAttacks.add(choseUltimateReplace);
		
		oldUlt = new JComboBox<>();
		ultimateAttacks.add(oldUlt);
		
		//assignUltimate = new JButton("Assign Ultimate Attack");
		assignUltimate = new JButton("<html><b><font color=#ff0000><u>A</u>ssign Ultimate Attack</font></b>");
		assignUltimate.setContentAreaFilled(false);
		assignUltimate.setBorderPainted(false);
		assignUltimate.setFont(new Font("Saiyan Sans", Font.BOLD, 22));
		assignUltimate.setToolTipText("Assign Selected Super Attack To Selected Fighter");
		assignUltimate.setName("Assign Ultimate Attack");
		
		ultimateAttacks.add(assignUltimate);
		
		add(ultimateAttacks);
		
		numbers = new JPanel();
		numbers.setSize(400,150);
		numbers.setLocation(550, 10);
		numbers.setOpaque(false);
		
		numSuper = new JLabel();
		numUltimate = new JLabel();
		
		numSuper.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 10));
		numUltimate.setFont(new Font("Pokemon GB", Font.CENTER_BASELINE, 10));
		
		numbers.add(numSuper);
		numbers.add(numUltimate);
		
		add(numbers);
	}
	
	public int getOldSuperIndex (){
		return oldSuper.getSelectedIndex();
	}
	
	public int getOldUltimateIndex (){
		return oldUlt.getSelectedIndex();
	}
	
	public int getSuperAttackIndex (){
		return sAttacks.getSelectedIndex();
	}
	
	public int getUltimateAttackIndex (){
		return UltAttacks.getSelectedIndex();
	}
	
	public int getSelectedFighter (){
		return fighters.getSelectedIndex();
	}
	
	public void setSuperNumber (int x){
		numSuper.setText("Current Super Attacks Number : " + x);
	}
	
	public void setUltimateNumber (int x){
		numUltimate.setText("Current Ultimate Attacks Number : " + x);
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
	
	public void setFighters (ArrayList<PlayableFighter> pf){
		fighters.removeAllItems();
		for(PlayableFighter f : pf){
			fighters.addItem(f.getName() + " " + f.getClass().getSimpleName());
		}
	}

	public void setSuperAttacks (ArrayList<SuperAttack> Super){
		sAttacks.removeAllItems();
		for(SuperAttack s : Super){
			sAttacks.addItem(s.getName() + " " + s.getClass().getSimpleName());
		}
	}
	
	public void setUltimateAttacks (ArrayList<UltimateAttack> ultimates){
		UltAttacks.removeAllItems();
		for(UltimateAttack u : ultimates){
			UltAttacks.addItem(u.getName() + " " + u.getClass().getSimpleName());
		}
	}
	
	public void setCurretSuperAndUltimates (ArrayList<SuperAttack> Super, ArrayList<UltimateAttack> ult){
		oldSuper.removeAllItems();
		oldUlt.removeAllItems();
		
		oldSuper.addItem("Empty");
		oldUlt.addItem("Empty");
		
		for(SuperAttack s : Super){
			oldSuper.addItem(s.getName() + " " + s.getClass().getSimpleName());
		}
		for(UltimateAttack u : ult){
			oldUlt.addItem(u.getName() + " " + ult.getClass().getSimpleName());
		}
	}
	
	public void setListener (ActionListener l){
		assignSuper.addActionListener(l);
		assignUltimate.addActionListener(l);
		Back.addActionListener(l);
	}
	
	public void setItemListener (ItemListener il){
		fighters.addItemListener(il);
	}
	
	public void setMouseListeners (MouseListener ml){
		Back.addMouseListener(ml);
		assignSuper.addMouseListener(ml);
		assignUltimate.addMouseListener(ml);
	}
	
	public static void main(String[] args) {
		AssignAttacksView test = new AssignAttacksView();
		JFrame f = new JFrame("TEST");
		f.setSize(1366, 768);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(test);
		f.setVisible(true);
	}

}
