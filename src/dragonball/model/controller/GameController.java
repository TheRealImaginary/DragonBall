package dragonball.model.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.world.World;
import dragonball.view.BattleView;
import dragonball.view.GameView;
import dragonball.view.NewGame;
import dragonball.view.WorldView;

public class GameController implements ActionListener, KeyListener, ItemListener ,GameListener ,Serializable {

	private static final String SaveFile = "SavedGame";
	
	private GameView gameView;
	private Game game;
	private ImageIcon fighterIcon, BossIcon = new ImageIcon("CellTest (copy).png");
	private WindowEventHandler windowHandler;	//Helper for controller, handles window stuff :P, mostly saving for now
	private MouseEventHandler mouseHandler;		//Helper for controller, handles mouse stuff :P, mostly changing cursor for now
	private Dragon dragon;		//Represents Dragon being called now
	private Battle battle;		//Represents the current battle
	private static int round;	//represents Battle round, to update GUI

	public GameController (){
		
		windowHandler = new WindowEventHandler();
		mouseHandler = new MouseEventHandler();
		
		gameView = new GameView();
		gameView.setItemListeners(this);
		gameView.setListeners(this);
		gameView.setMouseListeners(mouseHandler);
		gameView.setKeyListeners(this);
		gameView.addWindowListener(windowHandler);
		
		game = new Game();
		game.setListener(this);
		
		windowHandler.setGame(game);
		windowHandler.setGameView(gameView);
		mouseHandler.setGameView(gameView);
		//System.out.println(game.getWorld());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnSource = (JButton)e.getSource();
		DragonWish [] dragonWishes = null;
		if(dragon != null)
			dragonWishes = dragon.getWishes();
		if(btnSource.getName().equals("New Game")){
			gameView.getMainMenu().setVisible(false);
			gameView.add(gameView.getNewGame());
			gameView.getNewGame().setVisible(true);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Load Game")){
			if(!game.isSaved()){
				JOptionPane.showMessageDialog(gameView, "No previously saved file");
			}
			else {
				try {
					game.load();
					game.setListener(this);
					gameView.getNewGame().setVisible(false);
					gameView.getMainMenu().setVisible(false);
					setUpWorld();
				}
				catch (IOException | ClassNotFoundException ioex){
					JOptionPane.showMessageDialog(gameView, "An Error Occurred Reading The Saved File " + ioex.getMessage());
				}
			}
		}
		else if (btnSource.getName().equals("Back")){
			//NewGame
			if(gameView.getNewGame().isVisible()){
				gameView.getNewGame().setVisible(false);
				gameView.add(gameView.getMainMenu());
				gameView.getMainMenu().setVisible(true);
				gameView.revalidate();
			}
			//CreatingFighter
			else if(gameView.getCreateFighter().isVisible()){
				gameView.getCreateFighter().setVisible(false);
				gameView.getEscMenu().setVisible(true);
				gameView.revalidate();
			}
			//Upgrading Fighter
			else if (gameView.getUpgradeFighterView().isVisible()){
				gameView.getUpgradeFighterView().setVisible(false);
				gameView.getEscMenu().setVisible(true);
				gameView.revalidate();
			}
			//Assigning Attacks
			else if (gameView.getAssignAttacks().isVisible()){
				gameView.getAssignAttacks().setVisible(false);
				gameView.getEscMenu().setVisible(true);
				gameView.revalidate();
			}
			//SwitchFighter
			else {
				gameView.getSwitchFighterView().setVisible(false);
				gameView.getEscMenu().setVisible(true);
				gameView.revalidate();
			}
		}
		else if (btnSource.getName().equals("Done")){
			//Initialize Data
			NewGame ng = gameView.getNewGame();
			if(ng.AreFieldsEmpty()){
				JOptionPane.showMessageDialog(gameView, "You must fill both player and fighter names!");
				return;
			}
			game.getPlayer().setName(ng.getPlayername().getText());
			game.getPlayer().createFighter(((String)(ng.getFighters().getSelectedItem())).charAt(0), ng.getFighterName().getText());
			gameView.getNewGame().setVisible(false);
			//SetUpWorld
			setUpWorld();
		}
		else if (btnSource.getName().equals("Resume")){
			gameView.getEscMenu().setVisible(false);
			gameView.getWorldView().setVisible(true);
			Focus(gameView.getWorldView().getMap()[9][9]);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Create New Fighter")) {
			gameView.getCreateFighter().setVisible(true);
			gameView.getEscMenu().setVisible(false);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Create")){
			char f = ((String)(gameView.getCreateFighter().getFighters().getSelectedItem())).charAt(0);
			String name = gameView.getCreateFighter().getFighterName();
			game.getPlayer().createFighter(f, name);
			gameView.getCreateFighter().setVisible(false);
			gameView.getEscMenu().setVisible(true);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Switch")){
			int index = gameView.getSwitchFighterView().getFighters().getSelectedIndex();
			game.getPlayer().setActiveFighter(game.getPlayer().getFighters().get(index));
			gameView.getWorldView().getFighterName().setText(game.getPlayer().getActiveFighter().getName());
			gameView.getWorldView().getFighterLevel().setText("" + game.getPlayer().getActiveFighter().getLevel());
			setFighterIcon();
			gameView.getWorldView().getMap()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(fighterIcon);
			gameView.getSwitchFighterView().setVisible(false);
			gameView.getWorldView().setVisible(true);
			Focus(gameView.getWorldView().getMap()[9][9]);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Switch Fighter")){
			gameView.getSwitchFighterView().setFighters(game.getPlayer().getFighters());
			gameView.getSwitchFighterView().setVisible(true);
			gameView.getEscMenu().setVisible(false);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Save and Quit")){
			try {
				game.save(SaveFile);
				JOptionPane.showMessageDialog(gameView, "Game Saved Succesfully");
				System.exit(0);
			}
			catch(IOException ioe){
				JOptionPane.showMessageDialog(gameView, "An Error occurred while saving the file! " + ioe.getMessage());
				ioe.printStackTrace();
			}
		}
		else if (btnSource.getName().equals("Upgrade Fighter")){
			gameView.getUpgradeFighterView().setFighters(game.getPlayer().getFighters());
			gameView.getUpgradeFighterView().setVisible(true);
			gameView.getUpgradeFighterView().setPoints(game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex()).getAbilityPoints());
			gameView.getEscMenu().setVisible(false);
			
			PlayableFighter pf = game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex());
			gameView.getUpgradeFighterView().setHealthPoints(pf.getMaxHealthPoints());
			gameView.getUpgradeFighterView().setBlastDamage(pf.getBlastDamage());
			gameView.getUpgradeFighterView().setPhysicalDamage(pf.getPhysicalDamage());
			gameView.getUpgradeFighterView().setStamina(pf.getMaxStamina());
			gameView.getUpgradeFighterView().setKi(pf.getMaxKi());
			
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Upgrade")){
			try {
				game.getPlayer().upgradeFighter(game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex()), gameView.getUpgradeFighterView().getAttribute());
				gameView.getUpgradeFighterView().setPoints(game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex()).getAbilityPoints());
				
				PlayableFighter pf = game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex());
				gameView.getUpgradeFighterView().setHealthPoints(pf.getMaxHealthPoints());
				gameView.getUpgradeFighterView().setBlastDamage(pf.getBlastDamage());
				gameView.getUpgradeFighterView().setPhysicalDamage(pf.getPhysicalDamage());
				gameView.getUpgradeFighterView().setStamina(pf.getMaxStamina());
				gameView.getUpgradeFighterView().setKi(pf.getMaxKi());
				
				JOptionPane.showMessageDialog(gameView, "Your fighter grows stronger !");
			}
			catch (NotEnoughAbilityPointsException neape){
				JOptionPane.showMessageDialog(gameView, neape.getMessage());
			}
		}
		else if (btnSource.getName().equals("Assign Attacks")){
			gameView.getAssignAttacks().setFighters(game.getPlayer().getFighters());
			gameView.getAssignAttacks().setSuperAttacks(game.getPlayer().getSuperAttacks());
			gameView.getAssignAttacks().setUltimateAttacks(game.getPlayer().getUltimateAttacks());
			
			int fighterIndex = gameView.getAssignAttacks().getSelectedFighter();
			Fighter f = game.getPlayer().getFighters().get(fighterIndex);
			gameView.getAssignAttacks().setCurretSuperAndUltimates(f.getSuperAttacks(), f.getUltimateAttacks());
			
			gameView.getAssignAttacks().setSuperNumber(f.getSuperAttacks().size());
			gameView.getAssignAttacks().setUltimateNumber(f.getUltimateAttacks().size());
			
			gameView.getAssignAttacks().setVisible(true);
			gameView.getEscMenu().setVisible(false);
			gameView.revalidate();
		}
		else if (btnSource.getName().equals("Assign Super Attack")){
			try {
				PlayableFighter pf = game.getPlayer().getFighters().get(gameView.getAssignAttacks().getSelectedFighter());
				SuperAttack s = game.getPlayer().getSuperAttacks().get(gameView.getAssignAttacks().getSuperAttackIndex());
				int index = gameView.getAssignAttacks().getOldSuperIndex();
				SuperAttack oldAttack =  index == 0? null : pf.getSuperAttacks().get(index - 1);
				game.getPlayer().assignAttack(pf,s , oldAttack);
				gameView.getAssignAttacks().setCurretSuperAndUltimates(pf.getSuperAttacks(), pf.getUltimateAttacks());
			}
			catch (DuplicateAttackException | MaximumAttacksLearnedException ex){
				JOptionPane.showMessageDialog(gameView, ex.getMessage());
			}
		}
		else if (btnSource.getName().equals("Assign Ultimate Attack")){
			try {
				PlayableFighter pf = game.getPlayer().getFighters().get(gameView.getAssignAttacks().getSelectedFighter());
				UltimateAttack u = game.getPlayer().getUltimateAttacks().get(gameView.getAssignAttacks().getUltimateAttackIndex());
				int index = gameView.getAssignAttacks().getOldUltimateIndex();
				UltimateAttack oldAttack =  index == 0? null : pf.getUltimateAttacks().get(index - 1);
				game.getPlayer().assignAttack(pf,u , oldAttack);
				gameView.getAssignAttacks().setCurretSuperAndUltimates(pf.getSuperAttacks(), pf.getUltimateAttacks());
			}
			catch (DuplicateAttackException | MaximumAttacksLearnedException | NotASaiyanException ex){
				JOptionPane.showMessageDialog(gameView, ex.getMessage());
			}
		}
		else if (btnSource.getName().equals("Attack")){
			if(gameView.getBattleView().isAttacksVisible()) {
				int index = gameView.getBattleView().getSelectedAttackIndex();
				ArrayList<Attack> attacks = battle.getAssignedAttacks();
				try {
					System.err.println(attacks.get(index));
					battle.attack(attacks.get(index));
					//gameView.getBattleView().setTextText("You have attacked the enemy");
					//gameView.getBattleView().getTextPlay().setVisible(true);
					//gameView.getBattleView().startTextTimer();
				}
				catch (NotEnoughKiException neke){
					gameView.getBattleView().setTextText("Your availble ki is " + neke.getMessage() + " was required");
					gameView.getBattleView().setTextVisibility(true);
					gameView.getBattleView().startTextTimer();
				}
				catch (Exception ex){
					ex.printStackTrace();
				}
				gameView.getBattleView().AttacksOptions();
			}
			else {
				gameView.getBattleView().OptionsAttacks();
			}
		}
		else if (btnSource.getName().equals("Block")){
			try{
				battle.block();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		else if (btnSource.getName().equals("Use")){
			try {
				battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
				gameView.getBattleView().setTextText("You have used 1 senzu bean and restored your health");
				gameView.getBattleView().getTextPlay().setVisible(true);
				gameView.getBattleView().startTextTimer();
			}
			catch (NotEnoughSenzuBeansException nesbe){
				gameView.getBattleView().setTextText(nesbe.getMessage());
				gameView.getBattleView().getTextPlay().setVisible(true);
				gameView.getBattleView().startTextTimer();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		else if (btnSource.getName().equals("Options")){
			gameView.getWorldView().setVisible(false);
			Component [] components = gameView.getComponents();
			for(Component c : components){
				if(c == gameView.getEscMenu()){
					c.setVisible(true);
					Focus(gameView.getEscMenu().getResume());
					return;
				}
			}
			gameView.add(gameView.getEscMenu());
			gameView.getEscMenu().setVisible(true);
			Focus(gameView.getEscMenu().getResume());
		}
		//4 Dragon View Cases
		else if (btnSource.getText().equals("Senzu Beans") || btnSource.getText().equals("Ability Points") || btnSource.getText().equals("Super Attack") || btnSource.getText().equals("Ultimate Attack")) {
			if (btnSource.getText().equals("Senzu Beans")){
				game.getPlayer().chooseWish(dragonWishes[0]);
				gameView.getWorldView().getSenzuBeans().setText(": " + game.getPlayer().getSenzuBeans());
				
				gameView.getWorldView().setTextText(dragonWishes[0].getSenzuBeans() + " Senzu Beans have been added to your items ! :)");
				gameView.getWorldView().setTextVisibility(true);
				gameView.getWorldView().startTextTimer();
			}
			else if (btnSource.getText().equals("Ability Points")){
				game.getPlayer().chooseWish(dragonWishes[1]);
				
				gameView.getWorldView().setTextText(dragonWishes[1].getAbilityPoints() + " Ability Points have been added to your active fighter ! :)");
				gameView.getWorldView().setTextVisibility(true);
				gameView.getWorldView().startTextTimer();
				
			}
			else if (btnSource.getText().equals("Super Attack")){
				game.getPlayer().chooseWish(dragonWishes[2]);
				
				//gameView.getWorldView().setTextText("A randomly chosen super attack is added to your player's items! :)");
				gameView.getWorldView().setTextText(dragonWishes[2].getSuperAttack().getName() + " has been added to your list of Super Attacks");
				gameView.getWorldView().setTextVisibility(true);
				gameView.getWorldView().startTextTimer();
			}
			else if (btnSource.getText().equals("Ultimate Attack")){
				game.getPlayer().chooseWish(dragonWishes[3]);
				
				//gameView.getWorldView().setTextText("A randomly chosen ultimate attack is added to your player's items! :)");
				gameView.getWorldView().setTextText(dragonWishes[3].getUltimateAttack().getName() + " has been added to your list of Ulimate Attacks");
				gameView.getWorldView().setTextVisibility(true);
				gameView.getWorldView().startTextTimer();
			}
			gameView.getDragonView().setVisible(false);
			gameView.getWorldView().setVisible(true);
			Focus(gameView.getWorldView().getMap()[9][9]);
		}
	}
	
	//KeyListener's Methods
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {				//If it threw an exception then player won't move nothing else happens
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(gameView.getEscMenu().isVisible()){
				gameView.getEscMenu().setVisible(false);
				gameView.getWorldView().setVisible(true);
				Focus(gameView.getWorldView().getMap()[9][9]);
			}
			else {
				gameView.getWorldView().setVisible(false);
				Component [] components = gameView.getComponents();
				for(Component c : components){
					if(c == gameView.getEscMenu()){
						c.setVisible(true);
						Focus(gameView.getEscMenu().getResume());
						return;
					}
				}
				gameView.add(gameView.getEscMenu());
				gameView.getEscMenu().setVisible(true);
				Focus(gameView.getEscMenu().getResume());
			}
		}
		else if(gameView.getWorldView().isVisible()){				//condition prevents moving when world isn't on
			try{
				World currentWorld = game.getWorld();
				int prevRow = currentWorld.getPlayerRow();
				int prevCol = currentWorld.getPlayerColumn();
				
				if(e.getKeyCode() == 38)
					currentWorld.moveUp();
				else if(e.getKeyCode() == 40)
					currentWorld.moveDown();
				else if(e.getKeyCode() == 39)
					currentWorld.moveRight();
				else if(e.getKeyCode() == 37)
					currentWorld.moveLeft();
				gameView.getWorldView().getMap()[prevRow][prevCol].setIcon(null);
				gameView.getWorldView().getMap()[currentWorld.getPlayerRow()][currentWorld.getPlayerColumn()].setIcon(fighterIcon);
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	//ItemListener's Methods
	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox<String> comboSrc = (JComboBox<String>)e.getSource();
		//Upgrade Fighter
		if(comboSrc == gameView.getUpgradeFighterView().getFighters()){
			if(e.getStateChange() == ItemEvent.SELECTED){
				PlayableFighter f = (PlayableFighter)game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex());
				gameView.getUpgradeFighterView().setPoints(f.getAbilityPoints());
				
				PlayableFighter pf = game.getPlayer().getFighters().get(gameView.getUpgradeFighterView().getFighterIndex());
				gameView.getUpgradeFighterView().setHealthPoints(pf.getMaxHealthPoints());
				gameView.getUpgradeFighterView().setBlastDamage(pf.getBlastDamage());
				gameView.getUpgradeFighterView().setPhysicalDamage(pf.getPhysicalDamage());
				gameView.getUpgradeFighterView().setStamina(pf.getMaxStamina());
				gameView.getUpgradeFighterView().setKi(pf.getMaxKi());
				
			}
		}
		//Assign Attacks
		else {
			if(e.getStateChange() == ItemEvent.SELECTED){
				int fighterIndex = gameView.getAssignAttacks().getSelectedFighter();
				Fighter f = game.getPlayer().getFighters().get(fighterIndex);
				gameView.getAssignAttacks().setCurretSuperAndUltimates(f.getSuperAttacks(), f.getUltimateAttacks());
				
				gameView.getAssignAttacks().setSuperNumber(f.getSuperAttacks().size());
				gameView.getAssignAttacks().setUltimateNumber(f.getUltimateAttacks().size());
				
				System.err.println(game.getPlayer().getSuperAttacks());
				System.err.println(game.getPlayer().getUltimateAttacks());
			}
		}
	}
	
	//GameListener's Methods
	@Override
	public void onDragonCalled(Dragon dragon) {
		this.dragon = dragon;
		gameView.getDragonView().setTextText("The Dragon awaits, choose your wish!");
		gameView.getDragonView().setTextVisibility(true);
		Component [] components = gameView.getComponents();
		for(Component c : components){
			if(c == gameView.getDragonView()){
				gameView.getWorldView().setVisible(false);
				c.setVisible(true);
				gameView.getDragonView().startTimer();
				return;
			}
		}
		gameView.add(gameView.getDragonView());
		gameView.getWorldView().setVisible(false);
		gameView.getDragonView().startTimer();
		gameView.getWorldView().getDragonBalls().setText(": " + 0);		//handle it when a wish is chosen -> It doesn't matter, does it ? :P
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if(collectible == Collectible.SENZU_BEAN){
			gameView.getWorldView().getSenzuBeans().setText(": " + game.getPlayer().getSenzuBeans());
			gameView.getWorldView().setTextText("You have picked up a senzu bean :)");
		}
		else if(collectible == Collectible.DRAGON_BALL){
			gameView.getWorldView().getDragonBalls().setText(": " + game.getPlayer().getDragonBalls());
			gameView.getWorldView().setTextText("You have picked up a dragon ball :)");
		}
		gameView.getWorldView().setTextVisibility(true);
		gameView.getWorldView().startTextTimer();
	}

	@Override
	public void onBattleEvent(BattleEvent e) { 
		Battle b = (Battle)e.getSource();
		if(battle == null)
			battle = b;
		if(e.getType() == BattleEventType.STARTED){
			round = 1;
			gameView.setNewBattle();
			gameView.getBattleView().setListener(this);
			gameView.getBattleView().setPlayerIcon(game.getPlayer().getActiveFighter());
			setUpBattle(b);
			Component [] components = gameView.getComponents();
			for(Component c : components){
				if(c == gameView.getBattleView()){
					gameView.getWorldView().setVisible(false);
					c.setVisible(true);
					gameView.getBattleView().setTextText("You have met a foe, a battle has begun! You start first");
					gameView.getBattleView().getTextPlay().setVisible(true);
					gameView.getBattleView().startTextTimer();
					return;
				}
			}
			gameView.getWorldView().setVisible(false);
			gameView.add(gameView.getBattleView());
			gameView.getBattleView().setTextText("You have met a foe, a battle has begun! You start first");
			gameView.getBattleView().getTextPlay().setVisible(true);
			gameView.getBattleView().startTextTimer();
		}
		else if (e.getType() == BattleEventType.NEW_TURN){
			NonPlayableFighter foe = (NonPlayableFighter)b.getFoe();
			String who = b.getAttacker() == game.getPlayer().getActiveFighter() ? "your" : "the enemy's";
			JOptionPane.showMessageDialog(gameView, "It's " + who + " turn.");
			int maxStamina = game.getPlayer().getActiveFighter().getMaxStamina();
			int currStamina = ((Fighter)b.getMe()).getStamina();
			int xx = 500 * (currStamina * 100 / maxStamina) / 100;
			
			int maxStaminaE = foe.getMaxStamina();
			int currStaminaE = foe.getStamina();
			int xxE = 500 * (currStaminaE * 100 / maxStaminaE) / 100;
			
			gameView.getBattleView().setRoundText(++round);
			gameView.getBattleView().setPlayerKI(game.getPlayer().getActiveFighter().getKi());
			gameView.getBattleView().setEnemyKI(foe.getKi());
			gameView.getBattleView().setPlayerStamina(currStamina);
			gameView.getBattleView().setEnemyStamina(currStaminaE);
			//gameView.getBattleView().setStaminaRight(xx);								//for continuous stamina (replaced with discrete)
			//gameView.getBattleView().setStaminaLeft(xxE);
			gameView.getBattleView().repaint();
			
			//System.err.println("TURN " + b.getAttacker());
			if(b.getAttacker() instanceof NonPlayableFighter){
				try {
					battle.play();
				}
				catch (Exception ex){
					ex.printStackTrace();
				}
			}
		}
		else if (e.getType() == BattleEventType.ATTACK){			//Health bar's width is 500 (constant (for now))
			//Who is attacking
			System.err.println(b.getAttacker() + " " + ((Fighter)(b.getAttacker())).getHealthPoints() + " " + ((Fighter)(b.getDefender())).getHealthPoints());
			System.err.println("KI " + b.getAttacker() + " " + ((Fighter)(b.getAttacker())).getKi() + " " + ((Fighter)(b.getDefender())).getKi());
			String who = b.getAttacker() instanceof NonPlayableFighter ? "Enemy" : "You";
			if(e.getAttack() instanceof SuperSaiyan){
				gameView.getBattleView().setTextText(who.equals("Enemy")? who + " has":who + " have" + " transformed into Super Saiyan");
				gameView.getBattleView().getTextPlay().setVisible(true);
				gameView.getBattleView().startTextTimer();
			}
			if(b.getAttacker() instanceof NonPlayableFighter){
				int maxHP = game.getPlayer().getActiveFighter().getMaxHealthPoints();
				int currHP = ((Fighter)b.getDefender()).getHealthPoints();
				int prec = currHP * 100 / maxHP;			//Don't care about decimals, shouldn't matter, or would it ?
				int x = 500 * prec / 100;
				int maxStamina = game.getPlayer().getActiveFighter().getMaxStamina();
				int currStamina = ((Fighter)b.getDefender()).getStamina();
				int xx = 500 * (currStamina * 100 / maxStamina) / 100;
				
				gameView.getBattleView().setHealthRight(x);
				gameView.getBattleView().setPlayerStamina(currStamina);
				gameView.getBattleView().setPlayerHealthText(currHP, maxHP);
				//gameView.getBattleView().setStaminaRight(xx);
				
				gameView.getBattleView().setPlayerKI(((Fighter)b.getDefender()).getKi());
				gameView.getBattleView().setEnemyKI(((Fighter)b.getAttacker()).getKi());
				
				//gameView.getBattleView().setPlayerKI(((Fighter)b.getAttacker()).getKi());
				//gameView.getBattleView().setEnemyKI(((Fighter)b.getAttacker()).getKi());
				
				JOptionPane.showMessageDialog(gameView, "The Enemy has chosen to attack us !");
			}
			else {
				int maxHP = ((Fighter)b.getDefender()).getMaxHealthPoints();
				int currHP = ((Fighter)b.getDefender()).getHealthPoints();
				int prec = currHP * 100 / maxHP;			
				int x = 500 * prec / 100;
				int maxStamina = ((Fighter)b.getDefender()).getMaxStamina();
				int currStamina = ((Fighter)b.getDefender()).getStamina();
				int xx = 500 * (currStamina * 100 / maxStamina) / 100;
				
				gameView.getBattleView().setHealthLeft(x);
				gameView.getBattleView().setEnemyHealthText(currHP, maxHP);
				gameView.getBattleView().setEnemyStamina(currStamina);
				//gameView.getBattleView().setStaminaLeft(xx);
				
				gameView.getBattleView().setPlayerKI(((Fighter)b.getAttacker()).getKi());
				gameView.getBattleView().setEnemyKI(((Fighter)(b.getDefender())).getKi());
			
			}
			gameView.getBattleView().repaint();
			gameView.revalidate();
		}
		else if (e.getType() == BattleEventType.USE){
			gameView.getBattleView().setHealthRight(500);  //Full health
			gameView.getBattleView().setPlayerHealthText(game.getPlayer().getActiveFighter().getHealthPoints(), game.getPlayer().getActiveFighter().getMaxHealthPoints());
			gameView.getBattleView().setPlayerStamina(game.getPlayer().getActiveFighter().getMaxStamina());
			gameView.getBattleView().repaint();
			gameView.revalidate();
		}
		else if (e.getType() == BattleEventType.BLOCK){
			Fighter f = (Fighter) b.getAttacker();
			System.err.println("BLOCK " + f);
			if(f instanceof PlayableFighter){
				gameView.getBattleView().setTextText("You are now blocking,The damage you take in the next turn decreases depending on your stamina");
				gameView.getBattleView().getTextPlay().setVisible(true);
				gameView.getBattleView().startTextTimer();
			}
			else {
				JOptionPane.showMessageDialog(gameView, "The enemy has chosen to block!");
			}
		}
		else if (e.getType() == BattleEventType.ENDED){
			System.err.println("Winner " + e.getWinner());
			
			gameView.getWorldView().setVisible(true);
			gameView.remove(gameView.getBattleView());
			
			clear ();
			
			gameView.getWorldView().getMap()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(fighterIcon);
			gameView.getWorldView().getFighterLevel().setText(": " + game.getPlayer().getActiveFighter().getLevel());
			gameView.getWorldView().getPlayerName().setText(game.getPlayer().getName());
			gameView.getWorldView().getFighterName().setText(game.getPlayer().getActiveFighter().getName());
			gameView.getWorldView().getDragonBalls().setText(": " + game.getPlayer().getDragonBalls());
			gameView.getWorldView().getSenzuBeans().setText(": " + game.getPlayer().getSenzuBeans());
			gameView.revalidate();
			
			if(b.getDefender() instanceof NonPlayableFighter){
				gameView.getWorldView().setTextText("You have won the battle, we're back to world. You have gained XP and unlocked new Attacks");
			}
			else {
				gameView.getWorldView().setTextText("Darn it! You lost, if you saved before, you're were you stopped, otherwise you're starting over :(");
			}
			gameView.getWorldView().getTextPlay().setVisible(true);
			gameView.getWorldView().startTextTimer();
			
			Focus(gameView.getWorldView().getMap()[9][9]);
			battle = null;		//So that a new battle is registered when created
		}
	}
	
	//helper methods
	private void clear (){
		for(JButton [] b : gameView.getWorldView().getMap()){
			for(JButton bb : b){
				bb.setIcon(null);
			}
		}
		gameView.getWorldView().getMap()[0][0].setIcon(BossIcon);
	}
	
	private void Focus (JButton button){
		button.requestFocusInWindow();
	}
	
	private void setUpBattle (Battle b){
		gameView.getWorldView().getMap()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(null);
		NonPlayableFighter foe = (NonPlayableFighter)b.getFoe();
		BattleView bv = gameView.getBattleView();
		bv.setMaxKi(game.getPlayer().getActiveFighter().getMaxKi());
		bv.setPlayerKI(game.getPlayer().getActiveFighter().getKi());
		bv.setEnemyKI(foe.getKi());
		bv.setAttackList(b.getAssignedAttacks());  						//player's assigned attacks
		bv.setHealth();
		bv.setPlayerHealthText(game.getPlayer().getActiveFighter().getHealthPoints(), game.getPlayer().getActiveFighter().getMaxHealthPoints());
		bv.setEnemyHealthText(foe.getHealthPoints(), foe.getMaxHealthPoints());
		bv.setEnemyMaxKi(foe.getMaxKi());
		bv.setEnemyMaxStamina(foe.getMaxStamina());
		bv.setPlayerMaxKi(game.getPlayer().getActiveFighter().getMaxKi());
		bv.setPlayerMaxStamina(game.getPlayer().getActiveFighter().getMaxStamina());
		bv.setStaminaLeft(foe.getStamina());
		bv.setStaminaRight(game.getPlayer().getActiveFighter().getStamina());
		//bv.repaint();
		gameView.revalidate();
	}
	
	private void setFighterIcon (){
		PlayableFighter currentFighter = game.getPlayer().getActiveFighter();
		if(currentFighter instanceof Earthling)
			fighterIcon = new ImageIcon("Earthling.png");
		else if(currentFighter instanceof Namekian)
			fighterIcon = new ImageIcon("Namekian.png");
		else if(currentFighter instanceof Saiyan)
			fighterIcon = new ImageIcon("Saiyan.png");
		else if(currentFighter instanceof Frieza)
			fighterIcon = new ImageIcon("Frieza.png");
		else if (currentFighter instanceof Majin)
			fighterIcon = new ImageIcon("Majin.png");
		else
			fighterIcon = new ImageIcon("");
	}
	
	private void setUpWorld (){
		//Setup world
		WorldView wv = gameView.getWorldView();
		wv.getPlayerName().setText(game.getPlayer().getName());
		wv.getFighterName().setText(game.getPlayer().getActiveFighter().getName());
		wv.getFighterLevel().setText(": " + game.getPlayer().getActiveFighter().getLevel() + "");
		wv.getDragonBalls().setText(": " + game.getPlayer().getDragonBalls());
		wv.getSenzuBeans().setText(": " + game.getPlayer().getSenzuBeans());
		setFighterIcon();
		
		wv.getMap()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(fighterIcon);
		wv.getMap()[0][0].setIcon(BossIcon);
		//Add
		gameView.add(wv);
		gameView.getWorldView().setVisible(true);
		
		Focus(gameView.getWorldView().getMap()[9][9]);
		
		System.out.println(game.getWorld());
	}
	
	public static void main(String[] args) {
		new GameController();
	}

}
