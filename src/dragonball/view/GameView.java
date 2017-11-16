package dragonball.view;

import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;


import dragonball.model.character.fighter.PlayableFighter;

public class GameView extends JFrame {

	private static final int X = 1366;
	private static final int Y = 768;
	
	private MainMenu menu;
	private NewGame newGame;
	private WorldView worldView;
	private EscMenu escMenu;
	private CreateFighter createFighter;
	private DragonView dragonView;
	private BattleView battleView;
	private SwitchFighterView switchFighterView;
	private UpgradeFighterView upgradeFighterView;
	private AssignAttacksView assignAttacks;
//	private AssignAttacks assignAttacks;
	
	public GameView (){
		super("DragonBall v1.0");
		setSize(X,Y);
		//setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setResizable(false);
		
		menu = new MainMenu();
		newGame = new NewGame();
		worldView = new WorldView();
		escMenu = new EscMenu();
		createFighter = new CreateFighter();
		dragonView = new DragonView();
		battleView = new BattleView();
		switchFighterView = new SwitchFighterView();
		upgradeFighterView = new UpgradeFighterView();
		assignAttacks = new AssignAttacksView();
		//assignAttacks = new AssignAttacks();
		
		add(assignAttacks);
		add(upgradeFighterView);
		add(createFighter);
		add(switchFighterView);
		add(menu);
		
		assignAttacks.setVisible(false);
		upgradeFighterView.setVisible(false);
		createFighter.setVisible(false);
		switchFighterView.setVisible(false);
		
		setVisible(true);
	}
	
	public void setItemListeners (ItemListener il){
		upgradeFighterView.setItemListener(il);
		assignAttacks.setItemListener(il);
	}
	
	public void setKeyListeners (KeyListener l){
		worldView.setKeyListeners(l);
		escMenu.getResume().addKeyListener(l);
	}
		
	public void setListeners (ActionListener l){	
		menu.getLoadGame().addActionListener(l);
		menu.getNewGame().addActionListener(l);
		
		newGame.getReady().addActionListener(l);
		newGame.getBack().addActionListener(l);
		
		worldView.setListeners (l);
		
		escMenu.setListeners(l);
		
		createFighter.setListeners(l);
		
		dragonView.setActionListeners(l);
		
		switchFighterView.setListeners(l);
		
		upgradeFighterView.setListener(l);
		
		battleView.setListener(l);
		
		assignAttacks.setListener(l);
	}
	
	public void setNewBattle (){
		battleView = new BattleView();
	}
	
	public void setMouseListeners (MouseListener ml){
		menu.setMouseListener(ml);
		worldView.setMouseListeners(ml);
		newGame.setMouseListeners(ml);
		escMenu.setMouseListeners(ml);
		createFighter.setMouseListener(ml);
		switchFighterView.setMouseListener(ml);
		dragonView.setMouseListeners(ml);
		upgradeFighterView.setMouseListener(ml);
		assignAttacks.setMouseListeners(ml);
	}
	
	public MainMenu getMainMenu (){
		return menu;
	}
	
	public NewGame getNewGame (){
		return newGame;
	}
	
	public WorldView getWorldView (){
		return worldView;
	}
	
	public EscMenu getEscMenu(){
		return escMenu;
	}
	
	public CreateFighter getCreateFighter (){
		return createFighter;
	}
	
	public DragonView getDragonView (){
		return dragonView;
	}
	
	public BattleView getBattleView (){
		return battleView;
	}
	
	public SwitchFighterView IntiallizeSwitchFighterView (ArrayList<PlayableFighter> al){
		return switchFighterView = new SwitchFighterView();
	}
	
	public SwitchFighterView getSwitchFighterView (){
		return switchFighterView;
	}
	
	public void SetSwitchFighterView (SwitchFighterView sfv){
		switchFighterView = sfv;
	}
	
	public UpgradeFighterView getUpgradeFighterView (){
		return upgradeFighterView;
	}
	
	public AssignAttacksView getAssignAttacks (){
		return assignAttacks;
	}
	
	public static void main(String[] args) {
		new GameView();
	}

}
