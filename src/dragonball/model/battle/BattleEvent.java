package dragonball.model.battle;

import java.util.EventObject;

import dragonball.model.attack.Attack;
import dragonball.model.cell.Collectible;

public class BattleEvent extends EventObject {

	private BattleEventType type;
	private BattleOpponent currentOpponent;
	private BattleOpponent winner;
	private Attack attack;
	private Collectible collectible;
	
	public BattleEvent(Battle battle, BattleEventType type) {
		super(battle);
		this.type = type;
		this.currentOpponent = battle.getAttacker();
	}

	public BattleEvent(Battle battle, BattleEventType type, BattleOpponent winner){
		this(battle, type);
		if(type == BattleEventType.ENDED || type == BattleEventType.ATTACK){
			this.winner = winner;
			//this.currentOpponent = battle.getDefender();
		}
	}
	
	public BattleEvent(Battle battle, BattleEventType type, Attack attack){
		this(battle, type);
		if(type == BattleEventType.ATTACK)
			this.attack = attack;
	}
	
	public BattleEvent(Battle battle, BattleEventType type, Collectible collectible){
		this(battle, type);
		if(type == BattleEventType.USE)
			this.collectible = collectible;
	}
	
	public BattleEventType getType() {
		return type;
	}

	public BattleOpponent getCurrentOpponent() {
		return currentOpponent;
	}

	public BattleOpponent getWinner() {
		return winner;
	}

	public Attack getAttack() {
		return attack;
	}

	public Collectible getCollectible() {
		return collectible;
	}


}
