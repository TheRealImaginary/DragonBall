package dragonball.model.player;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.InvalidAssignAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;

public class Player implements Serializable {

	private static final int MAX_NUM_OF_SUPER_ATTACKS = 4;
	private static final int MAX_NUM_OF_ULTIMATE_ATTACKS = 2;
	
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	private PlayerListener listener;

	public Player() {
		fighters = new ArrayList<>();
		superAttacks = new ArrayList<>();
		ultimateAttacks = new ArrayList<>();
	}

	public Player(String name) {
		this.name = name;
		fighters = new ArrayList<>();
		superAttacks = new ArrayList<>();
		ultimateAttacks = new ArrayList<>();
	}

	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttack, int senzuBeans, int dragonBalls, PlayableFighter activeFighter,
			int exploredMaps) {
		super();
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttack;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttack) {
		this.ultimateAttacks = ultimateAttack;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public void setListener (PlayerListener listener){
		this.listener = listener;
	}

	public int getMaxFighterLevel (){
		int maxLevel = 0;
		for(PlayableFighter fighter : fighters){
			maxLevel = Math.max(maxLevel, fighter.getLevel());
		}
		return maxLevel;
	}
	
	public void callDragon (){
		if(listener != null){
			listener.onDragonCalled();
		}
	}
	
	public void chooseWish (DragonWish wish) {
		if(wish.getType() == DragonWishType.ABILITY_POINTS)
			activeFighter.setAbilityPoints(activeFighter.getAbilityPoints() + wish.getAbilityPoints());
		else if(wish.getType() == DragonWishType.SENZU_BEANS)
			setSenzuBeans(getSenzuBeans() + wish.getSenzuBeans());
		else if(wish.getType() == DragonWishType.SUPER_ATTACK)
			superAttacks.add(wish.getSuperAttack());
		else if (wish.getType() == DragonWishType.ULTIMATE_ATTACK)
			ultimateAttacks.add(wish.getUltimateAttack());
		if(listener != null)
			listener.onWishChosen(wish);
	}
	
	public void createFighter (char race, String name){
		PlayableFighter created = null;
		if (race == 'E')
			created = new Earthling(name);
		else if (race == 'S')
			created = new Saiyan(name);
		else if (race == 'N')
			created = new Namekian(name);
		else if (race == 'F')
			created = new Frieza(name);
		else if (race == 'M')
			created = new Majin(name);
		if (activeFighter == null)
			activeFighter = created;
		fighters.add(created);
	}
	
	public void upgradeFighter (PlayableFighter fighter, char fighterAttribute) throws NotEnoughAbilityPointsException {
		if(fighter.getAbilityPoints() < 1)
			throw new NotEnoughAbilityPointsException("You don't have enough ability points to upgrade your current fighter, You need 1");
		switch(fighterAttribute) {
			case'H':fighter.setMaxHealthPoints(fighter.getMaxHealthPoints() + 50); break;
			case'B':fighter.setBlastDamage(fighter.getBlastDamage() + 50); break;
			case'P':fighter.setPhysicalDamage(fighter.getPhysicalDamage() + 50); break;
			case'K':fighter.setMaxKi(fighter.getMaxKi() + 1); break;
			case'S':fighter.setMaxStamina(fighter.getMaxStamina() + 1); break;
		}
		fighter.setAbilityPoints(fighter.getAbilityPoints() - 1);
	}
	
	public void assignAttack (PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException {
		ArrayList<SuperAttack> superAttacks = fighter.getSuperAttacks();
		if (oldAttack != null){
			superAttacks.remove(oldAttack);
		}
		if(superAttacks.contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if(superAttacks.size() == MAX_NUM_OF_SUPER_ATTACKS)
			throw new MaximumAttacksLearnedException(fighter.getName() + " has learned 4 Super Attacks, He can't grow stronger :(!");
		superAttacks.add(newAttack);
	}
	
	public void assignAttack (PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException, NotASaiyanException {
		ArrayList<UltimateAttack> ultimateAttacks = fighter.getUltimateAttacks();
		if (oldAttack != null){
			ultimateAttacks.remove(oldAttack);
		}
		if(newAttack instanceof SuperSaiyan && !(fighter instanceof Saiyan))
			throw new NotASaiyanException("Current Fighter is not a Saiyan");
		if (ultimateAttacks.contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if(ultimateAttacks.size() == MAX_NUM_OF_ULTIMATE_ATTACKS)
			throw new MaximumAttacksLearnedException(fighter.getName() + " has learned 2 Ultimate Attacks, He can't grow stronger :(!");
		ultimateAttacks.add(newAttack);
	}
	
	
}
