package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class BattleView extends JLabel {

	private static final ImageIcon BACKGROUND = new ImageIcon("BattleView.resized.resized.jpg");
	private static final int X = 1366;
	private static final int Y = 768;
	private static final Color GREEN = Color.GREEN;
	private static final Color RED = Color.RED;

	private int wp, hp;					//player width and height
	private int we, he;					//enemy width and height
	private int xp, yp, xe, ye, se;			//player x, y ... enemy x, y
	private final int WIDTH, HEIGHT;
	private int maxKI;
	private Shape playerHealth, enemyHealth, playerKi, enemyKi;
	private int playerKI, enemyKI;
	private int playerStamina, enemyStamina;
	private ImageIcon player, enemy = new ImageIcon("Goku.png");
	private JButton playerIcon;
	private TextPlay text;
	private JPanel textPanel;
	
	private JPanel options;
	private JButton attack, attack1, use, block;
	
	private JPanel attacks;
	private JComboBox<String> attacksList;
	
	private JPanel roundPanel;
	private JLabel round, PlayerHealth, EnemyHealth, MaxStaminaPlayer, MaxStaminaEnemy, MaxKiPlayer, MaxKiEnemy;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (wp <= WIDTH / 2) 
			g2.setColor(RED);
		else
			g2.setColor(GREEN);
		
		g2.fill(playerHealth);
		
		if(we <= WIDTH / 2)
			g2.setColor(RED);
		else
			g2.setColor(GREEN);
		
		g2.fill(enemyHealth);
		
		g2.setColor(Color.MAGENTA);
		drawKi(g2);
		
		g2.setColor(Color.BLUE);
		drawStaminaDiscrete(g2);
		//drawStamina(g2);
		
		g2.setColor(Color.black);
		g2.fillOval(600, 10, 100, 100);
	}

	//Discrete
	private void drawStaminaDiscrete (Graphics2D g2){
		for(int i = 0;i < enemyStamina;i++)
			g2.fill3DRect(10 + (i * 50), ye + 30, 40, 20, true);
		for(int i = 0;i < playerStamina;i++)
			g2.fill3DRect(1260 - (i * 50), yp + 30, 40, 20, true);
	}
	
	//COntineous
	private void drawStamina (Graphics2D g2){		//TODO : Values instead of hard coded ones
		g2.fill3DRect(xp, yp + 30, enemyStamina, 10, true);			//Enemy
		
		g2.fill3DRect(se, ye + 30, playerStamina, 10, true);			//Player
	}
	
	private void drawKi (Graphics2D g2){
		//int offset = maxKI - playerKI;		//To Align right ki
		for(int i = 0;i < playerKI;i++)		//g2.fillRect(10 + (i * 50), 70, 40, 20);	//1160 + (i * 50) + (offset * 50), 85, 40, 20, true
			g2.fill3DRect(1260 - (i * 50), 100, 40, 20, true);
		for(int i = 0;i < enemyKI;i++)		//g2.fillRect(1160 + (i * 50), 70, 40, 20);
			g2.fill3DRect(10 + (i * 50), 100, 40, 20, true);				//1160 + (i * 50),85, 40, 20, true
	}
	
	public void setHealthLeft (int x){
		wp = x;
		playerHealth = new Rectangle2D.Double(xp, yp, wp, hp);
	}
	
	public void setHealthRight (int x){
		we = x;
		xe = 800 + (500 - x);
		//xe += (500 - x);
		enemyHealth = new Rectangle2D.Double(xe, ye, we, he);
	}
	
	public void setStaminaRight (int x){
		playerStamina = x;
		se = 800 + (500 - x);
		//se += (500 - x);
	}
	
	public void setStaminaLeft (int x){
		enemyStamina = x;
	}
	
	public void decrease(int x) {
		wp -= x;
		playerHealth = new Rectangle2D.Double(xp, yp, wp, hp);
	}

	public void decreaseToRight(int x) {
		we -= x;
		xe += x;
		enemyHealth = new Rectangle2D.Double(xe, ye, we, he);
	}

	public BattleView() {
		super(BACKGROUND);
		setSize(X, Y);
		
		registerFonts();
		
		wp = we = WIDTH = 500;
		hp = he = HEIGHT = 20;
		
		//setPlayerStamina(3);
		//setEnemyStamina(3);
		//setPlayerStamina(500);			//For Contineous
		//setEnemyStamina(500);				//For Contineous
		
		setXPlayer(10);
		setYPlayer(40);
		playerHealth = new Rectangle2D.Double(xp, yp, wp, hp);
		
		se = 800;
		
		setXEnemy(800);
		setYEnemy(40);
		enemyHealth = new Rectangle2D.Double(xe, ye, we, he);
		
//		JButton b = new JButton("Player");
//		b.setLocation(300,100);
//		b.setSize(150,150);
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				decrease(50);
//				setStaminaLeft(300);
//				//decreaseToRight(50);
//				//setLeft(200);
//				repaint();
//			}
//		});
		
//		JButton b1 = new JButton("Enemy");
//		b1.setLocation(800,100);
//		b1.setSize(150,150);
//		b1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//decrease(50);
//				//decreaseToRight(50);
//				//setRight(200);
//				//setStaminaRight(300);
//				setStaminaRight(300);
//				repaint();
//				setTextText("You have met a foe, a battle has begun!");
//				setTextVisibility(true);
//				startTextTimer();
//			}
//		});
		playerIcon = new JButton();
		
		JButton enemyIcon = new JButton(enemy);
		
		playerIcon.setSize(575,400);
		playerIcon.setLocation(900, 275);
		playerIcon.setBorderPainted(false);
		playerIcon.setContentAreaFilled(false);
		
		enemyIcon.setSize(375,366);
		enemyIcon.setLocation(-100, 300);
		enemyIcon.setBorderPainted(false);
		enemyIcon.setContentAreaFilled(false);
		
		//add(b);
		//add(b1);
		
		add(enemyIcon);
		add(playerIcon);
		
		//setPlayerKI(3);
		//setEnemyKI(3);
		//maxKI = 10;
		
		text = new TextPlay();
		text.setVisible(false);
		textPanel = new JPanel();
		textPanel.setSize(1366, 200);
		textPanel.setLocation(0,590);
		textPanel.setOpaque(false);
		textPanel.add(text);
		
		add(textPanel);
		
		options = new JPanel();
		
		//attack = new JButton("Attack");
		attack = new JButton(new ImageIcon("Attack.png"));
		attack.setContentAreaFilled(false);
		attack.setBorderPainted(false);
		attack.setToolTipText("Choose an Attack and Use it");
		attack.setName("Attack");
		
		//block = new JButton("Block");
		block = new JButton(new ImageIcon("Block.jpg"));
		block.setContentAreaFilled(false);
		block.setBorderPainted(false);
		block.setToolTipText("Block Next Attack");
		block.setName("Block");
		
		//use = new JButton("Use");
		use = new JButton(new ImageIcon("senzubeans.png"));
		use.setContentAreaFilled(false);
		use.setBorderPainted(false);
		use.setToolTipText("Use a senzu bean");
		use.setName("Use");
		
		options.add(attack);
		options.add(block);
		options.add(use);
		
		options.setOpaque(false);
		options.setSize(200,130);
		options.setLocation(550,630);
		
		add(options);
		
		attacksList = new JComboBox<>();
		
		attacks = new JPanel();
		attacks.setSize(350,200);
		attacks.setLocation(550, 650);
		attacks.setOpaque(false);
		attacks.setVisible(false);
		
		
		attack1 = new JButton("Attack");
		attack1.setName("Attack");
		
		attacks.add(attacksList);
		attacks.add(attack1);
		
		add(attacks);
		
		roundPanel = new JPanel();
		roundPanel.setSize(300,50);
		roundPanel.setLocation(500, 35);
		roundPanel.setOpaque(false);
		
		round = new JLabel();
		round.setText("1");
		round.setForeground(Color.RED);
		round.setFont(new Font("Arial", Font.BOLD, 30));
		
		roundPanel.add(round);
		
		add(roundPanel);
		
		JPanel playerHealthPanel = new JPanel();
		playerHealthPanel.setSize(100,100);
		playerHealthPanel.setLocation(1000, 38);
		playerHealthPanel.setOpaque(false);
		
		PlayerHealth = new JLabel();
		playerHealthPanel.add(PlayerHealth);
		
		add(playerHealthPanel);
	
		JPanel enemyHealthPanel = new JPanel();
		enemyHealthPanel.setSize(100,100);
		enemyHealthPanel.setLocation(200, 38);
		enemyHealthPanel.setOpaque(false);
		
		EnemyHealth = new JLabel();
		enemyHealthPanel.add(EnemyHealth);
		
		add(enemyHealthPanel);
		
		MaxStaminaEnemy = new JLabel();
		MaxStaminaEnemy.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		MaxStaminaEnemy.setForeground(new Color(0, 0, 220));
		
		MaxKiEnemy = new JLabel();
		MaxKiEnemy.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		MaxKiEnemy.setForeground(Color.MAGENTA);
		
		JPanel EnemyInfo = new JPanel();
		EnemyInfo.setSize(500,30);
		EnemyInfo.setLocation(-20, 10);
		EnemyInfo.setOpaque(false);
		
		EnemyInfo.add(MaxStaminaEnemy);
		EnemyInfo.add(MaxKiEnemy);
		
		add(EnemyInfo);
		
//		setEnemyMaxKi(3);
//		setEnemyMaxStamina(10);
		
		JPanel PlayerInfo = new JPanel();
		PlayerInfo.setSize(500,30);
		PlayerInfo.setLocation(800, 10);
		PlayerInfo.setOpaque(false);
		
		MaxStaminaPlayer = new JLabel();
		MaxStaminaPlayer.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		MaxStaminaPlayer.setForeground(new Color(0, 0, 220));
		
		MaxKiPlayer = new JLabel();
		MaxKiPlayer.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
		MaxKiPlayer.setForeground(Color.MAGENTA);
		
		PlayerInfo.add(MaxStaminaPlayer);
		PlayerInfo.add(MaxKiPlayer);
	
		add(PlayerInfo);
		
		//setPlayerMaxStamina(6);
		//setPlayerMaxKi(3);
	}

	public void setPlayerMaxStamina (int x){
		MaxStaminaPlayer.setText("Max Stamina : " + x);
	}
	
	public void setPlayerMaxKi (int x){
		MaxKiPlayer.setText("Max Ki : " + x);
	}
	
	public void setEnemyMaxStamina (int x){
		MaxStaminaEnemy.setText("Max Stamina : " + x);
	}
	
	public void setEnemyMaxKi (int x){
		MaxKiEnemy.setText("Max Ki : " + x);
	}
	
	public void setPlayerHealthText (int curr, int max){
		PlayerHealth.setText(curr + "/" + max);
	}
	
	public void setEnemyHealthText (int curr, int max){
		EnemyHealth.setText(curr + "/" + max);
	}
	
	public void setRoundText(int x){
		round.setText("" + x);
	}
	
	public void setHealth (){
		we = wp = WIDTH;
		repaint();
	}
	
	public void AttacksOptions (){
		options.setVisible(true);
		attacks.setVisible(false);
	}
	
	public void OptionsAttacks (){
		options.setVisible(false);
		attacks.setVisible(true);
	}
	
	public boolean isAttacksVisible (){
		return attacks.isVisible();
	}
	
	public boolean isOptionsVisible (){
		return options.isVisible();
	}
	
	public void setAttackList (ArrayList<Attack> attacks){
		for(Attack attack : attacks){
			attacksList.addItem(attack.getName() + " " + attack.getClass().getSimpleName());
		}
	}
	
	public int getSelectedAttackIndex (){
		return attacksList.getSelectedIndex();
	}
	
	public void setListener (ActionListener l){
		attack.addActionListener(l);
		block.addActionListener(l);
		use.addActionListener(l);
		attack1.addActionListener(l);
	}
	
	public void setXEnemy(int ss) {
		xe = ss;
	}

	public void setYEnemy(int ee) {
		ye = ee;
	}
	
	public void setXPlayer(int ss) {
		xp = ss;
	}

	public void setYPlayer(int ee) {
		yp = ee;
	}

	public void setPlayerStamina (int stamina){
		playerStamina = stamina;
	}
	
	public void setEnemyStamina (int stamina){
		enemyStamina = stamina;
	}
	
	public void setPlayerKI (int ki){
		playerKI = ki;
	}
	
	public void setEnemyKI (int ki){
		enemyKI = ki;
	}
	
	public void setPlayerIcon (PlayableFighter pf){
		if(pf instanceof Earthling)
			player = new ImageIcon("Krillin.png");
		else if(pf instanceof Namekian)
			player = new ImageIcon("PiccoloBattle.png");
		else if(pf instanceof Saiyan)
			player = new ImageIcon("GokuBattle.png");
		else if(pf instanceof Frieza)
			player = new ImageIcon("FreziaBattle.gif");
		else if (pf instanceof Majin)
			player = new ImageIcon("Kid_Buu.png");
		else
			player = new ImageIcon("");
		playerIcon.setIcon(player);
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
	
	public void setMaxKi (int ki){
		maxKI = ki;
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
	
	public static void main(String[] args) {
		BattleView bv = new BattleView();
		JFrame test = new JFrame("Testing123");
		test.setSize(1366, 768);
		test.add(bv);
		// test.add(new HealthBar(100, 20, 100, 100));
		// test.add(new JButton());
		test.setLocationRelativeTo(null);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

}
