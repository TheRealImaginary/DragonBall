package dragonball.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.PlayableFighter;

public class AssignAttacks extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("dragon-ball-super-wallpaper-3.jpg");

	private JComboBox<String> fighters, equippedsupers, availablesupers, equippedultimates, availableultimates;
	private JButton superReplace, superAdd, ultimateReplace, ultimateAdd, back;
	private JPanel panel;

	public AssignAttacks() {
		super(BACKGROUND);
		setSize(1366, 768);

		this.fighters = new JComboBox<>();
		this.fighters.setLocation(100, 50);
		this.fighters.setSize(210, 30);

		this.equippedsupers = new JComboBox<>();
		this.equippedsupers.setLocation(100, 250);
		this.equippedsupers.setSize(210, 30);

		this.availablesupers = new JComboBox<>();
		this.availablesupers.setLocation(200, 250);
		this.availablesupers.setSize(210, 30);

		this.equippedultimates = new JComboBox<>();
		this.equippedultimates.setLocation(100, 350);
		this.equippedultimates.setSize(210, 30);

		this.availableultimates = new JComboBox<>();
		this.availableultimates.setLocation(200, 350);
		this.availableultimates.setSize(210, 30);

		superReplace = new JButton("Replace super attack");
		superReplace.setLocation(300, 250);
		superReplace.setSize(100, 30);

		superAdd = new JButton("Add super attack");
		superAdd.setLocation(400, 250);
		superAdd.setSize(100, 30);

		ultimateReplace = new JButton("Replace ultimate attack");
		ultimateReplace.setLocation(300, 350);
		ultimateReplace.setSize(100, 30);

		ultimateAdd = new JButton("Add ultimate attack");
		ultimateAdd.setLocation(400, 350);
		ultimateAdd.setSize(100, 30);

		back = new JButton("Back");
		back.setLocation(100, 300);
		back.setSize(100, 30);

		JLabel label = new JLabel("Please Assign Attacks");
		label.setLocation(110, 10);
		label.setSize(300, 15);

		panel = new JPanel();
		panel.setSize(350, 200);
		panel.setLocation(60, 35);
		panel.setOpaque(false);

		panel.add(label);
		panel.add(this.fighters);
		panel.add(this.equippedsupers);
		panel.add(this.availablesupers);
		panel.add(this.equippedultimates);
		panel.add(this.availableultimates);
		panel.add(this.superReplace);
		panel.add(this.superAdd);
		panel.add(this.ultimateReplace);
		panel.add(this.ultimateAdd);
		panel.add(this.back);

		add(panel);
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters.removeAllItems();
		//System.err.println(fighters.size() + " SIZE");
		for (PlayableFighter pf : fighters) {
			this.fighters.addItem(pf.getName() + " " + pf.getClass().getSimpleName());
		}
	}

	public void setequippedsupers(ArrayList<SuperAttack> currentsupers) {
		this.equippedsupers.removeAllItems();
		//System.err.println(currentsupers.size() + " SIZE");
		for (SuperAttack pf : currentsupers) {
			this.equippedsupers.addItem(pf.getName());
		}
	}

	public void setavailablesupers(ArrayList<SuperAttack> unlockedsupers) {
		this.availablesupers.removeAllItems();
		//System.err.println(unlockedsupers.size() + " SIZE");
		for (SuperAttack pf : unlockedsupers) {
			this.availablesupers.addItem(pf.getName());
		}
	}

	public void setequippedultimates(ArrayList<UltimateAttack> currentultimates) {
		this.equippedultimates.removeAllItems();
		//System.err.println(currentultimates.size() + " SIZE");
		for (UltimateAttack pf : currentultimates) {
			this.equippedultimates.addItem(pf.getName());
		}
	}

	public void setavailableultimates(ArrayList<UltimateAttack> unlockedultimates) {
		this.availableultimates.removeAllItems();
		//System.err.println(unlockedultimates.size() + " SIZE");
		for (UltimateAttack pf : unlockedultimates) {
			this.availableultimates.addItem(pf.getName());
		}
	}

	public void setListeners(ActionListener l) {
		superReplace.addActionListener(l);
		superAdd.addActionListener(l);
		ultimateReplace.addActionListener(l);
		ultimateAdd.addActionListener(l);
		back.addActionListener(l);
	}

	public void setMouseListener(MouseListener ml) {
		superReplace.addMouseListener(ml);
		superAdd.addMouseListener(ml);
		ultimateReplace.addMouseListener(ml);
		ultimateAdd.addMouseListener(ml);
		back.addMouseListener(ml);
	}

	public static void main(String[] args) {
		AssignAttacks test = new AssignAttacks();
		JFrame f = new JFrame("TEST");
		f.setSize(1366, 768);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(test);
		f.setVisible(true);

	}

}
